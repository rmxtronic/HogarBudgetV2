package HogarBudget.api.Controllers;

import HogarBudget.api.DTOs.DatosEDSalida;
import HogarBudget.api.DTOs.DatosEgresoDetalle;
import HogarBudget.api.DTOs.sumCategoria;
import HogarBudget.api.Entities.EgresoDetalle;
import HogarBudget.api.Entities.Usuario;
import HogarBudget.api.Services.EgresoDetalleService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
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
    public ResponseEntity<EgresoDetalle> agregarEgresoDetalle (
            @RequestBody DatosEgresoDetalle datosEgresoDetalle,
            @AuthenticationPrincipal Usuario usuario){
        return ResponseEntity.status(201).body(egresoDetalleService.guardar(datosEgresoDetalle, usuario));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarEgresoDetalle(
            @PathVariable Long id,
            @AuthenticationPrincipal Usuario usuario){
        egresoDetalleService.eliminar(id, usuario);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<EgresoDetalle> editarEgresoDetalle (
            @RequestBody DatosEgresoDetalle datosEgresoDetalle,
            @PathVariable Long id,
            @AuthenticationPrincipal Usuario usuario){
        return ResponseEntity.ok(egresoDetalleService.editar(datosEgresoDetalle, id, usuario));
    }

    @GetMapping
    public ResponseEntity<List<DatosEDSalida>> listarEgresoDetalle (
            @AuthenticationPrincipal Usuario usuario){
        return ResponseEntity.ok(egresoDetalleService.listar(usuario));
    }

    @GetMapping("/actual")
    public ResponseEntity<List<sumCategoria>> sumCategoria(
            @AuthenticationPrincipal Usuario usuario){
        return ResponseEntity.ok(egresoDetalleService.sumCat(usuario));
    }
}