package library.library.persistence.repositorio;

import library.library.persistence.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

//Hereda de la interfaz JpaRepository
@Repository
public interface UsuarioRepository extends JpaRepository<UserEntity, Long> {

    //Crea la sentencia a partir del nombre del método
    //puede devolver un objeto o devolver null
    Optional<UserEntity> findUserEntityByUsername(String name);
}
