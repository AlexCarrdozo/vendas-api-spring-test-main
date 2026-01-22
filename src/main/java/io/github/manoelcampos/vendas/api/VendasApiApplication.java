package io.github.manoelcampos.vendas.api;

import io.github.manoelcampos.vendas.api.model.AbstractBaseModel;
import io.github.manoelcampos.vendas.api.shared.validator.CustomValidator;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.context.annotation.RequestScope;

@SpringBootApplication
@OpenAPIDefinition(
    info = @Info(
            title = "Vendas REST API",
            version = "0.0.1")
)
public class VendasApiApplication {
    public static void main(String[] args) {
        SpringApplication.run(VendasApiApplication.class, args);
    }

    /**
     * Configura a especificação OpenAPI para gerar
     * URLs relativas para os endpoints da API.
     * Assim, funciona para sites HTTP (como em localhost) ou HTTPS.
     * @return
     */
    @Bean
    public OpenAPI openAPI() {
        return new OpenAPI().addServersItem(new Server().url("/"));
    }

    /**
     * Instancia um {@link CustomValidator}.
     * Como a classe é genérica, no lugar de incluir nela alguma
     * anotação como {@code @Component}, os objetos estão sendo
     * criados por meio deste factory method.
     * Desta forma, a instanciação funciona também nos testes.
     * @return o validator instanciado
     * @param <T> tipo do objeto a ser validado pelo validator
     */
    @Bean @RequestScope
    public <T extends AbstractBaseModel> CustomValidator<T> validator() {
        return new CustomValidator<>();
    }
}
