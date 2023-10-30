package com.taxislibres.pruebatecnica.Domain.Models.Dto.User;

import jakarta.persistence.Column;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class NewUser {
    @NotBlank
    private String name;
    @NotNull
    @Min(value = 15)
    private Integer age;
    @Email
    private String email;
}
