package model;

import java.util.List;
import java.util.ArrayList;
import jakarta.persistence.Id;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;


@Entity
public class Genre {
	@Id	
	private String nom;
	 
	@ManyToMany(mappedBy = "listGenres")
	private List<Film> films = new ArrayList<>();

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public List<Film> getFilms() {
		return films;
	}

	public void setFilms(List<Film> films) {
		this.films = films;
	}
}
