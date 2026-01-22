package io.github.manoelcampos.vendas.api.feature.produto;

import io.github.manoelcampos.vendas.api.shared.controller.AbstractController;
import io.github.manoelcampos.vendas.api.shared.validator.CustomValidator;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/produto")
public class ProdutoController extends AbstractController<Produto, ProdutoRepository, ProdutoService> {
    public ProdutoController(final ProdutoService service, final CustomValidator<Produto> validator) {
        super(service, validator);
    }
}
