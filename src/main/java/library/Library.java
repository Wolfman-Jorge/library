package library;

import library.library.persistence.entity.RoleEntity;
import library.library.persistence.entity.RoleEnum;
import library.library.persistence.entity.UserEntity;
import library.library.persistence.repositorio.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Set;


@SpringBootApplication
public class Library {

	//excluir endpoints de security
	//bloque estático
	static {
		System.out.println("jejeje");
	}

	public static void main(String[] args) {
		SpringApplication.run(Library.class, args);
	}

//	@Autowired
//	PasswordEncoder passwordEncoder;
//
//	@Autowired
//	UsuarioRepository userRepository;
//
//	@Bean
//	CommandLineRunner init(){
//		return args -> {
//			UserEntity userEntity = UserEntity.builder()
//					.id(1)
//					.username("jorge")
//					.password(passwordEncoder.encode("1234"))
//					.email("jorgeadominguezgonzalez@gmail.com")
//					.roles(Set.of(RoleEntity.builder()
//							.name(RoleEnum.valueOf(RoleEnum.ADMIN.name()))
//							.build()))
//					.build();
//
//			UserEntity userEntity2 = UserEntity.builder()
//					.id(2)
//					.username("pedro")
//					.password(passwordEncoder.encode("1234"))
//					.email("pelud_22@hotmail.com")
//					.roles(Set.of(RoleEntity.builder()
//							.name(RoleEnum.valueOf(RoleEnum.ADMIN.name()))
//							.build()))
//					.build();
//
//			UserEntity userEntity3 = UserEntity.builder()
//					.id(3)
//					.username("laura")
//					.email("lauraboveda@gmail.com")
//					.password(passwordEncoder.encode("1234"))
//					.roles(Set.of(RoleEntity.builder()
//							.name(RoleEnum.valueOf(RoleEnum.USER.name()))
//							.build()))
//					.build();
//
//			userRepository.save(userEntity);
//			userRepository.save(userEntity2);
//			userRepository.save(userEntity3);
//		};
//	}



	/** Interfaz utilizada para indicar que un bean debe ejecutarse cuando
	 * está contenido dentro de una app Spring. Se ejecuta en cuanto
	 * arranca la app
	 * */
//	@Bean
//	CommandLineRunner init(UsuarioRepository usuarioRepository){
//
//		return args -> {
//			//CREAR PERMISOS
//			PrivilegesEntity createPrivilege = PrivilegesEntity.builder()
//					.nombre("CREATE")
//					.build();
//
//			PrivilegesEntity readPrivilege = PrivilegesEntity.builder()
//					.nombre("READ")
//					.build();
//
//			PrivilegesEntity updatePrivilege = PrivilegesEntity.builder()
//					.nombre("UPDATE")
//					.build();
//
//			PrivilegesEntity deletePrivilege = PrivilegesEntity.builder()
//					.nombre("DELETE")
//					.build();
//
//			//CREAR ROLES
//			RoleEntity adminRole = RoleEntity.builder()
//					.roleEnum(RoleEnum.ADMIN)
//					.privilegesList(Set.of(createPrivilege, readPrivilege, updatePrivilege, deletePrivilege))
//					.build();
//
//			RoleEntity userRole = RoleEntity.builder()
//					.roleEnum(RoleEnum.USER)
//					.privilegesList(Set.of(createPrivilege, readPrivilege))
//					.build();
//
//			//CREAR USUARIOS
//			Usuario usuJorge = Usuario.builder()
//					.username("Jorge")
//					.password("1234")
//					.isEnabled(true)
//					.accountNoExpired(true)
//					.accountNoLocked(true)
//					.credentialNoExpired(true)
//					.roles(Set.of(adminRole))
//					.build();
//
//			Usuario usuGonzalo = Usuario.builder()
//					.username("Gonzalo")
//					.password("1234")
//					.isEnabled(true)
//					.accountNoExpired(true)
//					.accountNoLocked(true)
//					.credentialNoExpired(true)
//					.roles(Set.of(userRole))
//					.build();
//
//			usuarioRepository.saveAll(List.of(usuJorge, usuGonzalo));
//
//		};
//	}



}


//tabla usuario con campo rol
//Patrón builder
//sing up
//JWT
//Añadir jwt a la libreria

//estudio propiedades de las tablas en html
//implementación de estilo css
//modificar formato html
//agregar funcionalidad en el .ts

//estudio de formularios reactivos
//metodo dirty formularios reactivos
//@Input() y @Output()
