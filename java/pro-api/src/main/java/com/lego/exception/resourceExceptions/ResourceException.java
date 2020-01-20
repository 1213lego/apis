package com.lego.exception.resourceExceptions;

public abstract class ResourceException extends RuntimeException {

    protected Object id;
    protected Class<?> resourceName;
    public ResourceException(Throwable throwable, Object id, Class<?> resourceName) {
        super(throwable);
        this.id = id;
        this.resourceName = resourceName;
    }

    public abstract String getDetailMessage();
}
