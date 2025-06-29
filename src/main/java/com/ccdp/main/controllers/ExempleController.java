package com.ccdp.main.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.ccdp.main.entities.Dossier;
import com.ccdp.main.entities.Exemple;
import com.ccdp.main.entities.User;
import com.ccdp.main.repositories.ExempleRepository;
import com.ccdp.main.repositories.UserRepository;

import jakarta.validation.Valid;

@Controller
public class ExempleController {
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	ExempleRepository exempleRepository;
	
	@GetMapping("/exemples")
	public String getExemples(Model model) {
		var userAuth = SecurityContextHolder.getContext().getAuthentication();
		if(userAuth != null && !"anonymousUser".equals(userAuth.getPrincipal())) {
			String username = SecurityContextHolder.getContext().getAuthentication().getName();
			User user = userRepository.findByUsername(username);
			model.addAttribute("user", user);
			model.addAttribute("logged", true);
			model.addAttribute("exemples", user.getExemples());
			model.addAttribute("hasExemple", !user.getExemples().isEmpty());
		}
		return "exemples";
	}

	
	@GetMapping("/addExemple")
	public String addExemple(Model model) {
		var userAuth = SecurityContextHolder.getContext().getAuthentication();
		if(userAuth != null && !"anonymousUser".equals(userAuth.getPrincipal())) {
			String username = SecurityContextHolder.getContext().getAuthentication().getName();
			User user = userRepository.findByUsername(username);
			model.addAttribute("user", user);
			model.addAttribute("exemple", new Exemple());
		}
		return "addExemple";
	}
	
	
	@PostMapping("/addExemple")
	public String addDossier(@Valid @ModelAttribute Exemple exemple, BindingResult result, Model model) {
		var userAuth = SecurityContextHolder.getContext().getAuthentication();
		if(userAuth != null && !"anonymousUser".equals(userAuth.getPrincipal())) {
			String username = SecurityContextHolder.getContext().getAuthentication().getName();
			User user = userRepository.findByUsername(username);
			if (result.hasErrors()) {
				return "redirect:/addExemple";
			}
			exemple.setUser(user);
			user.getExemples().add(exemple);
			model.addAttribute("exemple", exempleRepository.save(exemple));
			model.addAttribute("user", userRepository.save(user));
			model.addAttribute("hasDossier", user.getDossier() != null);
		}
		return "redirect:/exemples";
	}
	
	
	
}
