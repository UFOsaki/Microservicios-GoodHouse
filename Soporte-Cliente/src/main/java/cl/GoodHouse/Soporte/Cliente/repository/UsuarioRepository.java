package cl.GoodHouse.Soporte.Cliente.repository;

import cl.GoodHouse.Soporte.Cliente.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, String> {

    boolean existsByRut(String rut);

    Usuario findByRut(String rut);
}

