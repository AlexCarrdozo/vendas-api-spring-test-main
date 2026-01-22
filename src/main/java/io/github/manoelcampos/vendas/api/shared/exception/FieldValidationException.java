package io.github.manoelcampos.vendas.api.shared.exception;

import lombok.Getter;
import lombok.NonNull;

/**
 * @author Manoel Campos
 */
@Getter
public class FieldValidationException extends RuntimeException {
    private final String fieldName;

    /**
     * Cria uma exceção de validação de campo.
     * @param message mensagem de erro de validação
     * @param fieldName nome do campo sendo validado
     */
    public FieldValidationException(@NonNull final String message, @NonNull final String fieldName) {
        super(message);
        this.fieldName = fieldName;
    }
}
