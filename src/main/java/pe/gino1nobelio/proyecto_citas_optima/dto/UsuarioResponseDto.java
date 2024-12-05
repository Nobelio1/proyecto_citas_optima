package pe.gino1nobelio.proyecto_citas_optima.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UsuarioResponseDto {
    private Long id;
    private String nombres;
    private String apellidos;
    private String correo;
    private Integer celular;
    private String direccion;
}
