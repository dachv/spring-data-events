package io.github.dachv.spring.data.event;


import org.springframework.data.domain.Persistable;

public class BeforeCreateEntityEvent<T extends Persistable<?>> extends AbstractEntityEvent<T> {

    private static final long serialVersionUID = 508291041572555155L;

    public BeforeCreateEntityEvent(T entity) {
        super(entity);
    }
}
