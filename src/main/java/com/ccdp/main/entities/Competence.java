package com.ccdp.main.entities;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
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
@ToString(exclude = "bloc")
public class Competence {
	 @Id
	 @GeneratedValue(strategy = GenerationType.IDENTITY)
	 Integer id;
	 @NonNull
	 String title;
	 
	 @ManyToOne
	 @JoinColumn(name = "bloc_id", nullable = false)
	 Bloc bloc;
	 

}
