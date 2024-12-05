package pe.gino1nobelio.proyecto_citas_optima.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import pe.gino1nobelio.proyecto_citas_optima.entities.Usuario;

import java.util.List;
import java.util.Optional;

@Repository
public interface IUsuarioRepository extends JpaRepository<Usuario, Long> {
    Optional<Usuario> findByUsuario(String usuario);

    @Query("SELECT u FROM Usuario u JOIN u.rol r WHERE r.nombre = :nombreRol")
    List<Usuario> findUsuariosByRolNombre(@Param("nombreRol") String nombreRol);

    boolean existsByUsuario(String usuario);
}
