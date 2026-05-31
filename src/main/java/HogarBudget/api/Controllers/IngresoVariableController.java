package HogarBudget.api.Controllers;

import HogarBudget.api.DTOs.DatosIngresoVariable;
import HogarBudget.api.Entities.IngresoVariable;
import HogarBudget.api.Entities.Usuario;
import HogarBudget.api.Services.IngresoVariableService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("ingresos/variables")
public class IngresoVariableController {

    private final IngresoVariableService ingresoVariableService;

    public IngresoVariableController(IngresoVariableService ingresoVariableService) {
        this.ingresoVariableService = ingresoVariableService;
    }

    @PostMapping
    public ResponseEntity<IngresoVariable> guardarIngresoVariable(
            @RequestBody DatosIngresoVariable datosIngresoVariable,
            @AuthenticationPrincipal Usuario usuario) {
        IngresoVariable ingresoGuardado = ingresoVariableService.guardar(datosIngresoVariable, usuario);
        return ResponseEntity.status(201).body(ingresoGuardado);
    }

    @PutMapping("/{id}")
    public ResponseEntity<DatosIngresoVariable> actualizarIngresoVariable(
            @RequestBody DatosIngresoVariable datosIngresoVariable,
            @PathVariable Long id,
            @AuthenticationPrincipal Usuario usuario) {
        IngresoVariable variableActualizado = ingresoVariableService.actualizar(datosIngresoVariable, id, usuario);
        return ResponseEntity.ok(new DatosIngresoVariable(variableActualizado));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarVariables(
            @PathVariable Long id,
            @AuthenticationPrincipal Usuario usuario) {
        ingresoVariableService.eliminar(id, usuario);
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public ResponseEntity<Page<IngresoVariable>> listarVariables(
            Pageable pagina,
            @AuthenticationPrincipal Usuario usuario){
        return ResponseEntity.ok(ingresoVariableService.listar(pagina, usuario));
    }

    @GetMapping("/total")
    public ResponseEntity<Integer> totalIngresoVariable (
            @AuthenticationPrincipal Usuario usuario) {
        return ResponseEntity.ok(ingresoVariableService.sumIngresoVariable(usuario));
    }
}