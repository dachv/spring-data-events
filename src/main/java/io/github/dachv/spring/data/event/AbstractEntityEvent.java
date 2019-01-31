package io.github.dachv.spring.data.event;

import org.springframework.context.ApplicationEvent;
import org.springframework.data.domain.Persistable;

public abstract class AbstractEntityEvent<T extends Persistable<?>> extends ApplicationEvent {

    private static final long serialVersionUID = -2641553832203857520L;

    public AbstractEntityEvent(T entity) {
        super(entity);
    }

    @SuppressWarnings("unchecked")
    public T getEntity() {
        return (T) super.getSource();
    }
}
