package library.library.modelo;

import jakarta.persistence.*;

//Registra la entidad
@Entity
//Table permite mapear nuestra entidad contra una tabla que tenga el mismo nombre
@Table(name = "socio")
public class Socio {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String nombre;
    private String email;

    public Socio() {
    }

    public Socio(long id, String nombre, String email){
        this.id = id;
        this.nombre = nombre;
        this.email = email;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
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

    /* 1.- Poder añadir, eliminar y modificar socios y libros
        2.- Poder consultar, en endpoints separados, los socios, los libros y los alquileres.
        3.- Permitir a los socios alquilar libros:
            3.1.- Cuando un libro está alquilado, deber tener el atributo "alquilado" a true. En caso contrario false
            3.2.- No se debe permitir alquilar un libro que ya está alquilado.
            3.3.- Al alquilar, crear un nuevo alquiler relacionando socio y libro. La fecha debe ser el timestamp
            del momento en el que se realizó el alquiler. Mientras el libro esté alquilado, el alquiler debe estar vigente(vigente = true)
        4.- Permitir a los socios devolver libros:
            4.1.- Solo puede devolver libros qeu tengan en alquiler
            4.2.- Los libros devueltos deben tener alquilado = false.
            4.3.- Debe actualizarse el alquiler con vigente = false y fechadevolucion con el timestamp de momento de devolucion.
     *
     * */

}


