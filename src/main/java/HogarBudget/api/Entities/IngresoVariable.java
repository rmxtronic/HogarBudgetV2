package HogarBudget.api.Entities;

import HogarBudget.api.DTOs.DatosIngresoVariable;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "ingreso_variable")
public class IngresoVariable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String nombre;

    @Column
    private int cantidad;

    @Column
    private LocalDate fecha;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;

    public IngresoVariable (DatosIngresoVariable datosIngresoVariable) {
        this.nombre = datosIngresoVariable.nombre();
        this.cantidad = datosIngresoVariable.cantidad();
        this.fecha = datosIngresoVariable.fecha();
    }

    public void actualizar(DatosIngresoVariable datosIngresoVariable) {
        if (datosIngresoVariable.nombre() != null) this.nombre = datosIngresoVariable.nombre();
        this.cantidad = datosIngresoVariable.cantidad();
        if(datosIngresoVariable.fecha() != null) this.fecha = datosIngresoVariable.fecha();
    }
}
