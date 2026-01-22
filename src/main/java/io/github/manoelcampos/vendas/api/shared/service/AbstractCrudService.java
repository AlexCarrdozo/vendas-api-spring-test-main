package io.github.manoelcampos.vendas.api.shared.service;

import io.github.manoelcampos.vendas.api.shared.EntityRepository;
import io.github.manoelcampos.vendas.api.shared.controller.AbstractController;
import io.github.manoelcampos.vendas.api.model.AbstractBaseModel;
import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.function.Supplier;

/**
 * Implementação base de um {@link CrudService} para
 * executar operações CRUD em um {@link AbstractController}.
 * @param <T> {@inheritDoc}
 * @param <R> {@inheritDoc}
 * @author Manoel Campos
 */
@Slf4j @Service @RequiredArgsConstructor
public abstract class AbstractCrudService<T extends AbstractBaseModel, R extends EntityRepository<T>> implements CrudService<T, R> {
    @Getter
    private final R repository;

    /**
     * <p><b>AVISO:</b> Não use o atributo diretamente, chame o {@link #getEntityClassName()}
     * para fazer lazy initialization.</p>
     */
    private String entityClassName;

    /**
     * Obtém um {@link Supplier} de {@link NoSuchElementException} com a mensagem passada como parâmetro.
     * Tal método pode ser chamado em operações como {@link Optional#orElseThrow(Supplier)}
     * ao chamar métodos como {@link EntityRepository#findById(Object)} e qualquer
     * outro que retorne um Optional.
     * @param msg mensagem de erro a ser exibida quando a exceção retornada pelo Supplier for lançada
     * @return o supplier de {@link NoSuchElementException}
     */
    protected static Supplier<NoSuchElementException> notFoundSupplier(@NonNull final String msg) {
        return () -> new NoSuchElementException(msg);
    }

    @Override
    public boolean deleteById(final long id) {
        return findById(id).map(this::deleteEntity).orElse(false);
    }

    private boolean deleteEntity(final T entity){
        repository.delete(entity);
        return true;
    }

    @Override
    public Optional<T> findById(final long id) {
        return repository.findById(id);
    }

    @Override
    public List<T> findAll() {
        return repository.findAll();
    }

    @Override
    public T save(final T entity) {
        return repository.saveAndFlush(entity);
    }

    @Override
    public String getEntityClassName() {
        if (entityClassName != null) {
            return entityClassName;
        }

        final var typeParameters = repository.getClass().getTypeParameters();
        entityClassName = typeParameters.length == 0 ? "Objeto" : typeParameters[0].getClass().getSimpleName();
        return entityClassName;
    }
}
