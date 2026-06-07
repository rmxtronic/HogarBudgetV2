package HogarBudget.api.DTOs;

import HogarBudget.api.Entities.EgresoCategoria;
import HogarBudget.api.Entities.EgresoDetalle;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.springframework.lang.Nullable;

import java.time.LocalDate;

public record DatosEDSalida(

        Long id,

        @NotNull
        String nombreLugar,

        @NotBlank
        int monto,

        @Nullable
        LocalDate fecha,

        @NotNull
        String nombreCategoria) {

    public DatosEDSalida(EgresoDetalle egresoDetalle) {
        this(
                egresoDetalle.getId(),
                egresoDetalle.getNombreLugar(),
                egresoDetalle.getMonto(),
                egresoDetalle.getFecha(),
                egresoDetalle.getCategoria().getNombreCategoria());
    }
}
