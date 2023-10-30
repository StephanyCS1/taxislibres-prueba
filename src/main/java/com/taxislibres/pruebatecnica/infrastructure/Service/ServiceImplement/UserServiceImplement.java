package com.taxislibres.pruebatecnica.infrastructure.Service.ServiceImplement;

import com.taxislibres.pruebatecnica.Domain.Models.Dto.User.NewUser;
import com.taxislibres.pruebatecnica.Domain.Models.Dto.User.UpdateUser;
import com.taxislibres.pruebatecnica.infrastructure.Repository.BillRepository;
import com.taxislibres.pruebatecnica.infrastructure.Service.UserService;
import com.taxislibres.pruebatecnica.infrastructure.Repository.UserRepository;
import com.taxislibres.pruebatecnica.Domain.Models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Implementación de la interfaz UserService que proporciona operaciones para gestionar usuarios.
 */
@Service
public class UserServiceImplement implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BillRepository billRepository;

    /**
     * Crea un nuevo usuario en el sistema.
     *
     * @param newUser Datos del nuevo usuario.
     * @return El usuario creado.
     */
    @Override
    public User createUser(NewUser newUser) {
        User user = new User();
        user.setName(newUser.getName());
        user.setAge(newUser.getAge());
        user.setEmail(newUser.getEmail());
        return userRepository.save(user);
    }

    /**
     * Actualiza los datos de un usuario existente.
     *
     * @param id       ID del usuario a actualizar.
     * @param updateUser Datos actualizados del usuario.
     * @return El usuario actualizado.
     * @throws IllegalStateException Si no se encuentra un usuario con el ID especificado.
     */
    @Override
    public UpdateUser updateUser(Long id, UpdateUser updateUser) {
        User userById = userRepository.findById(id).orElseThrow(() ->
                new IllegalStateException("No fue encontrado el usuario con el ID: " + id));
        if (userById != null) {
            userById.setEmail(updateUser.getEmail());
            userRepository.save(userById);
        }
        return updateUser;
    }

    /**
     * Elimina un usuario del sistema.
     *
     * @param userId ID del usuario a eliminar.
     * @throws IllegalStateException Si no se encuentra un usuario con el ID especificado.
     */
    @Override
    public void deleteUser(Long userId) {
        User userById = userRepository.findById(userId).orElseThrow(() ->
                new IllegalStateException("No fue encontrado el usuario con el ID: " + userId));
        if (userById != null) {
            userRepository.deleteById(userId);
        }
    }

    /**
     * Obtiene un usuario por su ID.
     *
     * @param userId ID del usuario a recuperar.
     * @return El usuario con el ID especificado.
     * @throws IllegalStateException Si no se encuentra un usuario con el ID especificado.
     */
    @Override
    public User getUserById(Long userId) {
        User userById = userRepository.findById(userId).orElseThrow(() ->
                new IllegalStateException("No fue encontrado el usuario con el ID: " + userId));
        return userById;
    }

    /**
     * Obtiene una lista de todos los usuarios en el sistema.
     *
     * @return Lista de usuarios en el sistema.
     */
    @Override
    public List<User> getUsersList() {
        List<User> usersList = userRepository.findAll();
        return usersList;
    }

    @Override
    public Map<Object, Object> getUserByEmail(String email) {

        User user = (User) userRepository.findByEmail(email);

        if (user != null && user.getEmail().equals(email)) {

            Map<Object, Object> userMap = new HashMap<>();
            userMap.put("id", user.getId());
            userMap.put("name", user.getName());
            userMap.put("age", user.getAge());
            userMap.put("email", user.getEmail());

            return userMap;
        } else {
            return null;
        }
    }


}
