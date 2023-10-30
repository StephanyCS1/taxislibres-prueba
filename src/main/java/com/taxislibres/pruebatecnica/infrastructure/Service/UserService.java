package com.taxislibres.pruebatecnica.infrastructure.Service;

import com.taxislibres.pruebatecnica.Domain.Models.Dto.User.NewUser;
import com.taxislibres.pruebatecnica.Domain.Models.Dto.User.UpdateUser;
import com.taxislibres.pruebatecnica.Domain.Models.User;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * Interfaz que define las operaciones de servicio relacionadas con usuarios.
 */
@Service
public interface UserService {
    /**
     * Crea un nuevo usuario en el sistema.
     *
     * @param newUser Datos del nuevo usuario.
     * @return El usuario creado.
     */
    User createUser(NewUser newUser);

    /**
     * Actualiza los datos de un usuario existente.
     *
     * @param id       ID del usuario a actualizar.
     * @param updateUser Datos actualizados del usuario.
     * @return El usuario actualizado.
     * @throws RuntimeException Si el usuario no se encuentra.
     */
    UpdateUser updateUser(Long id, UpdateUser updateUser);

    /**
     * Elimina un usuario del sistema.
     *
     * @param userId ID del usuario a eliminar.
     * @throws RuntimeException Si el usuario no se encuentra.
     */
    void deleteUser(Long userId);

    /**
     * Obtiene un usuario por su ID.
     *
     * @param userId ID del usuario a recuperar.
     * @return El usuario con el ID especificado.
     * @throws RuntimeException Si el usuario no se encuentra.
     */
    User getUserById(Long userId);

    /**
     * Obtiene una lista de todos los usuarios en el sistema.
     *
     * @return Lista de usuarios en el sistema.
     */
    List<User> getUsersList();

    Map<Object, Object> getUserByEmail(String username);
}
