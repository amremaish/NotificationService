package com.notification.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.notification.dao.entites.UserEntity;
import com.notification.dao.repo.UserRepo;
import com.notification.error.CustomException;
import com.notification.security.MyUserDetails;


@Service
public class AuthService implements UserDetailsService {

	@Autowired
	private UserRepo userRepo;

	@Bean
	public PasswordEncoder getEncoder() {
		return new BCryptPasswordEncoder();
	}

	private void checkPassword(String email, String password, AuthenticationManager authenticationManager) {
		Authentication authentication = null;
		try {
			authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(email, password));

		} catch (DisabledException dis) {
			throw new CustomException("Disabled user");
		} catch (BadCredentialsException e) {
			throw new CustomException("The request is rejected because the credentials are invalid");
		}

		if (!authentication.isAuthenticated()) {
			throw new CustomException("The request is rejected because the credentials are invalid");
		}
	}

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		Optional<UserEntity> user = userRepo.findByEmail(email);
		user.orElseThrow(() -> new CustomException("The request is rejected because the credentials are invalid"));
		return user.map(MyUserDetails::new).get();
	}

	public List<UserEntity> findAll() {
		return userRepo.findAll();
	}

	public UserEntity findByEmail(String email) {
		return userRepo.findByEmail(email).orElseThrow(()-> new CustomException("The request is rejected because the credentials are invalid"));
	}


}
