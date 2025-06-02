package cl.GoodHouse.Soporte.Cliente.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class Usuario {

    @Id
    private String rut;

    private String nombre;
}

