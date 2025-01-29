package library.library.modelo;

import jakarta.persistence.*;

/**
 * Clase plantilla para los alquileres
 * */
//Registra la entidad
@Entity
//Table permite mapear nuestra entidad contra una tabla que tenga el mismo nombre
@Table(name = "alquiler")
public class Alquiler {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private int idSocio;
    private int idLibro;
    private long fecha;
    private boolean vigente;
    private String mostrarVigente;
    private long fechaDevolucion;

    public Alquiler() {
    }

    public Alquiler(int id, int idSocio, int idLibro, long fecha, boolean vigente, String mostrarVigente, long fechaDevolucion) {
        this.id = id;
        this.idSocio = idSocio;
        this.idLibro = idLibro;
        this.fecha = fecha;
        this.vigente = vigente;
        this.mostrarVigente = mostrarVigente;
        this.fechaDevolucion = fechaDevolucion;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdSocio() {
        return idSocio;
    }

    public void setIdSocio(int idSocio) {
        this.idSocio = idSocio;
    }

    public int getIdLibro() {
        return idLibro;
    }

    public void setIdLibro(int idLibro) {
        this.idLibro = idLibro;
    }

    public long getFecha() {
        return fecha;
    }

    public void setFecha(long fecha) {
        this.fecha = fecha;
    }

    public boolean isVigente() {
        return vigente;
    }

    public void setVigente(boolean vigente) {
        this.vigente = vigente;
    }

    public String getMostrarVigente() {
        return mostrarVigente;
    }

    public void setMostrarVigente(String mostrarVigente) {
        this.mostrarVigente = mostrarVigente;
    }

    public long getFechaDevolucion() {
        return fechaDevolucion;
    }

    public void setFechaDevolucion(long fechaDevolucion) {
        this.fechaDevolucion = fechaDevolucion;
    }
}
