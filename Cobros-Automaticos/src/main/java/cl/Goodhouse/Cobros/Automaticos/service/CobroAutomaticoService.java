package cl.Goodhouse.Cobros.Automaticos.service;

import cl.Goodhouse.Cobros.Automaticos.repository.GastoRepository;
import cl.Goodhouse.Cobros.Automaticos.model.CobroAutomatico;
import cl.Goodhouse.Cobros.Automaticos.model.Pago;
import cl.Goodhouse.Cobros.Automaticos.repository.CobroAutomaticoRepository;
import cl.Goodhouse.Cobros.Automaticos.repository.PagoRepository;
import cl.Goodhouse.Cobros.Automaticos.model.Gasto;
import cl.Goodhouse.Cobros.Automaticos.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class CobroAutomaticoService {

    @Autowired
    private final GastoRepository gastoRepository;

    private final CobroAutomaticoRepository cobroAutomaticoRepository;
    private final UsuarioRepository usuarioRepository;
    private final PagoRepository pagoRepository;

    public CobroAutomatico crearCobroAutomatico(CobroAutomatico cobroAutomatico) {
        validarCobro(cobroAutomatico);
        cobroAutomatico.setEstado("activo");
        return cobroAutomaticoRepository.save(cobroAutomatico);
    }

    public List<CobroAutomatico> obtenerTodos() {
        return cobroAutomaticoRepository.findAll();
    }

    public CobroAutomatico obtenerPorId(Long id) {
        return cobroAutomaticoRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Cobro no encontrado con ID: " + id));
    }

    public CobroAutomatico actualizarCobro(Long id, CobroAutomatico datosActualizados) {
        CobroAutomatico existente = obtenerPorId(id);
        validarCobro(datosActualizados);

        existente.setNombre(datosActualizados.getNombre());
        existente.setDescripcion(datosActualizados.getDescripcion());
        existente.setFechaCobro(datosActualizados.getFechaCobro());
        existente.setPeriodo(datosActualizados.getPeriodo());
        existente.setMonto(datosActualizados.getMonto());
        existente.setParticipantes(datosActualizados.getParticipantes());
        existente.setEstado(datosActualizados.getEstado());

        return cobroAutomaticoRepository.save(existente);
    }

    public void eliminarCobro(Long id) {
        CobroAutomatico existente = obtenerPorId(id);
        cobroAutomaticoRepository.delete(existente);
    }

    @Scheduled(cron = "0 0 * * * *") // Ejecuta cada hora
    public void ejecutarCobrosPendientes() {
        List<CobroAutomatico> pendientes = cobroAutomaticoRepository
                .findByEstadoAndFechaCobroLessThanEqual("activo", LocalDateTime.now());

        for (CobroAutomatico cobro : pendientes) {
            try {
                ejecutarCobroInterno(cobro);
            } catch (Exception e) {
                System.err.println("Error al ejecutar cobro ID " + cobro.getId() + ": " + e.getMessage());
            }
        }
    }

    private void ejecutarCobroInterno(CobroAutomatico cobro) {
        for (String rut : cobro.getParticipantes()) {

            // Crear el pago
            Pago nuevoPago = Pago.builder()
                    .fecha(LocalDateTime.now())
                    .monto(cobro.getMonto())
                    .estado("pendiente")
                    .creadorRut(cobro.getCreadorRut())
                    .tarjetaId(null) // definir si aplica
                    .build();

            pagoRepository.save(nuevoPago);

            // Crear el gasto asociado al pago
            Gasto nuevoGasto = Gasto.builder()
                    .fecha(LocalDateTime.now())
                    .monto(cobro.getMonto())
                    .categoria("Cobro automático")
                    .descripcion("Cobro generado automáticamente: " + cobro.getNombre())
                    .usuarioRut(rut)
                    .pagoId(nuevoPago.getId())
                    .build();

            gastoRepository.save(nuevoGasto);
        }

        // Calcular próxima fecha y guardar
        cobro.setFechaCobro(calcularProximaFecha(cobro.getFechaCobro(), cobro.getPeriodo()));
        cobroAutomaticoRepository.save(cobro);
    }


    private LocalDateTime calcularProximaFecha(LocalDateTime actual, String periodo) {
        return switch (periodo.toLowerCase()) {
            case "mensual" -> actual.plusMonths(1);
            case "bimestral" -> actual.plusMonths(2);
            case "trimestral" -> actual.plusMonths(3);
            case "semestral" -> actual.plusMonths(6);
            case "anual" -> actual.plusYears(1);
            default -> throw new IllegalArgumentException("Periodo inválido: " + periodo);
        };
    }

    private void validarCobro(CobroAutomatico cobro) {
        if (cobro.getNombre() == null || cobro.getNombre().trim().isEmpty()) {
            throw new IllegalArgumentException("El nombre del cobro no puede estar vacío");
        }

        if (cobro.getFechaCobro() == null || cobro.getFechaCobro().isBefore(LocalDateTime.now())) {
            throw new IllegalArgumentException("La fecha de cobro debe ser en el futuro");
        }

        if (!esPeriodoValido(cobro.getPeriodo())) {
            throw new IllegalArgumentException("Periodo inválido: " + cobro.getPeriodo());
        }

        if (!usuarioRepository.existsById(cobro.getCreadorRut())) {
            throw new IllegalArgumentException("Usuario creador no encontrado: " + cobro.getCreadorRut());
        }

        List<String> participantes = cobro.getParticipantes();
        if (participantes == null || participantes.isEmpty()) {
            throw new IllegalArgumentException("Debe haber al menos un participante");
        }

        long participantesExistentes = usuarioRepository.countByRutIn(participantes);
        if (participantesExistentes != participantes.size()) {
            throw new IllegalArgumentException("Uno o más participantes no existen");
        }
    }

    private boolean esPeriodoValido(String periodo) {
        return List.of("mensual", "bimestral", "trimestral", "semestral", "anual")
                .contains(periodo.toLowerCase());
    }
}


