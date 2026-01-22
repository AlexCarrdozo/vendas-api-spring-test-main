package io.github.manoelcampos.vendas.api.feature.cidade;

import io.github.manoelcampos.vendas.api.feature.AbstractServiceTest;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class CidadeServiceTest extends AbstractServiceTest {
    @Mock
    private CidadeRepository repository;

    @InjectMocks
    private CidadeService service;

    @Test
    void insert() {
        final var cidade = new Cidade();
        cidade.setDescricao("Nova Cidade");

        final var cidadeSalva = new Cidade(1);
        cidadeSalva.setDescricao("Nova Cidade");

        Mockito.when(repository.saveAndFlush(cidade)).thenReturn(cidadeSalva);

        final var cidadeInserida = service.save(cidade);

        assertNotNull(cidadeInserida);
        assertEquals(cidadeSalva, cidadeInserida);
    }
}
