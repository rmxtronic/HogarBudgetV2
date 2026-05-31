package HogarBudget.api.repositories;

import HogarBudget.api.Entities.EgresoCategoria;
import HogarBudget.api.Entities.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface EgresoCategoriaRepository extends JpaRepository<EgresoCategoria, Long> {

    List<EgresoCategoria> findByUsuario(Usuario usuario);

    Optional<EgresoCategoria> findByIdAndUsuario(Long id, Usuario usuario);

    boolean existsByIdAndUsuario(Long id, Usuario usuario);

    @Query("SELECT COALESCE(SUM(e.montoPresupuestado), 0) FROM EgresoCategoria e WHERE e.usuario = :usuario")
    int sumaPresupuestoCategoria(@Param("usuario") Usuario usuario);
}