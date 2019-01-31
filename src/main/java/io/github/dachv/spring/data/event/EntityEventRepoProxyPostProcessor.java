package io.github.dachv.spring.data.event;

import org.springframework.aop.framework.ProxyFactory;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.data.repository.core.RepositoryInformation;
import org.springframework.data.repository.core.support.RepositoryProxyPostProcessor;

import java.util.Optional;

public class EntityEventRepoProxyPostProcessor implements RepositoryProxyPostProcessor {

    private final ApplicationEventPublisher eventPublisher;

    public EntityEventRepoProxyPostProcessor(ApplicationEventPublisher eventPublisher) {
        this.eventPublisher = eventPublisher;
    }

    @Override
    public void postProcess(ProxyFactory factory, RepositoryInformation repositoryInformation) {
        final Optional<PublishEntityEvents> publishSettingsOpt = Optional.ofNullable(
            AnnotationUtils.findAnnotation(repositoryInformation.getRepositoryInterface(), PublishEntityEvents.class));
        if (publishSettingsOpt.isPresent()) {
            final PublishEntityEvents publishSettings = publishSettingsOpt.get();
            if (isNotAllEventsDisabled(publishSettings)) {
                factory.addAdvice(new EntityEventMethodInterceptor(eventPublisher, publishSettings));
            }
        }
    }

    private boolean isNotAllEventsDisabled(final PublishEntityEvents settings) {
        return settings.publishCreate() || settings.publishUpdate() || settings.publishDelete();
    }
}
