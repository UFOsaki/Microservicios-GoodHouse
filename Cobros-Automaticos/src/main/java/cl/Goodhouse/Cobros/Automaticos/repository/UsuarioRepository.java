package cl.Goodhouse.Cobros.Automaticos.repository;

import cl.Goodhouse.Cobros.Automaticos.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UsuarioRepository extends JpaRepository<Usuario, String> {

    long countByRutIn(List<String> ruts);

}
