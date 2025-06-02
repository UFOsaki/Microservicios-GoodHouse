package cl.Goodhouse.Autenticacion.controller;

import cl.Goodhouse.Autenticacion.model.AutenticacionUsuario;
import cl.Goodhouse.Autenticacion.model.RegistroLogin;
import cl.Goodhouse.Autenticacion.service.AutenticacionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/autenticacion")
public class AutenticacionController {

    @Autowired
    private AutenticacionService autenticacionService;

    // Registrar usuario
    @PostMapping("/registro")
    public AutenticacionUsuario registrarUsuario(@RequestBody AutenticacionUsuario usuario) {
        return autenticacionService.registrarUsuario(
                usuario.getRut(),
                usuario.getNombre(),
                usuario.getPassword()
        );
    }


    // Validar credenciales
    @PostMapping("/validar")
    public boolean validar(@RequestBody AutenticacionUsuario usuario) {
        return autenticacionService.validarCredenciales(
                usuario.getRut(),
                usuario.getPassword()
        );
    }


    // Obtener historial de logins
    @GetMapping("/logins/{rut}")
    public List<RegistroLogin> obtenerLogins(@PathVariable String rut) {
        return autenticacionService.obtenerHistorialLogins(rut);
    }
}
