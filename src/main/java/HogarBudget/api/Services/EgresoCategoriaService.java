package HogarBudget.api.Services;

import HogarBudget.api.DTOs.DatosEgresoCategoria;
import HogarBudget.api.Entities.EgresoCategoria;
import HogarBudget.api.Entities.Usuario;
import HogarBudget.api.repositories.EgresoCategoriaRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EgresoCategoriaService {

    private final EgresoCategoriaRepository egresoCategoriaRepository;

    public EgresoCategoriaService (EgresoCategoriaRepository egresoCategoriaRepository){
        this.egresoCategoriaRepository = egresoCategoriaRepository;
    }

    public EgresoCategoria guardar(DatosEgresoCategoria datosEgresoCategoria, Usuario usuario) {
        EgresoCategoria objetoGuardado = new EgresoCategoria(datosEgresoCategoria);
        objetoGuardado.setUsuario(usuario);
        return egresoCategoriaRepository.save(objetoGuardado);
    }

    @Transactional
    public EgresoCategoria editar(DatosEgresoCategoria datosEgresoCategoria, Long id, Usuario usuario) {
        EgresoCategoria egresoAModificar = egresoCategoriaRepository.findByIdAndUsuario(id, usuario)
                .orElseThrow(() -> new EntityNotFoundException("Categoria no encontrada"));
        egresoAModificar.modificar(datosEgresoCategoria);
        return egresoAModificar;
    }

    public void eliminar(Long id, Usuario usuario) {
        if(!egresoCategoriaRepository.existsByIdAndUsuario(id, usuario)) {
            throw new EntityNotFoundException("No existe categoria con id " + id);
        }
        egresoCategoriaRepository.deleteById(id);
    }

    public List<EgresoCategoria> listar(Usuario usuario) {
        return egresoCategoriaRepository.findByUsuario(usuario);
    }

    public int totalPresupuestado(Usuario usuario) {
        return egresoCategoriaRepository.sumaPresupuestoCategoria(usuario);
    }
}