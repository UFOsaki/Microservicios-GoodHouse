package cl.Goodhouse.Autenticacion.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AutenticacionUsuario {

    @Id
    private String rut;

    private String nombre;

    @Column(nullable = false)
    private String password; // antes era passwordHash
}

