package library.library.security.jwt;

import library.library.persistence.entity.UserEntity;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.HashMap;
import java.util.function.Function;

/** clase de utilería que genera el token de acceso
 * Obtiene la firma
 * Comprueba la validez del token
 * Obtiene el usuario a través del token
 * Obtiene los Claims
 *
 * */
@Component
public class JwtUtils {

// Es para firmar el método, el token necesita un sello de autorización
    @Value("${jwt.secret.key}")
    private String secretKey;

// Tiempo de validez del token
    @Value("${jwt.time.expiration}")
    private String timeExpiration;

// Obtener la firma del método
    public SecretKey getSignatureKey(){
    //Decodifica la secretKey y lo convierte en un array de bytes en base 64
        byte[] keyBytes = Decoders.BASE64.decode(secretKey);
        //Devuelve el array de bytes encriptado con el algoritmo hmac sha(solo compatible con HS256)
        return Keys.hmacShaKeyFor(keyBytes);
    }

// Generar el token de acceso
    public String generateAccessToken(UserDetails userDetails){

        var claims = new HashMap<String, Object>();

        // Codifica el token y lo genera
        return Jwts.builder()
                .claims(claims)
                //Añade el nombre del usuario
                .subject(userDetails.getUsername())
                //Añade fecha de creación
                .issuedAt(new Date(System.currentTimeMillis()))
                //Añade tiempo de expiración en este caso 24h
                .expiration(new Date(System.currentTimeMillis() + Long.parseLong(timeExpiration)))
                //el algoritmo de encriptación que utiliza
                .signWith(getSignatureKey(), Jwts.SIG.HS256)
                .compact();
    }

// Validar si el token el correcto
    public boolean isTokenValid(String token, UserDetails userDetails){

        return extractClaim(token, Claims::getSubject).equals(userDetails.getUsername())
                && !extractClaim(token, Claims::getExpiration).before(new Date());
    }

// Obtener todos los Claims del token
    private <T> T extractClaim(String token, Function<Claims, T> claimsResolver){
        //lee y devuelve el token
        Claims claims = Jwts.parser()
                .verifyWith(getSignatureKey())
                .build()
                .parseSignedClaims(token)
                .getPayload();

        return claimsResolver.apply(claims);
    }


// Obtener el username que tiene el Claim (el usuario del token)
    public String getUsernameFromToken(String token){
        return extractClaim(token, Claims::getSubject);
    }

// Obtener los datos del usuario que tiene el Claim (el usuario del token)
    public UserEntity getUser(String token){

        UserEntity usuario = new UserEntity();
        usuario.setUsername(getUsernameFromToken(token));
        return usuario;
    }


}
