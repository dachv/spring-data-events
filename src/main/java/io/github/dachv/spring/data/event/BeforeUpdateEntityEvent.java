package io.github.dachv.spring.data.event;


import org.springframework.data.domain.Persistable;

public class BeforeUpdateEntityEvent<T extends Persistable<?>> extends AbstractEntityEvent<T> {

    private static final long serialVersionUID = -3186463643225261668L;

    public BeforeUpdateEntityEvent(T entity) {
        super(entity);
    }
}
