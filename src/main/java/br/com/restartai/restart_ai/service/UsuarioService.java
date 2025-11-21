package br.com.restartai.restart_ai.service;

import br.com.restartai.restart_ai.domain.Usuario;
import br.com.restartai.restart_ai.dto.UsuarioCadastroDTO;
import br.com.restartai.restart_ai.repository.UsuarioRepository;
import java.time.LocalDateTime;
import java.util.UUID;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;

    public UsuarioService(UsuarioRepository usuarioRepository,
                          PasswordEncoder passwordEncoder) {
        this.usuarioRepository = usuarioRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public Usuario registrar(UsuarioCadastroDTO dto) {
        if (!dto.getSenha().equals(dto.getConfirmarSenha())) {
            throw new IllegalArgumentException("As senhas não conferem.");
        }

        if (usuarioRepository.existsByEmail(dto.getEmail())) {
            throw new IllegalArgumentException("E-mail já cadastrado.");
        }

        if (usuarioRepository.existsByCpf(dto.getCpf())) {
            throw new IllegalArgumentException("CPF já cadastrado.");
        }

        String salt = UUID.randomUUID().toString();
        String senhaHash = passwordEncoder.encode(dto.getSenha() + salt);

        Usuario usuario = new Usuario();
        usuario.setNomeCompleto(dto.getNomeCompleto());
        usuario.setCpf(dto.getCpf());
        usuario.setDataNascimento(dto.getDataNascimento());
        usuario.setEmail(dto.getEmail());
        usuario.setSenhaSalt(salt);
        usuario.setSenhaHash(senhaHash);
        usuario.setApplyClicksHoje(0);
        usuario.setJobsViewedHoje(0);
        usuario.setCriadoEm(LocalDateTime.now());

        return usuarioRepository.save(usuario);
    }

    public Usuario buscarPorEmail(String email) {
        return usuarioRepository.findByEmail(email).orElse(null);
    }
}
