package io.github.manoelcampos.vendas.api.feature.cliente;

import io.github.manoelcampos.vendas.api.config.ConstraintKeys;
import io.github.manoelcampos.vendas.api.feature.cidade.Cidade;
import io.github.manoelcampos.vendas.api.model.AbstractBaseModel;
import jakarta.persistence.Entity;
import jakarta.persistence.ForeignKey;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.hibernate.validator.constraints.br.CPF;

@Entity @Getter @Setter @NoArgsConstructor @AllArgsConstructor @ToString
public class Cliente extends AbstractBaseModel {
    @NotNull @NotBlank
    private String nome;

    @CPF @NotNull @NotBlank
    private String cpf;

    @ManyToOne
    @JoinColumn(foreignKey = @ForeignKey(name = ConstraintKeys.FK_CLIENTE_CIDADE))
    private Cidade cidade;

    public Cliente(final long id) {
        setId(id);
    }
}
