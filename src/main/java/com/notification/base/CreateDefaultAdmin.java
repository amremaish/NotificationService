package com.notification.base;

import org.apache.juli.logging.Log;
import org.apache.juli.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.notification.dao.entites.UserEntity;
import com.notification.dao.repo.UserRepo;
import com.notification.service.AuthService;

@Component
public class CreateDefaultAdmin implements CommandLineRunner {

	private final Log log = LogFactory.getLog(CreateDefaultAdmin.class);

	@Autowired
	private UserRepo userRepo;
	@Autowired
	private AuthService authService;

	@Override
	public void run(String... args) throws Exception {

		log.info("create Admin user");
		String email = "admin@admin.com";
		UserEntity exist_user = userRepo.findByEmail(email).orElse(null);
		if (exist_user != null) {
			return;
		}
		UserEntity user = new UserEntity();
		user.setUsername("Admin");
		user.setEmail(email);
		user.setPassword(authService.getEncoder().encode("123456"));
		user.setRoles(UserEntity.ADMIN_ROLE);
		user.setIs_admin(true);
		userRepo.save(user);
		log.info("finish admin user");

		// create icons
	}
}
