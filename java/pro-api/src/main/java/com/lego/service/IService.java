package com.lego.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface IService<T, ID> {

    JpaRepository<T, ID> getRepository();

    default T save(T entity) {
        return getRepository().save(entity);
    }

    default void delete(T entity) {
        getRepository().delete(entity);
    }

    default Page<T> findAll(int page, int size) {
        return getRepository().findAll(PageRequest.of(page, size));
    }

    default Optional<T> findById(ID id) {
        return getRepository().findById(id);
    }
}
