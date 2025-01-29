package library.library.controllers;

import library.library.modelo.Alquiler;
import library.library.modelo.Libro;
import library.library.modelo.Socio;
import library.library.services.LibreriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Clase controladora para la gestión de los accesos al servicio
* */
@CrossOrigin("*" )
@RestController
@RequestMapping("library")
@PreAuthorize("denyAll()")
public class LibreriaController {

    //Se encarga de contruir las ligazones entre los distintos elementos
    @Autowired
    LibreriaService servicio;

    /**
     * Método que solicita al servicio la lista de socios
     * Autorizado para todos los usuarios
     * @Return lista de socios actualizada
     * */
    @GetMapping("/socios")
    @PreAuthorize("permitAll()")
    public List<Socio> getSocios(){
        return servicio.getSocios();
    }

    /**
     * Método que solicita al servicio añadir un socio
     * Autorizado para todos los usuarios
     * @Param recibe un Json
     * @Return lista de socios actualizada
     * */
    @ResponseStatus(HttpStatus.CREATED) //Responde 201 si se ha creado correctamente
    @PostMapping("/socios")
    @PreAuthorize("permitAll()")
    public List<Socio> addSocios(@RequestBody Socio socio){ //Convierte el parámetro (JSON) a objeto Java
        return servicio.createSocio(socio);
    }

    /**
     * Método que solicita al servicio modificar un socio
     * Autorizado para los usuarios ADMIN y USER
     * @Param recibe un Json
     * @Return lista de socios actualizada
     * */
    @PutMapping("/socios")
    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    public List<Socio> putSocio(@RequestBody Socio socio){ //Convierte el parámetro (JSON) a objeto Java
        return servicio.updateSocio(socio);
    }

    /**
     * Método que solicita al servicio eliminar un socio
     * Autorizado para los usuarios ADMIN
     * @Param recibe un Long con el valor del id del socio a eliminar
     * @Return lista de socios actualizada
     * */
    @DeleteMapping("/socios/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public List<Socio> deleteSocio(@PathVariable long id){
        return servicio.deleteSocio(id);
    }

    /**
     * Método que solicita al servicio la lista de libros
     * Autorizado para todos los usuarios
     * @Return lista de libros actualizada
     * */
    @GetMapping("/libros")
    @PreAuthorize("permitAll()")
    public List<Libro> getLibros(){
        return servicio.getLibros();
    }

    /**
     * Método que solicita al servicio añadir un libro
     * Autorizado para todos los usuarios
     * @Param recibe un Json
     * @Return lista de libros actualizada
     * */
    @ResponseStatus(HttpStatus.CREATED) //Responde 201 si se ha creado correctamente
    @PostMapping("/libros")
    @PreAuthorize("permitAll()")
    public List<Libro> addLibros(@RequestBody Libro libro){ //Convierte el parámetro (JSON) a objeto Java
        return servicio.createLibro(libro);

    }

    /**
     * Método que solicita al servicio modificar un libro
     * Autorizado para los usuarios ADMIN y USER
     * @Param recibe un Json
     * @Return lista de libros actualizada
     * */
    @PutMapping("/libros")
    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    public List<Libro> putLibro(@RequestBody Libro libro){ //Convierte el parámetro (JSON) a objeto Java
        return servicio.updateLibro(libro);
    }

    /**
     * Método que solicita al servicio eliminar un libro
     * Autorizado para los usuarios ADMIN
     * @Param recibe un Long con el valor del id del libro a eliminar
     * @Return lista de libros actualizada
     * */
    @DeleteMapping("/libros/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public List<Libro> deleteLibro(@PathVariable long id){
        return servicio.deleteLibro(id);
    }

    /**
     * Método que solicita al servicio la lista de alquileres
     * Autorizado para todos los usuarios
     * @Return lista de alquileres actualizada
     * */
    @GetMapping("/alquileres")
    @PreAuthorize("permitAll()")
    public List<Alquiler> getAlquileres(){
        return servicio.getAlquileres();
    }

    /**
     * Método que solicita al servicio añadir un alquiler
     * Autorizado para todos los usuarios
     * @Param recibe un Json
     * @Return lista de alquileres actualizada
     * */
    @ResponseStatus(HttpStatus.CREATED) //Responde 201 si se ha creado correctamente
    @PostMapping("/alquileres")
    @PreAuthorize("permitAll()")
    public List<Object> addAlquileres(@RequestBody Alquiler alquiler){ //Convierte el parámetro (JSON) a objeto Java
        return servicio.createAlquiler(alquiler);
    }

    /**
     * Método que solicita al servicio devolver un libro
     * Autorizado para los usuarios ADMIN y USER
     * @Param recibe un Json
     * @Return lista de alquileres actualizada
     * */
    @PutMapping("/alquileres")
    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    public List<Object> devolverLibro(@RequestBody Alquiler alquiler) { //Convierte el parámetro (JSON) a objeto Java
        return servicio.updateAlquiler(alquiler);
    }

    /**
     * Método que solicita al servicio eliminar un alquiler
     * Autorizado para los usuarios ADMIN
     * @Param recibe un Long con el valor del id del alquiler a eliminar
     * @Return lista de alquileres actualizada
     * */
    @DeleteMapping("/alquileres/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public List<Alquiler> deleteAlquiler(@PathVariable long id){
        return servicio.deleteAlquiler(id);
    }

}
