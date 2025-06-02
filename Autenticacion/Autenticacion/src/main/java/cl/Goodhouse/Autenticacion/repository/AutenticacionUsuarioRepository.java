package cl.Goodhouse.Autenticacion.repository;

import cl.Goodhouse.Autenticacion.model.AutenticacionUsuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AutenticacionUsuarioRepository extends JpaRepository<AutenticacionUsuario, String> {

    AutenticacionUsuario findByRut(String rut);
}
