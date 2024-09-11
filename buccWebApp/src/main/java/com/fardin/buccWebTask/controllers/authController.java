package com.fardin.buccWebTask.controllers;

import com.fardin.buccWebTask.DTO.loginDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.fardin.buccWebTask.DTO.registerDTO;
import com.fardin.buccWebTask.repo.userRepo;
import com.fardin.buccWebTask.Model.userEntity;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping(value = "api/bucc")
public class authController {
    userRepo userRepo;
    AuthenticationManager authenticationManager;
    PasswordEncoder passwordEncoder;
    public authController(userRepo userRepo, AuthenticationManager authenticationManager,
                          PasswordEncoder passwordEncoder){
        this.userRepo = userRepo;
        this.authenticationManager = authenticationManager;
        this.passwordEncoder = passwordEncoder;
    }
    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody registerDTO registerDTO){
        if(userRepo.existsByUsername(registerDTO.getUsername())){
            return new ResponseEntity<>("User exists",HttpStatus.BAD_REQUEST);
        }
        userEntity user = new userEntity();
        user.setUsername(registerDTO.getUsername());
        user.setFirstName(registerDTO.getFirstName());
        user.setLastName(registerDTO.getLastName());
        user.setPassword(passwordEncoder.encode(registerDTO.getPassword()));
        user.setAge(registerDTO.getAge());
        user.setPersonalExp(registerDTO.getPersonalExp());
        userRepo.save(user);
        return new ResponseEntity<>("You have registered successfully",HttpStatus.CREATED);
    }
    @PostMapping("/login")
    public ResponseEntity<Map<String, Object>> login(@RequestBody loginDTO loginDTO){
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginDTO.getUsername(), loginDTO.getPassword())
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);
        userEntity user = userRepo.findByUsername(loginDTO.getUsername())
                .orElseThrow(() -> new UsernameNotFoundException("User does not exist"));
        Map<String, Object> userInfo = new HashMap<>();
        userInfo.put("username", user.getUsername());
        userInfo.put("firstname", user.getFirstName());
        userInfo.put("lastname",user.getLastName());
        userInfo.put("age",user.getAge());
        userInfo.put("personalExp",user.getPersonalExp());
        return new ResponseEntity<>(userInfo, HttpStatus.OK);
    }
}
