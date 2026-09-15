package com.portifolyo.airlinesystem.service;

import com.portifolyo.airlinesystem.entity.BaseEntity;

import java.util.List;

public interface BaseService<T extends BaseEntity> {

    T save(T entity);

    T update(T entity);

    void delete(T entity);

    T getById(String id);

    List<T> getAll();


}
