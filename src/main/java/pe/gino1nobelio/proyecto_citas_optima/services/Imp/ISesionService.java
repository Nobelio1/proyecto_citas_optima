package pe.gino1nobelio.proyecto_citas_optima.services.Imp;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import pe.gino1nobelio.proyecto_citas_optima.dto.LoginRequestDto;
import pe.gino1nobelio.proyecto_citas_optima.dto.LoginResponseDto;
import pe.gino1nobelio.proyecto_citas_optima.dto.RegisterRequestDto;
import pe.gino1nobelio.proyecto_citas_optima.entities.Rol;
import pe.gino1nobelio.proyecto_citas_optima.entities.Usuario;
import pe.gino1nobelio.proyecto_citas_optima.entities.UsuarioInfo;
import pe.gino1nobelio.proyecto_citas_optima.repository.IRolRepository;
import pe.gino1nobelio.proyecto_citas_optima.repository.IUsuarioInfoRepository;
import pe.gino1nobelio.proyecto_citas_optima.repository.IUsuarioRepository;
import pe.gino1nobelio.proyecto_citas_optima.security.JwtGenerador;
import pe.gino1nobelio.proyecto_citas_optima.services.SesionService;

import java.util.Collections;

@Service
public class ISesionService implements SesionService {

    private final AuthenticationManager authenticationManager;
    private final PasswordEncoder passwordEncoder;
    private final JwtGenerador jwtGenerador;
    private final IRolRepository rolRepository;
    private final IUsuarioRepository usuarioRepository;

    public ISesionService(
            AuthenticationManager authenticationManager,
            PasswordEncoder passwordEncoder,
            JwtGenerador jwtGenerador,
            IRolRepository rolRepository,
            IUsuarioRepository usuarioRepository) {
        this.authenticationManager = authenticationManager;
        this.passwordEncoder = passwordEncoder;
        this.jwtGenerador = jwtGenerador;
        this.rolRepository = rolRepository;
        this.usuarioRepository = usuarioRepository;
    }

    @Override
    public LoginResponseDto login(LoginRequestDto loginRequestDto) {
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                loginRequestDto.getUsuario(), loginRequestDto.getContrasena()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String token = jwtGenerador.generarToken(authentication);

        return LoginResponseDto.builder()
                .accessToken(token)
                .tokenType("Bearer")
                .build();
    }

    @Override
    public boolean register(RegisterRequestDto registerRequestDto) {
        if (usuarioRepository.existsByUsuario(registerRequestDto.getUsuario())) {
            return false;
        }

        Usuario usuario = new Usuario();
        usuario.setUsuario(registerRequestDto.getUsuario());
        usuario.setContrasena(passwordEncoder.encode(registerRequestDto.getContrasena()));
        Rol rol = rolRepository.findByNombre(registerRequestDto.getRol())
                .orElseThrow(() -> new RuntimeException("Rol no encontrado."));
        usuario.setRol(Collections.singletonList(rol));

        UsuarioInfo usuarioInfo = new UsuarioInfo();
        usuarioInfo.setNombres(registerRequestDto.getNombres());
        usuarioInfo.setApellidos(registerRequestDto.getApellidos());
        usuarioInfo.setCorreo(registerRequestDto.getCorreo());
        usuarioInfo.setCelular(registerRequestDto.getCelular());
        usuarioInfo.setDireccion(registerRequestDto.getDireccion());

        usuario.setUsuarioInfo(usuarioInfo);
        usuarioInfo.setUsuario(usuario);

        usuarioRepository.save(usuario);
        return true;
    }
}
