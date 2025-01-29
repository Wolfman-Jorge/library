package library.library.security;



//import cursoSpringBoot.libreria.security.filters.JwtAuthenticationFilter;
import library.library.security.filters.JwtAuthorizationFilter;
import library.library.security.jwt.JwtUtils;
import library.library.services.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import static org.springframework.security.config.Customizer.withDefaults;

/** Se crea de forma programática y se especifican
 * todas las reglas de seguridad
 *
 * SecurityFilterChain cadena de filtros que va a evaluar
 * si las rutas son públicas o privadas y se especifica que
 * tipo de autenticación se va a utilizar y también
 * el password encoder
 * */
@Configuration
//Especifíca a Spring Security que se necesita un mecanismo de seguridad
// a nivel global
//@EnableWebSecurity
// Habilita la seguridad a nivel de metodo
@EnableMethodSecurity(prePostEnabled = true)

//@EnableGlobalAuthentication

public class SecurityConfig implements WebMvcConfigurer {

    //Realiza la comprobación de la validez del nombre de usuario/contraseña
    // y devuelve las autorizaciones permitidas de dicho usuario
//    @Autowired
//    AuthenticationConfiguration authenticationConfiguration;

    /** @EnableMethodSecurity las restricciones se configuran directamente
     * en le controlador
     * SecurityFilterChain es una interfaz que utiliza SpringSecurity para
     * configurar la seguridad.
     * HttpSecurity es un Bean de SpringSecurity que recorre
     * los filtros de la cadena
     * */


    @Autowired
    JwtUtils jwtUtils;

    @Autowired
    UserDetailsServiceImpl userDetailsService;

    @Autowired
    JwtAuthorizationFilter authorizationFilter;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http,
                                           AuthenticationManager authenticationManager) throws Exception {

//    //La inyección de dependencias se hace con el constructor porque no es un @Component
//        JwtAuthenticationFilter jwtAuthenticationFilter = new JwtAuthenticationFilter(jwtUtils);
//    //al filtro hay que setearle una authenticationManager
//        jwtAuthenticationFilter.setAuthenticationManager(authenticationManager);
//    //establece la url del login, por defecto es /login
//        jwtAuthenticationFilter.setFilterProcessesUrl("/login");

        return http
                .cors(withDefaults())
        // SI NO SE DESHABILITA NO TE DA PERMISOS PARA EL POST
                .csrf(csrf-> csrf.disable())
                .authorizeHttpRequests(auth -> {
                    auth.requestMatchers("/library/auth/login").permitAll();
                    auth.anyRequest().authenticated();
                })
                .addFilterBefore(authorizationFilter, UsernamePasswordAuthenticationFilter.class)
                //forma básica de imponer controles de acceso, no requiere cookies
                //.httpBasic(Customizer.withDefaults())
                //Configuración de la sesión, la ventaja de trabajar con sesiones es que se puede
                //guardar la info del usuario para no pedirle que se autentique a cada paso
                // .ALWAYS crea un sesión si no hay niguna
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
        //se sutituye el httpBasic por el filtro personalizado
        //        .addFilter(jwtAuthenticationFilter)
        //se va a ejecutar antes, lo primero es validar le token
                .build();
    }


//    @Bean
//    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
//
//        return http
//                .authorizeHttpRequests(auth-> {
//                    auth.requestMatchers(HttpMethod.POST, "/libreria/socios").permitAll();
//                    auth.requestMatchers(HttpMethod.GET, "/libreria/socios").permitAll();
//                    //auth.anyRequest().denyAll();
//                })
//                //forma básica de imponer controles de acceso, no requiere cookies
//                .httpBasic(Customizer.withDefaults())
//                //Configuración de la sesión, la ventaja de trabajar con sesiones es que se puede
//                //guardar la info del usuario para no pedirle que se autentique a cada paso
//                // .ALWAYS crea un sesión si no hay niguna
//                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.ALWAYS))
//                .build();
//    }


    /** Gestiona lo relacionado con la autenticación de usuarios,
     * hace uso de un provider
     * */

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception{

        return authenticationConfiguration.getAuthenticationManager();

    }





//    /** Proveedor de autenticación, se conecta a la bbdd para traer los usuarios
//     * */
//    @Bean
//    public AuthenticationProvider autheticationProvider(UserDetailsServiceImpl usuarioDetailService){
//
//        //necesita un PaswordEncoder (componente que encripta y valida las contraseñas) y
//        //necesita UserDetailsServiceImpl, es el componente que llama a la bbdd
//        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
//        provider.setPasswordEncoder(passwordEncoder());
//        provider.setUserDetailsService(usuarioDetailService);
//        return provider;
//    }

    //encripta y valida las contraseñas
    @Bean
    public PasswordEncoder passwordEncoder(){
        //solo para pruebas
        //return NoOpPasswordEncoder.getInstance();

    //algoritmo de encriptación
        return new BCryptPasswordEncoder();
    }



//            @Override
//            public void addCorsMappings(CorsRegistry registry){
//                registry.addMapping("/**")
//                        .allowedOrigins("http://localhost:4200")
//                        .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")
//                        .allowCredentials(true)
//                        .allowedHeaders("*");
//            }



//    @Bean
//    public SessionRegistry sessionRegistry(){
//        return new SessionRegistryImpl();
//    }
//
//    public AuthenticationSuccessHandler successHandler(){
//        return((request, response, authentication) -> {
//           response.sendRedirect("/libreria/socios");
//        });
//    }



/*
    // @Bean se utiliza en Spring para especificar cuales van a ser
    // los objetos de configuración
    @Bean
    //Creación de lista de usuarios
    public InMemoryUserDetailsManager users(){

        //devuelve los usuarios
        return new InMemoryUserDetailsManager(
                //Usuarios comunes
                User.withUsername("user")
                        //{noop} importante para que Spring sec deje acceder en modo plano (sin encoders)
                        .password("{noop}password")
                        .roles("USER")
                        .build(),
                //Usuarios admin
                User.withUsername("admin")
                        //{noop} importante para que Spring sec deje acceder en modo plano (sin encoders)
                        //.password("{noop}password")
                        .password("1234")
                        .roles("ADMIN")
                        .build()
        );
    }
*/


//    /** SecurityFilterChain es una cadena de filtros que
//     * define qué rutas deben protegerse y cuáles no.
//     *
//     *  @Bean se utiliza en Spring para especificar cuales van a
//     *  ser los objetos de configuración
//     * */
//    @Bean
//    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//
//        //devuelve un objeto http con las configuraciones dentro
//        return http
//                //Deshabilitar la protección de Cross-site request forgery para esta demo
//                //.csrf(csrf -> csrf.disable())
//                //Especifica a Spring Sec qué rutas requieren autenticación(Cuales son privadas y cuales públicas)
//                .authorizeHttpRequests(auth -> {
//                    auth.requestMatchers("/public/**").permitAll();
//                    //endpoint privado
//                    auth.requestMatchers("/v1/home").authenticated();
//                    //endpoint privado solo para usuario admin
//                    auth.requestMatchers("/v1/admin").hasRole("ADMIN").anyRequest().authenticated();
//                })
//                //Formulario de login con configuración por defecto
//                .formLogin(Customizer.withDefaults())
//                .build();
//
//
//    }




}
