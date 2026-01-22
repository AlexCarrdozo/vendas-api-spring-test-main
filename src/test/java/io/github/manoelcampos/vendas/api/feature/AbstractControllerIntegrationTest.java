package io.github.manoelcampos.vendas.api.feature;

import lombok.Getter;
import lombok.experimental.Accessors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.test.web.reactive.server.WebTestClient;

/// Classe abstrata para a criação de testes de integração de APIs REST,
/// que não usa mocks, testando todas as camadas da aplicação de forma integrada.
///
/// @author Manoel Campos
/// @link [WebClient and WebClientTest](https://www.baeldung.com/spring-5-webclient)
/// @link [Spring WebClientTest](https://docs.spring.io/spring-framework/reference/testing/webtestclient.html#webtestclient-context-config)
/// @link [Spring WebClientTest Tutorial](https://34codefactory.medium.com/spring-5-webclient-and-webtestclient-tutorial-code-factory-84e32978149a)
/// @link [Testing of your rest api](https://rieckpil.de/spring-webtestclient-for-efficient-testing-of-your-rest-api/)
/// @see AbstractControllerTest
@Getter @Accessors(fluent = true)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public abstract class AbstractControllerIntegrationTest {
    @Autowired
    private WebTestClient client;

    /**
     * {@link LocalServerPort} obtém a porta do servidor Spring com a instância da aplicação para testes automatizados.
     * Não é necessário, mas pode ser usado para saber qual porta está sendo usada.
     */
    @LocalServerPort
    private int port;
}
