package com.notification.controller.api;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.notification.dao.entites.UserEntity;
import com.notification.error.CustomException;
import com.notification.error.CustomResponse;
import com.notification.security.JWTResponse;
import com.notification.security.TokenUtiles;
import com.notification.service.AuthService;

@RestController
@RequestMapping(value = "/api/")
public class AuthController {

	Logger log = LoggerFactory.getLogger(AuthController.class);
	@Autowired
	private TokenUtiles tokenUtils;

	@Autowired
	private AuthService userService;

	@Autowired
	private AuthenticationManager authenticationManager;

	@PostMapping(value = "/login")
	public Object singIn(@RequestBody Map<String, Object> body) throws Exception {
		String email = (String) body.get("email");
		log.info("authentication >> ");
		Authentication authentication = null;
		try {
			authentication = authenticationManager
					.authenticate(new UsernamePasswordAuthenticationToken(email, body.get("password")));

		} catch (DisabledException dis) {
			throw new CustomException("USER_DISABLED");
		} catch (BadCredentialsException e) {
			throw new CustomException("The request is rejected because the credentials are invalid");
		}

		log.info("authentication >> " + authentication.isAuthenticated());
		if (authentication.isAuthenticated()) {
			log.info("authentication >> " + authentication.isAuthenticated());
			SecurityContextHolder.getContext().setAuthentication(authentication);
			UserDetails userDetails = userService.loadUserByUsername(email);
			UserEntity user = userService.findByEmail(email);
			String token = tokenUtils.generateToken(userDetails);

			return new CustomResponse(
					new JWTResponse(token, email, user.getUsername(), user.getRoles(), "Succeefully logged"));
		}

		return new CustomResponse("INVALID", HttpStatus.BAD_REQUEST.value());
	}

	@GetMapping("")
	public Object ViewBranch() {
		return "kjaskldjas";
	}
}
