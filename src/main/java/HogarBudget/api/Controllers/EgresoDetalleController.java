package HogarBudget.api.Controllers;

import HogarBudget.api.DTOs.DatosEDSalida;
import HogarBudget.api.DTOs.DatosEgresoDetalle;
import HogarBudget.api.DTOs.sumCategoria;
import HogarBudget.api.Entities.EgresoDetalle;
import HogarBudget.api.Services.EgresoDetalleService;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("egreso/detalle")
public class EgresoDetalleController {

    private final EgresoDetalleService egresoDetalleService;

    public EgresoDetalleController (EgresoDetalleService egresoDetalleService){
        this.egresoDetalleService = egresoDetalleService;
    }

    @PostMapping
    public ResponseEntity<EgresoDetalle> agregarEgresoDetalle (@RequestBody DatosEgresoDetalle datosEgresoDetalle){
        return ResponseEntity.status(201).body(egresoDetalleService.guardar(datosEgresoDetalle));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarEgresoDetalle(@PathVariable Long id){
        egresoDetalleService.eliminar(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<EgresoDetalle> editarEgresoDetalle (
            @RequestBody DatosEgresoDetalle datosEgresoDetalle,
            @PathVariable Long id){
        return ResponseEntity.ok(egresoDetalleService.editar(datosEgresoDetalle, id));
    }

    @GetMapping
    public ResponseEntity<List<DatosEDSalida>> listarEgresoDetalle (){
        return ResponseEntity.ok(egresoDetalleService.listar());
    }

    @GetMapping("/actual")
    public ResponseEntity<List<sumCategoria>> sumCategoria(){
        return ResponseEntity.ok(egresoDetalleService.sumCat());
    }
}
