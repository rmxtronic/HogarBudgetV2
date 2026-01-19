package HogarBudget.api.DTOs;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record DatosEgresoCategoria(

        @NotBlank(message = "Ponle nombre")
        String nombreCategoria,

        @NotNull
        int montoPresupuestado,

        @NotNull
        boolean fija) {
}
