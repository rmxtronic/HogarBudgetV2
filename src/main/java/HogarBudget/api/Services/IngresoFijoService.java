package HogarBudget.api.Services;

import HogarBudget.api.DTOs.DatosIngresoFijo;
import HogarBudget.api.Entities.IngresoFijo;
import HogarBudget.api.Entities.Usuario;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import HogarBudget.api.repositories.IngresoFijoRepository;

import java.time.LocalDate;

@Service
public class IngresoFijoService {

    private final IngresoFijoRepository ingresoFijoRepository;

    public IngresoFijoService(IngresoFijoRepository ingresoFijoRepository) {
        this.ingresoFijoRepository = ingresoFijoRepository;
    }

    public IngresoFijo guardar(DatosIngresoFijo datosIngresoFijo, Usuario usuario){
        LocalDate fecha = datosIngresoFijo.fecha() == null ? LocalDate.now() : datosIngresoFijo.fecha();

        IngresoFijo fijo = new IngresoFijo(
                datosIngresoFijo.nombre(),
                datosIngresoFijo.cantidad(),
                fecha
        );
        fijo.setUsuario(usuario);

        return ingresoFijoRepository.save(fijo);
    }

    @Transactional
    public IngresoFijo modificar(Long id, DatosIngresoFijo datosIngresoFijo, Usuario usuario) {
        IngresoFijo ingresoFijo = ingresoFijoRepository.findByIdAndUsuario(id, usuario)
                .orElseThrow(() -> new EntityNotFoundException("No existe ingreso fijo con el ID " + id));
        ingresoFijo.modificar(datosIngresoFijo);
        return ingresoFijoRepository.save(ingresoFijo);
    }

    @Transactional
    public void eliminar(Long id, Usuario usuario) {
        if(!ingresoFijoRepository.existsByIdAndUsuario(id, usuario)){
            throw new EntityNotFoundException("No existe ingreso fijo con el ID " + id);
        }
        ingresoFijoRepository.deleteById(id);
    }

    public Page<IngresoFijo> listar(Pageable paginas, Usuario usuario) {
        return ingresoFijoRepository.findByUsuario(usuario, paginas);
    }

    public int sumIngresoFijo(Usuario usuario) {
        return ingresoFijoRepository.totalFijo(usuario);
    }
}