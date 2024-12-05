package pe.gino1nobelio.proyecto_citas_optima.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pe.gino1nobelio.proyecto_citas_optima.entities.UsuarioInfo;

@Repository
public interface IUsuarioInfoRepository extends JpaRepository<UsuarioInfo, Long> {
}
