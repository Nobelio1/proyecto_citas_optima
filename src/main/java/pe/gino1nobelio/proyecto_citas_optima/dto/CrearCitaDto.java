package pe.gino1nobelio.proyecto_citas_optima.dto;

import lombok.Data;

@Data
public class CrearCitaDto {
    private Long idCliente;
    private String proyecto;
    private String consulta;
}
