package com.ccdp.main.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.ccdp.main.entities.User;
import com.ccdp.main.repositories.UserRepository;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;




@Controller
public class UserController {
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	PasswordEncoder encoder;
	
	@GetMapping("/compte")
	public String getMethodName(Model model) {
		var userAuth = SecurityContextHolder.getContext().getAuthentication();
		if(userAuth != null && !"anonymousUser".equals(userAuth.getPrincipal())) {
			String username = SecurityContextHolder.getContext().getAuthentication().getName();
			User user = userRepository.findByUsername(username);
			model.addAttribute("user", user);
		}
		return "compte";
	}
	

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
	
	@GetMapping("/deleteUser")
	public String deleteUser(HttpServletRequest request, HttpServletResponse response, @RequestParam Integer userId) {
	    Authentication auth = SecurityContextHolder.getContext().getAuthentication();

	    if (auth != null && !"anonymousUser".equals(auth.getPrincipal())) {
	       
	        User user = userRepository.findById(userId).orElseThrow();

	        // Supprime le compte
	        userRepository.delete(user);

	        // Force la déconnexion
	        new SecurityContextLogoutHandler().logout(request, response, auth);
	    }

	    // Redirige vers page d'accueil ou de login
	    return "redirect:/home";
	}
	
	@GetMapping("/editCompte") 
	public String editUser(Model model) {
		var userAuth = SecurityContextHolder.getContext().getAuthentication();
		if(userAuth != null && !"anonymousUser".equals(userAuth.getPrincipal())) {
			String username = SecurityContextHolder.getContext().getAuthentication().getName();
			User user = userRepository.findByUsername(username);
			model.addAttribute("user", user);
		}	
		return "editCompte";
	}
	
	@PostMapping("/editCompte")
	public String editCompteSubmit(@RequestParam String firstname, @RequestParam String lastname, @RequestParam String password, Model model) {
		String usernameSecurity = SecurityContextHolder.getContext().getAuthentication().getName();
		User user = userRepository.findByUsername(usernameSecurity);

	    user.setFirstname(firstname);
	    user.setLastname(lastname);
	    user.setPassword(encoder.encode(password)); 

	    userRepository.save(user);
		model.addAttribute("user", user);
	    return "compte"; 
	}


	
}
