package com.taxislibres.pruebatecnica.Domain.Models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Clase que representa una factura en el sistema.
 */
@Entity
@Table(name="bills")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Bill {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Double totalAmount;

    private String description;
    /**
     * Usuario al que se asocia esta factura.
     */
    @ManyToOne
    @JoinColumn(name = "id_user")
    @JsonBackReference
    private User user;
}
