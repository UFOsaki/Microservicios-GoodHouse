package cl.Goodhouse.Cobros.Automaticos.model;

import cl.Goodhouse.Cobros.Automaticos.model.Usuario;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;
import java.util.List;


@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "cobros_automaticos")
public class CobroAutomatico {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;

    private String descripcion;

    @Column(name = "fecha_cobro", nullable = false)
    private LocalDateTime fechaCobro; // próxima fecha programada (inicia con la primera)

    private String periodo; // "mensual", "bimestral", etc.

    private String estado; // "activo", "pausado", "finalizado"

    private String monto;

    @Column(name = "creador_rut", nullable = false)
    private String creadorRut;

    @ElementCollection
    @CollectionTable(name = "participantes_cobro", joinColumns = @JoinColumn(name = "cobro_id"))
    @Column(name = "usuario_rut")
    private List<String> participantes;
}

