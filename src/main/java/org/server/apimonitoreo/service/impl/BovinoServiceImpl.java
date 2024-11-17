package org.server.apimonitoreo.service.impl;

import org.server.apimonitoreo.exceptions.EntityNotFount;
import org.server.apimonitoreo.models.dtos.save.BovinoDtoSave;
import org.server.apimonitoreo.models.dtos.send.BovinoDtoSend;
import org.server.apimonitoreo.models.entities.Bovino;
import org.server.apimonitoreo.models.entities.Potrero;
import org.server.apimonitoreo.models.entities.Sensore;
import org.server.apimonitoreo.models.entities.Usuario;
import org.server.apimonitoreo.models.mapper.BovinoMapper;
import org.server.apimonitoreo.repository.BovinoRepository;
import org.server.apimonitoreo.repository.PotreroRepository;
import org.server.apimonitoreo.repository.SensorRepository;
import org.server.apimonitoreo.repository.UserRepository;
import org.server.apimonitoreo.service.BovinoService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class BovinoServiceImpl extends ServiceImpl<BovinoDtoSave, BovinoDtoSend, Bovino> implements BovinoService {
    private final BovinoRepository bovinoRepository;
    private final BovinoMapper bovinoMapper;
    private final UserRepository userRepository;
    private final SensorRepository sensorRepository;
    private final PotreroRepository potreroRepository;


    public BovinoServiceImpl(BovinoRepository bovinoRepository, BovinoMapper bovinoMapper, UserRepository userRepository, SensorRepository sensorRepository, PotreroRepository potreroRepository) {
        super(bovinoMapper, bovinoRepository);
        this.bovinoRepository = bovinoRepository;
        this.bovinoMapper = bovinoMapper;
        this.userRepository = userRepository;
        this.sensorRepository = sensorRepository;
        this.potreroRepository = potreroRepository;
    }

    @Override
    public BovinoDtoSend save(BovinoDtoSave bovinoDtoSave) {
        Usuario propietario = userRepository.findById(bovinoDtoSave.getPropietarioId())
                .orElseThrow(() -> new EntityNotFount("Propietario no encontrado"));
        Sensore sensor = sensorRepository.findById(bovinoDtoSave.getSensorId())
                .orElseThrow(() -> new EntityNotFount("Sensor no encontrado"));
        Potrero potrero = potreroRepository.findById(bovinoDtoSave.getPotreroId())
                .orElseThrow(() -> new EntityNotFount("Potrero no encontrado"));

        Bovino bovino = bovinoMapper.dtoSaveToEntity(bovinoDtoSave);
        bovino.setSensor(sensor);
        bovino.setPropietario(propietario);
        bovino.setPotrero(potrero);

        String codigo = String.valueOf(bovino.getFechaNacimiento().getYear() + (bovino.getFechaIngreso().getYear() + bovino.getFechaNacimiento().getYear()));

        bovino.setCodigo(codigo);

        return bovinoMapper.EntityToDtoSend(bovinoRepository.save(bovino));
    }

    @Override
    public Bovino findBySensor_Id(UUID sensorId) {
        Sensore sensor = sensorRepository.findById(sensorId)
                .orElseThrow(() -> new EntityNotFount("Sensor no encontrado"));

        Bovino bovino = bovinoRepository.findBySensor_Id(sensor.getId())
                .orElseThrow(() -> new EntityNotFount("El sensor no está asocioado a ningun bovino"));
        return bovino;
    }

    @Override
    public Usuario findByPropietario(String bovinoId) {
        return userRepository.findByPropietario(bovinoId)
                .orElseThrow(() -> new EntityNotFount("Propietario no encontrado"));
    }

    @Override
    public Usuario findByCapataz(String codigoBovino) {
        return userRepository.findByCapataz(codigoBovino)
                .orElseThrow(() -> new EntityNotFount("Capataz no encontrado"));
    }

    @Override
    public String updateSensor(UUID sensorId, String bovinoId) {
        Bovino bovino = bovinoRepository.findByCodigo(bovinoId)
                .orElseThrow(() -> new EntityNotFount("Bovino no encontrado"));
        Sensore sensor = sensorRepository.findById(sensorId)
                .orElseThrow(() -> new EntityNotFount("Sensor no encontrado"));

        bovino.setSensor(sensor);
        bovinoRepository.save(bovino);
        return "Sensor actualizado para el bovino: "+bovino.getCodigo();
    }

    @Override
    public List<BovinoDtoSend> findByPropietarioId(UUID propietarioId) {
        userRepository.findById(propietarioId)
                .orElseThrow(() -> new EntityNotFount("Propietario no encontrado"));
        List<Bovino> bovinos = bovinoRepository.findByPropietario_Id(propietarioId);
        return bovinos
                .stream()
                .map(bovinoMapper::EntityToDtoSend)
                .toList();
    }

    @Override
    public List<BovinoDtoSend> findByCapatazId(UUID capatazId) {
        userRepository.findById(capatazId)
                .orElseThrow(() -> new EntityNotFount("Capataz no encontrado"));
        List<Bovino> bovinos = bovinoRepository.findByPotrero_Finca_Capataz_Id(capatazId);
        return bovinos
                .stream()
                .map(bovinoMapper::EntityToDtoSend)
                .toList();
    }

    @Override
    public BovinoDtoSend findByCodigo(String codigo) {
        Bovino bovino = bovinoRepository.findByCodigo(codigo)
                .orElseThrow(() -> new EntityNotFount("Bovino no encontrado"));
        return bovinoMapper.EntityToDtoSend(bovino);
    }

}
