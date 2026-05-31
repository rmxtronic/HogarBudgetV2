package HogarBudget.api.Controllers;

import HogarBudget.api.DTOs.DatosEgresoCategoria;
import HogarBudget.api.Entities.EgresoCategoria;
import HogarBudget.api.Entities.Usuario;
import HogarBudget.api.Services.EgresoCategoriaService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("egreso/categorias")
public class EgresoCategoriaController {

    private final EgresoCategoriaService egresoCategoriaService;

    public EgresoCategoriaController (EgresoCategoriaService egresoCategoriaService){
        this.egresoCategoriaService = egresoCategoriaService;
    }

    @PostMapping
    public ResponseEntity<EgresoCategoria> guardarCategoria (
            @RequestBody DatosEgresoCategoria datosEgresoCategoria,
            @AuthenticationPrincipal Usuario usuario){
        EgresoCategoria guardado = egresoCategoriaService.guardar(datosEgresoCategoria, usuario);
        return ResponseEntity.status(201).body(guardado);
    }

    @PutMapping("/{id}")
    public ResponseEntity<EgresoCategoria> editarCategoria (
            @RequestBody DatosEgresoCategoria datosEgresoCategoria,
            @PathVariable Long id,
            @AuthenticationPrincipal Usuario usuario){
        EgresoCategoria editado = egresoCategoriaService.editar(datosEgresoCategoria, id, usuario);
        return ResponseEntity.ok(editado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarCategoria(
            @PathVariable Long id,
            @AuthenticationPrincipal Usuario usuario) {
        egresoCategoriaService.eliminar(id, usuario);
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public ResponseEntity<List<EgresoCategoria>> listarCategorias (
            @AuthenticationPrincipal Usuario usuario) {
        List<EgresoCategoria> lista = egresoCategoriaService.listar(usuario);
        return ResponseEntity.ok(lista);
    }

    @GetMapping("/total-presupuestado")
    public ResponseEntity<Integer> obtenerMontoPresupuestado (
            @AuthenticationPrincipal Usuario usuario) {
        int total = egresoCategoriaService.totalPresupuestado(usuario);
        return ResponseEntity.ok(total);
    }
}