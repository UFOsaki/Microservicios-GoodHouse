package cl.Goodhouse.Cobros.Automaticos.model;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Gasto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @CreationTimestamp
    @Column(updatable = false)
    private LocalDateTime fecha; // Fecha del gasto generado automáticamente

    private String monto;

    private String categoria; // Ejemplo: "Cobro automático", "Mensualidad", etc.

    private String descripcion;

    @Column(name = "usuario_rut", nullable = false)
    private String usuarioRut; // A quién se le asigna el gasto

    @Column(name = "pago_id")
    private Integer pagoId; // Si deseas enlazar este gasto con el pago generado
}
