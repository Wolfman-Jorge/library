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


@CrossOrigin("*" )
@RestController
@RequestMapping("library")
//@PreAuthorize("denyAll()")
public class LibreriaController {

    @Autowired
    LibreriaService servicio;


    @GetMapping("/socios")
    @PreAuthorize("permitAll()")
    public List<Socio> getSocios(){
        return servicio.getSocios();
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/socios")
    @PreAuthorize("hasRole('ADMIN')")
    public List<Socio> addSocios(@RequestBody Socio socio){ //convierte el parámetro (que viene en JSON) a objeto java
        return servicio.createSocio(socio);
    }

    @PutMapping("/socios")
    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    public List<Socio> putSocio(@RequestBody Socio socio){
        return servicio.updateSocio(socio);
    }

//    @DeleteMapping("/socios/{id}")
//    @PreAuthorize("hasRole('ADMIN')")
//    public Socio deleteSocio(@PathVariable long id){
//        return servicio.deleteSocio(id);
//    }

    @DeleteMapping("/socios/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public List<Socio> deleteSocio(@PathVariable long id){
        return servicio.deleteSocio(id);
    }

    @GetMapping("/libros")
    @PreAuthorize("permitAll()")
    public List<Libro> getLibros(){
        return servicio.getLibros();
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/libros")
    @PreAuthorize("hasRole('ADMIN')")
    public List<Libro> addLibros(@RequestBody Libro libro){
        return servicio.createLibro(libro);

    }

    @PutMapping("/libros")
    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    public List<Libro> putLibro(@RequestBody Libro libro){
        return servicio.updateLibro(libro);
    }

    @DeleteMapping("/libros/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public List<Libro> deleteLibro(@PathVariable long id){
        return servicio.deleteLibro(id);
    }

    @GetMapping("/alquileres")
    @PreAuthorize("permitAll()")
    public List<Alquiler> getAlquileres(){
        return servicio.getAlquileres();
    }


//    @PostMapping("/alquileres")
//    public String addAlquileres(@RequestBody Alquiler alquiler){
//
//        for(Libro l : servicio.getLibros()){
//
//            if(l.getId() == alquiler.getIdLibro()){
//                if(l.isAlquilado())
//                    return "Este libro no está disponible";
//                else{
//                    l.setAlquilado(true);
//                    alquiler.setVigente(true);
//                    alquiler.setFecha(new Date().getTime());
//                    servicio.updateLibro(l);
//                    servicio.createAlquiler(alquiler);
//                    return "Alquiler realizado";
//                }
//            }
//
//        }
//        return "No se ha encontrado el libro";
//    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/alquileres")
    @PreAuthorize("hasRole('ADMIN')")
    public List<Object> addAlquileres(@RequestBody Alquiler alquiler){
        return servicio.createAlquiler(alquiler);
    }

//    @ResponseStatus(HttpStatus.CREATED)
//    @PostMapping("/alquileres")
//    @PreAuthorize("hasRole('ADMIN')")
//    public Alquiler addAlquileres(@RequestBody Alquiler alquiler){
//        System.out.println("paso por método Controller");
//        return servicio.createAlquiler(alquiler);
//    }



//    @PutMapping("/alquileres")
//    public String devolverLibro(@PathVariable Long id){
//        for(Alquiler a : servicio.getAlquileres()){
//            if(a.getId() == id){
//                for(Libro l : servicio.getLibros()){
//                    if(l.getId() == a.getIdLibro()){
//                        if(!l.isAlquilado()){
//                            return "Este libro no estaba alquilado";
//                        } else {
//                            l.setAlquilado(false);
//                            servicio.updateLibro(l);
//                            a.setVigente(false);
//                            a.setFechaDevolucion(new Date().getTime());
//                            servicio.updateAlquiler(a);
//                            return "Devolución realizada";
//                        }
//                    }
//                }
//            }
//        }
//        return "El alquiler introducido no tiene coincidencias";
//    }

    @PutMapping("/alquileres")
    //@PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    //@PreAuthorize("hasRole('ADMIN')")
    @PreAuthorize("permitAll()")
    public List<Object> devolverLibro(@RequestBody Alquiler alquiler) {
        return servicio.updateAlquiler(alquiler);
    }


    @DeleteMapping("/alquileres/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public List<Alquiler> deleteAlquiler(@PathVariable long id){
        return servicio.deleteAlquiler(id);
    }

}
