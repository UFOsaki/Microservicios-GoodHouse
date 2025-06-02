package cl.GoodHouse.Soporte.Cliente.controller;

import cl.GoodHouse.Soporte.Cliente.model.Ticket;
import cl.GoodHouse.Soporte.Cliente.service.TicketService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/tickets")
@RequiredArgsConstructor
public class TicketController {

    private final TicketService ticketService;

    // Crear un nuevo ticket
    @PostMapping
    public Ticket crear(@RequestBody Ticket ticket) {
        return ticketService.crearTicket(ticket);
    }

    // Listar todos los tickets
    @GetMapping
    public List<Ticket> obtenerTodos() {
        return ticketService.obtenerTodos();
    }

    // Obtener un ticket por su ID
    @GetMapping("/{id}")
    public Ticket obtenerPorId(@PathVariable Long id) {
        return ticketService.obtenerPorId(id);
    }

    // Obtener tickets por RUT de usuario
    @GetMapping("/usuario/{rut}")
    public List<Ticket> obtenerPorUsuario(@PathVariable String rut) {
        return ticketService.obtenerPorRut(rut);
    }

    // Actualizar el estado de un ticket
    @PutMapping("/{id}/estado")
    public Ticket actualizarEstado(@PathVariable Long id, @RequestBody String estado) {
        return ticketService.actualizarEstado(id, estado);
    }

    // Eliminar un ticket
    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable Long id) {
        ticketService.eliminar(id);
    }
}


