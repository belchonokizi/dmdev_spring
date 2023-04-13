package com.dmdev.spring.bpp;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME) //аннотацию д.б. видно в рантайме
@Target(ElementType.FIELD)
public @interface InjectBean {
}
