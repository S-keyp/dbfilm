package model;

import java.util.List;

import java.util.Date;
import java.util.Calendar;
import java.util.ArrayList;

import jakarta.persistence.Id;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.CascadeType;
import jakarta.persistence.NamedQueries;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@NamedQueries({
		@NamedQuery(name = "Film.findFilm", query = "SELECT f FROM Film f WHERE f.title LIKE :title"),
		@NamedQuery(name = "Film.findFilmBetweenYears", query = "SELECT f FROM Film f WHERE f.anneeSortie BETWEEN :date1 AND :date2"),
})
@JsonIgnoreProperties(value = { "castingPrincipal" })
public class Film {
	@Id
	private String id;

	@ManyToOne(cascade = CascadeType.MERGE)
	@JoinColumn(name = "id_pays")
	private Pays pays;

	@ManyToOne(cascade = CascadeType.MERGE)
	@JoinColumn(name = "id_lieu")
	@JsonProperty("lieuTournage")
	private Lieu lieu;

	@ManyToMany(cascade = CascadeType.MERGE)
	private List<Realisateur> realisateurs = new ArrayList<>();

	@ManyToMany(cascade = CascadeType.MERGE)
	private List<Genre> listGenres = new ArrayList<>();

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

	public void setAnneeSortie(String anneeSortie) {
		if (anneeSortie.length() > 4)
			anneeSortie = anneeSortie.substring(0, 4);
		int dateSortieInt = Integer.parseInt(anneeSortie);
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.YEAR, dateSortieInt);
		cal.set(Calendar.MONTH, 0);
		cal.set(Calendar.DAY_OF_MONTH, 1);
		this.anneeSortie = cal.getTime();
	}

	@JsonProperty("genres")
	private void transformListToGenreList(List<String> noms) {
		if (noms != null) {
			for (String nom : noms) {
				Genre genre = new Genre();
				genre.setNom(nom);
				genre.getFilms().add(this);
				listGenres.add(genre);
			}
		}
	}

	public List<Genre> getGenres() {
		return listGenres;
	}

	public void setGenres(List<Genre> listGenres) {
		this.listGenres = listGenres;
	}

	@Override
	public String toString() {
		return "Film [id=" + id + ", title=" + title + ", anneeSortie="
				+ anneeSortie + ", pays=" + pays + ", REALISATEURS=" + realisateurs + "]";
	}

}
