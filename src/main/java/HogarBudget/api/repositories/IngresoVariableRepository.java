package HogarBudget.api.repositories;

import HogarBudget.api.Entities.IngresoVariable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface IngresoVariableRepository extends JpaRepository <IngresoVariable, Long> {

    @Query("SELECT SUM(e.cantidad) FROM IngresoVariable e")
    int total();
}
