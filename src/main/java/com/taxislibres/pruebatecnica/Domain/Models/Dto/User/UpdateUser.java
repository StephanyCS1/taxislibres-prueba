package com.taxislibres.pruebatecnica.Domain.Models.Dto.User;

import jakarta.validation.constraints.Email;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateUser {
    @Email
    private String email;
}
