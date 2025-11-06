package HogarBudget.api.DTOs;

import java.time.LocalDate;

public record DatosIngresoFijo(
        String nombre,
        int cantidad,
        LocalDate fecha
) {

}
