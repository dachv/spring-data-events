package io.github.dachv.spring.data.event.jpa;

import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Inherited
@Documented
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@EnableJpaRepositories(repositoryFactoryBeanClass = EntityEventJpaRepoFactoryBean.class)
public @interface EnableEntityEventJpaRepositories {
}
