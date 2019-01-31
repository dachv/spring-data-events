package io.github.dachv.spring.data.event;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.data.domain.Persistable;

public class EntityEventMethodInterceptor implements MethodInterceptor {

    private static final String SAVE_METHOD_NAME = "save";
    private static final String DELETE_METHOD_NAME = "delete";

    private final ApplicationEventPublisher eventPublisher;
    private final PublishEntityEvents publishSettings;

    public EntityEventMethodInterceptor(ApplicationEventPublisher eventPublisher, PublishEntityEvents publishSettings) {
        this.eventPublisher = eventPublisher;
        this.publishSettings = publishSettings;
    }

    @Override
    public Object invoke(MethodInvocation invocation) throws Throwable {
        final String methodName = invocation.getMethod().getName();
        Object result;
        switch (methodName) {
            case SAVE_METHOD_NAME: {
                result = handleSave(invocation);
                break;
            }
            case DELETE_METHOD_NAME: {
                result = handleDelete(invocation);
                break;
            }
            default: {
                result = invocation.proceed();
            }
        }
        return result;
    }

    private Object handleSave(final MethodInvocation invocation) throws Throwable {
        final Persistable<?> entityToSave = getEntityArgument(invocation);
        final Persistable<?> savedEntity;
        if (entityToSave.isNew() && publishSettings.publishCreate()) {
            eventPublisher.publishEvent(new BeforeCreateEntityEvent<>(entityToSave));
            savedEntity = (Persistable<?>) invocation.proceed();
            eventPublisher.publishEvent(new AfterCreateEntityEvent<>(savedEntity));
        } else if (publishSettings.publishUpdate()) {
            eventPublisher.publishEvent(new BeforeUpdateEntityEvent<>(entityToSave));
            savedEntity = (Persistable<?>) invocation.proceed();
            eventPublisher.publishEvent(new AfterUpdateEntityEvent<>(savedEntity));
        } else {
            savedEntity = (Persistable<?>) invocation.proceed();
        }
        return savedEntity;
    }

    private Object handleDelete(final MethodInvocation invocation) throws Throwable {
        if (publishSettings.publishDelete()) {
            final Persistable<?> entityToDelete = getEntityArgument(invocation);
            eventPublisher.publishEvent(new BeforeDeleteEntityEvent<>(entityToDelete));
            Object result = invocation.proceed();
            eventPublisher.publishEvent(new AfterDeleteEntityEvent<>(entityToDelete));
            return result;
        } else {
            return invocation.proceed();
        }
    }

    private Persistable<?> getEntityArgument(final MethodInvocation invocation) {
        final Object[] args = invocation.getArguments();
        if (args == null || args.length == 0 || !(args[0] instanceof Persistable<?>)) {
            throw new IllegalArgumentException(
                "Method invocation arguments is not eligible for entity event publish. Invocation should have instance of Persistable class");
        }
        return (Persistable<?>) args[0];
    }
}
