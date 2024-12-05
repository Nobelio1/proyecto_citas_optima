package pe.gino1nobelio.proyecto_citas_optima.dto;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class CitaDto {
    private Long id;
    private Long idCliente;
    private Long idAgente;
    private String proyecto;
    private String consulta;
    private String estado;
    private LocalDateTime fechaCreacion;
    private LocalDateTime fechaAsignacion;
    private LocalDateTime fechaReactivacion;
    private LocalDateTime fechaCierre;

}
