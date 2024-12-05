package pe.gino1nobelio.proyecto_citas_optima.services;

import pe.gino1nobelio.proyecto_citas_optima.dto.CitaDto;
import pe.gino1nobelio.proyecto_citas_optima.dto.CrearCitaDto;

import java.util.List;

public interface CitaService {
    boolean crearCita(CrearCitaDto crearCitaDto);
    boolean asignarCita(Long idCita, Long idAgente);
    boolean tomaCita(Long idCita);
    boolean cerrarCita(Long idCita);
    boolean reabrir(Long idCita);
    boolean eliminar(Long idCita);
    List<CitaDto> listarCitas();
    CitaDto cita(Long idCita);
}
