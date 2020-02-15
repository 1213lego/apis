package com.lego.repository.jpa;

import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.Repository;

import java.util.Optional;

@NoRepositoryBean// @NoRepositoryBean Make sure you add that annotation to all
// repository interfaces for which Spring Data should not create instances at runtime.
public interface CustomBaseRepository<T, ID> extends Repository<T, ID> {
    Optional<T> findById(ID id);

    <S extends T> S save(S entity);
}
