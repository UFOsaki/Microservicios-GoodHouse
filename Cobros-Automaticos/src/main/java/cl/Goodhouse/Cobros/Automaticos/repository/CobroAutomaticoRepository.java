package cl.Goodhouse.Cobros.Automaticos.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import cl.Goodhouse.Cobros.Automaticos.model.CobroAutomatico;

import java.time.LocalDateTime;
import java.util.List;

public interface CobroAutomaticoRepository extends JpaRepository<CobroAutomatico, Long> {

    List<CobroAutomatico> findByEstadoAndFechaCobroLessThanEqual(String estado, LocalDateTime fecha);

    List<CobroAutomatico> findByCreadorRut(String rut);
}

