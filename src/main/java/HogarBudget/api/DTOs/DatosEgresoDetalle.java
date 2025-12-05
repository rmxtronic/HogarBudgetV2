package HogarBudget.api.DTOs;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.springframework.lang.Nullable;

import java.time.LocalDate;

public record DatosEgresoDetalle(

    @NotNull
    String nombreLugar,

    @NotBlank
    int monto,

    @Nullable
    LocalDate fecha,

    @NotBlank
    Long idCategoria){
}
