package com.ccdp.main.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class DossierController {

	@GetMapping("/dossier")
	public String dossier() {
		return "dossier";
	}
	
}
