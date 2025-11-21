package br.com.restartai.restart_ai.service;

import br.com.restartai.restart_ai.domain.Curriculo;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.stereotype.Service;

@Service
public class AnaliseCurriculoIaService {

    private final CurriculoService curriculoService;
    private final ChatClient chatClient;

    public AnaliseCurriculoIaService(CurriculoService curriculoService, ChatClient.Builder chatClientBuilder) {
        this.curriculoService = curriculoService;
        this.chatClient = chatClientBuilder.build();
    }

    public String analisarCurriculo(Long curriculoId) {
        Curriculo curriculo = curriculoService.buscarPorId(curriculoId);
        if (curriculo.getTexto() == null || curriculo.getTexto().isBlank()) {
            throw new IllegalStateException("Currículo sem texto extraído para análise.");
        }

        String prompt = """
                Você é um especialista em carreira e mercado de trabalho no Brasil.

                Abaixo está o texto de um currículo. Com base nele, analise:

                - Um resumo do perfil profissional
                - Principais competências e habilidades
                - Áreas de atuação atuais prováveis
                - Três a cinco áreas alternativas para migração de carreira, com breve justificativa

                Responda em português, de forma clara e organizada em seções.

                CURRÍCULO:
                %s
                """.formatted(curriculo.getTexto());

        return chatClient.prompt()
                .user(prompt)
                .call()
                .content();
    }
}
