package HogarBudget.api.DTOs;

import HogarBudget.api.Entities.IngresoFijo;
import jakarta.annotation.Nullable;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public record DatosIngresoFijo(

        Long id,

        @NotBlank(message = "El nombre no puede estar vacio")
        String nombre,

        @Min(value = 1000, message = "La cantidad debe ser mayor a mil pesos. Puedes donar esa cantidad a pigyBank")
        int cantidad,

        @Nullable
        LocalDate fecha
) {

        public DatosIngresoFijo(IngresoFijo fijoModificado) {
                this(
                        fijoModificado.getId(),
                        fijoModificado.getNombreIngreFi(),
                        fijoModificado.getMontoPresupuestado(),
                        fijoModificado.getFecha());
        }
}
