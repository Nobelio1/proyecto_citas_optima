package pe.gino1nobelio.proyecto_citas_optima.services;

import pe.gino1nobelio.proyecto_citas_optima.dto.UsuarioResponseDto;

import java.util.List;

public interface UsuarioService {
    List<UsuarioResponseDto> listarClientes();
    List<UsuarioResponseDto> listarAgentes();
}
