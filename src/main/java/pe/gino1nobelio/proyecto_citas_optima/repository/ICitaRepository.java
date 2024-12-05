package pe.gino1nobelio.proyecto_citas_optima.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pe.gino1nobelio.proyecto_citas_optima.entities.Cita;

@Repository
public interface ICitaRepository extends JpaRepository<Cita, Long> {
}
