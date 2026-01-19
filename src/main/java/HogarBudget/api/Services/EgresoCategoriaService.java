package HogarBudget.api.Services;

import HogarBudget.api.DTOs.DatosEgresoCategoria;
import HogarBudget.api.Entities.EgresoCategoria;
import HogarBudget.api.repositories.EgresoCategoriaRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class EgresoCategoriaService {

    private final EgresoCategoriaRepository egresoCategoriaRepository;

    public EgresoCategoriaService (EgresoCategoriaRepository egresoCategoriaRepository){
        this.egresoCategoriaRepository = egresoCategoriaRepository;
    }

    public EgresoCategoria guardar(DatosEgresoCategoria datosEgresoCategoria) {
        EgresoCategoria objetoGuardado = new EgresoCategoria(datosEgresoCategoria);
        //guardar(datosEgresoCategoria);
        return egresoCategoriaRepository.save(objetoGuardado);
    }

    @Transactional
    public EgresoCategoria editar(DatosEgresoCategoria datosEgresoCategoria, Long id) {
        EgresoCategoria egresoAModificar = egresoCategoriaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("No encontrada"));
        egresoAModificar.modificar(datosEgresoCategoria);
        return egresoAModificar;
    }

    public void eliminar(Long id) {
        if(!egresoCategoriaRepository.existsById(id)) {
            throw new EntityNotFoundException("No existe ingreso variable con id " + id);
        }
        EgresoCategoria AEliminar = egresoCategoriaRepository.getReferenceById(id);
        egresoCategoriaRepository.delete(AEliminar);

    }

    public List<EgresoCategoria> listar() {
        return egresoCategoriaRepository.findAll();
    }

    public int totalPresupuestado() {
        return egresoCategoriaRepository.sumaPresupuestoCategoria();
    }
}
