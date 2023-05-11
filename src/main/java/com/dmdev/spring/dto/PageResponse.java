package com.dmdev.spring.dto;

import lombok.Value;
import org.springframework.data.domain.Page;

import java.util.List;

@Value
public class PageResponse<T> {
    List<T> content;
    MetaData metaData;

    public static <T> PageResponse<T> of(Page<T> page) {
        MetaData metaData = new MetaData(page.getNumber(),
                page.getSize(), page.getTotalElements());
        return new PageResponse<>(page.getContent(),metaData);
    }

    @Value
    public static class MetaData {
        int page;
        //кол-во записей на одной страничке
        int size;
        long totalElements;
    }

}
