package library.library.services;

import library.library.modelo.Alquiler;
import library.library.modelo.Libro;
import library.library.modelo.Socio;
import library.library.persistence.repositorio.AlquilerRepositorio;
import library.library.persistence.repositorio.LibroRepositorio;
import library.library.persistence.repositorio.SocioRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class LibreriaService {

    @Autowired
    SocioRepositorio socioRepositorio;
    @Autowired
    LibroRepositorio libroRepositorio;
    @Autowired
    AlquilerRepositorio alquilerRepositorio;

    public List<Socio> createSocio(Socio socio){
        socioRepositorio.save(socio);
        return getSocios();
    }

    public List<Socio> getSocios(){ return socioRepositorio.findAll(); }

    public Socio getSocioById(Long id){
        return socioRepositorio.findById(id).orElse(null);
    }

    public List<Socio> updateSocio(Socio upSocio){
        for(Socio s : getSocios()){
            if(s.getId() == upSocio.getId()){
                socioRepositorio.save(upSocio);
                return getSocios();
            }
        }
        return null;
    }

    public List<Socio> deleteSocio(Long id){
        for(Socio s : getSocios()){
            if(s.getId() == id){
                socioRepositorio.deleteById(id);
                return getSocios();
            }
        }
        return null;
    }

    public List<Libro> createLibro(Libro libro){
        libroRepositorio.save(libro);
        return getLibros();
    }

    public List<Libro> getLibros(){
        return libroRepositorio.findAll();
    }

    public Libro getLibroById(Long id){
        return libroRepositorio.findById(id).orElse(null);
    }

    public List<Libro> updateLibro(Libro upLibro){
        for(Libro l : getLibros()){
            if(l.getId() == upLibro.getId()){
                libroRepositorio.save(upLibro);
                return getLibros();
            }
        }
        return null;
    }

    public List<Libro> deleteLibro(Long id){
        for(Libro l : getLibros()){
            if(l.getId() == id){
                libroRepositorio.deleteById(id);
                return getLibros();
            }
        }
        return null;
    }
/*
    public Alquiler createAlquiler(Alquiler alquiler){
        for(Libro l : getLibros()){

            if(l.getId() == alquiler.getIdLibro()){
                System.out.println(l);
                if(l.isAlquilado()){
                    System.out.println(l.isAlquilado());
                    return null;
                }else{
                    l.setAlquilado(true);
                    alquiler.setVigente(true);
                    alquiler.setFecha(new Date().getTime());
                    updateLibro(l);
                    return alquilerRepositorio.save(alquiler);

                }
            }
        }
        return null;
    }*/


    public List<Object> createAlquiler(Alquiler alquiler){
        for(Libro l : getLibros()){
            if(l.getId() == alquiler.getIdLibro()){
                if(l.isAlquilado()){
                    return null;
                }else{
                    l.setAlquilado(true);
                    alquiler.setVigente(true);
                    alquiler.setFecha(new Date().getTime());
                    updateLibro(l);
                    alquilerRepositorio.save(alquiler);

                    ArrayList<Object> list = new ArrayList<>();
                    list.add(getAlquileres());
                    list.add(getLibros());
                    return list;
                }
            }
        }
        return null;
    }

    public List<Alquiler> getAlquileres(){
        return alquilerRepositorio.findAll();
    }

    public Alquiler getAlquilerById(Long id){
        return alquilerRepositorio.findById(id).orElse(null);
    }

    public List<Object> updateAlquiler(Alquiler upAlquiler){
        for(Alquiler a : getAlquileres()){
            if(a.getId() == upAlquiler.getId()){
                for(Libro l : getLibros()){
                    if(l.getId() == a.getIdLibro()){
                        if(!l.isAlquilado()){
                            return null;
                        }else{
                            l.setAlquilado(false);
                            updateLibro(l);
                            upAlquiler.setVigente(false);
                            upAlquiler.setFechaDevolucion(new Date().getTime());
                           // updateAlquiler(upAlquiler);
                           alquilerRepositorio.save(upAlquiler);

                            ArrayList<Object> list = new ArrayList<>();
                            list.add(getAlquileres());
                            list.add(getLibros());
                            return list;

                        }
                    }
                }
            }
        }
        return null;
    }

    public List<Alquiler> deleteAlquiler(Long id){
        for(Alquiler a : getAlquileres()){
            if(a.getId() == id){
                alquilerRepositorio.deleteById(id);
                return getAlquileres();
            }
        }
        return null;
    }


//    public List<Socio> listaSocios = new ArrayList<>(Arrays.asList(
//            new Socio(1, "María"),
//            new Socio(2, "Laura"),
//            new Socio(3, "Jorge"),
//            new Socio(4, "Víctor")
//    ));
//    public List<Libro> listaLibros = new ArrayList<>(Arrays.asList(
//            new Libro(101, "Clean code", false),
//            new Libro(102, "Code complete", false),
//            new Libro(103, "Java a fondo", false),
//            new Libro(104, "Lógica de programación", false)
//    ));
//    public List<Alquiler> listaAlquiler = new ArrayList<>();




}
