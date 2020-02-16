package com.lego.proapi.service;

import com.lego.proapi.exception.resourceExceptions.ResourceConflictException;
import com.lego.proapi.exception.resourceExceptions.ResourceNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.persistence.Id;
import java.lang.reflect.Field;
import java.util.Optional;

public interface IService<T, ID> {
    Logger logger = LoggerFactory.getLogger(IService.class);

    JpaRepository<T, ID> getRepository();

    /*T save(T entity);*/

    default T save(T entity) throws ResourceConflictException, IllegalAccessException {
        try {
            return getRepository().save(entity);
        } catch (DataAccessException dataAccessException) {
            Optional<Object> idOptional = Optional.empty();
            Field[] fields = getEntityType().getDeclaredFields();
            for (Field field : fields) {
                if (field.getAnnotation(Id.class) != null) {
                    field.setAccessible(true);
                    idOptional = Optional.ofNullable(field.get(entity));
                    break;
                }
            }
            logger.warn("Error saving " + getEntityType().getTypeName(), dataAccessException);
            throw new ResourceConflictException(dataAccessException.getRootCause(),
                    idOptional,
                    getEntityType());
        }
    }

    Class<T> getEntityType();

    void delete(T entity);

    default Page<T> findAll(int page, int size) {
        return getRepository().findAll(PageRequest.of(page, size));
    }

    default T findById(ID id) {
        Optional<T> optionalT = getRepository().findById(id);
        return optionalT.orElseThrow(() -> new ResourceNotFoundException(new RuntimeException("Resource not found"),
                Optional.ofNullable(id),
                getEntityType()));
    }
}
