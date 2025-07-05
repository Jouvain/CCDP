package com.ccdp.main.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.ccdp.main.entities.Bloc;
import com.ccdp.main.entities.Competence;
import com.ccdp.main.entities.Dossier;
import com.ccdp.main.entities.Exemple;
import com.ccdp.main.entities.User;
import com.ccdp.main.repositories.CompetenceRepository;
import com.ccdp.main.repositories.ExempleRepository;
import com.ccdp.main.repositories.UserRepository;

import jakarta.validation.Valid;

@Controller
public class ExempleController {
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	ExempleRepository exempleRepository;
	
	@Autowired
	CompetenceRepository competenceRepository;
	
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
	
	
	@GetMapping("/deleteExemple")
	public String deleteExemple(@RequestParam Integer exempleId, Model model) {
		var userAuth = SecurityContextHolder.getContext().getAuthentication();
		if(userAuth != null && !"anonymousUser".equals(userAuth.getPrincipal())) {
			String username = SecurityContextHolder.getContext().getAuthentication().getName();
			User user = userRepository.findByUsername(username);
			Exemple exemple = exempleRepository.findById(exempleId).orElseThrow();
			user.getExemples().removeIf(e -> e.getId().equals(exempleId));
			exemple.setUser(null);
			exempleRepository.delete(exemple);
			userRepository.save(user);
			model.addAttribute("user", user);
			model.addAttribute("logged", true);
			model.addAttribute("exemples", user.getExemples());
			model.addAttribute("hasExemple", !user.getExemples().isEmpty());				
		}	
		return "redirect:/exemples";
	}
	
	@GetMapping("/integrateCp")
	public String integrateCp(@RequestParam Integer exempleId,  Model model) {
		var userAuth = SecurityContextHolder.getContext().getAuthentication();
		if(userAuth != null && !"anonymousUser".equals(userAuth.getPrincipal())) {
			String username = SecurityContextHolder.getContext().getAuthentication().getName();
			User user = userRepository.findByUsername(username);
			Dossier dossier = user.getDossier();
			List<Bloc> blocs = dossier.getBlocs();
			List<Competence> competences = new ArrayList<Competence>();
			for (Bloc bloc : blocs) {
				List<Competence> cps = bloc.getCompetences();
				System.out.println(bloc.getCompetences());
				for (Competence competence : cps) {
					competences.add(competence);
				}
			}
			for (Competence competence : competences) {
				System.out.println("Compétence retenue = " + competence.getTitle());
			}
			Exemple exemple = exempleRepository.findById(exempleId).orElseThrow();
//			model.addAttribute("user", user);			
			model.addAttribute("competences", competences);			
			model.addAttribute("exemple", exemple);
		}
		return "integrateCp";
	}
	

	@PostMapping("/integrateCp")
	public String integrateCp(@RequestParam("cpId") Integer cpId, @RequestParam("exempleId") Integer exempleId,  Model model) {
		var userAuth = SecurityContextHolder.getContext().getAuthentication();
		if(userAuth != null && !"anonymousUser".equals(userAuth.getPrincipal())) {
			String username = SecurityContextHolder.getContext().getAuthentication().getName();
			User user = userRepository.findByUsername(username);
			Dossier dossier = user.getDossier();
			List<Bloc> blocs = dossier.getBlocs();
			List<Competence> competences = new ArrayList<Competence>();
			for (Bloc bloc : blocs) {
				List<Competence> cps = bloc.getCompetences();
				for (Competence competence : cps) {
					competences.add(competence);
				}
			}
			Competence competence = competenceRepository.findById(cpId).orElseThrow();
			Exemple exemple = exempleRepository.findById(exempleId).orElseThrow();
			
			exemple.getCompetences().add(competence);
			
			exempleRepository.save(exemple);
			
			
			model.addAttribute("user", user);
			model.addAttribute("logged", true);
			model.addAttribute("exemples", user.getExemples());
			model.addAttribute("hasExemple", !user.getExemples().isEmpty());
		}
		return "redirect:/exemples";
	}
	
	
	@PostMapping("/removeCpFromEx")
	private String removeCpFromEx(@RequestParam Integer cpId, @RequestParam Integer exempleId, Model model) {
		var userAuth = SecurityContextHolder.getContext().getAuthentication();
		if(userAuth != null && !"anonymousUser".equals(userAuth.getPrincipal())) {
			String username = SecurityContextHolder.getContext().getAuthentication().getName();
			User user = userRepository.findByUsername(username);
			Exemple exemple = exempleRepository.findById(exempleId).orElseThrow();
			Competence competence = competenceRepository.findById(cpId).orElseThrow();
			exemple.getCompetences().removeIf(cp -> cpId.equals(cp.getId()));
			exempleRepository.save(exemple);
			competenceRepository.save(competence);
			model.addAttribute("user", user);
			model.addAttribute("logged", true);
			model.addAttribute("exemples", user.getExemples());
			model.addAttribute("hasExemple", !user.getExemples().isEmpty());
		}
		return "exemples";
	}
	
	
	
}
