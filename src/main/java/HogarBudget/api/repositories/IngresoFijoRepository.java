package HogarBudget.api.repositories;

import HogarBudget.api.Entities.IngresoFijo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface IngresoFijoRepository extends JpaRepository<IngresoFijo, Long> {

    @Query("SELECT SUM(e.montoPresupuestado) FROM IngresoFijo e")
    int totalFijo();
}
