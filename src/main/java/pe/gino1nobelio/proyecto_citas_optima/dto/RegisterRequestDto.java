package pe.gino1nobelio.proyecto_citas_optima.dto;

import lombok.Data;

@Data
public class RegisterRequestDto {
    private String usuario;
    private String contrasena;
    private String rol;
    private String nombres;
    private String apellidos;
    private String correo;
    private Integer celular;
    private String direccion;
}
