package io.github.manoelcampos.vendas.api.feature.cidade;

import io.github.manoelcampos.vendas.api.feature.estado.Estado;
import static org.assertj.core.api.Assertions.*;

import org.assertj.core.util.Preconditions;
import org.jetbrains.annotations.NotNull;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.dao.DataIntegrityViolationException;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;


@DataJpaTest
//@TestMethodOrder(value = MethodOrderer.OrderAnnotation.class)
class CidadeRepositoryTest {

    @Autowired
    private CidadeRepository cidadeRepository;

    @Test
    void findByDescricaoLike() {
        final  var listaObtida = cidadeRepository.findByDescricaoLike("São %");
        System.out.println(listaObtida);

        final var listaEsperada = List.of(new Cidade(13L, "São Luiz"),
                                          new Cidade(1L, "São Paulo"),
                                          new Cidade(28L, "São Jose dos Campos"));

        assertThat(listaObtida).size().isEqualTo(listaEsperada.size());
        assertThat(listaObtida).containsAll(listaEsperada);

    }

    @Test
    void deleteByidExcluiCidade() {

        final long id=28;
        assertThat(cidadeRepository.findById(id)).isPresent();
        cidadeRepository.deleteById(id);
        assertThat(cidadeRepository.findById(id)).isEmpty();

    }

    @Test
    void updateCidade() {
        final long id =28;
        final var nomeCidadeEsperado = "SÃO JOSÉ DOS CAMPOS";
        var cidade = getCidade(id);
        cidade.setDescricao(nomeCidadeEsperado);
        cidadeRepository.saveAndFlush(cidade);

        var cidadeObtida = getCidade(id);
        assertThat(cidadeObtida.getDescricao()).isEqualTo(nomeCidadeEsperado);
    }

    private @NotNull Cidade getCidade(long id) {
        Cidade cidade = cidadeRepository.findById(id).orElseThrow();
        return cidade;
    }

    @Test
    void deleteByidLancaExcecaoViolacaoFK() {
        final long id = 4;
        assertThatThrownBy(() -> {
            cidadeRepository.deleteById(id);
            cidadeRepository.flush();
        })
                .isInstanceOf(DataIntegrityViolationException.class);




    }
}