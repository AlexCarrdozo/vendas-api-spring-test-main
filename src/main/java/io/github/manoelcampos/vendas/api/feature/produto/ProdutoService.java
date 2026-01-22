package io.github.manoelcampos.vendas.api.feature.produto;

import io.github.manoelcampos.vendas.api.shared.service.AbstractCrudService;
import org.springframework.stereotype.Service;

@Service
public class ProdutoService extends AbstractCrudService<Produto, ProdutoRepository> {
    public ProdutoService(final ProdutoRepository repository) {
        super(repository);
    }

    @Override
    public boolean deleteById(final long id) {
        final ProdutoRepository repository = getRepository();
        repository.findById(id).ifPresent(prod -> {
            if(prod.getEstoque() > 0)
                throw new IllegalStateException("Produto %s não pode ser excluído pois ainda tem estoque".formatted(prod.getDescricao()));
        });

        return super.deleteById(id);
    }
}
