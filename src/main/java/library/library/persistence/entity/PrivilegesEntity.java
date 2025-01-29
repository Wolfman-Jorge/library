//package library.library.persistence.entity;
//
//
//import jakarta.persistence.*;
//import lombok.Builder;
//
//@Builder
//@Entity
//@Table(name = "privileges")
//public class PrivilegesEntity {
//
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long id;
//
//    //Nombre único, no puede ser nulo, no se puede actualizar
//    @Column(unique = true, nullable = false, updatable = false)
//    private String nombre;
//
//    public PrivilegesEntity() {
//    }
//
//    public PrivilegesEntity(Long id, String nombre) {
//        this.id = id;
//        this.nombre = nombre;
//    }
//
//    public Long getId() {
//        return id;
//    }
//
//    public void setId(Long id) {
//        this.id = id;
//    }
//
//    public String getNombre() {
//        return nombre;
//    }
//
//    public void setNombre(String nombre) {
//        this.nombre = nombre;
//    }
//}
