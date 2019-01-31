package io.github.dachv.spring.data.event;


import org.springframework.data.domain.Persistable;

public class BeforeDeleteEntityEvent<T extends Persistable<?>> extends AbstractEntityEvent<T> {

    private static final long serialVersionUID = -9103625065484694788L;

    public BeforeDeleteEntityEvent(T entity) {
        super(entity);
    }
}
