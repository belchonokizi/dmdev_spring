package com.dmdev.spring.service;

import com.dmdev.spring.database.repository.CompanyRepository;
import com.dmdev.spring.dto.CompanyReadDto;
import com.dmdev.spring.listener.entity.AccessType;
import com.dmdev.spring.listener.entity.EntityEvent;
import com.dmdev.spring.mapper.CompanyReadMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class CompanyService {

    private final CompanyRepository repository;
    //для отправки ивентов
    private final ApplicationEventPublisher eventPublisher;
    private final CompanyReadMapper companyReadMapper;

    public Optional<CompanyReadDto> findById(Integer id) {
        return repository.findById(id)
                .map(entity -> {
                    //отправляем ивент
                    eventPublisher.publishEvent(new EntityEvent(entity, AccessType.READ));
                    return companyReadMapper.map(entity);
                });
    }

    public List<CompanyReadDto> findAll() {
        return repository.findAll().stream()
                .map(companyReadMapper::map)
                .toList();
    }
}
