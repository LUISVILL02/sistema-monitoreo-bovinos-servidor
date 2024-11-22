package org.server.apimonitoreo.service.impl;

import org.server.apimonitoreo.exceptions.EntityNotFount;
import org.server.apimonitoreo.models.dtos.save.HistorialDtoSave;
import org.server.apimonitoreo.models.dtos.send.HistorialDtoSend;
import org.server.apimonitoreo.models.entities.Bovino;
import org.server.apimonitoreo.models.entities.HistorialUbicacione;
import org.server.apimonitoreo.models.entities.Sensore;
import org.server.apimonitoreo.models.mapper.HistorialMapper;
import org.server.apimonitoreo.repository.BovinoRepository;
import org.server.apimonitoreo.repository.HistorialUbiRepository;
import org.server.apimonitoreo.repository.SensorRepository;
import org.server.apimonitoreo.service.HistorialUbiService;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class HistUbiServiceImpl extends ServiceImpl<HistorialDtoSave, HistorialDtoSend, HistorialUbicacione> implements HistorialUbiService {
    private final HistorialMapper mapper;
    private final HistorialUbiRepository repository;
    private final SensorRepository sensoreRepository;
    private final BovinoRepository bovinoRepository;

    private final List<HistorialUbicacione> historiales = new ArrayList<>();

    public HistUbiServiceImpl(HistorialMapper mapper, HistorialUbiRepository repository, SensorRepository sensoreRepository, BovinoRepository bovinoRepository) {
        super(mapper, repository);
        this.mapper = mapper;
        this.repository = repository;
        this.sensoreRepository = sensoreRepository;
        this.bovinoRepository = bovinoRepository;
    }

    @Override
    public void save(HistorialDtoSave historial, UUID codigoSensor, String CodigoBovino) {
        Sensore sensor = sensoreRepository.findById(codigoSensor)
                .orElseThrow(() -> new EntityNotFount("Sensor no encontrado"));
        Bovino bovino = bovinoRepository.findByCodigo(CodigoBovino)
                .orElseThrow(() -> new EntityNotFount("Bovino no encontrado"));

        HistorialUbicacione historialSave = new HistorialUbicacione();
        historialSave.setLatitud(historial.getLatitud());
        historialSave.setLongitud(historial.getLongitud());
        historialSave.setSensor(sensor);
        historialSave.setBovino(bovino);
        historialSave.setFecha(historial.getFecha());

        int size = historiales.size();

        if ((Math.abs(
                historialSave.getLongitud() - historiales.get(size - 1).getLongitud()) < 3)
                &&
                (Math.abs(historialSave.getLatitud() - historiales.get(size - 1).getLatitud()) < 3)) {
            historiales.add(historialSave);
            System.out.println("Historial guardado en lista, esperando a ser guardado en la base de datos");
        }
    }

    @Override
    public List<HistorialDtoSend> findByBovino_Codigo(String codigoBovino) {
        List<HistorialUbicacione> historiales = repository.findByBovino_Codigo(codigoBovino);
        return historiales.stream().map(mapper::EntityToDtoSend).toList();
    }

    @Scheduled(fixedRate = 1800000) // cada 30 minutos
    public void saveHistorial() {
        if (!historiales.isEmpty()){
            repository.saveAll(historiales);
            System.out.println("Historiales guardados");
            historiales.clear();
        }
    }
}
