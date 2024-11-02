package org.server.apimonitoreo.service.impl;

import org.server.apimonitoreo.models.dtos.save.BovinoDtoSave;
import org.server.apimonitoreo.models.dtos.send.BovinoDtoSend;
import org.server.apimonitoreo.models.entities.Bovino;
import org.server.apimonitoreo.models.mapper.BovinoMapper;
import org.server.apimonitoreo.repository.BovinoRepository;
import org.server.apimonitoreo.service.BovinoService;
import org.springframework.stereotype.Service;

@Service
public class BovinoServiceImpl extends ServiceImpl<BovinoDtoSave, BovinoDtoSend, Bovino> implements BovinoService {
    private final BovinoRepository bovinoRepository;
    private final BovinoMapper bovinoMapper;

    public BovinoServiceImpl(BovinoRepository bovinoRepository, BovinoMapper bovinoMapper) {
        super(bovinoMapper, bovinoRepository);
        this.bovinoRepository = bovinoRepository;
        this.bovinoMapper = bovinoMapper;
    }
}
