package HogarBudget.api.repositories;

import HogarBudget.api.Entities.IngresoVariable;
import HogarBudget.api.Entities.Usuario;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface IngresoVariableRepository extends JpaRepository<IngresoVariable, Long> {

    Page<IngresoVariable> findByUsuario(Usuario usuario, Pageable pageable);

    Optional<IngresoVariable> findByIdAndUsuario(Long id, Usuario usuario);

    boolean existsByIdAndUsuario(Long id, Usuario usuario);

    @Query("SELECT COALESCE(SUM(e.cantidad), 0) FROM IngresoVariable e WHERE e.usuario = :usuario")
    int total(@Param("usuario") Usuario usuario);
}