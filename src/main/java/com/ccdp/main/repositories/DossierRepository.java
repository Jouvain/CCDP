package com.ccdp.main.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ccdp.main.entities.Dossier;

public interface DossierRepository extends JpaRepository<Dossier, Integer>{

}
