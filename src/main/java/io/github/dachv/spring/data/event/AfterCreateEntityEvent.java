package io.github.dachv.spring.data.event;

import org.springframework.data.domain.Persistable;

public class AfterCreateEntityEvent<T extends Persistable<?>> extends AbstractEntityEvent<T> {

    private static final long serialVersionUID = 1679459923858268688L;

    public AfterCreateEntityEvent(T entity) {
        super(entity);
    }
}
