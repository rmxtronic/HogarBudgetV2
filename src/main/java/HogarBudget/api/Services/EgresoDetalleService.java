package HogarBudget.api.Services;

import HogarBudget.api.DTOs.DatosEDSalida;
import HogarBudget.api.DTOs.DatosEgresoDetalle;
import HogarBudget.api.DTOs.sumCategoria;
import HogarBudget.api.Entities.EgresoCategoria;
import HogarBudget.api.Entities.EgresoDetalle;
import HogarBudget.api.Entities.Usuario;
import HogarBudget.api.repositories.EgresoCategoriaRepository;
import HogarBudget.api.repositories.EgresoDetalleRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
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

    public EgresoDetalle guardar(DatosEgresoDetalle datosEgresoDetalle, Usuario usuario) {
        EgresoCategoria categoria = egresoCategoriaRepository.findByIdAndUsuario(datosEgresoDetalle.idCategoria(), usuario)
                .orElseThrow(() -> new EntityNotFoundException("Categoria no encontrada"));
        EgresoDetalle nuevoEgresoDetalle = new EgresoDetalle(datosEgresoDetalle, categoria);
        nuevoEgresoDetalle.setUsuario(usuario);
        return egresoDetalleRepository.save(nuevoEgresoDetalle);
    }

    public void eliminar(Long id, Usuario usuario) {
        if(!egresoDetalleRepository.existsByIdAndUsuario(id, usuario)){
            throw new EntityNotFoundException("Este detalle no existe");
        }
        egresoDetalleRepository.deleteById(id);
    }

    @Transactional
    public EgresoDetalle editar(DatosEgresoDetalle datosEgresoDetalle, Long id, Usuario usuario) {
        EgresoDetalle aSerEditado = egresoDetalleRepository.findByIdAndUsuario(id, usuario)
                .orElseThrow(() -> new EntityNotFoundException("Detalle no encontrado"));
        if (datosEgresoDetalle.idCategoria() > 0) {
            EgresoCategoria nuevaCategoria = egresoCategoriaRepository.findByIdAndUsuario(datosEgresoDetalle.idCategoria(), usuario)
                    .orElseThrow(() -> new EntityNotFoundException("Categoria no encontrada"));
            aSerEditado.editar(datosEgresoDetalle, nuevaCategoria);
        } else {
            aSerEditado.editar(datosEgresoDetalle);
        }
        return aSerEditado;
    }

    public List<DatosEDSalida> listar(Usuario usuario) {
        return egresoDetalleRepository.findAllWithCategoria(usuario).stream().map(DatosEDSalida::new).toList();
    }

    public List<sumCategoria> sumCat(Usuario usuario) {
        return egresoDetalleRepository.totalPorCategoria(usuario);
    }
}