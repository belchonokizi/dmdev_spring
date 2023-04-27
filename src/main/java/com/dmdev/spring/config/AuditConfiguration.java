package com.dmdev.spring.config;

import com.dmdev.spring.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.envers.repository.config.EnableEnversRepositories;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import java.util.Optional;

@Configuration
@EnableJpaAuditing
@EnableEnversRepositories(basePackageClasses = ApplicationRunner.class)
public class AuditConfiguration {

    //создаем провайдер, который устанавливает стринг значение
    //в поля createdBy и modifiedBy
    @Bean
    public AuditorAware<String> auditorAware() {
        //SecurityContext.getCurrentUser().getEmail() или другое
        return () -> Optional.of("dmdev");
    }

}
