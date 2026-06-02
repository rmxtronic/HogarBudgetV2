package HogarBudget.api.Services;

import HogarBudget.api.DTOs.DatosIngresoVariable;
import HogarBudget.api.Entities.IngresoVariable;
import HogarBudget.api.Entities.Usuario;
import HogarBudget.api.repositories.IngresoVariableRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class IngresoVariableService {

    private final IngresoVariableRepository ingresoVariableRepository;

    public IngresoVariableService (IngresoVariableRepository ingresoVariableRepository) {
        this.ingresoVariableRepository = ingresoVariableRepository;
    }

    public DatosIngresoVariable guardar (DatosIngresoVariable datosIngresoVariable, Usuario usuario) {
        IngresoVariable ingresoVariable = new IngresoVariable(datosIngresoVariable);
        ingresoVariable.setUsuario(usuario);
        return new DatosIngresoVariable(ingresoVariableRepository.save(ingresoVariable));
    }

    @Transactional
    public DatosIngresoVariable actualizar(DatosIngresoVariable datosIngresoVariable, Long id, Usuario usuario) {
        IngresoVariable ingresoAActualizar = ingresoVariableRepository.findByIdAndUsuario(id, usuario)
                .orElseThrow(() -> new EntityNotFoundException("No existe ingreso variable con id " + id));
        ingresoAActualizar.actualizar(datosIngresoVariable);
        return new DatosIngresoVariable(ingresoAActualizar);
    }

    public void eliminar(Long id, Usuario usuario) {
        if (!ingresoVariableRepository.existsByIdAndUsuario(id, usuario)) {
            throw new EntityNotFoundException("No existe ingreso variable con id " + id);
        }
        ingresoVariableRepository.deleteById(id);
    }

    public Page<DatosIngresoVariable> listar(Pageable pagina, Usuario usuario) {
        return ingresoVariableRepository.findByUsuario(usuario, pagina)
                .map(DatosIngresoVariable::new);
    }

    public int sumIngresoVariable(Usuario usuario) {
        return ingresoVariableRepository.total(usuario);
    }
}