package com.taxislibres.pruebatecnica.Domain.Security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.taxislibres.pruebatecnica.Domain.Models.User;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.auth0.jwt.exceptions.JWTVerificationException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.DependsOn;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

/**
 * Servicio para la generación y verificación de tokens JWT.
 */
@Service
public class TokenService {

    @Value("${api.security.secret}")
    private String apiSecret;

    /**
     * Genera un token JWT para el usuario especificado.
     *
     * @param user Usuario para el cual se generará el token.
     * @return Token JWT generado.
     * @throws RuntimeException Si ocurre un error durante la generación del token.
     */
    public String generarToken(User user) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(apiSecret);
            return JWT.create()
                    .withIssuer("taxislibres")
                    .withSubject(user.getEmail())
                    .withClaim("id", user.getId())
                    .withExpiresAt(ExpirationDate())
                    .sign(algorithm);
        } catch (JWTCreationException exception){
            throw new RuntimeException();
        }
    }

    /**
     * Obtiene el sujeto (subject) de un token JWT, verificando su validez.
     *
     * @param token Token JWT a verificar.
     * @return El sujeto del token si es válido, o una cadena vacía si no es válido.
     * @throws RuntimeException Si el token es nulo o no es válido.
     */
    public String getSubject(String token) {
        if (token == null) {
            throw new RuntimeException();
        }
        DecodedJWT verifier = null;
        try {
            Algorithm algorithm = Algorithm.HMAC256(apiSecret);
            verifier = JWT.require(algorithm)
                    .withIssuer("taxislibres")
                    .build()
                    .verify(token);
            String subject = verifier.getSubject();
            if (subject == null) {
                throw new RuntimeException("Verifier invalido");
            }
            return subject;
        } catch (JWTVerificationException exception) {
            System.out.println(exception.toString());
        }
        return "";
    }

    /**
     * Calcula la fecha de vencimiento para un token JWT.
     *
     * @return La fecha de vencimiento calculada como una instancia de Instant.
     */
    private Instant ExpirationDate() {
        return LocalDateTime.now().plusHours(23).toInstant(ZoneOffset.of("-05:00"));
    }
}