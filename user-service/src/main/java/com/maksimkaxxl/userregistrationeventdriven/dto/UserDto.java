package com.maksimkaxxl.userregistrationeventdriven.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public record UserDto(

        @Schema(example = "Maksym")
        @NotBlank
        String firstName,

        @Schema(example = "Chalyi")
        @NotBlank
        String lastName,

        @Schema(example = "Vitalievich")
        String middleName,

        @Schema(example = "maksymTest@gmail.com")
        @NotBlank
        @Email
        String email,

        @Schema(example = "+380967853123")
        @NotBlank
        @Pattern(regexp = "^[+]?[0-9]{10,15}$")
        String phone
) {
}