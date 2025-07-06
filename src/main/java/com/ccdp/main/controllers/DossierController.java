package com.ccdp.main.controllers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
import com.ccdp.main.repositories.CompetenceRepository;
import com.ccdp.main.repositories.DossierRepository;
import com.ccdp.main.repositories.ExempleRepository;
import com.ccdp.main.repositories.UserRepository;

import jakarta.validation.Valid;

import com.ccdp.main.entities.Bloc;
import com.ccdp.main.entities.Competence;
import com.ccdp.main.entities.Dossier;
import com.ccdp.main.entities.Exemple;
import com.ccdp.main.entities.User;
import org.springframework.web.bind.annotation.RequestBody;


@Controller
public class DossierController {
	
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private DossierRepository dossierRepository;
	
	@Autowired
	private BlocRepository blocRepository;
	
	@Autowired
	private CompetenceRepository competenceRepository;
	
	@Autowired
	private ExempleRepository exempleRepository;

	@GetMapping("/dossier")
	public String dossier(Model model) {
		var userAuth = SecurityContextHolder.getContext().getAuthentication();
		if(userAuth != null && !"anonymousUser".equals(userAuth.getPrincipal())) {
			String username = SecurityContextHolder.getContext().getAuthentication().getName();
			User user = userRepository.findByUsername(username);
			Dossier dossier = user.getDossier();
			Boolean hasDossier = dossier != null;
			
			if(hasDossier) {
				List<Bloc> blocs = user.getDossier().getBlocs();
				List<Integer> cpCountsList =  new ArrayList<Integer>();
				for (Bloc bloc : blocs) {
					for (Competence cp : bloc.getCompetences()) {
						cpCountsList.add(countCpInExemples(bloc, cp));	
						System.out.println(countCpInExemples(bloc, cp));
					}
				}
				Map<Integer, Integer> cpCountMap = new HashMap<>();
				for (Bloc bloc : blocs) {
				    for (Competence cp : bloc.getCompetences()) {
				        int count = countCpInExemples(bloc, cp);
				        cpCountMap.put(cp.getId(), count);
				    }
				}
				model.addAttribute("cpCountMap", cpCountMap);
				model.addAttribute("cpCountsList", cpCountsList);
				model.addAttribute("dossier", user.getDossier());
			}	
			
			model.addAttribute("user", user);
			model.addAttribute("logged", true);
			model.addAttribute("hasDossier", hasDossier);

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
	
	@GetMapping("/deleteDossier")
	public String deleteDossier(@RequestParam Integer dossierId) {
		var userAuth = SecurityContextHolder.getContext().getAuthentication();
		if(userAuth != null && !"anonymousUser".equals(userAuth.getPrincipal())) {
			String username = SecurityContextHolder.getContext().getAuthentication().getName();
			User user = userRepository.findByUsername(username);
			Dossier dossier = user.getDossier();
			List<Exemple> exemples = user.getExemples();
			if(dossier != null) {

				if(!exemples.isEmpty()) {
					for (Exemple exemple : exemples) {
						exemple.setCompetences(null);
						exemple.setBloc(null);
						exempleRepository.save(exemple);
					}
					exempleRepository.deleteAll(user.getExemples());
				}
				user.getExemples().clear();
	            user.setDossier(null); 
	            userRepository.save(user); 
	            dossierRepository.deleteById(dossier.getId());
			}			
		}	
		return "dossier";
	}
	
	@GetMapping("/deleteBloc")
	public String deleteBloc(@RequestParam Integer blocId, Model model) {
		var userAuth = SecurityContextHolder.getContext().getAuthentication();
		if(userAuth != null && !"anonymousUser".equals(userAuth.getPrincipal())) {
			String username = SecurityContextHolder.getContext().getAuthentication().getName();
			User user = userRepository.findByUsername(username);
			Dossier dossier = user.getDossier();
			List<Exemple> exemples = user.getExemples();
			if(dossier != null) {
				
				if(!exemples.isEmpty()) {
					for (Exemple exemple : exemples) {
						Boolean modif = false;
						List<Competence> cps = exemple.getCompetences();
						if(exemple.getCompetences().removeIf(cp -> cp.getBloc() != null && cp.getBloc().getId().equals(blocId))) {
							modif = true;
						}
						
					    if (exemple.getBloc() != null && exemple.getBloc().getId().equals(blocId)) {
					        exemple.setBloc(null);
					        modif = true;
					    }
					    if(modif) {
					    	exempleRepository.save(exemple);
						}
				    }
						
				}				
				
				Bloc bloc = blocRepository.findById(blocId).orElseThrow();
				dossier.getBlocs().removeIf(b -> b.getId().equals(blocId));
				bloc.setDossier(null);
				dossierRepository.save(dossier);
				blocRepository.delete(bloc);
				model.addAttribute("dossier", dossier);
			}			
		}
		return "redirect:/dossier";
	}
	
	@GetMapping("/deleteCp")
	public String deleteCp(@RequestParam Integer cpId, @RequestParam Integer blocId, Model model) {
		var userAuth = SecurityContextHolder.getContext().getAuthentication();
		if(userAuth != null && !"anonymousUser".equals(userAuth.getPrincipal())) {
			String username = SecurityContextHolder.getContext().getAuthentication().getName();
			User user = userRepository.findByUsername(username);
			List<Exemple> exemples = user.getExemples();

			Dossier dossier = user.getDossier();
			if(dossier != null) {
				Bloc bloc = blocRepository.findById(blocId).orElseThrow();
				Competence competence = competenceRepository.findById(cpId).orElseThrow();
				
				if(!exemples.isEmpty()) {
					for (Exemple exemple : exemples) {
						List<Competence> cps = exemple.getCompetences();
						cps.removeIf(cp -> cp.getId().equals(cpId));
						exemple.setCompetences(cps);
						exempleRepository.save(exemple);
					}
				}
				
				bloc.getCompetences().removeIf(c -> c.getId().equals(cpId));
				competence.setBloc(null);
				
				competenceRepository.save(competence);
				blocRepository.save(bloc);
				dossierRepository.save(dossier);
				model.addAttribute("dossier", dossier);
			}			
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
	
	
	@GetMapping("/integrateExemple")
	public String integrateExemple(@RequestParam Integer blocId, Model model) {
		var userAuth = SecurityContextHolder.getContext().getAuthentication();
		if(userAuth != null && !"anonymousUser".equals(userAuth.getPrincipal())) {
			String username = SecurityContextHolder.getContext().getAuthentication().getName();
			User user = userRepository.findByUsername(username);
			Bloc bloc = blocRepository.findById(blocId).orElseThrow();
			model.addAttribute("user", user);
			model.addAttribute("bloc", bloc);
			model.addAttribute("hasExemples", !user.getExemples().isEmpty());
		}		
		return "integrateExemple";
	}
	
	@PostMapping("/integrateExemple")
	public String integrateExemple(@RequestParam Integer exempleId, @RequestParam Integer blocId, Model model) {
		var userAuth = SecurityContextHolder.getContext().getAuthentication();
		if(userAuth != null && !"anonymousUser".equals(userAuth.getPrincipal())) {
			String username = SecurityContextHolder.getContext().getAuthentication().getName();
			User user = userRepository.findByUsername(username);
			Bloc bloc = blocRepository.findById(blocId).orElseThrow();
			Exemple exemple = exempleRepository.findById(exempleId).orElseThrow();
			bloc.getExemples().add(exemple);
			exemple.setBloc(bloc);
			exempleRepository.save(exemple);
			blocRepository.save(bloc);
			model.addAttribute("user", user);
			model.addAttribute("logged", true);
			model.addAttribute("dossier", user.getDossier());
			model.addAttribute("hasDossier", user.getDossier() != null);
		}		
		return "redirect:/dossier";
	}
	
	
	@GetMapping("/removeExFromBloc")
	public String removeExFromBloc(@RequestParam Integer exempleId, @RequestParam Integer blocId, Model model) {
		var userAuth = SecurityContextHolder.getContext().getAuthentication();
		if(userAuth != null && !"anonymousUser".equals(userAuth.getPrincipal())) {
			String username = SecurityContextHolder.getContext().getAuthentication().getName();
			User user = userRepository.findByUsername(username);
			Bloc bloc = blocRepository.findById(blocId).orElseThrow();
			Exemple exemple = exempleRepository.findById(exempleId).orElseThrow();
			
			exemple.setBloc(null);
			exempleRepository.save(exemple);
			bloc.getExemples().removeIf(e -> e.getId().equals(exemple.getId()));
			blocRepository.save(bloc);
			model.addAttribute("user", user);
			model.addAttribute("logged", true);
			model.addAttribute("dossier", user.getDossier());
			model.addAttribute("hasDossier", user.getDossier() != null);
		}
		return "dossier";
	}
	
	
	
	public int countCpInExemples(Bloc bloc, Competence cp) {
	    int count = 0;
	    for (Exemple exemple : bloc.getExemples()) {
	        if (exemple.getCompetences().contains(cp)) {
	            count++;
	        }
	    }
	    return count;
	}
	
	
	
}
