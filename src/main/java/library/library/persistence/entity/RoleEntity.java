//package library.library.persistence.entity;
//
//
//import jakarta.persistence.*;
//import lombok.Builder;
//
//@Builder
//@Entity
//@Table(name = "role")
//public class RoleEntity {
//
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long id;
//
//    @Enumerated(EnumType.STRING)
//    private RoleEnum name;
//
//    public RoleEntity() {
//    }
//
//    public RoleEntity(Long id, RoleEnum name) {
//        this.id = id;
//        this.name = name;
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
//    public RoleEnum getName() {
//        return name;
//    }
//
//    public void setName(RoleEnum name) {
//        this.name = name;
//    }
//}
