package com.ccdp.main.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.ccdp.main.entities.User;
import com.ccdp.main.repositories.UserRepository;


import jakarta.validation.Valid;



@Controller
public class UserController {
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	PasswordEncoder encoder;

	@GetMapping("/signup")
	public String signUp(Model model) {
		model.addAttribute("user", new User());
		return "signup";
	}
	
	@PostMapping("/signup")
	public String signUp(@Valid @ModelAttribute User user, BindingResult result, Model model) {
		if (result.hasErrors()) {
			return "redirect:/signup";
		}
		user.setPassword(encoder.encode(user.getPassword()));
		model.addAttribute("user", userRepository.save(user));
		return "login";
	}

	
}
