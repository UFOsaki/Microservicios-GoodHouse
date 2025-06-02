package cl.Goodhouse.Autenticacion.service;

import cl.Goodhouse.Autenticacion.model.AutenticacionUsuario;
import cl.Goodhouse.Autenticacion.model.RegistroLogin;
import cl.Goodhouse.Autenticacion.repository.AutenticacionUsuarioRepository;
import cl.Goodhouse.Autenticacion.repository.RegistroLoginRepository;
import cl.Goodhouse.Autenticacion.repository.UsuarioRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class AutenticacionService {

    @Autowired
    private AutenticacionUsuarioRepository authRepo;

    @Autowired
    private RegistroLoginRepository loginRepo;

    @Autowired
    private UsuarioRepository usuarioRepo;

    // Registrar un nuevo usuario con contraseña en texto plano
    public AutenticacionUsuario registrarUsuario(String rut, String nombre, String password) {
        if (!usuarioRepo.existsByRut(rut)) {
            throw new IllegalArgumentException("El RUT no existe en la base de datos de usuarios.");
        }

        if (authRepo.existsById(rut)) {
            throw new IllegalArgumentException("El usuario ya está registrado.");
        }

        AutenticacionUsuario nuevo = new AutenticacionUsuario(rut, nombre, password);
        return authRepo.save(nuevo);
    }

    // Validar credenciales (comparación directa)
    public boolean validarCredenciales(String rut, String password) {
        AutenticacionUsuario usuario = authRepo.findByRut(rut);
        if (usuario == null) return false;

        boolean valido = password.equals(usuario.getPassword());

        if (valido) {
            RegistroLogin login = new RegistroLogin();
            login.setRutUsuario(rut);
            loginRepo.save(login);
        }

        return valido;
    }

    // Obtener historial de logins por rut
    public List<RegistroLogin> obtenerHistorialLogins(String rut) {
        return loginRepo.findByRutUsuario(rut);
    }
}

