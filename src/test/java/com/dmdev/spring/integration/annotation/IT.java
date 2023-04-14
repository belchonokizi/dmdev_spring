package com.dmdev.spring.integration.annotation;

import com.dmdev.spring.integration.TestApplicationRunner;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

//создаем аннотацию, чтобы не дублировать все аннотации в IT
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@SpringBootTest(classes = TestApplicationRunner.class)
@ActiveProfiles("test")
public @interface IT {
}