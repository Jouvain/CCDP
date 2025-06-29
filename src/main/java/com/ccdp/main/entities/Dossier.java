package com.ccdp.main.entities;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
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
@ToString(exclude = "user")
public class Dossier {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Integer id;
	@NonNull
	String title;
	
	@OneToOne
	@JoinColumn(name = "user_id", nullable = false, unique = true)
	User user;
	
	@OneToMany(mappedBy = "dossier", cascade = CascadeType.ALL, orphanRemoval = true)
	List<Bloc> blocs;

}
