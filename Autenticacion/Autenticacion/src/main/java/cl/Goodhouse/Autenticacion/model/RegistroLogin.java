package cl.Goodhouse.Autenticacion.model;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity
@Data
public class RegistroLogin {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String rutUsuario;

    @CreationTimestamp
    private LocalDateTime fechaHora;
}
