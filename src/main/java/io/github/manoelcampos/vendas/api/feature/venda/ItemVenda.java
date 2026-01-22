package io.github.manoelcampos.vendas.api.feature.venda;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.github.manoelcampos.vendas.api.config.ConstraintKeys;
import io.github.manoelcampos.vendas.api.feature.produto.Produto;
import io.github.manoelcampos.vendas.api.model.AbstractBaseModel;
import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.*;

/**
 * @author Manoel Campos
 */
@Entity @Getter @Setter @NoArgsConstructor @AllArgsConstructor @ToString
public class ItemVenda extends AbstractBaseModel {
    @NotNull @ManyToOne @JsonIgnore
    @JoinColumn(foreignKey = @ForeignKey(name = ConstraintKeys.FK_ITEM_VENDA__VENDA))
    private Venda venda;

    /**
     * Produto sendo vendido.
     * O valor do campo é ignorado em alterações (updates).
     * Se for feita uma alteração em algum item, o valor deste atributo é desconsiderado.
     * Depois da venda inserida, o produto não pode ser alterada.
     */
    @NotNull @ManyToOne
    @JoinColumn(updatable = false, foreignKey = @ForeignKey(name = ConstraintKeys.FK_ITEM_VENDA__PRODUTO))
    private Produto produto;

    /**
     * Quantidade de itens do produto vendidos.
     * O valor do campo é ignorado em alterações (updates).
     * Se for feita uma alteração em algum item, o valor deste atributo é desconsiderado.
     * Depois da venda inserida, a quantidade não pode ser alterada.
     */
    @NotNull @Min(1) @Column(updatable = false)
    private int quant;

    public ItemVenda(final long id) {
        this.setId(id);
    }

    public ItemVenda(final long produtoId, final int quant) {
        setProduto(new Produto(produtoId));
        setQuant(quant);
    }

    public ItemVenda(final Produto produto) {
        setProduto(produto);
    }
}
