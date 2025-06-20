package com.ccdp.main.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.ccdp.main.repositories.BlocRepository;
import com.ccdp.main.repositories.DossierRepository;
import com.ccdp.main.repositories.UserRepository;

import jakarta.validation.Valid;

import com.ccdp.main.entities.Bloc;
import com.ccdp.main.entities.Competence;
import com.ccdp.main.entities.Dossier;
import com.ccdp.main.entities.User;

@Controller
public class DossierController {
	
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private DossierRepository dossierRepository;
	
	@Autowired
	private BlocRepository blocRepository;

	@GetMapping("/dossier")
	public String dossier(Model model) {
		var userAuth = SecurityContextHolder.getContext().getAuthentication();
		if(userAuth != null && !"anonymousUser".equals(userAuth.getPrincipal())) {
			String username = SecurityContextHolder.getContext().getAuthentication().getName();
			User user = userRepository.findByUsername(username);
			model.addAttribute("user", user);
			model.addAttribute("logged", true);
			model.addAttribute("dossier", user.getDossier());
			model.addAttribute("hasDossier", user.getDossier() != null);
		}
		return "dossier";
	}
	
	
	@GetMapping("/addDossier")
	public String addDossier(Model model) {
		var userAuth = SecurityContextHolder.getContext().getAuthentication();
		if(userAuth != null && !"anonymousUser".equals(userAuth.getPrincipal())) {
			String username = SecurityContextHolder.getContext().getAuthentication().getName();
			User user = userRepository.findByUsername(username);
			model.addAttribute("user", user);
			model.addAttribute("dossier", new Dossier());
		}
		return "addDossier";
	}
	
	@PostMapping("/addDossier")
	public String addDossier(@Valid @ModelAttribute Dossier dossier, BindingResult result, Model model) {
		var userAuth = SecurityContextHolder.getContext().getAuthentication();
		if(userAuth != null && !"anonymousUser".equals(userAuth.getPrincipal())) {
			String username = SecurityContextHolder.getContext().getAuthentication().getName();
			User user = userRepository.findByUsername(username);
			if (result.hasErrors()) {
				return "redirect:/addDossier";
			}
			dossier.setUser(user);
			user.setDossier(dossier);
			model.addAttribute("dossier", dossierRepository.save(dossier));
			model.addAttribute("user", userRepository.save(user));
			model.addAttribute("hasDossier", user.getDossier() != null);
			System.out.println("user: " + user.getUsername());
			System.out.println("dossier: " + dossier.getTitle());
			System.out.println("dossier de l'user: " + user.getDossier().getTitle());
		}
		return "redirect:/dossier";
	}
	
	
	
	@GetMapping("/addBloc")
	public String addBloc(Model model) {
		var userAuth = SecurityContextHolder.getContext().getAuthentication();
		if(userAuth != null && !"anonymousUser".equals(userAuth.getPrincipal())) {
			String username = SecurityContextHolder.getContext().getAuthentication().getName();
			User user = userRepository.findByUsername(username);
			model.addAttribute("user", user);
			model.addAttribute("bloc", new Bloc());
		}
		return "addBloc";
	}
	
	
	@PostMapping("/addBloc")
	public String addBloc(@Valid @ModelAttribute Bloc bloc, BindingResult result, Model model) {
		var userAuth = SecurityContextHolder.getContext().getAuthentication();
		if(userAuth != null && !"anonymousUser".equals(userAuth.getPrincipal())) {
			String username = SecurityContextHolder.getContext().getAuthentication().getName();
			User user = userRepository.findByUsername(username);
			if (result.hasErrors()) {
				return "redirect:/addDossier";
			}
			Dossier dossier = user.getDossier();
			dossier.getBlocs().add(bloc);
			bloc.setDossier(dossier);
			model.addAttribute("dossier", dossierRepository.save(dossier));
			model.addAttribute("user", userRepository.save(user));
			model.addAttribute("hasDossier", user.getDossier() != null);
		}
		return "redirect:/dossier";
	}	
	
	
	@GetMapping("/addCp")
	public String addCp(@RequestParam Integer blocId, Model model) {
		var userAuth = SecurityContextHolder.getContext().getAuthentication();
		if(userAuth != null && !"anonymousUser".equals(userAuth.getPrincipal())) {
			String username = SecurityContextHolder.getContext().getAuthentication().getName();
			User user = userRepository.findByUsername(username);
			Bloc bloc = blocRepository.findById(blocId).orElseThrow();
			model.addAttribute("user", user);
			model.addAttribute("bloc", bloc);
			model.addAttribute("cp", new Competence());
		}
		return "addCp";
	}
	
	
	@PostMapping("/addCp")
	public String addCp(@RequestParam Integer blocId,  @Valid @ModelAttribute Competence cp, BindingResult result, Model model) {
		var userAuth = SecurityContextHolder.getContext().getAuthentication();
		if(userAuth != null && !"anonymousUser".equals(userAuth.getPrincipal())) {
			String username = SecurityContextHolder.getContext().getAuthentication().getName();
			User user = userRepository.findByUsername(username);
			if (result.hasErrors()) {
				return "redirect:/addDossier";
			}
			Dossier dossier = user.getDossier();
			Bloc bloc = blocRepository.findById(blocId).orElseThrow();
			bloc.getCompetences().add(cp);
			cp.setBloc(bloc);
			model.addAttribute("dossier", dossierRepository.save(dossier));
			model.addAttribute("user", userRepository.save(user));
			model.addAttribute("hasDossier", user.getDossier() != null);
		}
		return "redirect:/dossier";
	}
	
	
}
