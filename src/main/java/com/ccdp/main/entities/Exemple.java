package com.ccdp.main.entities;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
@RequiredArgsConstructor
public class Exemple {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Integer id;
	@NonNull
	String title;

	@ManyToOne
	@JoinColumn(name = "user_id", nullable = false)
	User user;
	
    @ManyToOne
    @JoinColumn(name = "bloc_id", nullable = true)
    Bloc bloc;

	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(
			  name = "exemple_competence",
			  joinColumns = @JoinColumn(name = "exemple_id"),
			  inverseJoinColumns = @JoinColumn(name = "competence_id")
			)
	List<Competence> competences;

}
