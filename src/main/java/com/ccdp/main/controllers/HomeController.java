package com.ccdp.main.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.ccdp.main.repositories.UserRepository;

@Controller
public class HomeController {
	
	@Autowired
	private UserRepository userRepository;

	@GetMapping({"/", "/home"})
	public String home(Model model) {
		var user = SecurityContextHolder.getContext().getAuthentication();
		if(user != null && !"anonymousUser".equals(user.getPrincipal())) {
			String username = SecurityContextHolder.getContext().getAuthentication().getName();
			model.addAttribute("user", userRepository.findByUsername(username));
			model.addAttribute("logged", true);
		}
		return "home";
	}
	
}
