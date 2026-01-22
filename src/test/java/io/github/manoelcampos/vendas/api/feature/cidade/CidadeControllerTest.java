package io.github.manoelcampos.vendas.api.feature.cidade;

import io.github.manoelcampos.vendas.api.feature.AbstractControllerTest;
import io.github.manoelcampos.vendas.api.feature.estado.Estado;
import io.github.manoelcampos.vendas.api.shared.util.PathUtil;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.assertj.MockMvcTester;

import java.util.Optional;

/// Testes para a API REST de [Cidade] implementada pelo [CidadeController].
/// A classe testa apenas os métodos do controller, ignorando a camada de serviço (para a qual um mock é criado).
/// Ela é diferente de [CidadeControllerIT] que implementa testes de integração.
/// Logo, não usa mocks e testa todas as camadas da aplicação funcionando de forma integrada.
///
/// Pode-se usar a anotação `@SpringBootTest` (sem indicar a propriedade webEnvironment para iniciar
/// um servidor HTTP real em uma porta específica) no lugar de [WebMvcTest].
/// Neste caso, como não é criado um servidor real,
/// temos que usar um objeto [MockMvcTester] (substituto do [MockMvc]) da mesma forma,
/// para simular as requisições HTTP.
/// No entanto, a anotação `@SpringBootTest` não configura
/// o [MockMvcTester], exigindo a adição da anotação `@AutoConfigureMockMvc`.
///
/// Portanto, é muito mais fácil usar [WebMvcTest] no lugar, já recebendo tudo pré-configurado
/// e ainda garantindo que apenas os objetos necessários para testar o controller
/// específico serão criados.
///
/// @author Manoel Campos
/// @link <https://spring.io/guides/gs/testing-web>
@WebMvcTest(CidadeController.class)
class CidadeControllerTest extends AbstractControllerTest {
    private static final String RELATIVE_URL = "/cidade";
    private static final String BY_ID_URL = PathUtil.concat(RELATIVE_URL, "/{id}");

    /**
     * Cria um mock para o objeto CidadeService e injeta ele dentro
     * da instância da classe controller (indicada a anotação {@link WebMvcTest} acima)
     * que é criada internamente pelo Spring para receber as requisições HTTP enviadas nos testes.
     */
    @MockitoBean // Substitui a anotação obsoleta @MockBean
    private CidadeService service;

    @Test
    void findById() {
        final long id = 1;
        final var cidade = new Cidade(id, "Cidade 1");
        Mockito.when(service.findById(id)).thenReturn(Optional.of(cidade));
        mockMvcTester()
                .get()
                .uri(BY_ID_URL, id)
                .exchange()
                .assertThat()
                .hasStatus(HttpStatus.OK)
                .bodyJson()
                .isEqualTo(objectToJson(cidade));

        // Forma antiga de fazer com objeto MockMvc (exibindo vários imports difíceis de descobrir)
        //mockMvc().perform(get(BY_ID_URL, id)).andExpect(status().isOk()).andExpect(content().json(objectToJson(cidade)));
    }

    @Test
    void insert() {
        final var cidade = new Cidade("Nova Cidade", new Estado(1));
        final var novaCidade = new Cidade(1, cidade);
        Mockito.when(service.save(cidade)).thenReturn(novaCidade);
        mockMvcTester()
                .post()
                .uri(RELATIVE_URL)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectToJson(cidade))
                .assertThat()
                .hasStatus(HttpStatus.CREATED)
                .bodyJson().isEqualTo(objectToJson(novaCidade));

        /*
        // Forma antiga de fazer com objeto MockMvc (exibindo vários imports difíceis de descobrir)
        mockMvc()
                .perform(post(RELATIVE_URL).contentType(MediaType.APPLICATION_JSON).content(objectToJson(cidade)))
                .andExpect(status().isCreated())
                .andExpect(content().json(objectToJson(novaCidade)));
        */
    }

    @Test
    void delete() {
        final long id = 1;
        Mockito.when(service.deleteById(id)).thenReturn(true);
        mockMvcTester().delete().uri(BY_ID_URL, id).assertThat().hasStatus(HttpStatus.NO_CONTENT);

        // Forma antiga de fazer com objeto MockMvc (exibindo vários imports difíceis de descobrir)
        // mockMvc().perform(delete(BY_ID_URL, id)).andExpect(status().isNoContent());
    }
}
