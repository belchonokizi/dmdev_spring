package com.dmdev.spring;

import com.dmdev.spring.config.ApplicationConfiguration;
import com.dmdev.spring.service.CompanyService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class ApplicationRunner {
    public static void main(String[] args) {
        try (AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(ApplicationConfiguration.class)) {
            var service = context.getBean("companyService", CompanyService.class);
            service.findById(1);
        }
    }
}
