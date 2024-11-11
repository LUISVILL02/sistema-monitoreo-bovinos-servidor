package org.server.apimonitoreo.service.impl;

import org.server.apimonitoreo.exceptions.EntityNotFount;
import org.server.apimonitoreo.models.dtos.save.CoordenadaDtoSave;
import org.server.apimonitoreo.models.dtos.save.PotreroDtoSave;
import org.server.apimonitoreo.models.dtos.send.PotreroDtoSend;
import org.server.apimonitoreo.models.entities.Coordenada;
import org.server.apimonitoreo.models.entities.Finca;
import org.server.apimonitoreo.models.entities.Potrero;
import org.server.apimonitoreo.models.mapper.CoordenadaMapper;
import org.server.apimonitoreo.models.mapper.PotreroMapper;
import org.server.apimonitoreo.repository.CoordenadaRepository;
import org.server.apimonitoreo.repository.FincaRepository;
import org.server.apimonitoreo.repository.PotreroRepository;
import org.server.apimonitoreo.service.PotreroService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class PotreroServiceImpl extends ServiceImpl<PotreroDtoSave, PotreroDtoSend, Potrero> implements PotreroService {
    private final PotreroRepository potreroRepository;
    private final PotreroMapper potreroMapper;
    private final FincaRepository fincaRepository;
    private final CoordenadaRepository coordenadaRepository;
    private final CoordenadaMapper coordenadaMapper;

    public PotreroServiceImpl(PotreroRepository potreroRepository,
                              PotreroMapper potreroMapper,
                              FincaRepository fincaRepository1,
                              CoordenadaRepository coordenadaRepository,
                              CoordenadaMapper coordenadaMapper) {
        super(potreroMapper, potreroRepository);
        this.potreroRepository = potreroRepository;
        this.potreroMapper = potreroMapper;
        this.fincaRepository = fincaRepository1;
        this.coordenadaRepository = coordenadaRepository;
        this.coordenadaMapper = coordenadaMapper;
    }

    @Override
    public PotreroDtoSend save(PotreroDtoSave potreroDtoSave, UUID idFinca, List<CoordenadaDtoSave> coordenadas) {
        Finca finca = fincaRepository.findById(idFinca)
                .orElseThrow(() -> new EntityNotFount("Finca no encontrada"));

        Potrero potrero = potreroMapper.dtoSaveToEntity(potreroDtoSave);
        potrero.setFinca(finca);
        potrero.setLatitud(finca.getLatitud());
        potrero.setLongitud(finca.getLongitud());

        Potrero potreroSave = potreroRepository.save(potrero);
        coordenadas.stream()
                .map(coordenadaDtoSave -> {
                    Coordenada coordenada = coordenadaMapper.dtoSaveToEntity(coordenadaDtoSave);
                    coordenada.setPotrero(potreroSave);
                    return coordenada;
                }).toList();

        return potreroMapper.EntityToDtoSend(potreroSave);
    }

    @Override
    public List<PotreroDtoSend> findByFincaId(UUID idFinca) {
        fincaRepository.findById(idFinca)
                .orElseThrow(() -> new EntityNotFount("Finca no encontrada"));
        List<Potrero> potreros = potreroRepository.findByFinca_Id(idFinca);
        return potreros.stream().map(potreroMapper::EntityToDtoSend).toList();
    }
}
