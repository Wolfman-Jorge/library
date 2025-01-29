package library.library.controllers;

import library.library.persistence.repositorio.UsuarioRepository;
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

@Controller
@RequestMapping("library/auth")
public class LoginController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserDetailsServiceImpl userDetailsService;

    @Autowired
    private JwtUtils jwtUtils;

    @Autowired
    private UsuarioRepository userRepository;

    CreateUserDTO userDTO;

    public LoginController(){
        this.userDTO = new CreateUserDTO();

    }


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
            System.out.println(userDetails.getUsername());

            //3 generar el token
            String jwt = this.jwtUtils.generateAccessToken(userDetails);
            CreateTokenDTO tokenDTO = new CreateTokenDTO();
            tokenDTO.setToken(jwt);

            this.userDTO.setUsername(userDTO.getUsername());
            this.userDTO.setEmail(userDTO.getEmail());

            System.out.println(jwt);
            return new ResponseEntity<CreateTokenDTO>(tokenDTO, HttpStatus.OK);

        }catch(Exception e){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Error de autenticación " + e.getMessage());
        }
    }

//    @GetMapping("/user")
//    @PreAuthorize("hasRole('ADMIN')")
//    public String getUser(){
//        System.out.println("solicitud ususario");
//        System.out.println(this.userDTO.toString());
//        //return this.userDTO;
//        return "Jorge";
//    }

    @GetMapping("/user")
    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    public ResponseEntity<CreateUserDTO> getUser(){
        System.out.println("solicitud ususario");
        System.out.println(this.userDTO.toString());
        //return this.userDTO;
        return ResponseEntity.ok(this.userDTO);
    }


}
