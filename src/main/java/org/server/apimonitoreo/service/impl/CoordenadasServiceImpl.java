package org.server.apimonitoreo.service.impl;

import org.server.apimonitoreo.exceptions.EntityNotFount;
import org.server.apimonitoreo.models.dtos.save.CoordenadaDtoSave;
import org.server.apimonitoreo.models.dtos.send.CoordenadaDtoSend;
import org.server.apimonitoreo.models.entities.Coordenada;
import org.server.apimonitoreo.models.entities.Potrero;
import org.server.apimonitoreo.models.mapper.CoordenadaMapper;
import org.server.apimonitoreo.repository.CoordenadaRepository;
import org.server.apimonitoreo.repository.PotreroRepository;
import org.server.apimonitoreo.service.CoordenadaService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class CoordenadasServiceImpl extends ServiceImpl<CoordenadaDtoSave, CoordenadaDtoSend, Coordenada> implements CoordenadaService {

    private final CoordenadaRepository coordenadaRepository;
    private final CoordenadaMapper coordenadaMapper;
    private final PotreroRepository potreroRepository;

    public CoordenadasServiceImpl(CoordenadaRepository coordenadaRepository, CoordenadaMapper coordenadaMapper, PotreroRepository potreroRepository) {
        super(coordenadaMapper, coordenadaRepository);
        this.coordenadaRepository = coordenadaRepository;
        this.coordenadaMapper = coordenadaMapper;
        this.potreroRepository = potreroRepository;
    }


    @Override
    public List<CoordenadaDtoSend> findByPotreroId(UUID idPotrero) {
        Potrero potrero = potreroRepository.findById(idPotrero)
                .orElseThrow(() -> new EntityNotFount("Potrero no encontrado"));

        return potrero.getCoordenadas()
                .stream()
                .map(coordenada -> coordenadaMapper.EntityToDtoSend(coordenada))
                .toList();
    }

    @Override
    public String deleteAllByPotrero_Id(UUID idPotrero) {
        coordenadaRepository.deleteAllByPotrero_Id(idPotrero);
        return "Se han eliminado todas las coordenadas registradas para este potrero";
    }

    @Override
    public CoordenadaDtoSend save(CoordenadaDtoSave coordenadaDtoSave, UUID idPotrero) {
        Potrero potrero = potreroRepository.findById(idPotrero)
                .orElseThrow(() -> new EntityNotFount("Potrero no encontrado"));
        Coordenada coordenada = coordenadaMapper.dtoSaveToEntity(coordenadaDtoSave);
        coordenada.setPotrero(potrero);
        return coordenadaMapper.EntityToDtoSend(coordenadaRepository.save(coordenada));
    }
}
