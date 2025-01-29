package library.library.persistence.entity;

import jakarta.persistence.*;
import lombok.Builder;
import jakarta.validation.constraints.NotBlank;

import java.util.Set;


/** Esta clase se encarga de manejar los
 * usuarios en la bbdd
 * */
@Builder
//Registra la entidad
@Entity
//Table permite mapear nuestra entidad contra una tabla que tenga el mismo nombre
@Table(name = "usuario")
public class UserEntity {

    //Campos que hay que mapear para que Spring Security los entienda
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotBlank
    private String username;

    @NotBlank
    private String password;

    @NotBlank
    private String email;


    /* Set no permite datos repetidos
        mapear la relación muchos a muchos
        FetchType.EAGER cuando llama un usuario carga todos sus datos
        targetEntity, con qué entidad establece la relación
        CascadeType.ALL cuando guarda un usuario en la tabla, guarda todos los roles asociados
        CascadeType.PESIST, al ingresar datos en la bbdd añade los roles de una vez, pero al
        eliminar el usuario no elimina los roles de la tabla roles
    */
    @ManyToMany(fetch = FetchType.EAGER, targetEntity = RoleEntity.class, cascade = CascadeType.PERSIST)
    @JoinTable(name = "usuario_roles",
            joinColumns = @JoinColumn(name = "usuario_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    private Set<RoleEntity> roles;

    public UserEntity() {
    }

    public UserEntity(int id, String username, String password, String email, Set<RoleEntity> roles) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.email = email;
        this.roles = roles;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public @NotBlank String getUsername() {
        return username;
    }

    public void setUsername(@NotBlank String username) {
        this.username = username;
    }

    public @NotBlank String getPassword() {
        return password;
    }

    public void setPassword(@NotBlank String password) {
        this.password = password;
    }

    public @NotBlank String getEmail() {
        return email;
    }

    public void setEmail(@NotBlank String email) {
        this.email = email;
    }

    public Set<RoleEntity> getRoles() {
        return roles;
    }

    public void setRoles(Set<RoleEntity> roles) {
        this.roles = roles;
    }
}
