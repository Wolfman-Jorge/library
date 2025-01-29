package library.library.persistence.repositorio;

import library.library.modelo.Socio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SocioRepositorio extends JpaRepository<Socio, Long> {
}
