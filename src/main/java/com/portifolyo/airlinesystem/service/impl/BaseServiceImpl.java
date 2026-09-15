package com.portifolyo.airlinesystem.service.impl;

import com.portifolyo.airlinesystem.entity.BaseEntity;
import com.portifolyo.airlinesystem.repository.BaseRepository;
import com.portifolyo.airlinesystem.service.BaseService;
import lombok.extern.log4j.Log4j2;

import java.util.List;
@Log4j2
public class BaseServiceImpl<T extends BaseEntity> implements BaseService<T> {

    private final BaseRepository<T> baseRepository;

    public BaseServiceImpl(BaseRepository<T> baseRepository) {
        this.baseRepository = baseRepository;
    }

    @Override
    public T save(T entity) {
       T e = this.baseRepository.save(entity);
       log.info("Entity saved: {}", e);
       return e;
    }

    @Override
    public T update(T entity) {
        T e = this.baseRepository.save(entity);
        log.info("Entity updated: {}", e);
        return e;
    }

    @Override
    public void delete(T entity) {
        this.baseRepository.delete(entity);
        log.info("Entity deleted: {}", entity);
    }

    @Override
    public T getById(String id) {
        return this.baseRepository.findById(id).orElse(null);

    }

    @Override
    public List<T> getAll() {
        return this.baseRepository.findAll();
    }
}
