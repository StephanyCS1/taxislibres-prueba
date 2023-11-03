package com.taxislibres.pruebatecnica.Domain.Controller;


import com.taxislibres.pruebatecnica.Domain.Models.Bill;
import com.taxislibres.pruebatecnica.Domain.Models.Dto.Bill.NewBill;
import com.taxislibres.pruebatecnica.Domain.Models.Dto.Bill.ShowDataBill;
import com.taxislibres.pruebatecnica.Domain.Models.Dto.Bill.UpdateBill;
import com.taxislibres.pruebatecnica.Domain.Models.User;
import com.taxislibres.pruebatecnica.infrastructure.Repository.UserRepository;
import com.taxislibres.pruebatecnica.infrastructure.Service.ServiceImplement.BillServiceImplement;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Controlador que gestiona las operaciones relacionadas con facturas.
 */

@RestController
@RequestMapping("/bill")
public class BillController {

    @Autowired
    private BillServiceImplement billServiceImplement;

    /**
     * Crea una nueva factura.
     *
     * @param newBill Datos de la nueva factura.
     * @return ResponseEntity con los detalles de la factura creada en caso de éxito, o un mensaje de error en caso contrario.
     */
    @PostMapping
    public ResponseEntity<?> createBill(@Valid @RequestBody NewBill newBill) {
            try {
                ShowDataBill bill = billServiceImplement.createBill(newBill);
                ShowDataBill dataBill = new ShowDataBill(
                        bill.getId(),
                        bill.getTotalAmount(),
                        bill.getDescription(),
                        bill.getUser()
                );
                return ResponseEntity.status(HttpStatus.CREATED).body(dataBill);
            } catch (Exception e) {
                return new ResponseEntity<>("Error al crear la factura: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
            }
    }

    /**
     * Actualiza una factura existente.
     *
     * @param id         ID de la factura que se va a actualizar.
     * @param updateBill Datos actualizados de la factura.
     * @return ResponseEntity con los detalles de la factura actualizada en caso de éxito, o un mensaje de error en caso contrario.
     */
    @PatchMapping("/{id}")
    @Transactional
    @PreAuthorize("hasRole('ROLE_USER')")
    public ResponseEntity<?> updateBill(@PathVariable Long id, @Valid @RequestBody UpdateBill updateBill) {
        try {
            Bill billById = billServiceImplement.getBillById(id);
            if (billById != null) {
                billServiceImplement.updateBill(id, updateBill);
                return new ResponseEntity<>(billById, HttpStatus.OK);
            }
            return ResponseEntity.badRequest().build();
        } catch (Exception e) {
            return new ResponseEntity<>("Error al actualizar la factura: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * Elimina una factura por su ID.
     *
     * @param id ID de la factura que se va a eliminar.
     * @return ResponseEntity con los detalles de la factura eliminada en caso de éxito, o un mensaje de error en caso contrario.
     */
    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_USER')")
    public ResponseEntity<?> deleteBillById(@PathVariable Long id) {
        try {
            Bill bill = billServiceImplement.getBillById(id);
            if (bill != null) {
                billServiceImplement.deleteBillById(id);
                return new ResponseEntity<>(bill, HttpStatus.OK);
            }
            return ResponseEntity.badRequest().build();
        } catch (Exception e) {
            return new ResponseEntity<>("Error al eliminar la factura: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * Obtiene todas las facturas.
     *
     * @return ResponseEntity con la lista de todas las facturas en caso de éxito, o un mensaje de error en caso contrario.
     */
    @GetMapping
    @PreAuthorize("hasRole('ROLE_USER')")
    public ResponseEntity<?> getAllBills() {
        try {
            List<ShowDataBill> allBills = billServiceImplement.getAllBills();
            if (allBills.size() > 0) {
                return new ResponseEntity<>(allBills, HttpStatus.OK);
            }
            return ResponseEntity.status(204).build();
        } catch (Exception e) {
            return new ResponseEntity<>("Error al cargar todas las facturas: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * Obtiene una factura por su ID.
     *
     * @param id ID de la factura que se va a obtener.
     * @return ResponseEntity con los detalles de la factura en caso de éxito, o un mensaje de error en caso contrario.
     */
    @GetMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_USER')")
    public ResponseEntity<?> getBillById(@PathVariable Long id) {
        try {
            Bill billById = billServiceImplement.getBillById(id);
            if (billById != null) {
                return new ResponseEntity<>(billById, HttpStatus.OK);
            }
            return ResponseEntity.badRequest().build();
        } catch (Exception e) {
            return new ResponseEntity<>("Error al cargar la factura: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * Obtiene las facturas de un usuario por su ID.
     *
     * @param id ID del usuario cuyas facturas se van a obtener.
     * @return ResponseEntity con la lista de facturas del usuario en caso de éxito, o un mensaje de error en caso contrario.
     */
    @GetMapping("/userBills/{id}")
    @PreAuthorize("hasRole('ROLE_USER')")
    public ResponseEntity<?> getBillsByUserId(@PathVariable Long id) {
        try {
            List<ShowDataBill> billsByUserId = billServiceImplement.getBillByUserId(id);
            if (billsByUserId != null) {
                return new ResponseEntity<>(billsByUserId, HttpStatus.OK);
            }
            return ResponseEntity.badRequest().build();
        } catch (Exception e) {
            return new ResponseEntity<>("Error al cargar las facturas del usuario: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
