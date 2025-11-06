package HogarBudget.api.Services;

import HogarBudget.api.DTOs.DatosIngresoFijo;
import HogarBudget.api.Entities.IngresoFijo;
import org.springframework.stereotype.Service;
import HogarBudget.api.repositories.IngresoFijoRepository;

@Service
public class IngresoFijoService {

    private final IngresoFijoRepository ingresoFijoRepository;

    public IngresoFijoService(IngresoFijoRepository ingresoFijoRepository) {
        this.ingresoFijoRepository = ingresoFijoRepository;
    }

    public void guardar(DatosIngresoFijo datosIngresoFijo){
        IngresoFijo fijo = new IngresoFijo(
                datosIngresoFijo.nombre(),
                datosIngresoFijo.cantidad(),
                datosIngresoFijo.fecha()
        );
        ingresoFijoRepository.save(fijo);
    }
}
