package io.github.manoelcampos.vendas.api.feature.cidade;


import io.github.manoelcampos.vendas.api.config.ConstraintKeys;
import io.github.manoelcampos.vendas.api.feature.estado.Estado;
import io.github.manoelcampos.vendas.api.model.AbstractBaseModel;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Entity
@Table (uniqueConstraints = {
        @UniqueConstraint(name = ConstraintKeys.UC_CIDADE_DESCRICAO, columnNames = "descricao"),
})
@Setter @Getter @NoArgsConstructor @AllArgsConstructor @ToString
public class Cidade extends AbstractBaseModel {
    @NotNull @NotBlank
    private String descricao;

    @NotNull
    @JoinColumn(foreignKey = @ForeignKey(name = ConstraintKeys.FK_CIDADE__ESTADO))
    @ManyToOne
    private Estado estado;

    public Cidade(final long id) {
        this.setId(id);
    }

    public Cidade(final String descricao) {
        this(null, descricao);
    }

    public Cidade(final Long id, final String descricao) {
        this.setDescricao(descricao);
        this.setId(id);
    }

    /**
     * Copy constructor que cria uma cidade a partir de dados de outra.
     * @param id id da nova cidade
     * @param outra outra cidade para copiar os dados (exceto o id)
     */
    public Cidade(final long id, final Cidade outra) {
        this.setId(id);
        this.setDescricao(outra.descricao);
        this.setEstado(outra.estado);
    }

}
