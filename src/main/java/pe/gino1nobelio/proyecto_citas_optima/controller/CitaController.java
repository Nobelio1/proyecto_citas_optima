package pe.gino1nobelio.proyecto_citas_optima.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.gino1nobelio.proyecto_citas_optima.dto.CitaDto;
import pe.gino1nobelio.proyecto_citas_optima.dto.CrearCitaDto;
import pe.gino1nobelio.proyecto_citas_optima.dto.MessageReponseDto;
import pe.gino1nobelio.proyecto_citas_optima.services.CitaService;

@RestController
@RequestMapping("cita")
public class CitaController {

    private final CitaService citaService;

    public CitaController(CitaService citaService) {
        this.citaService = citaService;
    }

    @PostMapping("crear")
    public ResponseEntity<?> crearCita(@RequestBody CrearCitaDto crearCitaDto){
        try{
            citaService.crearCita(crearCitaDto);
            return ResponseEntity.ok().body(
                    MessageReponseDto.builder().message("Cita creada correctamente").build()
            );
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @PutMapping("asignar/{idCita}/{idAgente}")
    public ResponseEntity<?> asignarCita(@PathVariable Long idCita, @PathVariable Long idAgente){
        try {
            boolean result = citaService.asignarCita(idCita, idAgente);
            if (result) {
                return ResponseEntity.status(HttpStatus.OK).body(
                        MessageReponseDto.builder().message("Cita asignada correctamente").build()
                );
            }
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                    MessageReponseDto.builder().message("No se pudo asignar la cita").build()
            );
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @PutMapping("tomar/{id}")
    public ResponseEntity<?> tomaCita(@PathVariable Long id){
        try {
            boolean result = citaService.tomaCita(id);

            if (result) {
                return ResponseEntity.status(HttpStatus.OK).body(
                        MessageReponseDto.builder().message("Cita tomada correctamente").build()
                );
            }
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                    MessageReponseDto.builder().message("No se pudo tomar la cita").build()
            );
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @PutMapping("cerrar/{id}")
    public ResponseEntity<?> cerrarCita(@PathVariable Long id){
        try {
            boolean result = citaService.cerrarCita(id);

            if (result) {
                return ResponseEntity.status(HttpStatus.OK).body(
                        MessageReponseDto.builder().message("Cita cerrada correctamente").build()
                );
            }
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                    MessageReponseDto.builder().message("No se pudo cerrar la cita").build()
            );
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @PutMapping("reabrir/{id}")
    public ResponseEntity<?> reabrirCita(@PathVariable Long id){
        try {
            boolean result = citaService.reabrir(id);

            if (result) {
                return ResponseEntity.status(HttpStatus.OK).body(
                        MessageReponseDto.builder().message("Cita reabrierta correctamente").build()
                );
            }
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                    MessageReponseDto.builder().message("No se pudo reabrir la cita").build()
            );
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @DeleteMapping("eliminar/{id}")
    public ResponseEntity<?> eliminarCita(@PathVariable Long id){
        try {
            boolean result = citaService.eliminar(id);

            if (result) {
                return ResponseEntity.status(HttpStatus.OK).body(
                        MessageReponseDto.builder().message("Cita eliminada correctamente").build()
                );
            }
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                    MessageReponseDto.builder().message("No se pudo eliminar la cita").build()
            );
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @GetMapping("lista")
    public ResponseEntity<?> listarCitas(){
        return ResponseEntity.ok().body(
                citaService.listarCitas()
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> CitaPorId(@PathVariable Long id){
        try {
            CitaDto result = citaService.cita(id);
            return ResponseEntity.status(HttpStatus.OK).body(result);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }



}
