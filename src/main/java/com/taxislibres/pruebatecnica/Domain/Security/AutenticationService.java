package com.taxislibres.pruebatecnica.Domain.Security;

import com.taxislibres.pruebatecnica.infrastructure.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * Servicio de autenticación que implementa la interfaz UserDetailsService para cargar los detalles del usuario durante la autenticación.
 */
@Service
public class AutenticationService implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;

    /**
     * Carga los detalles del usuario a partir de su nombre de usuario (en este caso, el correo electrónico).
     *
     * @param username Nombre de usuario (correo electrónico) del usuario.
     * @return Detalles del usuario si se encuentra, o lanza una excepción UsernameNotFoundException si no se encuentra.
     * @throws UsernameNotFoundException Si el usuario con el nombre de usuario especificado no se encuentra.
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByEmail(username);
    }

}
