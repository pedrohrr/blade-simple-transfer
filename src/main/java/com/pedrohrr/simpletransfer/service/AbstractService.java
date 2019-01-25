package com.pedrohrr.simpletransfer.service;

import com.pedrohrr.simpletransfer.exception.DuplicateException;
import com.pedrohrr.simpletransfer.exception.NotFoundException;
import com.pedrohrr.simpletransfer.exception.SimpleTransferException;
import com.pedrohrr.simpletransfer.model.AbstractModel;
import io.github.biezhi.anima.Anima;
import io.github.biezhi.anima.core.AnimaQuery;

import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

abstract class AbstractService<T extends AbstractModel> {
    public Long create(final T model, Predicate<T> uniquePredicate, String... attributes) throws DuplicateException {
        model.setId(null);
        if (hasAny(uniquePredicate)) {
            throw new DuplicateException(attributes);
        }
        return model.save().asLong();
    }

    public void update(final T model) throws NotFoundException {
        if (model.update() == 0) {
            throw new NotFoundException(modelClass().getName());
        }
    }

    public T findById(final Long id) throws NotFoundException {
        final T model = from().byId(id);

        if (model == null) {
            throw new NotFoundException(modelClass().getName());
        }

        return model;
    }

    public void delete(final Long id) throws SimpleTransferException {
        if (Anima.deleteById(modelClass(), id) == 0) {
            throw new NotFoundException(modelClass().getName());
        }
    }

    AnimaQuery<T> from() {
        return Anima.select().from(modelClass());
    }

    List<T> find(final Predicate<T> predicate) throws NotFoundException {
        List<T> result = getFilter(predicate).collect(Collectors.toList());

        if (result.isEmpty()) {
            throw new NotFoundException(modelClass().getName());
        }

        return result;
    }

    boolean hasAny(final Predicate<T> predicate) {
        return getFilter(predicate).count() != 0;
    }

    private Stream<T> getFilter(final Predicate<T> predicate) {
        return from().filter(predicate);
    }

    abstract Class<T> modelClass();
}
