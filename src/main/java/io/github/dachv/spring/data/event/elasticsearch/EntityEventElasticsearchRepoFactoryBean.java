package io.github.dachv.spring.data.event.elasticsearch;

import io.github.dachv.spring.data.event.EntityEventRepoProxyPostProcessor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.data.elasticsearch.repository.support.ElasticsearchRepositoryFactoryBean;
import org.springframework.data.repository.Repository;
import org.springframework.data.repository.core.support.RepositoryFactorySupport;

import java.io.Serializable;

public class EntityEventElasticsearchRepoFactoryBean<T extends Repository<S, ID>, S, ID extends Serializable>
    extends ElasticsearchRepositoryFactoryBean<T, S, ID> {

    private ApplicationEventPublisher eventPublisher;

    public EntityEventElasticsearchRepoFactoryBean(Class<? extends T> repositoryInterface) {
        super(repositoryInterface);
    }

    @Override
    protected RepositoryFactorySupport createRepositoryFactory() {
        final RepositoryFactorySupport factorySupport = super.createRepositoryFactory();
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
