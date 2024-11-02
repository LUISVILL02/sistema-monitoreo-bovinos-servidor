package org.server.apimonitoreo.service;

import org.springframework.data.domain.Page;

import java.util.Optional;
import java.util.UUID;

public interface Service<S, M, E> {
    Page<M> findAll();
    Optional<M> findById(UUID id);
    void deleteById(UUID id);
}
