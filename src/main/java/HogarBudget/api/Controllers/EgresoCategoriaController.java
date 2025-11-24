package HogarBudget.api.Controllers;

import HogarBudget.api.DTOs.DatosEgresoCategoria;
import HogarBudget.api.Entities.EgresoCategoria;
import HogarBudget.api.Services.EgresoCategoriaService;
import org.apache.coyote.Response;
import org.springframework.data.domain.Page;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("egresos/categoria")
public class EgresoCategoriaController {

    private final EgresoCategoriaService egresoCategoriaService;

    public EgresoCategoriaController (EgresoCategoriaService egresoCategoriaService){
        this.egresoCategoriaService = egresoCategoriaService;
    }

    @PostMapping
    public ResponseEntity<EgresoCategoria> guardarCategoria (@RequestBody DatosEgresoCategoria datosEgresoCategoria){
        EgresoCategoria guardado = egresoCategoriaService.guardar(datosEgresoCategoria);
        return ResponseEntity.status(201).body(guardado);
    }

    @PutMapping("/{id}")
    public ResponseEntity<EgresoCategoria> editarCategoria (
            @RequestBody DatosEgresoCategoria datosEgresoCategoria,
            @PathVariable Long id){
        EgresoCategoria editado = egresoCategoriaService.editar(datosEgresoCategoria, id);
        return ResponseEntity.ok(editado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarCategoria(@PathVariable Long id) {
        egresoCategoriaService.eliminar(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public ResponseEntity<List<EgresoCategoria>> listarCategorias () {
        List<EgresoCategoria> lista =egresoCategoriaService.listar();
        return ResponseEntity.ok(lista);
    }


}
