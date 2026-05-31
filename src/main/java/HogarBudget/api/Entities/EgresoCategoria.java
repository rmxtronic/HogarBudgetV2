package HogarBudget.api.Entities;

import HogarBudget.api.DTOs.DatosEgresoCategoria;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "egreso_categoria")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class EgresoCategoria {

    @Column
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String nombreCategoria;

    @Column
    private int montoPresupuestado;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;

    public EgresoCategoria (DatosEgresoCategoria datosEgresoCategoria) {
        this.nombreCategoria = datosEgresoCategoria.nombreCategoria();
        this.montoPresupuestado = datosEgresoCategoria.montoPresupuestado();
    }

    public void modificar(DatosEgresoCategoria datosEgresoCategoria) {
        if(datosEgresoCategoria.nombreCategoria() != null) this.nombreCategoria = datosEgresoCategoria.nombreCategoria();
        if(datosEgresoCategoria.montoPresupuestado() > 0) this.montoPresupuestado = datosEgresoCategoria.montoPresupuestado();
    }
}


