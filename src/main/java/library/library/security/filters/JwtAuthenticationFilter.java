//package cursoSpringBoot.libreria.security.filters;
//
//import com.fasterxml.jackson.core.exc.StreamReadException;
//import com.fasterxml.jackson.databind.DatabindException;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import cursoSpringBoot.libreria.persistence.entity.UserEntity;
//import cursoSpringBoot.libreria.security.jwt.JwtUtils;
//import cursoSpringBoot.libreria.services.UserDetailsServiceImpl;
//import jakarta.servlet.FilterChain;
//import jakarta.servlet.ServletException;
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpServletResponse;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.MediaType;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.AuthenticationException;
//import org.springframework.security.core.userdetails.User;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
//
//import java.io.IOException;
//import java.util.HashMap;
//import java.util.Map;
//
///** UsernamePasswordAuthenticationFilter es para autenticarse en la app,
// * proporciona un path, llamando a ese endpoint (login), solicita un
// * usuario y contraseña, y con esos datos genera un token de autenticación.
// * Solo responde peticiones de tipo POST.
// * - Resumiendo: nos autenticamos con una petición de tipo POST al endpoint (login)
// * y le enviamos usuario y contraseña.
// *
// * No es un componente así que las dependencias se inyectan en el constructor
// * */
//public class JwtAuthenticationFilter extends UsernamePasswordAuthenticationFilter {
//
//    private JwtUtils jwtUtils;
//
//    @Autowired
//    UserDetailsServiceImpl userDetailsService;
//
//// Inyección de dependencias
//    public JwtAuthenticationFilter(JwtUtils jwtUtils){
//        this.jwtUtils = jwtUtils;
//    }
//
//// Gestiona el intento de autenticación de un usuario en la app
//    @Override
//    public Authentication attemptAuthentication(HttpServletRequest request,
//                                                HttpServletResponse response) throws AuthenticationException{
//
//    //Objeto en el que almacenar el usuario que está intentando autenticarse
//        UserEntity userEntity = null;
//        String username = "";
//        String password = "";
//
//    //Se intenta recuperar el usuario y la contraseña que vienen en el request
//        try{
//        //el request viene en formato Json, se mapea con la librería Jackson de Spring
//            userEntity = new ObjectMapper().readValue(request.getInputStream(), UserEntity.class);
//            username = userEntity.getUsername();
//            password = userEntity.getPassword();
//
//        }catch (DatabindException e) {
//            throw new RuntimeException(e.getMessage());
//        } catch (StreamReadException e) {
//            throw new RuntimeException(e);
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
//
//    //Se utiliza para llevar los datos de autenticación, obtenidos de la solicitud,
//    //para después ser autentiados por un manager capaz de realizar el estilo de
//    //autenticación usuario-contraseña
//        UsernamePasswordAuthenticationToken authenticationToken =
//                new UsernamePasswordAuthenticationToken(username, password);
//
//    //Se encarga de administrar la autenticación
//        return getAuthenticationManager().authenticate(authenticationToken);
//
//    }
//
//// Gestiona qué hacer cuando el usuario se autentica correctamente
//// Aquí se genera el token
//    @Override
//    protected void successfulAuthentication(HttpServletRequest request,
//                                            HttpServletResponse response,
//                                            FilterChain chain,
//                                            Authentication authResult) throws IOException, ServletException{
//
//    //Para generar el token se necesitan los detalles del usuario (nombre, contraseña y roles)
//    //esos datos vienen en el authResult
//
//        //User de Spring Security
//        UserDetails userDetails  = userDetailsService.loadUserByUsername()
//
//        //Ahora hay que generar el token de acceso
//        String token = jwtUtils.generateAccessToken(userDetails.getUsername());
//
//    //HEADER de la respuesta
//        //hay que responder a la solicitud de login con el token de acceso
//        response.addHeader("Autorizado", token);
//
//    //CUERPO de la respuesta
//        //mapa para añadir datos en la respuesta
//        Map<String, Object> httpResponse = new HashMap<>();
//        httpResponse.put("token", token);
//        httpResponse.put("mensaje", "Autenticación correcta");
//        //usuario al que se le creó el token
//        httpResponse.put("username", user.getUsername());
//
//        //se añade el map a la respuesta
//        //para mapear la respuesta hay que convertirla en Json
//        response.getWriter().write(new ObjectMapper().writeValueAsString(httpResponse));
//        //añade el estatus a la respuesta. Envía un 200
//        response.setStatus(HttpStatus.OK.value());
//        //añade el contenido de la respuesta
//        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
//        //para garantizar que se escriba todo correctamente
//        response.getWriter().flush();
//
//        super.successfulAuthentication(request, response,chain, authResult);
//    }
//}
