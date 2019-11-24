package ru.idea.test.spring.boot;

import java.util.Collection;
import java.util.Optional;

public interface IdRepo<Id, T> {

    Collection<T> getAll();

    Optional<T> getById(Id id);

    Optional<T> add(T entity);

    T delete(T entity);

    Optional<T> deleteById(Id id);
}
