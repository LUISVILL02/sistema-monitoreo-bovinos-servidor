package org.server.apimonitoreo.service.impl;

import org.server.apimonitoreo.models.dtos.send.SensorDtoSend;
import org.server.apimonitoreo.models.entities.Sensore;
import org.server.apimonitoreo.models.mapper.SensorMapper;
import org.server.apimonitoreo.repository.SensorRepository;
import org.server.apimonitoreo.service.SensorService;
import org.springframework.stereotype.Service;

@Service
public class SensorServiceImpl extends ServiceImpl<Sensore, SensorDtoSend, Sensore> implements SensorService {
    private final SensorRepository sensorRepository;
    private final SensorMapper sensorMapper;

    public SensorServiceImpl(SensorRepository sensorRepository, SensorMapper sensorMapper) {
        super(sensorMapper, sensorRepository);
        this.sensorRepository = sensorRepository;
        this.sensorMapper = sensorMapper;
    }

    @Override
    public SensorDtoSend save() {
        return sensorMapper.EntityToDtoSend(sensorRepository.save(new Sensore()));
    }

}
