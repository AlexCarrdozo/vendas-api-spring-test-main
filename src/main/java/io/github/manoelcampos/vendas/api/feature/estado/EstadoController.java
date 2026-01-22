package io.github.manoelcampos.vendas.api.feature.estado;

import io.github.manoelcampos.vendas.api.shared.controller.AbstractController;
import io.github.manoelcampos.vendas.api.shared.validator.CustomValidator;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/estado")
public class EstadoController extends AbstractController<Estado, EstadoRepository, EstadoService> {
    public EstadoController(final EstadoService service, final CustomValidator<Estado> validator) {
        super(service, validator);
    }
}
