package cl.Goodhouse.Autenticacion.repository;

import cl.Goodhouse.Autenticacion.model.RegistroLogin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RegistroLoginRepository extends JpaRepository<RegistroLogin, Long> {

    List<RegistroLogin> findByRutUsuario(String rutUsuario);
}
