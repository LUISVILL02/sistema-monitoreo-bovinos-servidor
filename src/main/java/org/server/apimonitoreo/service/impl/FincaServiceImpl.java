package org.server.apimonitoreo.service.impl;

import org.server.apimonitoreo.exceptions.EntityNotFount;
import org.server.apimonitoreo.models.dtos.save.FincaDtoSave;
import org.server.apimonitoreo.models.dtos.send.FincaDtoSend;
import org.server.apimonitoreo.models.entities.Finca;
import org.server.apimonitoreo.models.entities.Role;
import org.server.apimonitoreo.models.entities.Usuario;
import org.server.apimonitoreo.models.enums.ERole;
import org.server.apimonitoreo.models.mapper.FincaMapper;
import org.server.apimonitoreo.repository.FincaRepository;
import org.server.apimonitoreo.repository.RoleRepository;
import org.server.apimonitoreo.repository.UserRepository;
import org.server.apimonitoreo.service.FincaService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class FincaServiceImpl extends ServiceImpl<FincaDtoSave, FincaDtoSend, Finca> implements FincaService {
    private final FincaRepository fincaRepository;
    private final UserRepository usuarioRepository;
    private final FincaMapper fincaMapper;
    private final RoleRepository roleRepository;

    public FincaServiceImpl(FincaRepository fincaRepository, UserRepository usuario, FincaMapper fincaMapper, RoleRepository roleRepository) {
        super(fincaMapper, fincaRepository);
        this.fincaRepository = fincaRepository;
        this.usuarioRepository = usuario;
        this.fincaMapper = fincaMapper;
        this.roleRepository = roleRepository;
    }

    @Override
    public FincaDtoSend save(FincaDtoSave fincaDtoSave, UUID idPropietario) {
        Role rol = roleRepository.findByNombre(ERole.PROPIETARIO)
                .orElseThrow(() -> new EntityNotFount("Rol no encontrado"));
        Usuario propietario = usuarioRepository.findByIdAndRol_Id(idPropietario, rol.getId())
                .orElseThrow(() -> new EntityNotFount("Propietarrio no encontrado"));

        Finca finca = fincaMapper.DtoSaveToEntity(fincaDtoSave);

        finca.setPropietario(propietario);
        return fincaMapper.EntityToDtoSend(fincaRepository.save(finca));
    }

    @Override
    public Optional<FincaDtoSend> findByPropietario(UUID idPropietario) {
        Finca finca = fincaRepository.findByPropietario_Id(idPropietario)
                .orElseThrow(() -> new EntityNotFount("Finca no encontrada"));
        return Optional.of(fincaMapper.EntityToDtoSend(finca));
    }

    @Override
    public Page<FincaDtoSend> findAllByPropietario(UUID idPropietario, int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Finca> fincas = fincaRepository.findAllByPropietario_Id(idPropietario, pageable);
        return fincas.map(fincaMapper::EntityToDtoSend);
    }
}
