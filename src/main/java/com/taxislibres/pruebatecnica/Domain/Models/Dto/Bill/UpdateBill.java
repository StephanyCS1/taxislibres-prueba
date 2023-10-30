package com.taxislibres.pruebatecnica.Domain.Models.Dto.Bill;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateBill {
    private Double totalAmount;
    private String description;
}
