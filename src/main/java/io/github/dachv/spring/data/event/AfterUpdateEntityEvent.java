package io.github.dachv.spring.data.event;


import org.springframework.data.domain.Persistable;

public class AfterUpdateEntityEvent<T extends Persistable<?>> extends AbstractEntityEvent<T> {

    private static final long serialVersionUID = -1504286479778044455L;

    public AfterUpdateEntityEvent(T entity) {
        super(entity);
    }
}
