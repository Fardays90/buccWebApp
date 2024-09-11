package com.fardin.buccWebTask.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import com.fardin.buccWebTask.repo.userRepo;
import com.fardin.buccWebTask.Model.userEntity;
import com.fardin.buccWebTask.Model.userPrincipal;
@Service
public class CustomUserDetailsService implements UserDetailsService {
     userRepo userRepo;
     @Autowired
     public CustomUserDetailsService(userRepo userRepo){
         this.userRepo = userRepo;
     }
     @Override
     public UserDetails loadUserByUsername(String username)throws UsernameNotFoundException {
         userEntity user = userRepo.findByUsername(username)
                 .orElseThrow(() ->new UsernameNotFoundException("User does not exist"));
         return new userPrincipal(user);
     }
}
