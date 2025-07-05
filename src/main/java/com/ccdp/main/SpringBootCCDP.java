package com.ccdp.main;

import java.util.List;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.ccdp.main.entities.User;
import com.ccdp.main.repositories.UserRepository;

@SpringBootApplication
public class SpringBootCCDP implements ApplicationRunner{
	
    private final UserRepository userRepository;
    private final PasswordEncoder encoder;
    
    public SpringBootCCDP(UserRepository userRepository, PasswordEncoder encoder) {
    	this.userRepository = userRepository;
    	this.encoder = encoder;
    }
	

	public static void main(String[] args) {
		SpringApplication.run(SpringBootCCDP.class, args);
	}
	
	@Override
	public void run(ApplicationArguments args) throws Exception {
		if(userRepository.count()==0)
		{
			userRepository.saveAll(
					List.of(
							new User("Mack", "MACKY", "user", encoder.encode("user"))
					)
			);			
		}

	}
	

}
