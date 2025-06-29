package com.ccdp.main.entities;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.ToString;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
@RequiredArgsConstructor
@ToString(exclude = {"competences", "exemples"})
public class Bloc {
	 @Id
	 @GeneratedValue(strategy = GenerationType.IDENTITY)
	 Integer id;
	 @NonNull
	 String title;
	 
	 @ManyToOne
	 @JoinColumn(name = "dossier_id", nullable = false)
	 Dossier dossier;
	 
	 @OneToMany(mappedBy = "bloc", cascade = CascadeType.ALL, orphanRemoval = true)
	 List<Competence> competences;
	 
	 @OneToMany(mappedBy = "bloc", cascade = CascadeType.ALL, orphanRemoval = true)
	 List<Exemple> exemples;
	 
}
