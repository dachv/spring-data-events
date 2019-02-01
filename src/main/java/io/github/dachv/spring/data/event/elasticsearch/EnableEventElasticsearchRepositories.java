package io.github.dachv.spring.data.event.elasticsearch;

import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;

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
@EnableElasticsearchRepositories(repositoryFactoryBeanClass = EntityEventElasticsearchRepoFactoryBean.class)
public @interface EnableEventElasticsearchRepositories {
}
