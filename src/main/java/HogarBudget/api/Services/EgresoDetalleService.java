package HogarBudget.api.Services;

import HogarBudget.api.DTOs.DatosEDSalida;
import HogarBudget.api.DTOs.DatosEgresoDetalle;
import HogarBudget.api.DTOs.sumCategoria;
import HogarBudget.api.Entities.EgresoCategoria;
import HogarBudget.api.Entities.EgresoDetalle;
import HogarBudget.api.repositories.EgresoCategoriaRepository;
import HogarBudget.api.repositories.EgresoDetalleRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EgresoDetalleService {

    private final EgresoDetalleRepository egresoDetalleRepository;
    private final EgresoCategoriaRepository egresoCategoriaRepository;

    public EgresoDetalleService (
            EgresoDetalleRepository egresoDetalleRepository,
            EgresoCategoriaRepository egresoCategoriaRepository){
        this.egresoDetalleRepository = egresoDetalleRepository;
        this.egresoCategoriaRepository = egresoCategoriaRepository;
    }

    public EgresoDetalle guardar(DatosEgresoDetalle datosEgresoDetalle) {
        EgresoCategoria categoria = egresoCategoriaRepository.findById(datosEgresoDetalle.idCategoria())
                .orElseThrow(() -> new RuntimeException("Categoria no encontrada"));
        EgresoDetalle nuevoEgresoDetalle = new EgresoDetalle(datosEgresoDetalle, categoria);
        return egresoDetalleRepository.save(nuevoEgresoDetalle);
    }

    public void eliminar(Long id) {
        if(!egresoDetalleRepository.existsById(id)){
            throw new EntityNotFoundException("Este detalle no existe");
        }
        EgresoDetalle detalle = egresoDetalleRepository.getReferenceById(id);
        egresoDetalleRepository.delete(detalle);
    }

    @Transactional
    public EgresoDetalle editar(DatosEgresoDetalle datosEgresoDetalle, Long id) {
        EgresoDetalle aSerEditado = egresoDetalleRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Detalle no encontrado"));
        if (datosEgresoDetalle.idCategoria() > 0) {
            EgresoCategoria nuevaCategoria = egresoCategoriaRepository.findById(datosEgresoDetalle.idCategoria())
                    .orElseThrow(() -> new RuntimeException("Categoria no encontrada"));
            aSerEditado.editar(datosEgresoDetalle, nuevaCategoria);
        } else {
            aSerEditado.editar(datosEgresoDetalle);
        }
        return aSerEditado;
    }

    public List<DatosEDSalida> listar() {
        return egresoDetalleRepository.findAllWithCategoria().stream().map(DatosEDSalida::new).toList();
    }

    public List<sumCategoria> sumCat() {
        return egresoDetalleRepository.totalPorCategoria();
    }
}
