package cl.Goodhouse.Autenticacion.repository;

import cl.Goodhouse.Autenticacion.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, String> {

    boolean existsByRut(String rut);
}
