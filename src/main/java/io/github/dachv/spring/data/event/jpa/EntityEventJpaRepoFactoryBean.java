package io.github.dachv.spring.data.event.jpa;

import io.github.dachv.spring.data.event.EntityEventRepoProxyPostProcessor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.data.jpa.repository.support.JpaRepositoryFactoryBean;
import org.springframework.data.repository.Repository;
import org.springframework.data.repository.core.support.RepositoryFactorySupport;

import javax.persistence.EntityManager;

public class EntityEventJpaRepoFactoryBean<T extends Repository<S, ID>, S, ID> extends JpaRepositoryFactoryBean<T, S, ID> {

    private ApplicationEventPublisher eventPublisher;

    public EntityEventJpaRepoFactoryBean(Class<? extends T> repositoryInterface) {
        super(repositoryInterface);
    }

    @Override
    protected RepositoryFactorySupport createRepositoryFactory(EntityManager entityManager) {
        final RepositoryFactorySupport factorySupport = super.createRepositoryFactory(entityManager);
        if (eventPublisher != null) {
            factorySupport.addRepositoryProxyPostProcessor(new EntityEventRepoProxyPostProcessor(eventPublisher));
        }
        return factorySupport;
    }

    @Override
    public void setApplicationEventPublisher(ApplicationEventPublisher publisher) {
        this.eventPublisher = publisher;
        super.setApplicationEventPublisher(publisher);
    }
}
