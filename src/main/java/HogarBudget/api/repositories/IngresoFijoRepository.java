package HogarBudget.api.repositories;

import HogarBudget.api.Entities.IngresoFijo;
import HogarBudget.api.Entities.Usuario;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface IngresoFijoRepository extends JpaRepository<IngresoFijo, Long> {

    Page<IngresoFijo> findByUsuario(Usuario usuario, Pageable pageable);

    Optional<IngresoFijo> findByIdAndUsuario(Long id, Usuario usuario);

    boolean existsByIdAndUsuario(Long id, Usuario usuario);

    @Query("SELECT COALESCE(SUM(e.montoPresupuestado), 0) FROM IngresoFijo e WHERE e.usuario = :usuario")
    int totalFijo(@Param("usuario") Usuario usuario);
}