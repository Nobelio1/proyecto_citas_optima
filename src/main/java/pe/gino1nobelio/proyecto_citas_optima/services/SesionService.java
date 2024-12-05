package pe.gino1nobelio.proyecto_citas_optima.services;

import pe.gino1nobelio.proyecto_citas_optima.dto.LoginRequestDto;
import pe.gino1nobelio.proyecto_citas_optima.dto.LoginResponseDto;
import pe.gino1nobelio.proyecto_citas_optima.dto.RegisterRequestDto;

public interface SesionService {
    LoginResponseDto login(LoginRequestDto loginRequestDto);
    boolean register(RegisterRequestDto registerRequestDto);
}
