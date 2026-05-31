package HogarBudget.api.DTOs;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record DatosLogin(
        @NotBlank @Email String email,
        @NotBlank String senha
) {
}