package com.llp.userservice.services.impl;

import com.llp.sharedproject.exceptions.ConflictException;
import com.llp.sharedproject.exceptions.InternalServerException;
import com.llp.sharedproject.exceptions.NotFoundException;
import com.llp.userservice.dtos.user.AuthenticationRequest;
import com.llp.userservice.dtos.user.AuthenticationResponse;
import com.llp.userservice.dtos.user.RegisterRequest;
import com.llp.userservice.entities.Role;
import com.llp.userservice.entities.User;
import com.llp.userservice.entities.UserRole;
import com.llp.userservice.repositories.RoleRepository;
import com.llp.userservice.repositories.UserRepository;
import com.llp.userservice.repositories.UserRoleRepository;
import com.llp.userservice.services.JwtService;
import com.llp.userservice.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final RoleRepository roleRepository;
    private final UserRoleRepository userRoleRepository;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    @Override
    public AuthenticationResponse register(RegisterRequest request) {
        try {
            if(!userRepository.findByEmail(request.getEmail()).isEmpty()){
                throw new ConflictException("Already has this user");
            }
            Role role = roleRepository.findByName("USER");

            if (role == null) {
                throw new IllegalStateException("Default user role not found");
            }
            var user = User.builder()
                    .fullname(request.getFullName())
                    .email(request.getEmail())
                    .password(passwordEncoder.encode(request.getPassword()))
                    .verified(true)
                    .participateDay(LocalDate.now())
                    .build();
            userRepository.save(user);
            userRoleRepository.create(user.getId().intValue(), role.getId().intValue());
            var token = jwtService.generateToken(user);
            return AuthenticationResponse.builder()
                    .token(token)
                    .build();
        } catch (ConflictException e){
            throw new ConflictException(e.getMessage());
        } catch (Exception e){
            throw new InternalServerException("Server Error");
        }
    }

    @Override
    public AuthenticationResponse loginViaForm(AuthenticationRequest request) {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            request.getEmail(),
                            request.getPassword()
                    )
            );
            var user = userRepository.findByEmail(request.getEmail())
                    .orElseThrow();
            var token = jwtService.generateToken(user);
            return AuthenticationResponse.builder()
                    .token(token)
                    .build();
        } catch (Exception e){
            throw new InternalServerException("Server Error");
        }
    }
}
