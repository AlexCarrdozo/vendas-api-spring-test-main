package io.github.manoelcampos.vendas.api.shared;

import io.github.manoelcampos.vendas.api.model.AbstractBaseModel;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Fornece um contrato para criação de outras interfaces anotadas com
 * {@link org.springframework.stereotype.Repository}
 * e que manipulam entidades do tipo {@link AbstractBaseModel}.
 * A implentação de tais interfaces é criada automaticamente pelo Spring Data JPA
 * e instanciadas por meio de injeção de dependência.
 * @author Manoel Campos
 */
public interface EntityRepository<T extends AbstractBaseModel> extends JpaRepository<T, Long> {
}
