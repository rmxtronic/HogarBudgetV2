package HogarBudget.api.repositories;

import HogarBudget.api.Entities.EgresoDetalle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Arrays;
import java.util.List;

public interface EgresoDetalleRepository extends JpaRepository<EgresoDetalle, Long> {

    @Query("SELECT d FROM EgresoDetalle d JOIN FETCH d.categoria")
    List<EgresoDetalle> findAllWithCategoria();
}
