package pe.gino1nobelio.proyecto_citas_optima.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pe.gino1nobelio.proyecto_citas_optima.dto.LoginRequestDto;
import pe.gino1nobelio.proyecto_citas_optima.dto.LoginResponseDto;
import pe.gino1nobelio.proyecto_citas_optima.dto.MessageReponseDto;
import pe.gino1nobelio.proyecto_citas_optima.dto.RegisterRequestDto;
import pe.gino1nobelio.proyecto_citas_optima.services.SesionService;

@RestController
@RequestMapping("sesion")
public class SesionController {

    private final SesionService sesionService;

    public SesionController(SesionService sesionService) {
        this.sesionService = sesionService;
    }

    @PostMapping("login")
    public ResponseEntity<?> login(@RequestBody LoginRequestDto loginRequestDto) {
        LoginResponseDto loginResponseDto = sesionService.login(loginRequestDto);
        return ResponseEntity.ok(loginResponseDto);
    }

    @PostMapping("register")
    public ResponseEntity<?> register(@RequestBody RegisterRequestDto registerRequestDto) {
        try {
            boolean result = sesionService.register(registerRequestDto);
            if (result) {
                return ResponseEntity.status(HttpStatus.OK).body(
                        MessageReponseDto.builder().message("Usuario registrado correctamente").build()
                );
            }
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                    MessageReponseDto.builder().message("No se pudo registrar el usuario").build()
            );
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

}
