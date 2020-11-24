package com.itlize.springsecurityjwt.service;

import com.itlize.springsecurityjwt.DAO.UserRepository;
import com.itlize.springsecurityjwt.models.AuthenticationRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class MyUserDetailsService implements UserDetailsService {
    @Autowired
    UserRepository userRepository;

    BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        Optional<AuthenticationRequest> user = userRepository.findByUsername(s);
        if(user.get() == null){
             throw new UsernameNotFoundException("not found"); 
        }
        List authorities = new ArrayList();
        //List<SimpleGrantedAuthority> authorities = new ArrayList<>();
        //authorities.add(new SimpleGrantedAuthority(user.getRole().name()));
        return new org.springframework.security.core.userdetails.User(user.get().getUsername(), passwordEncoder.encode(user.get().getPassword()), authorities); 
    }
}
