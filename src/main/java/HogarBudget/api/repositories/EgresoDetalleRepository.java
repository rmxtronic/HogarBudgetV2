package HogarBudget.api.repositories;

import HogarBudget.api.DTOs.sumCategoria;
import HogarBudget.api.Entities.EgresoDetalle;
import HogarBudget.api.Entities.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface EgresoDetalleRepository extends JpaRepository<EgresoDetalle, Long> {

    Optional<EgresoDetalle> findByIdAndUsuario(Long id, Usuario usuario);

    boolean existsByIdAndUsuario(Long id, Usuario usuario);

    @Query("SELECT d FROM EgresoDetalle d JOIN FETCH d.categoria WHERE d.usuario = :usuario")
    List<EgresoDetalle> findAllWithCategoria(@Param("usuario") Usuario usuario);

    @Query("""
            SELECT new HogarBudget.api.DTOs.sumCategoria(e.categoria.nombreCategoria, SUM(e.monto))
            FROM EgresoDetalle e
            WHERE e.usuario = :usuario
            GROUP BY e.categoria.nombreCategoria
            """)
    List<sumCategoria> totalPorCategoria(@Param("usuario") Usuario usuario);
}