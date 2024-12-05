package pe.gino1nobelio.proyecto_citas_optima.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pe.gino1nobelio.proyecto_citas_optima.services.UsuarioService;

@RestController
@RequestMapping("usuario")
public class UsuarioController {

    private final UsuarioService usuarioService;

    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @GetMapping("listar-clientes")
    public ResponseEntity<?> listarCliente() {
        return ResponseEntity.ok().body(
                usuarioService.listarClientes()
        );
    }

    @GetMapping("listar-agentes")
    public ResponseEntity<?> listarAgentes() {
        return ResponseEntity.ok().body(
                usuarioService.listarAgentes()
        );
    }
}
