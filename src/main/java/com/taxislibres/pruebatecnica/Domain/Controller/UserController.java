package com.taxislibres.pruebatecnica.Domain.Controller;

import com.taxislibres.pruebatecnica.Domain.Models.Dto.User.NewUser;
import com.taxislibres.pruebatecnica.Domain.Models.Dto.User.UpdateUser;
import com.taxislibres.pruebatecnica.Domain.Models.User;
import com.taxislibres.pruebatecnica.infrastructure.Repository.UserRepository;
import com.taxislibres.pruebatecnica.infrastructure.Service.ServiceImplement.UserServiceImplement;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

/**
 * Controlador que gestiona las operaciones relacionadas con usuarios.
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserServiceImplement userServiceImplement;

    @Autowired
    private UserRepository userRepository;

    /**
     * Crea un nuevo usuario.
     *
     * @param newUser Datos del nuevo usuario.
     * @return ResponseEntity con el usuario creado en caso de éxito, o un mensaje de error en caso contrario.
     */
    @PostMapping("/new")
    public ResponseEntity<?> newUser(@Valid @RequestBody NewUser newUser){
        try{
            User user = userServiceImplement.createUser(newUser);
            return ResponseEntity.created(URI.create("/users/" + user.getId())).body(user);
        }catch (Exception e) {
            return new ResponseEntity<>("Error al crear el usuario: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * Actualiza un usuario existente.
     *
     * @param id         ID del usuario que se va a actualizar.
     * @param updateUser Datos actualizados del usuario.
     * @return ResponseEntity con los detalles del usuario actualizado en caso de éxito, o un mensaje de error en caso contrario.
     */
    @PatchMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_USER')")
    @Transactional
    public ResponseEntity<?> updateUser(@PathVariable Long id, @Valid @RequestBody UpdateUser updateUser){
        try {
            User userById = userServiceImplement.getUserById(id);
            if (userById != null) {
                userById.setEmail(updateUser.getEmail());
                userServiceImplement.updateUser(id, updateUser);
                return ResponseEntity.ok(userById);
            }
            return ResponseEntity.badRequest().build();
        }catch (Exception e){
            return new ResponseEntity<>("Error al actualizar el usuario: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * Obtiene todos los usuarios.
     *
     * @return ResponseEntity con la lista de todos los usuarios en caso de éxito, o un mensaje de error en caso contrario.
     */
    @GetMapping
    @PreAuthorize("hasRole('ROLE_USER')")
    public ResponseEntity<?> getAllUser(){
        try{
            List<User> listUsers = userServiceImplement.getUsersList();
            return new ResponseEntity<>(listUsers, HttpStatus.OK);
        }catch (Exception e) {
            return new ResponseEntity<>("Error al cargar los usuarios: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * Obtiene un usuario por su ID.
     *
     * @param id ID del usuario que se va a obtener.
     * @return ResponseEntity con los detalles del usuario en caso de éxito, o un mensaje de error en caso contrario.
     */
    @GetMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_USER')")
    public ResponseEntity<?> getUserById(@PathVariable Long id){
        try{
            User userById = userServiceImplement.getUserById(id);
            if(userById != null){
                return new ResponseEntity<>(userById, HttpStatus.OK);
            }
            return ResponseEntity.badRequest().build();
        }catch (Exception e) {
            return new ResponseEntity<>("Error al encontrar el usuario: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * Elimina un usuario por su ID.
     *
     * @param id ID del usuario que se va a eliminar.
     * @return ResponseEntity con los detalles del usuario eliminado en caso de éxito, o un mensaje de error en caso contrario.
     */
    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_USER')")
    public ResponseEntity<?> deleteUserById(@PathVariable Long id){
        try{
            User userById = userServiceImplement.getUserById(id);
            if(userById != null){
                userServiceImplement.deleteUser(id);
                return new ResponseEntity<>(userById, HttpStatus.OK);
            }
            return ResponseEntity.badRequest().build();
        }catch (Exception e) {
            return new ResponseEntity<>("Error al eliminar el usuario: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
