package HogarBudget.api.Entities;

import HogarBudget.api.DTOs.DatosEgresoDetalle;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "egreso_detalle")
public class EgresoDetalle {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String nombreLugar;

    @Column
    private int monto;

    @Column
    private LocalDate fecha;

    @ManyToOne
    @JoinColumn(name = "categoria_id")
    private EgresoCategoria categoria;

    public EgresoDetalle (DatosEgresoDetalle datosEgresoDetalle, EgresoCategoria categoria){
        this.nombreLugar = datosEgresoDetalle.nombreLugar();
        this.monto = datosEgresoDetalle.monto();
        this.fecha = datosEgresoDetalle.fecha();
        this.categoria = categoria;
    }

    public void editar(DatosEgresoDetalle datosEgresoDetalle) {
        if(datosEgresoDetalle.nombreLugar() != null) this.nombreLugar = datosEgresoDetalle.nombreLugar();
        if(datosEgresoDetalle.monto() > 0) this.monto = datosEgresoDetalle.monto();
        if(datosEgresoDetalle.fecha() != null) this.fecha = datosEgresoDetalle.fecha();
    }

    public void editar(DatosEgresoDetalle datosEgresoDetalle, EgresoCategoria nuevaCategoria) {
        if(datosEgresoDetalle.nombreLugar() != null) this.nombreLugar = datosEgresoDetalle.nombreLugar();
        if(datosEgresoDetalle.monto() > 0) this.monto = datosEgresoDetalle.monto();
        if(datosEgresoDetalle.fecha() != null) this.fecha = datosEgresoDetalle.fecha();
        this.categoria = nuevaCategoria;
    }
}
