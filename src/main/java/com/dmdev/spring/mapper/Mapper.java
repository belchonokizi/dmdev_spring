package com.dmdev.spring.mapper;

//преобразует один объект F в другой T
public interface Mapper<F, T> {
    T map(F object);
    default T map(F fromObject, T toObject) {
        return toObject;
    }
}
