package org.server.apimonitoreo.repository;

import org.server.apimonitoreo.models.entities.HistorialUbicacione;

import java.util.List;

public interface HistorialUbiRepository extends Repository<HistorialUbicacione> {
    List<HistorialUbicacione> findByBovino_Codigo(String codigoBovino);
}