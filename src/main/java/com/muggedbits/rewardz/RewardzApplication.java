package com.muggedbits.rewardz;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;

@SpringBootApplication
public class RewardzApplication {

//	@Autowired
//	private UserRepository userRepository;

	public static void main(String[] args) {
		SpringApplication.run(RewardzApplication.class, args);
	}

//	@EventListener(ApplicationReadyEvent.class)
//	public void runAfterStartup() {
//		User user = new User();
//		user.setFirstName("John");
//		user.setLastName("Doe");
//		user.setEmail("johndoe@email.com");
//		user.setPassword("password");
//		user.setRole("USER");
//		userRepository.save(user);
//
//		userRepository.findAll()
//				.stream()
//				.peek(u -> System.out.println(u.getFirstName() + " " + u.getLastName()));
//	}
}
