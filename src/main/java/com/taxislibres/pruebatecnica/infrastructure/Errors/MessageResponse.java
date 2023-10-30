package com.taxislibres.pruebatecnica.infrastructure.Errors;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Builder
@Getter
@Setter
public class MessageResponse {
    private String message;
}
