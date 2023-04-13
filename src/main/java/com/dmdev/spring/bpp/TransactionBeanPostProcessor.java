package com.dmdev.spring.bpp;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;

import java.lang.reflect.Proxy;
import java.util.HashMap;
import java.util.Map;

@Component
public class TransactionBeanPostProcessor implements BeanPostProcessor {
    private final Map<String, Class<?>> transactionBeans = new HashMap<>();

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        if (bean.getClass().isAnnotationPresent(Transaction.class)) {
           transactionBeans.put(beanName, bean.getClass());
        }
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        Class<?> beanClass = transactionBeans.get(beanName);
        if (beanClass != null) {
            //создаем прокси для добавления сквозной функциональности (дайнамик прокси в данном случае)
            //но по умолчанию в спринге используются прокси через наследование (надо подключать библиотеки)
            return Proxy.newProxyInstance(beanClass.getClassLoader(),
                    beanClass.getInterfaces(),
                    //Добавляем новую функциональность по открытию и закрытию транзакции
                    (proxy, method, args) -> {
                        System.out.println("Open transaction");
                        try {
                            //метод вызываем у реального бина, а не прокси
                            //но возвращается прокси бин
                            return method.invoke(bean, args);
                        } finally {
                            System.out.println("Close transaction");
                        }
                    });
        }
        return bean;
    }
}
