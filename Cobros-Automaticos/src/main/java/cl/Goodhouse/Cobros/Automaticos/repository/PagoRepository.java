package cl.Goodhouse.Cobros.Automaticos.repository;

import cl.Goodhouse.Cobros.Automaticos.model.Pago;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PagoRepository extends JpaRepository<Pago, Integer> {
}
