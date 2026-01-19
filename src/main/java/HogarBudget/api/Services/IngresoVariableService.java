package HogarBudget.api.Services;

import HogarBudget.api.DTOs.DatosIngresoVariable;
import HogarBudget.api.Entities.IngresoVariable;
import HogarBudget.api.repositories.IngresoVariableRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class IngresoVariableService {

    private final IngresoVariableRepository ingresoVariableRepository;

    public IngresoVariableService (IngresoVariableRepository ingresoVariableRepository) {
        this.ingresoVariableRepository = ingresoVariableRepository;
    }

    public IngresoVariable guardar (DatosIngresoVariable datosIngresoVariable) {
        LocalDate fecha = datosIngresoVariable.fecha() == null ? LocalDate.now() : datosIngresoVariable.fecha();
        IngresoVariable ingresoVariable = new IngresoVariable(datosIngresoVariable);
        return ingresoVariableRepository.save(ingresoVariable);

    }

    @Transactional
    public IngresoVariable actualizar(DatosIngresoVariable datosIngresoVariable, Long id) {
            IngresoVariable ingresoAActualizar = ingresoVariableRepository.getReferenceById(id);
            ingresoAActualizar.actualizar(datosIngresoVariable);
            return ingresoAActualizar;
    }

    public void eliminar(Long id) {
        if (!ingresoVariableRepository.existsById(id)) {
            throw new EntityNotFoundException("No existe ingreso variable con id " + id);
        }
        ingresoVariableRepository.deleteById(id);
    }

    public Page<IngresoVariable> listar(Pageable pagina) {
        return ingresoVariableRepository.findAll(pagina);
    }

    public int sumIngresoVariable() {
        return ingresoVariableRepository.total();
    }
}
