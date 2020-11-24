package com.itlize.springsecurityjwt.DAO;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Repository;

import com.itlize.springsecurityjwt.models.AuthenticationRequest;

@Repository
public interface UserRepository extends JpaRepository<AuthenticationRequest, String> {
	Optional<AuthenticationRequest> findByUsername(String username); 
}