package library.library.services;

import library.library.persistence.entity.UserEntity;
import library.library.persistence.repositorio.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.stream.Collectors;

//Implementa la interfaz de Spring Security
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        //Recuperar el usuario de la BBDD
        UserEntity userEntity = this.usuarioRepository.findUserEntityByUsername(username)
        //si no encuentra el usuario arroja esta excepción
                .orElseThrow(() -> new UsernameNotFoundException("El usuario " + username + " no existe."));

        /** Ahora hay que convertir nuestro Usuario a un User que "entienda" SpringSecurity.
         * mapeo de Usuario a User
         * GrantedAuthority es una interfaz de SpringSecurity que maneja los permisos
         *
         * en el userEntity viene un Set<RoleEntity>, hay que convertirlo a un objeto de
         * tipo Collection<GrantedAuthoriry>
         * */
        Collection<? extends GrantedAuthority> authorities = userEntity.getRoles().stream()
                //los roles de SSecurity empiezan por ROLE_
                        .map(role-> new SimpleGrantedAuthority("ROLE_".concat(role.getName().name())))
                //ahora hay que convertirlo en una lista
                        .collect(Collectors.toSet());

        //hay que decirle a SSecurity que este es el usuario que va a utilizar y
        //hay qeu devolverlos como un User de SSecurity
        return new User(userEntity.getUsername(),
            userEntity.getPassword(),
            true, //usuario activo
            true, //la cuenta no expira
            true, //las credenciales no expiran
            true, //la cuenta no se bloquea
            authorities //lista de roles
        );

    }


}
