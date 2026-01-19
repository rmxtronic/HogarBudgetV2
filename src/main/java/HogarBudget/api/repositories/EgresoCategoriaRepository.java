package HogarBudget.api.repositories;

import HogarBudget.api.Entities.EgresoCategoria;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface EgresoCategoriaRepository extends JpaRepository<EgresoCategoria, Long> {

    @Query("SELECT SUM(e.montoPresupuestado) FROM EgresoCategoria e")
    int sumaPresupuestoCategoria();
}
