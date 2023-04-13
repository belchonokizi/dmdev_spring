package com.dmdev.spring.bpp;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;
import org.springframework.util.ReflectionUtils;

import java.util.Arrays;

@Component
//реалиузем ApplicationContextAware, чтобы достать из контекста нужный бин
public class InjectBeanPostProcessor implements BeanPostProcessor, ApplicationContextAware {
    private ApplicationContext applicationContext;

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        Arrays.stream(bean.getClass().getDeclaredFields())
                .filter(field -> field.isAnnotationPresent(InjectBean.class))
                .forEach(field -> {
                    //достаем бин из констекста по типу поля
                    Object beanToInject = applicationContext.getBean(field.getType());
                    //открываем доступность поля
                    ReflectionUtils.makeAccessible(field);
                    //для того, чтобы не обрабатывать экс самим, используется ReflectionUtils
                    ReflectionUtils.setField(field, bean, beanToInject);
                });
        return bean;
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }
}
