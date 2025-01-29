package library.library.modelo;

import jakarta.persistence.*;

/**
 * Clase plantilla para los libros
 * */
//Registra la entidad
@Entity
//Table permite mapear nuestra entidad contra una tabla que tenga el mismo nombre
@Table(name = "libro")
public class Libro {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String titulo;
    private boolean alquilado;

    public Libro() {
    }

    public Libro(int id, String titulo, boolean alquilado) {
        this.id = id;
        this.titulo = titulo;
        this.alquilado = alquilado;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public boolean isAlquilado() {
        return alquilado;
    }

    public void setAlquilado(boolean alquilado) {
        this.alquilado = alquilado;
    }
}
