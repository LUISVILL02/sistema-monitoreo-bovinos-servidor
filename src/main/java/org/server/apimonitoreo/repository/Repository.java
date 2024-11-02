package org.server.apimonitoreo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

import java.util.UUID;

@NoRepositoryBean
public interface Repository<E> extends JpaRepository<E, UUID> {
}
