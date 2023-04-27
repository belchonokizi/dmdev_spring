package com.dmdev.spring.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import java.util.Optional;

@Configuration
@EnableJpaAuditing
public class AuditConfiguration {

    //создаем провайдер, который устанавливает стринг значение
    //в поля createdBy и modifiedBy
    @Bean
    public AuditorAware<String> auditorAware() {
        //SecurityContext.getCurrentUser().getEmail() или другое
        return () -> Optional.of("dmdev");
    }

}
