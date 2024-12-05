package pe.gino1nobelio.proyecto_citas_optima.services.Imp;

import org.springframework.stereotype.Service;
import pe.gino1nobelio.proyecto_citas_optima.dto.CitaDto;
import pe.gino1nobelio.proyecto_citas_optima.dto.CrearCitaDto;
import pe.gino1nobelio.proyecto_citas_optima.entities.Cita;
import pe.gino1nobelio.proyecto_citas_optima.entities.Usuario;
import pe.gino1nobelio.proyecto_citas_optima.repository.ICitaRepository;
import pe.gino1nobelio.proyecto_citas_optima.repository.IUsuarioRepository;
import pe.gino1nobelio.proyecto_citas_optima.services.CitaService;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ICitaService implements CitaService {

    private final ICitaRepository citaRepository;
    private final IUsuarioRepository usuarioRepository;

    public ICitaService(ICitaRepository citaRepository, IUsuarioRepository usuarioRepository) {
        this.citaRepository = citaRepository;
        this.usuarioRepository = usuarioRepository;
    }

    @Override
    public boolean crearCita(CrearCitaDto crearCitaDto) {
        Usuario usuario = usuarioRepository.findById(crearCitaDto.getIdCliente()).orElseThrow(
                () -> new RuntimeException("Usuario no encontrado")
        );

        Cita cita = new Cita();
        cita.setCliente(usuario);
        cita.setProyecto(crearCitaDto.getProyecto());
        cita.setConsulta(crearCitaDto.getConsulta());
        cita.setEstado("PENDIENTE");
        cita.setFechaCreacion(LocalDateTime.now());

        usuario.getCitasComoCliente().add(cita);
        usuarioRepository.save(usuario);
        return true;
    }

    @Override
    public boolean asignarCita(Long idCita, Long idAgente) {
        if (!citaRepository.existsById(idCita)) {
            return false;
        }
        Cita citaExistente = citaRepository.findById(idCita).orElseThrow(
                () -> new RuntimeException("Cita no encontrada")
        );

        Usuario agente = usuarioRepository.findById(idAgente).orElseThrow(
                () -> new RuntimeException("Agente no encontrado")
        );

        citaExistente.setAgente(agente);
        citaExistente.setFechaAsignacion(LocalDateTime.now());
        citaRepository.save(citaExistente);
        return true;
    }

    @Override
    public boolean tomaCita(Long idCita) {
        if (!citaRepository.existsById(idCita)) {
            return false;
        }
        Cita citaExistente = citaRepository.findById(idCita).orElseThrow(
                () -> new RuntimeException("Cita no encontrada")
        );

        citaExistente.setEstado("EN CURSO");
        citaRepository.save(citaExistente);
        return true;
    }

    @Override
    public boolean cerrarCita(Long idCita) {
        if (!citaRepository.existsById(idCita)) {
            return false;
        }
        Cita citaExistente = citaRepository.findById(idCita).orElseThrow(
                () -> new RuntimeException("Cita no encontrada")
        );

        citaExistente.setEstado("COMPLETADO");
        citaExistente.setFechaCierre(LocalDateTime.now());
        citaRepository.save(citaExistente);
        return true;
    }

    @Override
    public boolean reabrir(Long idCita) {
        if (!citaRepository.existsById(idCita)) {
            return false;
        }
        Cita citaExistente = citaRepository.findById(idCita).orElseThrow(
                () -> new RuntimeException("Cita no encontrada")
        );

        citaExistente.setEstado("EN CURSO");
        citaExistente.setFechaReactivacion(LocalDateTime.now());
        citaRepository.save(citaExistente);
        return true;
    }

    @Override
    public boolean eliminar(Long idCita) {
        if (citaRepository.existsById(idCita)) {
            citaRepository.deleteById(idCita);
            return true;
        }
        return false;
    }

    @Override
    public List<CitaDto> listarCitas() {
        return citaRepository.findAll().stream().map(
                cita -> CitaDto.builder()
                        .id(cita.getIdCita())
                        .idCliente(cita.getCliente().getIdUsuario())
                        .idAgente(cita.getAgente().getIdUsuario())
                        .proyecto(cita.getProyecto())
                        .consulta(cita.getConsulta())
                        .estado(cita.getEstado())
                        .fechaCreacion(cita.getFechaCreacion())
                        .fechaAsignacion(cita.getFechaAsignacion())
                        .fechaReactivacion(cita.getFechaReactivacion())
                        .fechaCierre(cita.getFechaCierre())
                        .build()
        ).collect(Collectors.toList());
    }

    @Override
    public CitaDto cita(Long idCita) {

        Cita cita = citaRepository.findById(idCita).orElseThrow(
                () -> new RuntimeException("Cita no encontrada")
        );
        return CitaDto.builder()
                .id(cita.getIdCita())
                .idCliente(cita.getCliente().getIdUsuario())
                .idAgente(cita.getAgente() != null ? cita.getAgente().getIdUsuario() : 0)
                .proyecto(cita.getProyecto())
                .consulta(cita.getConsulta())
                .estado(cita.getEstado())
                .fechaCreacion(cita.getFechaCreacion())
                .fechaAsignacion(cita.getFechaAsignacion())
                .fechaReactivacion(cita.getFechaReactivacion())
                .fechaCierre(cita.getFechaCierre())
                .build();
    }
}
