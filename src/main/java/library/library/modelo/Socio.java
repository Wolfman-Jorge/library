package library.library.modelo;

import jakarta.persistence.*;

/**
 * Clase plantilla para los socios
 * */
//Registra la entidad
@Entity
//Table permite mapear nuestra entidad contra una tabla que tenga el mismo nombre
@Table(name = "socio")
public class Socio {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String nombre;
    private String email;

    public Socio() {
    }

    public Socio(int id, String nombre, String email){
        this.id = id;
        this.nombre = nombre;
        this.email = email;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

}


