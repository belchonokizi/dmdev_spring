package com.dmdev.spring.service;

import com.dmdev.spring.database.entity.Company;
import com.dmdev.spring.database.repository.CrudRepository;
import com.dmdev.spring.dto.CompanyReadDto;
import com.dmdev.spring.listener.entity.AccessType;
import com.dmdev.spring.listener.entity.EntityEvent;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CompanyService {

    private final UserService userService;
    private final CrudRepository<Integer, Company> repository;
    //для отправки ивентов
    private final ApplicationEventPublisher eventPublisher;

    public CompanyService(UserService userService,
                          CrudRepository<Integer, Company> repository,
                          ApplicationEventPublisher eventPublisher) {
        this.userService = userService;
        this.repository = repository;
        this.eventPublisher = eventPublisher;
    }

    public Optional<CompanyReadDto> findById(Integer id) {
        return repository.findById(id)
                .map(entity -> {
                    //отправляем ивент
                    eventPublisher.publishEvent(new EntityEvent(entity, AccessType.READ));
                    return new CompanyReadDto(entity.id());
                });
    }
}
