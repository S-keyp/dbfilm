package model;

import jakarta.persistence.Id;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.NamedQueries;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.JoinColumn;

import jakarta.persistence.CascadeType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


@Entity
@NamedQueries({
	@NamedQuery(name = "Role.getRolesForActorId", query = "SELECT r FROM Role r WHERE r.acteur = :acteur"),
	@NamedQuery(name = "Role.getRolesForFilm", query = "SELECT r FROM Role r WHERE r.film = :film"),
})
@JsonIgnoreProperties(value = {"film"})
public class Role {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String nomPersonnage;
	
	@ManyToOne
	@JoinColumn(name="id_film")
	private Film film;
	
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name="id_acteur")
	private Acteur acteur;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@JsonProperty("characterName")
	public String getNomPersonnage() {
		return nomPersonnage;
	}

	public void setNomPersonnage(String nomPersonnage) {
		this.nomPersonnage = nomPersonnage;
	}

	public Film getFilm() {
		return film;
	}

	public void setFilm(Film film) {
		this.film = film;
	}

	public Acteur getActeur() {
		return acteur;
	}

	public void setActeur(Acteur acteur) {
		this.acteur = acteur;
	}

	@Override
	public String toString() {
		return "Role [nomPersonnage=" + nomPersonnage + ", film=" + film + ", acteur=" + acteur + "]";
	}

}
