/*Este controlador se va a encargar de pedir, listar, editar y eliminar los ingresos fijos que el usuario tenga
   Esperamos recibir desde el cliente, un JSON con el nombre del ingreso fijo, el montoPresupuestado y la fecha
   (La idea es incluir automaticamente la fecha en la que se hace el ingreso o dar la opcion de quieres poner otra fecha)
    */

package HogarBudget.api.Controllers;

import HogarBudget.api.DTOs.DatosIngresoFijo;
import HogarBudget.api.Entities.IngresoFijo;
import HogarBudget.api.Entities.Usuario;
import HogarBudget.api.Services.IngresoFijoService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/ingresos/fijos")
public class IngresoFijoController {

    private final IngresoFijoService ingresoFijoService;

    public IngresoFijoController(IngresoFijoService ingresoFijoService){
        this.ingresoFijoService = ingresoFijoService;
    }

    @PostMapping
    public ResponseEntity<IngresoFijo> guardarIngresoFijo(
            @Valid @RequestBody DatosIngresoFijo datosIngresoFijo,
            @AuthenticationPrincipal Usuario usuario){
        IngresoFijo ingresoGuardado = ingresoFijoService.guardar(datosIngresoFijo, usuario);
        return ResponseEntity.status(201).body(ingresoGuardado);
    }

    @PutMapping("/{id}")
    public ResponseEntity<DatosIngresoFijo> modificarIngresoFijo(
            @PathVariable Long id,
            @RequestBody DatosIngresoFijo datosIngresoFijo,
            @AuthenticationPrincipal Usuario usuario){
        IngresoFijo fijoModificado = ingresoFijoService.modificar(id, datosIngresoFijo, usuario);
        return ResponseEntity.ok(new DatosIngresoFijo(fijoModificado));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarIngresoFijo(
            @PathVariable Long id,
            @AuthenticationPrincipal Usuario usuario){
        ingresoFijoService.eliminar(id, usuario);
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public ResponseEntity<Page<IngresoFijo>> listarIngresoFijo(
            @PageableDefault(size = 3) Pageable paginas,
            @AuthenticationPrincipal Usuario usuario){
        return ResponseEntity.ok(ingresoFijoService.listar(paginas, usuario));
    }

    @GetMapping("/total")
    public ResponseEntity<Integer> totalIngresoFijo (
            @AuthenticationPrincipal Usuario usuario) {
        return ResponseEntity.ok(ingresoFijoService.sumIngresoFijo(usuario));
    }
}