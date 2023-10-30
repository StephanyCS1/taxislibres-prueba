package com.taxislibres.pruebatecnica.Domain.Models.Dto.Bill;

import com.taxislibres.pruebatecnica.Domain.Models.User;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter

@Setter
public class NewBill {
    @NotNull
    private Double totalAmount;
    @NotBlank
    private String description;
    @NotNull
    @Valid
    private User user;
}
