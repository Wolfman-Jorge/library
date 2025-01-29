package library.library.security.filters;

import library.library.security.jwt.JwtUtils;
import library.library.services.UserDetailsServiceImpl;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

/** OncePerRequestFilter este filtro intercepta todas las invocaciones
 * al servidor
 * */
@Component
public class JwtAuthorizationFilter extends OncePerRequestFilter {

    //para validar el token
    @Autowired
    private JwtUtils jwtUtils;

    //para consultar el usuario en la bbdd
    @Autowired
    private UserDetailsServiceImpl userDetailsService;

// Hay que asegurarse de qeu los parámetros no sean null con la anotación
//@NonNull de Spring Framework
    @Override
    protected void doFilterInternal(@NonNull HttpServletRequest request,
                                    @NonNull HttpServletResponse response,
                                    @NonNull FilterChain filterChain) throws ServletException, IOException {

    //extraer el token de la petición
        final String authorizationHeader = request.getHeader("Authorization");

    //validar que el token no sea null y que empiece por Bearer (más espacio)
        if(authorizationHeader != null && authorizationHeader.startsWith("Bearer ")){
            //extraer solo el token, sin la palabra Bearer
            String token = authorizationHeader.substring(7);
            String username = this.jwtUtils.getUsernameFromToken(token);

            if(username != null && SecurityContextHolder.getContext().getAuthentication() == null){

                UserDetails userDetails = this.userDetailsService.loadUserByUsername(username);

                //comprobar que el token sea válido
                if(jwtUtils.isTokenValid(token, userDetails)){
//                    //se obtiene el nombre del usuario que viene en el token
//                    username = jwtUtils.getUsernameFromToken(token);
//
//                    //a través del nombre se recuperan sus datos
//                    UserDetails userDetails = userDetailsService.loadUserByUsername(username);
//
//                    //Ahora con el usuario y sus permisos se autentica

                    UsernamePasswordAuthenticationToken authenticationToken =
                            //se envía usuario, contraseña y los permisos, la contraseña va en null porque
                            //Spring Security la obtiene del userDetails
                            new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());

                    authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                    //es el objeto que contiene la autenticación propia de la app
                    //aquí se guardó la autenticación cuando se superó el login, hay
                    //que setearle la nueva autenticación con los permisos del usuario
                    SecurityContextHolder.getContext().setAuthentication(authenticationToken);
                }
            }


        }
//si no se verifica el token, SpringSecurity continúa con el filtro de validación
//y en el core de SpringSecurity observa que no tiene un de validación y deniega el acceso
        filterChain.doFilter(request, response);

    }
}
