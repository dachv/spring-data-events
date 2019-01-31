package io.github.dachv.spring.data.event;


import org.springframework.data.domain.Persistable;

public class AfterDeleteEntityEvent<T extends Persistable<?>> extends AbstractEntityEvent<T> {

    private static final long serialVersionUID = 4046691135339179183L;

    public AfterDeleteEntityEvent(T entity) {
        super(entity);
    }
}
