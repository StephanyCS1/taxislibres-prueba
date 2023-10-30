package com.taxislibres.pruebatecnica.infrastructure.Repository;

import com.taxislibres.pruebatecnica.Domain.Models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;

/**
 * Repositorio de datos para la entidad User.
 *
 * Esta interfaz extiende JpaRepository, lo que permite realizar operaciones de
 * acceso a datos relacionadas con la entidad User en la base de datos.
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    UserDetails findByEmail(String email);

}
