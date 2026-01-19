package HogarBudget.api.Controllers;

import HogarBudget.api.DTOs.DatosIngresoFijo;
import HogarBudget.api.DTOs.DatosIngresoVariable;
import HogarBudget.api.Entities.IngresoVariable;
import HogarBudget.api.Services.IngresoFijoService;
import HogarBudget.api.Services.IngresoVariableService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("ingresos/variables")
public class IngresoVariableController {

    private final IngresoVariableService ingresoVariableService;

    public IngresoVariableController(IngresoVariableService ingresoVariableService) {
        this.ingresoVariableService = ingresoVariableService;
    }

    @PostMapping
    public ResponseEntity<IngresoVariable> guardarIngresoVariable(@RequestBody DatosIngresoVariable datosIngresoVariable) {
        IngresoVariable ingresoGuardado = ingresoVariableService.guardar(datosIngresoVariable);
        return ResponseEntity.status(201).body(ingresoGuardado);
    }

    @PutMapping("/{id}")
    public ResponseEntity<DatosIngresoVariable> actualizarIngresoVariable(
            @RequestBody DatosIngresoVariable datosIngresoVariable,
            @PathVariable Long id) {
        IngresoVariable variableActualizado = ingresoVariableService.actualizar(datosIngresoVariable, id);
        return ResponseEntity.ok(new DatosIngresoVariable(variableActualizado));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarVariables(@PathVariable Long id) {
        ingresoVariableService.eliminar(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public ResponseEntity<Page<IngresoVariable>> listarVariables(Pageable pagina){
        return ResponseEntity.ok(ingresoVariableService.listar(pagina));
    }

    @GetMapping("/total")
    public ResponseEntity<Integer> totalIngresoVariable () {
        return ResponseEntity.ok(ingresoVariableService.sumIngresoVariable());
    }

}
