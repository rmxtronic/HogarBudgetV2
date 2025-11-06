package HogarBudget.api.Entities;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@Table(name = "ingreso_fijo")
public class IngresoFijo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "nombre")
    private String nombreIngreFi;
    @Column(name = "monto")
    private int montoPresupuestado;
    @Column(name = "fecha")
    private LocalDate fecha;

    public IngresoFijo(String nombreIngreFi, int montoPresupuestado, LocalDate fecha) {
        this.montoPresupuestado = montoPresupuestado;
        this.nombreIngreFi = nombreIngreFi;
        this.fecha = fecha;
    }
}
