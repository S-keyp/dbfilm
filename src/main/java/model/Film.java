package model;

import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSetter;

import java.util.ArrayList;
import jakarta.persistence.Id;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.CascadeType;

@Entity
@JsonIgnoreProperties(value = { "castingPrincipal", "genres" })
public class Film {
	@Id
	private String id;

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "id_pays")
	private Pays pays;

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "id_lieu")
	@JsonProperty("lieuTournage")
	private Lieu lieu;

	@ManyToMany(cascade = CascadeType.MERGE)
	private List<Realisateur> realisateurs = new ArrayList<>();

	@ManyToMany(cascade = CascadeType.MERGE)
	private List<Genre> genres = new ArrayList<>();

	@OneToMany(mappedBy = "film", cascade = CascadeType.ALL)
	private List<Role> roles = new ArrayList<>();

	@JsonProperty("nom")
	private String title;

	private String url;

	private String plot;

	private String langue;

	private Date anneeSortie;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	@JsonProperty("nom")
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getPlot() {
		return plot;
	}

	public void setPlot(String plot) {
		this.plot = plot;
	}

	public String getLangue() {
		return langue;
	}

	public void setLangue(String langue) {
		this.langue = langue;
	}

	@JsonProperty("lieuTournage")
	public Lieu getLieu() {
		return lieu;
	}

	public void setLieu(Lieu lieu) {
		this.lieu = lieu;
	}

	public Pays getPays() {
		return pays;
	}

	public void setPays(Pays pays) {
		this.pays = pays;
	}

	public List<Realisateur> getRealisateurs() {
		return realisateurs;
	}

	public void setRealisateurs(List<Realisateur> realisateurs) {
		this.realisateurs = realisateurs;
	}

	public List<Role> getRoles() {
		return roles;
	}

	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}

	public Date getAnneeSortie() {
		return anneeSortie;
	}

	public void setAnneeSortie(Date anneeSortie) {
		this.anneeSortie = anneeSortie;
	}

	public List<Genre> getGenres() {
		return genres;
	}

	public void setGenres(List<Genre> genres) {
		this.genres = genres;
	}

	@Override
	public String toString() {
		return "Film [id=" + id + ", title=" + title + ", ROLES=" + roles + ", plot=" + plot + ", anneeSortie="
				+ anneeSortie + ", pays=" + pays + ", REALISATEURS=" + realisateurs + "]";
	}

}
