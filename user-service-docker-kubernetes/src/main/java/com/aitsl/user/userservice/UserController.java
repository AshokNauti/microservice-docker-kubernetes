package com.aitsl.user.userservice;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

	@Autowired
	private Environment environment;
	
	@Autowired
	private UserDetailRepository userDetailRepository;
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@GetMapping("users/{id}")
	public Optional<UserDetail> getUserDetail(@PathVariable long id) {
		
		Optional<UserDetail> userDetail = userDetailRepository.findById(id);
		
		if(userDetail.isPresent()) {
			logger.info("User: " + userDetail);
			userDetail.get().setPort(environment.getProperty("local.server.port"));
		}
		
		return userDetail;
	}
	
	@GetMapping("users")
	public List<UserDetail> getUsers() {
		
		logger.info("All Users: ");
		
		return userDetailRepository.findAll();
	}
}
