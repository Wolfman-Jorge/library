package library.library.controllers;

import library.library.request.CreateTokenDTO;
import library.library.request.CreateUserDTO;
import library.library.security.jwt.JwtUtils;
import library.library.services.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


/**
 * Clase controladora para la gestión del Login
 * */
@Controller
@RequestMapping("library/auth")
@PreAuthorize("permitAll()")
public class LoginController {

    //Se encarga de contruir las ligazones entre los distintos elementos
    @Autowired
    private AuthenticationManager authenticationManager;

    //Se encarga de contruir las ligazones entre los distintos elementos
    @Autowired
    private UserDetailsServiceImpl userDetailsService;

    //Se encarga de contruir las ligazones entre los distintos elementos
    @Autowired
    private JwtUtils jwtUtils;


    CreateUserDTO userDTO;

    public LoginController(){
        this.userDTO = new CreateUserDTO();
    }

    /**
     * Método que gestiona la autenticación de los usuarios
     * Autorizado para todos los usuarios
     * @Param recibe un Json con usuario y contraseña
     * @Return token de autorización
     * */
    @PostMapping("/login")
    public ResponseEntity<?> auth(@RequestBody CreateUserDTO userDTO){

        try{
            //1 enviar usuario y contraseña al authenticationManager
            this.authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                    userDTO.getUsername(),
                    userDTO.getPassword()
            ));

            //2 validar el usuario en la BBDD
            UserDetails userDetails = this.userDetailsService.loadUserByUsername(userDTO.getUsername());

            //3 generar el token
            String jwt = this.jwtUtils.generateAccessToken(userDetails);
            CreateTokenDTO tokenDTO = new CreateTokenDTO();
            tokenDTO.setToken(jwt);

            this.userDTO.setUsername(userDTO.getUsername());
            this.userDTO.setEmail(userDTO.getEmail());

            return new ResponseEntity<CreateTokenDTO>(tokenDTO, HttpStatus.OK);

        }catch(Exception e){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Error de autenticación " + e.getMessage());
        }
    }

    /**
     * Método que devuelve el usuario autenticado
     * Autorizado para todos los usuarios
     * @Return el usuario autenticado
     * */
    @GetMapping("/user")
    @PreAuthorize("permitAll()")
    public ResponseEntity<CreateUserDTO> getUser(){
        return ResponseEntity.ok(this.userDTO);
    }


}
