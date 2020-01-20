package com.lego.exception.resourceExceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.CONFLICT)
public final class ResourceAlreadyExistsException extends ResourceException {
    private static final String MESSAGE_TEMPLATE = "The resource %s with id: %s already exists";

    public ResourceAlreadyExistsException(Throwable throwable,Object id, Class<?>  resourceName) {
        super(throwable,id, resourceName);
    }

    @Override
    public String getDetailMessage() {
        return String.format(MESSAGE_TEMPLATE, super.resourceName.getSimpleName(), super.id.toString());
    }
}
