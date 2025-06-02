package cl.Goodhouse.Cobros.Automaticos.controller;

import cl.Goodhouse.Cobros.Automaticos.model.CobroAutomatico;
import cl.Goodhouse.Cobros.Automaticos.service.CobroAutomaticoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/cobrosautomaticos")
@RequiredArgsConstructor
public class CobroAutomaticoController {

    private final CobroAutomaticoService cobroAutomaticoService;

    // Crear un nuevo cobro automático
    @PostMapping
    public ResponseEntity<CobroAutomatico> crear(@RequestBody CobroAutomatico cobro) {
        CobroAutomatico nuevo = cobroAutomaticoService.crearCobroAutomatico(cobro);
        return ResponseEntity.status(201).body(nuevo);
    }

    // Listar todos los cobros
    @GetMapping
    public ResponseEntity<List<CobroAutomatico>> obtenerTodos() {
        return ResponseEntity.ok(cobroAutomaticoService.obtenerTodos());
    }

    // Obtener un cobro por su ID
    @GetMapping("/{id}")
    public ResponseEntity<CobroAutomatico> obtenerPorId(@PathVariable Long id) {
        return ResponseEntity.ok(cobroAutomaticoService.obtenerPorId(id));
    }

    // Actualizar un cobro por ID
    @PutMapping("/{id}")
    public ResponseEntity<CobroAutomatico> actualizar(@PathVariable Long id, @RequestBody CobroAutomatico actualizado) {
        return ResponseEntity.ok(cobroAutomaticoService.actualizarCobro(id, actualizado));
    }

    // Eliminar un cobro por ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        cobroAutomaticoService.eliminarCobro(id);
        return ResponseEntity.noContent().build();
    }

}
