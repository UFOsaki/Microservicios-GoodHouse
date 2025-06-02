package cl.GoodHouse.Soporte.Cliente.repository;

import cl.GoodHouse.Soporte.Cliente.model.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TicketRepository extends JpaRepository<Ticket, Long> {

    // Buscar tickets por RUT de usuario
    List<Ticket> findByRutUsuario(String rutUsuario);
}

