package io.github.dachv.spring.data.event;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface PublishEvents {

    boolean publishCreate() default true;

    boolean publishUpdate() default true;

    boolean publishDelete() default true;
}
