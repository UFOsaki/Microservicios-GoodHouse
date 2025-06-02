package cl.GoodHouse.Soporte.Cliente.service;

import cl.GoodHouse.Soporte.Cliente.model.Ticket;
import cl.GoodHouse.Soporte.Cliente.model.Usuario;
import cl.GoodHouse.Soporte.Cliente.repository.TicketRepository;
import cl.GoodHouse.Soporte.Cliente.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class TicketService {

    private final TicketRepository ticketRepository;
    private final UsuarioRepository usuarioRepository;

    // Crear nuevo ticket
    public Ticket crearTicket(Ticket ticket) {
        Usuario usuario = usuarioRepository.findByRut(ticket.getRutUsuario());

        if (usuario == null) {
            throw new IllegalArgumentException("El usuario con RUT " + ticket.getRutUsuario() + " no existe.");
        }

        ticket.setNombreUsuario(usuario.getNombre());
        ticket.setEstado("pendiente");

        return ticketRepository.save(ticket);
    }

    // Listar todos los tickets
    public List<Ticket> obtenerTodos() {
        return ticketRepository.findAll();
    }

    // Obtener ticket por ID
    public Ticket obtenerPorId(Long id) {
        return ticketRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Ticket no encontrado con ID: " + id));
    }

    // Obtener tickets por RUT
    public List<Ticket> obtenerPorRut(String rutUsuario) {
        return ticketRepository.findByRutUsuario(rutUsuario);
    }

    // Actualizar estado del ticket
    public Ticket actualizarEstado(Long id, String nuevoEstado) {
        if (!List.of("resuelto", "cancelado").contains(nuevoEstado.toLowerCase())) {
            throw new IllegalArgumentException("Estado inválido. Debe ser 'resuelto' o 'cancelado'");
        }

        Ticket ticket = obtenerPorId(id);
        ticket.setEstado(nuevoEstado.toLowerCase());
        return ticketRepository.save(ticket);
    }

    // Eliminar ticket
    public void eliminar(Long id) {
        Ticket ticket = obtenerPorId(id);
        ticketRepository.delete(ticket);
    }
}


