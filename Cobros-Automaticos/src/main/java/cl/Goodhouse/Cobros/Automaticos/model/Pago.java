package cl.Goodhouse.Cobros.Automaticos.model;

import java.time.LocalDateTime;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Pago {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @CreationTimestamp
    @Column(name = "fecha", updatable = false)
    private LocalDateTime fecha; // Fecha en la que se generó el pago

    private String monto;

    private String estado; // "pendiente", "pagado", "fallido", etc.

    @Column(name = "creador_rut", nullable = false)
    private String creadorRut;

    @Column(name = "tarjeta_id")
    private Integer tarjetaId; // Puede ser null si no se define en la creación
}

