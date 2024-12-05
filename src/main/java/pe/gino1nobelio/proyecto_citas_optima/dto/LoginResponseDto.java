package pe.gino1nobelio.proyecto_citas_optima.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class LoginResponseDto {
    private String accessToken;
    private String tokenType = "Bearer ";
}
