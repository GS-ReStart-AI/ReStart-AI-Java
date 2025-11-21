package br.com.restartai.restart_ai.web.api;

import br.com.restartai.restart_ai.domain.Usuario;
import br.com.restartai.restart_ai.dto.UsuarioCadastroDTO;
import br.com.restartai.restart_ai.dto.UsuarioRespostaDTO;
import br.com.restartai.restart_ai.service.UsuarioService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Tag(name = "Usuários", description = "Cadastro e consulta de usuários do Restart.Ai")
@RestController
@RequestMapping("/api/usuarios")
public class UsuarioRestController {

    private final UsuarioService usuarioService;

    public UsuarioRestController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @Operation(summary = "Cadastrar novo usuário")
    @PostMapping
    public ResponseEntity<UsuarioRespostaDTO> registrar(@Valid @RequestBody UsuarioCadastroDTO dto) {
        Usuario usuario = usuarioService.registrar(dto);
        UsuarioRespostaDTO resposta = toRespostaDTO(usuario);
        return ResponseEntity.status(HttpStatus.CREATED).body(resposta);
    }

    @Operation(summary = "Buscar usuário por e-mail")
    @GetMapping("/por-email")
    public ResponseEntity<UsuarioRespostaDTO> buscarPorEmail(@RequestParam String email) {
        Usuario usuario = usuarioService.buscarPorEmail(email);
        if (usuario == null) {
            return ResponseEntity.notFound().build();
        }
        UsuarioRespostaDTO resposta = toRespostaDTO(usuario);
        return ResponseEntity.ok(resposta);
    }

    private UsuarioRespostaDTO toRespostaDTO(Usuario usuario) {
        UsuarioRespostaDTO dto = new UsuarioRespostaDTO();
        dto.setId(usuario.getId());
        dto.setNomeCompleto(usuario.getNomeCompleto());
        dto.setCpf(usuario.getCpf());
        dto.setDataNascimento(usuario.getDataNascimento());
        dto.setEmail(usuario.getEmail());
        return dto;
    }
}
