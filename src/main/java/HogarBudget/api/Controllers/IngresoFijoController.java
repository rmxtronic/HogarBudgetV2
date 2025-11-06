/*Este controlador se va a encargar de pedir, listar, editar y eliminar los ingresos fijos que el usuario tenga
   Esperamos recibir desde el cliente, un JSON con el nombre del ingreso fijo, el montoPresupuestado y la fecha
   (La idea es incluir automaticamente la fecha en la que se hace el ingreso o dar la opcion de quieres poner otra fecha)
    */

package HogarBudget.api.Controllers;

import HogarBudget.api.DTOs.DatosIngresoFijo;
import HogarBudget.api.Services.IngresoFijoService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/ingresos/fijos")
public class IngresoFijoController {

    private final IngresoFijoService ingresoFijoService;

    public IngresoFijoController(IngresoFijoService ingresoFijoService){
        this.ingresoFijoService = ingresoFijoService;
    }

    @PostMapping
    public void saveIngreso(@RequestBody DatosIngresoFijo datosIngresoFijo){
        ingresoFijoService.guardar(datosIngresoFijo);
    }
}
