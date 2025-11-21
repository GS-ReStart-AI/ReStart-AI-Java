package br.com.restartai.restart_ai.web.api;

import br.com.restartai.restart_ai.domain.Curriculo;
import br.com.restartai.restart_ai.dto.curriculo.CurriculoCadastroDTO;
import br.com.restartai.restart_ai.dto.curriculo.CurriculoRespostaDTO;
import br.com.restartai.restart_ai.service.CurriculoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@Tag(name = "2. Currículos", description = "CRUD de currículos de usuários")
@RestController
@RequestMapping("/api/curriculos")
public class CurriculoRestController {

    private final CurriculoService curriculoService;

    public CurriculoRestController(CurriculoService curriculoService) {
        this.curriculoService = curriculoService;
    }

    @Operation(summary = "Listar currículos (paginado)")
    @GetMapping
    public Page<CurriculoRespostaDTO> listar(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "id,asc") String sort) {

        String[] sortParts = sort.split(",");
        String sortField = sortParts[0];
        String sortDirection = sortParts.length > 1 ? sortParts[1] : "asc";

        Sort.Direction direction = Sort.Direction.fromString(sortDirection);
        Pageable pageable = PageRequest.of(page, size, Sort.by(direction, sortField));

        return curriculoService.listar(pageable).map(this::toRespostaDTO);
    }

    @Operation(summary = "Buscar currículo por ID")
    @GetMapping("/{id}")
    public ResponseEntity<CurriculoRespostaDTO> buscarPorId(@PathVariable Long id) {
        Curriculo curriculo = curriculoService.buscarPorId(id);
        return ResponseEntity.ok(toRespostaDTO(curriculo));
    }

    @Operation(summary = "Cadastrar novo currículo")
    @PostMapping
    public ResponseEntity<CurriculoRespostaDTO> criar(@Valid @RequestBody CurriculoCadastroDTO dto) {
        Curriculo curriculo = curriculoService.criar(dto);
        CurriculoRespostaDTO resposta = toRespostaDTO(curriculo);
        var uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(resposta.getId())
                .toUri();
        return ResponseEntity.created(uri).body(resposta);
    }

    @Operation(summary = "Atualizar currículo existente")
    @PutMapping("/{id}")
    public ResponseEntity<CurriculoRespostaDTO> atualizar(@PathVariable Long id,
                                                          @Valid @RequestBody CurriculoCadastroDTO dto) {
        Curriculo curriculo = curriculoService.atualizar(id, dto);
        return ResponseEntity.ok(toRespostaDTO(curriculo));
    }

    @Operation(summary = "Excluir currículo")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluir(@PathVariable Long id) {
        curriculoService.excluir(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    private CurriculoRespostaDTO toRespostaDTO(Curriculo curriculo) {
        CurriculoRespostaDTO dto = new CurriculoRespostaDTO();
        dto.setId(curriculo.getId());
        dto.setUsuarioId(curriculo.getUsuario().getId());
        dto.setTipo(curriculo.getTipo());
        dto.setNomeArquivo(curriculo.getNomeArquivo());
        dto.setTamanhoBytes(curriculo.getTamanhoBytes());
        dto.setArquivoUrl(curriculo.getArquivoUrl());
        dto.setTexto(curriculo.getTexto());
        dto.setStatus(curriculo.getStatus());
        dto.setAnalisadoEm(curriculo.getAnalisadoEm());
        dto.setCriadoEm(curriculo.getCriadoEm());
        return dto;
    }
}
