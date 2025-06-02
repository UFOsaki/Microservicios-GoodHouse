package cl.Goodhouse.Cobros.Automaticos.repository;

import cl.Goodhouse.Cobros.Automaticos.model.Gasto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GastoRepository extends JpaRepository<Gasto, Integer> {
}

