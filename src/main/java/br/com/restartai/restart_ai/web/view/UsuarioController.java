package br.com.restartai.restart_ai.web.view;

import br.com.restartai.restart_ai.dto.usuario.UsuarioCadastroDTO;
import br.com.restartai.restart_ai.service.UsuarioService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class UsuarioController {

    private final UsuarioService usuarioService;

    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @ModelAttribute("usuario")
    public UsuarioCadastroDTO usuarioModel() {
        return new UsuarioCadastroDTO();
    }

    @GetMapping("/usuarios/cadastro")
    public String mostrarFormularioCadastro() {
        return "usuarios/cadastro";
    }

    @PostMapping("/usuarios/cadastro")
    public String processarCadastro(@Valid @ModelAttribute("usuario") UsuarioCadastroDTO dto,
                                    BindingResult result,
                                    RedirectAttributes redirectAttributes) {
        if (result.hasErrors()) {
            return "usuarios/cadastro";
        }

        try {
            usuarioService.registrar(dto);
            redirectAttributes.addFlashAttribute("mensagemSucesso", "Cadastro realizado com sucesso.");
            return "redirect:/login";
        } catch (IllegalArgumentException ex) {
            result.reject("erro.negocio", ex.getMessage());
            return "usuarios/cadastro";
        }
    }

    @GetMapping("/login")
    public String telaLogin() {
        return "usuarios/login";
    }
}
