package com.pedrohrr.simpletransfer.service;

import io.github.biezhi.anima.Anima;
import io.github.biezhi.anima.Model;

import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

abstract class AbstractService<T extends Model> {
    List<T> find(final Predicate<T> predicate) {
        return getFilter(predicate).collect(Collectors.toList());
    }

    boolean hasAny(final Predicate<T> predicate) {
        return getFilter(predicate).count() != 0;
    }

    private Stream<T> getFilter(final Predicate<T> predicate) {
        return Anima.select().from(modelClass()).filter(predicate);
    }

    abstract Class<T> modelClass();
}
