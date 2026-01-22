package io.github.manoelcampos.vendas.api.feature.cidade;

import io.github.manoelcampos.vendas.api.feature.AbstractRepositoryTest;
import static org.assertj.core.api.Assertions.*;
import org.jetbrains.annotations.NotNull;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.dao.DataIntegrityViolationException;
import java.util.List;

@DataJpaTest
class CidadeRepositoryTest extends AbstractRepositoryTest {
    @Autowired
    private CidadeRepository cidadeRepository;

    @Test
    void findByDescricaoLike(){
        final var listaObtida = cidadeRepository.findByDescricaoLike("São %");
        final var listaEsperada = List.of(new Cidade(13L), new Cidade(1L), new Cidade(28L));
        assertThat(listaObtida).size().isEqualTo(listaEsperada.size());
        assertThat(listaObtida).containsAll(listaEsperada);
    }

    @Test
    void deleteByIdExcluiCidade() {
        final long id = 28L;
        cidadeRepository.deleteById(id);

        assertThat(cidadeRepository.findById(id).isEmpty());
    }

    @Test
    void deleteByIdLancaExcecaoViolacaoFK() {
        final long id = 4;
      assertThatThrownBy(() -> {
          cidadeRepository.deleteById(id);
          cidadeRepository.flush();
      })
              .isInstanceOf(DataIntegrityViolationException.class);
    }

    @Test
    void updateCidade() {
        final long id = 28;
        final String nomeEsperado = "São José dos Campos".toUpperCase();

        var cidade = getCidade(id);
        cidade.setDescricao(nomeEsperado);

        cidadeRepository.saveAndFlush(cidade);
        var cidadeObtida = getCidade(id);
        assertThat(cidadeObtida.getDescricao()).isEqualTo(nomeEsperado);
    }

    private @NotNull Cidade getCidade(long id) {
        Cidade cidade = cidadeRepository.findById(id).orElseThrow();
        return cidade;
    }
}
