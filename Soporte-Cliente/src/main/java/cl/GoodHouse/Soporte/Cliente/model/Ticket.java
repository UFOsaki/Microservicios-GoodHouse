package cl.GoodHouse.Soporte.Cliente.model;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Ticket {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @CreationTimestamp
    @Column(updatable = false, nullable = false)
    private LocalDateTime fechaCreacion;

    @Column(nullable = false)
    private String estado; // "pendiente", "respondido", "cancelado"

    @Column(nullable = false, length = 1000)
    private String descripcion;

    @Column(name = "rut_usuario", nullable = false)
    private String rutUsuario;

    @Column(name = "nombre_usuario", nullable = false)
    private String nombreUsuario;
}

