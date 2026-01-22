package io.github.manoelcampos.vendas.api.feature.cliente;

import io.github.manoelcampos.vendas.api.shared.controller.AbstractController;
import io.github.manoelcampos.vendas.api.shared.validator.CustomValidator;
import org.hibernate.validator.constraints.br.CPF;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/cliente")
public class ClienteController extends AbstractController<Cliente, ClienteRepository, ClienteService> {
    public ClienteController(final ClienteService service, final CustomValidator<Cliente> validator) {
        super(service, validator);
    }

    /**
     * Localiza um cliente pelo CPF no formato ddddddddddd ou ddd.ddd.ddd-dd.
     * Se o CPF for inválido, o método retorna status 400 (Bad Request).
     * @param cpf CPF do cliente
     * @return o cliente, caso tenha sido localizado
     */
    @GetMapping("/cpf/{cpf}")
    public ResponseEntity<Cliente> findByCpf(@PathVariable @CPF final String cpf) {
        return getService()
                    .findByCpf(cpf)
                    .map(ResponseEntity::ok)
                    .orElseThrow(() -> newNotFoundException("%s não encontrado para o CPF " + cpf));
    }
}
