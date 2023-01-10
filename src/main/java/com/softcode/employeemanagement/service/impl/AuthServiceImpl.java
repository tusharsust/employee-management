package com.softcode.employeemanagement.service.impl;

import com.softcode.employeemanagement.entity.EmployeeEntity;
import com.softcode.employeemanagement.entity.RoleEntity;
import com.softcode.employeemanagement.entity.UserEntity;
import com.softcode.employeemanagement.exception.APIException;
import com.softcode.employeemanagement.model.LoginDto;
import com.softcode.employeemanagement.model.SignUpDto;
import com.softcode.employeemanagement.repository.EmployeeRepository;
import com.softcode.employeemanagement.repository.RoleRepository;
import com.softcode.employeemanagement.repository.UserRepository;
import com.softcode.employeemanagement.security.AuthoritiesConstants;
import com.softcode.employeemanagement.security.JwtTokenProvider;
import com.softcode.employeemanagement.service.AuthService;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.Set;

@Service
public class AuthServiceImpl implements AuthService {

    private final AuthenticationManager authenticationManager;
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenProvider jwtTokenProvider;
    private final EmployeeRepository employeeRepository;

    public AuthServiceImpl(AuthenticationManager authenticationManager,
                           UserRepository userRepository,
                           RoleRepository roleRepository,
                           PasswordEncoder passwordEncoder,
                           JwtTokenProvider jwtTokenProvider,
                           EmployeeRepository employeeRepository) {
        this.authenticationManager = authenticationManager;
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtTokenProvider = jwtTokenProvider;
        this.employeeRepository = employeeRepository;
    }

    @Override
    public String login(LoginDto loginDto) {

        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginDto.getEmail(),
                loginDto.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        return jwtTokenProvider.generateToken(authentication);
    }

    @Transactional
    @Override
    public String register(SignUpDto signUpDto) {

        // add check for email exists in database
        if(userRepository.existsByEmail(signUpDto.getEmail())){
            throw new APIException(HttpStatus.BAD_REQUEST, "Email is already exists!.");
        }

        UserEntity user = new UserEntity();
        user.setEmail(signUpDto.getEmail());
        user.setPassword(passwordEncoder.encode(signUpDto.getPassword()));

        Set<RoleEntity> roles = new HashSet<>();
        RoleEntity userRole = roleRepository.findByName(AuthoritiesConstants.USER).get();
        roles.add(userRole);
        user.setRoles(roles);

        UserEntity savedUser = userRepository.save(user);

        //Creating employee
        EmployeeEntity employee = new EmployeeEntity();
        employee.setPhone(signUpDto.getPhone());
        employee.setName(signUpDto.getName());
        employee.setJoiningDate(signUpDto.getJoiningDate());
        employee.setUser(savedUser);

        employeeRepository.save(employee);

        return "User registered successfully!.";
    }
}
