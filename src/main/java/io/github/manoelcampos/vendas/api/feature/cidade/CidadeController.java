package io.github.manoelcampos.vendas.api.feature.cidade;

import io.github.manoelcampos.vendas.api.shared.controller.AbstractController;
import io.github.manoelcampos.vendas.api.shared.validator.CustomValidator;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/cidade")
public class CidadeController extends AbstractController<Cidade, CidadeRepository, CidadeService> {
    public CidadeController(final CidadeService service, final CustomValidator<Cidade> validator) {
        super(service, validator);
    }
}
