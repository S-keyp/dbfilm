package model;

import java.util.Date;

import jakarta.persistence.Id;
import jakarta.persistence.Entity;

@Entity
public class Film {
    @Id
    private String id;

    private String title;

    private String url;
    
    private String plot;

    private String langue;

    private String lieu;

    private String realisateur;

    private String castingPrincipal;

    private Date anneeSortie;

	

    public String getId() {
		return id;
	}

    public void setId(String id) {
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

	public String getLieu() {
		return lieu;
	}

	public void setLieu(String lieu) {
		this.lieu = lieu;
	}

	public String getRealisateur() {
		return realisateur;
	}

	public void setRealisateur(String realisateur) {
		this.realisateur = realisateur;
	}

	public String getCastingPrincipal() {
		return castingPrincipal;
	}

	public void setCastingPrincipal(String castingPrincipal) {
		this.castingPrincipal = castingPrincipal;
	}

	public Date getAnneeSortie() {
		return anneeSortie;
	}

	public void setAnneeSortie(Date anneeSortie) {
		this.anneeSortie = anneeSortie;
	}
}
