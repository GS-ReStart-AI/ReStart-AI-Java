# 🪀 ReStart.AI – Recolocação Profissional Inteligente

A **ReStart.AI** é uma aplicação pensada para ajudar pessoas a se realocarem no mercado de trabalho em um cenário de mudanças rápidas trazidas pela IA. Em vez de começar uma carreira do zero, o sistema analisa as **habilidades que você já possui** e indica caminhos de carreira compatíveis, com **vagas e cargos alinhados ao seu perfil**.

Com poucos cliques, você cadastra seu currículo, a plataforma analisa seu perfil e entrega recomendações inteligentes de áreas e oportunidades.

---

## 🔗 Links Importantes

- 🎥 Pitch do projeto: **[Assista ao pitch](https://youtu.be/rePcFQ3a4aI)**  
- 🎥 Video Demonstrativo: **[Assista ao video](https://youtu.be/Tny-OqQ1nF4?si=f1usM7mStxXClzg7)**  
- 🌐 Deploy (aplicação online): **[Acesse a ReStart.AI](https://restart-rm558191.azurewebsites.net/)**  
- 📚 Documentação da API (Swagger/OpenAPI): **[Ver documentação](https://restart-rm558191.azurewebsites.net/swagger-ui/index.html)**

---

## 🚀 Visão Geral da Solução

A ReStart.AI:

- Recebe os dados do currículo do usuário (formulário estruturado).
- Analisa o perfil profissional e habilidades.
- Sugere:
  - áreas de atuação compatíveis;
  - áreas alternativas para migração;
  - cargos e vagas que mais combinam com o perfil.

A partir disso, o usuário pode navegar pelas vagas recomendadas e se candidatar às melhores oportunidades.

---

## 🧠 Saída da Análise de Currículo

A análise do currículo gera uma visão estruturada do perfil do usuário, organizada em quatro blocos principais:

### 1. Resumo do Perfil Profissional

Descrição em linguagem natural do perfil da pessoa, incluindo:

- nível de senioridade provável;
- principais áreas de conhecimento;
- contexto de experiência (setores, tipos de empresas, tempo de atuação);
- estilo de atuação (mais generalista, mais técnico, mais de gestão etc.).

### 2. Principais Competências e Habilidades

Lista das competências-chave identificadas no currículo, como:

- linguagens, ferramentas e tecnologias;
- soft skills relevantes (comunicação, liderança, resolução de problemas);
- certificações e formações que se destacam;
- habilidades que geram mais valor de mercado.

### 3. Áreas de Atuação Atuais Prováveis

Sugestão das áreas em que a pessoa **provavelmente atua hoje**, por exemplo:

- Desenvolvimento de Software;
- Suporte Técnico / Infraestrutura;
- Análise de Dados;
- Atendimento / Customer Success;
- Gestão de Projetos.

Cada área pode ser acompanhada de uma explicação curta de por que foi sugerida.

### 4. Áreas Alternativas para Migração de Carreira

Sugestão de **novas áreas** para as quais a pessoa pode migrar aproveitando a base de habilidades já existente, como:

- transição de desenvolvimento backend para análise de dados;
- de suporte técnico para segurança da informação;
- de analista de negócios para product owner.

Para cada área alternativa, são sugeridos possíveis **cargos-alvo** e, idealmente, os **gaps de conhecimento** a serem preenchidos (skills recomendadas para estudo).

---

## 🏗️ Arquitetura e Tecnologias

A aplicação foi desenvolvida com foco em boas práticas de Java avançado e arquitetura em camadas.

**Backend:**

- Java 17  
- Spring Boot  
- Spring Web (APIs REST)  
- Spring Data JPA  
- Spring Security + JWT  
- Bean Validation (jakarta.validation)  
- Flyway (migrações de banco)  
- PostgreSQL  
- Testes com JUnit 5 + Mockito  
- Documentação com Springdoc OpenAPI (Swagger UI)

**Frontend (views):**

- Thymeleaf para renderização server-side  
- HTML5 + CSS3  
- Bootstrap 5 para layout e componentes  
- JavaScript para chamadas assíncronas (fetch API)

---

## 📂 Estrutura do Projeto

Estrutura básica de pacotes do backend:

- `br.com.restartai.restart_ai.config` – configurações gerais da aplicação  
- `br.com.restartai.restart_ai.domain` – entidades de domínio (Usuário, Vaga, Currículo etc.)  
- `br.com.restartai.restart_ai.dto` – DTOs de entrada/saída da API  
- `br.com.restartai.restart_ai.repository` – repositórios JPA  
- `br.com.restartai.restart_ai.service` – regras de negócio  
- `br.com.restartai.restart_ai.web.api` – controllers REST  
- `br.com.restartai.restart_ai.web.view` – controllers de página (Thymeleaf)  
- `br.com.restartai.restart_ai.security` – segurança, JWT, filtros e configurações

---

## ⚙️ Como Rodar o Projeto Localmente

### 1. Pré-requisitos

- JDK 17 instalado  
- PostgreSQL em execução  
- Gradle (ou usar o wrapper `./gradlew`)  

### 2. Configurar o banco de dados

Crie um database no PostgreSQL, por exemplo:

```sql
CREATE DATABASE restart_ai;
```

Configure as credenciais no `application.properties` ou `application.yml`, algo como:

```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/restart_ai
spring.datasource.username=seu_usuario
spring.datasource.password=sua_senha

spring.jpa.hibernate.ddl-auto=none
spring.flyway.enabled=true
```

### 3. Rodar a aplicação

No diretório do projeto:

```bash
./gradlew clean bootRun
```

Ou no Windows:

```bash
gradlew clean bootRun
```

A aplicação deverá subir em algo como:

```text
http://localhost:8080
```

### 4. Rodar os testes

```bash
./gradlew test
```

---

### Equipe 👥

* ⭐️ **Valéria Conceição Dos Santos** — RM: **557177**  
* ⭐️ **Mirela Pinheiro Silva Rodrigues** — RM: **558191**








