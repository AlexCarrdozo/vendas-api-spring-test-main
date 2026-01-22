package io.github.manoelcampos.vendas.api.feature;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.assertj.MockMvcTester;

import java.io.UncheckedIOException;

/**
 * Classe abstrata para implementar testes exclusivos para a camada de controller, realizando mock da camada service.
 * As classes filhas precisam ser anotadas com {@link WebMvcTest}, indicando qual controller será testado.
 * @author Manoel Campos
 * @see AbstractControllerIntegrationTest
 */
public abstract class AbstractControllerTest {
    /** @see #mockMvcTester() */
    @Autowired
    private MockMvcTester mockMvcTester;

    /**
     * Converte um objeto Java para sua representação em JSON.
     * @param object objeto a ser convertido
     * @return String com a representação em JSON do objeto Java
     */
    protected String objectToJson(final Object object)  {
        final var writer = new ObjectMapper().writer().withDefaultPrettyPrinter();
        try {
            return writer.writeValueAsString(object);
        } catch (final JsonProcessingException e) {
            throw new UncheckedIOException(e);
        }
    }

    /// Objeto que simula o envio de requisições HTTP para o controller
    /// que atende requisições numa determinada URL requisitada
    /// por meio do método [MockMvc#perform(RequestBuilder)].
    /// Assim, o controller estará sendo testado enviando-se requisições
    /// seguindo o formato do protocolo HTTP, mas nenhuma comunicação de rede estará realmente
    /// sendo feita. Todas as requisições serão processadas internamente pela aplicação
    /// para um objeto controller instanciado automaticamente.
    ///
    /// A classe [MockMvcTester] substitui a antiga [MockMvc].
    /// Ela facilita a escrita de testes para controllers Spring,
    /// possuindo uma API fluente como do {@link WebTestClient}
    /// (usado para testes de integração reativos) e facilita
    /// descobrir como usar tal API, pois não é preciso ficar fazendo imports
    /// diferentes para cada etapa do processo.
    ///
    /// Apesar do nome, o objeto não serve apenas para testar controllers
    /// do tipo [Spring MVC](https://spring.io/guides/gs/serving-web-content) anotados com `@Controller`
    /// (usados por exemplo com Thymeleaf), mas também para controllers REST anotados com `@RestController`.
    protected MockMvcTester mockMvcTester() {
        return mockMvcTester;
    }
}
