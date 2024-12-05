package pe.gino1nobelio.proyecto_citas_optima.services.Imp;

import org.springframework.stereotype.Service;
import pe.gino1nobelio.proyecto_citas_optima.dto.UsuarioResponseDto;
import pe.gino1nobelio.proyecto_citas_optima.repository.IUsuarioRepository;
import pe.gino1nobelio.proyecto_citas_optima.services.UsuarioService;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class IUsuarioService implements UsuarioService {

    private final IUsuarioRepository usuarioRepository;

    public IUsuarioService(IUsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    @Override
    public List<UsuarioResponseDto> listarClientes() {
        return usuarioRepository.findUsuariosByRolNombre("CLIENTE").stream().map(
                usuario -> UsuarioResponseDto.builder()
                        .id(usuario.getIdUsuario())
                        .nombres(usuario.getUsuarioInfo().getNombres())
                        .apellidos(usuario.getUsuarioInfo().getApellidos())
                        .correo(usuario.getUsuarioInfo().getCorreo())
                        .celular(usuario.getUsuarioInfo().getCelular())
                        .direccion(usuario.getUsuarioInfo().getDireccion())
                        .build()
        ).collect(Collectors.toList());
    }

    @Override
    public List<UsuarioResponseDto> listarAgentes() {
        return usuarioRepository.findUsuariosByRolNombre("AGENTE").stream().map(
                usuario -> UsuarioResponseDto.builder()
                        .id(usuario.getIdUsuario())
                        .nombres(usuario.getUsuarioInfo().getNombres())
                        .apellidos(usuario.getUsuarioInfo().getApellidos())
                        .correo(usuario.getUsuarioInfo().getCorreo())
                        .celular(usuario.getUsuarioInfo().getCelular())
                        .direccion(usuario.getUsuarioInfo().getDireccion())
                        .build()
        ).collect(Collectors.toList());
    }
}
