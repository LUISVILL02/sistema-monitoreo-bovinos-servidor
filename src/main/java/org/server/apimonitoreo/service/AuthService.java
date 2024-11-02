package org.server.apimonitoreo.service;

import org.server.apimonitoreo.models.dtos.Auth.LoginDto;
import org.server.apimonitoreo.models.dtos.Auth.RegisterDto;
import org.server.apimonitoreo.models.dtos.Auth.ResponseAuth;

public interface AuthService {
    ResponseAuth login(LoginDto loginDto);
    String register(RegisterDto registerDto);
}
