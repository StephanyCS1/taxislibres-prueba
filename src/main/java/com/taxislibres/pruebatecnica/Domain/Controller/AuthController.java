package com.taxislibres.pruebatecnica.Domain.Controller;

import com.taxislibres.pruebatecnica.Domain.Models.Dto.User.AutenticationUserData;
import com.taxislibres.pruebatecnica.infrastructure.Repository.UserRepository;
import com.taxislibres.pruebatecnica.Domain.Models.User;
import com.taxislibres.pruebatecnica.Domain.Security.JWTTokenData;
import com.taxislibres.pruebatecnica.Domain.Security.TokenService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.DependsOn;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 * Controlador que maneja la autenticación de usuarios y la generación de tokens JWT.
 */
@RestController
@RequestMapping("/login")
public class AuthController {

    @Autowired
    private TokenService tokenService;

    @Autowired
    private UserRepository userRepository;

    /**
     * Maneja la autenticación de usuarios y la generación de tokens JWT.
     *
     * @param autenticationUserData Datos de autenticación del usuario.
     * @return ResponseEntity con el token JWT generado en caso de autenticación exitosa.
     */
    @PostMapping
    public ResponseEntity authentication(@RequestBody @Valid AutenticationUserData autenticationUserData) {
        UserDetails user = userRepository.findByEmail(autenticationUserData.email());

        Authentication authToken = new UsernamePasswordAuthenticationToken(user, autenticationUserData.name());

        String JWTtoken = tokenService.generarToken((User) authToken.getPrincipal());

        return ResponseEntity.ok(new JWTTokenData(JWTtoken));
    }
}
