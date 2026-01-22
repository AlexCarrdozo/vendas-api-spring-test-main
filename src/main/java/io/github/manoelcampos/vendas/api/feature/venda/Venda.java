package io.github.manoelcampos.vendas.api.feature.venda;

import io.github.manoelcampos.vendas.api.feature.cliente.Cliente;
import io.github.manoelcampos.vendas.api.model.AbstractBaseModel;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.hibernate.annotations.Cascade;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * @author Manoel Campos
 */
@Entity @Getter @Setter @NoArgsConstructor @AllArgsConstructor @ToString
public class Venda extends AbstractBaseModel {
    @NotNull @ManyToOne
    private Cliente cliente;

    @Column(nullable = false)
    private LocalDateTime dataHora = LocalDateTime.now();

    @OneToMany(mappedBy = "venda")
    @Cascade(org.hibernate.annotations.CascadeType.ALL)
    private List<ItemVenda> itens = new ArrayList<>();

    public Venda(final long id) {
        this.setId(id);
    }

    public Venda(final long id, final List<ItemVenda> itens) {
        this(id);
        setItens(itens);
    }

    public Venda(final Cliente cliente) {
        setCliente(cliente);
    }

    public void setItens(final List<ItemVenda> itens) {
        this.itens = Objects.requireNonNullElse(itens, new ArrayList<>());
        this.itens.forEach(item -> item.setVenda(this));
    }

    @Override
    public String toString() {
        return "Venda{id: %d cliente: %d}".formatted(getId(), cliente == null ? cliente : cliente.getId());
    }
}
