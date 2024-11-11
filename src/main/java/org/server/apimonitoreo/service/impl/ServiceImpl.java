package org.server.apimonitoreo.service.impl;

import org.server.apimonitoreo.models.mapper.MapperGen;
import org.server.apimonitoreo.repository.Repository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.Optional;
import java.util.UUID;

public abstract class ServiceImpl<S,M,E> {
    private final MapperGen<S,M,E> mapper;
    private final Repository<E> repository;

    protected ServiceImpl(MapperGen<S, M, E> mapper, Repository<E> repository) {
        this.mapper = mapper;
        this.repository = repository;
    }

    public Page<M> findAll() {
        Pageable pageable = PageRequest.of(0, 10);
        Page<E> listUser = repository.findAll(pageable);
        return listUser.map(mapper::EntityToDtoSend);
    }

    public Optional<M> findById(UUID id) {
        System.out.println("depurando");
        Optional<E> e = Optional.ofNullable(repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Entity not found")));
        return e.map(mapper::EntityToDtoSend);
    }

    public void deleteById(UUID id) {
        repository.deleteById(id);
    }

}
