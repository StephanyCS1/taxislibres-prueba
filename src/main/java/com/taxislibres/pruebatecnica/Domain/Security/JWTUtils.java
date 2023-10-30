package com.taxislibres.pruebatecnica.Domain.Security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.auth0.jwt.JWTVerifier;

/**
 * Clase utilitaria para verificar y obtener el sujeto de un token JWT.
 */
public class JWTUtils {
    /**
     * Obtiene el sujeto (subject) de un token JWT.
     *
     * @param token Token JWT a verificar y del cual se extraerá el sujeto.
     * @return El sujeto del token si es válido, o una cadena vacía si no es válido.
     */
    public static String getSubject(String token) {
        JWTVerifier verifier = JWT.require(Algorithm.HMAC256("secret")).withIssuer("my-issuer").build();
        DecodedJWT decodedJWT = verifier.verify(token);
        String subject = decodedJWT.getSubject();
        return subject;
    }
}
