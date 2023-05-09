package model;

import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JacksonInject;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSubTypes;

import java.util.ArrayList;
import jakarta.persistence.Id;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.CascadeType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;


@Entity
public class Film {
    @Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@JsonIgnore
    private Long id;

	@Column(unique = true)
	@JsonProperty("id")
	private String ref;

	@ManyToOne(cascade = CascadeType.PERSIST)
	@JoinColumn(name="id_pays")
	private Pays pays;
	
	@ManyToOne(cascade = CascadeType.PERSIST)
	@JoinColumn(name="id_lieu")
	@JsonProperty("lieuTournage")
	private Lieu lieu;

	@ManyToMany(cascade = CascadeType.PERSIST)
    private List<Realisateur> realisateurs = new ArrayList<>();
	
	@ManyToMany(cascade = CascadeType.PERSIST)
    private List<Genre> genres = new ArrayList<>();

	@OneToMany(mappedBy = "film", cascade = CascadeType.PERSIST)
	@JsonProperty("castingPrincipal")
	private List<Role> casting = new ArrayList<>();

	@JsonProperty("nom")
	private String title;

    private String url;
    
    private String plot;

    private String langue;    

    private Date anneeSortie;

    public Long getId() {
		return id;
	}

    public void setId(Long id) {
		this.id = id;
	}

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

	public List<Role> getCasting() {
		return casting;
	}

	public void setCasting(List<Role> casting) {
		this.casting = casting;
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
}
