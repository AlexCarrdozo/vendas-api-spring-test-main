package io.github.manoelcampos.vendas.api.feature.estado;

import io.github.manoelcampos.vendas.api.feature.AbstractControllerIntegrationTest;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

/**
 * Testes de integração para a API REST de {@link Estado} implementada pelo {@link EstadoController}.
 * @author Manoel Campos
 */
class EstadoControllerIntegrationTest extends AbstractControllerIntegrationTest {
    @Test
    void findById() {
        final var estadoEsperado = new Estado(1);
        estadoEsperado.setDescricao("São Paulo");

        Estado estadoObtido = client().get()
                                      .uri("/estado/{id}", 1)
                                      .exchange() // envia a requisição
                                      .expectStatus()
                                      .isOk()
                                      .expectBody(Estado.class)
                                      .isEqualTo(estadoEsperado)
                                      .returnResult()
                                      .getResponseBody();

        assertNotNull(estadoObtido);
        assertEquals(estadoEsperado.getDescricao(), estadoObtido.getDescricao());
    }
}
