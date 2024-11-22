package org.server.apimonitoreo.service;

import org.server.apimonitoreo.models.dtos.save.CoordenadaDtoSave;
import org.server.apimonitoreo.models.entities.Bovino;
import org.server.apimonitoreo.models.entities.Coordenada;

import java.util.List;

public interface ValidationCoordenadas {
    boolean isValid(CoordenadaDtoSave coordenada, List<Coordenada> poligono);
    void alerta(CoordenadaDtoSave coordenada, Bovino bovino);
}
