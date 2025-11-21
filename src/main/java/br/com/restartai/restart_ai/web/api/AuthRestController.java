package br.com.restartai.restart_ai.web.api;

import br.com.restartai.restart_ai.domain.Usuario;
import br.com.restartai.restart_ai.dto.usuario.UsuarioLoginDTO;
import br.com.restartai.restart_ai.dto.usuario.UsuarioRespostaDTO;
import br.com.restartai.restart_ai.service.UsuarioService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Tag(name = "Autenticação", description = "Login de usuários do Restart.Ai")
@RestController
@RequestMapping("/api/auth")
public class AuthRestController {

    private final UsuarioService usuarioService;

    public AuthRestController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @Operation(
            summary = "Login de usuário",
            description = "Autentica um usuário usando e-mail e senha.",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Login realizado com sucesso",
                            content = @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = UsuarioRespostaDTO.class))
                    ),
                    @ApiResponse(
                            responseCode = "400",
                            description = "Credenciais inválidas",
                            content = @Content
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "Usuário não encontrado",
                            content = @Content
                    )
            }
    )
    @PostMapping("/login")
    public ResponseEntity<UsuarioRespostaDTO> login(@Valid @RequestBody UsuarioLoginDTO dto) {
        Usuario usuario = usuarioService.autenticar(dto.getEmail(), dto.getSenha());
        UsuarioRespostaDTO resposta = toRespostaDTO(usuario);
        return ResponseEntity.status(HttpStatus.OK).body(resposta);
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
