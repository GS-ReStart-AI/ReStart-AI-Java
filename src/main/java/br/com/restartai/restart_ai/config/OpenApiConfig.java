package br.com.restartai.restart_ai.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI restartAiOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Restart.Ai API")
                        .description("API para realocação de pessoas no mercado de trabalho usando IA e análise de currículos em PDF.")
                        .version("v1")
                        .contact(new Contact()
                                .name("Restart.Ai")
                                .email("contato@restart.ai"))
                        .license(new License()
                                .name("Apache 2.0")
                                .url("https://www.apache.org/licenses/LICENSE-2.0.html")));
    }
}
