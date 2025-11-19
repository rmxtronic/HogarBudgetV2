package HogarBudget.api.Services;

import HogarBudget.api.DTOs.DatosIngresoFijo;
import HogarBudget.api.Entities.IngresoFijo;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import HogarBudget.api.repositories.IngresoFijoRepository;

import java.time.LocalDate;
import java.util.List;

@Service
public class IngresoFijoService {

    private final IngresoFijoRepository ingresoFijoRepository;

    public IngresoFijoService(IngresoFijoRepository ingresoFijoRepository) {
        this.ingresoFijoRepository = ingresoFijoRepository;
    }

    public IngresoFijo guardar(DatosIngresoFijo datosIngresoFijo){

        LocalDate fecha = datosIngresoFijo.fecha() == null ? LocalDate.now() : datosIngresoFijo.fecha();

        IngresoFijo fijo = new IngresoFijo(
                datosIngresoFijo.nombre(),
                datosIngresoFijo.cantidad(),
                fecha
        );

        return ingresoFijoRepository.save(fijo);
    }

    public IngresoFijo modificar(Long id, DatosIngresoFijo datosIngresoFijo) {
        IngresoFijo ingresoFijo = ingresoFijoRepository.getReferenceById(id);
        ingresoFijo.modificar(datosIngresoFijo);
        return ingresoFijoRepository.save(ingresoFijo);
    }

    @Transactional
    public void eliminar(Long id) {
        if(!ingresoFijoRepository.existsById(id)){
            throw new EntityNotFoundException("No existe ingreso fijo con el ID " + id);
        }
        ingresoFijoRepository.deleteById(id);
    }

    public Page<IngresoFijo> listar(Pageable paginas) {
        return ingresoFijoRepository.findAll(paginas);
    }
}
