package com.ccdp.main.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.ccdp.main.repositories.UserRepository;
import com.ccdp.main.entities.User;

@Controller
public class DossierController {
	
	@Autowired
	private UserRepository userRepository;

	@GetMapping("/dossier")
	public String dossier(Model model) {
		var userAuth = SecurityContextHolder.getContext().getAuthentication();
		if(userAuth != null && !"anonymousUser".equals(userAuth.getPrincipal())) {
			String username = SecurityContextHolder.getContext().getAuthentication().getName();
			User user = userRepository.findByUsername(username);
			model.addAttribute("user", user);
			model.addAttribute("logged", true);
			model.addAttribute("hasDossier", user.getDossier() != null);
		}
		return "dossier";
	}
	
}
