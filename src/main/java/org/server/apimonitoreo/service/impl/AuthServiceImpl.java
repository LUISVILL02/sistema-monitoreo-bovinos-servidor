package org.server.apimonitoreo.service.impl;

import lombok.AllArgsConstructor;
import org.server.apimonitoreo.models.dtos.Auth.LoginDto;
import org.server.apimonitoreo.models.dtos.Auth.RegisterDto;
import org.server.apimonitoreo.models.dtos.Auth.ResponseAuth;
import org.server.apimonitoreo.models.entities.Role;
import org.server.apimonitoreo.models.entities.UserApp;
import org.server.apimonitoreo.models.entities.Usuario;
import org.server.apimonitoreo.models.enums.ERole;
import org.server.apimonitoreo.repository.RoleRepository;
import org.server.apimonitoreo.repository.UserRepository;
import org.server.apimonitoreo.security.jwt.JwtUtils;
import org.server.apimonitoreo.service.AuthService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import static java.util.Objects.isNull;

@AllArgsConstructor
@Service
public class AuthServiceImpl implements AuthService {
    private final PasswordEncoder encoder;
    private final AuthenticationManager authenticationManager;
    private final JwtUtils jwtUtils;
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;

    @Override
    public ResponseAuth login(LoginDto loginDto) {
        Usuario usuario = userRepository.findByCorreo(loginDto.getCorreo())
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        UserApp userApp = new UserApp(usuario);

        Authentication auth = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginDto.getCorreo(),
                        loginDto.getContraseña()
                )
        );
        SecurityContextHolder.getContext().setAuthentication(auth);


        String jwt = jwtUtils.getToken(userApp);
        return ResponseAuth.builder().token(jwt).build();
    }

    @Override
    public String register(RegisterDto registerDto) {
        if (userRepository.existsByCorreo(registerDto.getCorreo()))
            throw new RuntimeException("Correo ya registrado");
        if(userRepository.existsByIdentificación(registerDto.getIdentificación()))
            throw new RuntimeException("Identificación ya registrada");

        Usuario usuario = new Usuario();
        Usuario Propietario = null;

        Role role = Role.builder()
                .nombre(ERole.valueOf(registerDto.getRol()))
                .build();
        Role roleSearch = roleRepository.findByNombre(ERole.valueOf(registerDto.getRol())).orElseGet(
                () -> roleRepository.save(role)
        );

        if (!isNull(registerDto.getPropietario())){
            Propietario = userRepository.findByCorreo(registerDto.getPropietario()).orElseThrow(
                    () -> new RuntimeException("El propietario no ha sido encontrado")
            );
            if (!Propietario.getRol().getNombre().equals(ERole.PROPIETARIO)){
                throw new RuntimeException("El usuario "+Propietario.getNombre()+" "+Propietario.getApellido()+" no es un propietario");
            }
        }
        usuario = Usuario.builder()
                .nombre(registerDto.getNombre())
                .apellido(registerDto.getApellido())
                .identificación(registerDto.getIdentificación())
                .correo(registerDto.getCorreo())
                .contraseña(encoder.encode(registerDto.getContraseña()))
                .rol(roleSearch)
                .build();

        usuario.setPropietario(Propietario);
        userRepository.save(usuario);

        return "Usuario registrado con éxito";
    }
}
