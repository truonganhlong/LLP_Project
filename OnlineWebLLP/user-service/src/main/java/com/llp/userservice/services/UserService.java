package com.llp.userservice.services;

import com.llp.userservice.dtos.user.AuthenticationRequest;
import com.llp.userservice.dtos.user.AuthenticationResponse;
import com.llp.userservice.dtos.user.RegisterRequest;

public interface UserService {
    AuthenticationResponse register(RegisterRequest request);

    AuthenticationResponse loginViaForm(AuthenticationRequest request);

}
