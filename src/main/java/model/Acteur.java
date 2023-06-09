package model;

import java.util.List;

import java.util.ArrayList;
import jakarta.persistence.Id;
import jakarta.persistence.Entity;
import jakarta.persistence.Embedded;
import jakarta.persistence.OneToMany;
import jakarta.persistence.FetchType;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.NamedQueries;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


@Entity
@NamedQueries({
	@NamedQuery(name="Acteur.findActeur", query="SELECT f FROM Acteur f WHERE f.identite LIKE :identite"),
})
@JsonIgnoreProperties(value = {"height, roles"})
public class Acteur {
	@Id
	private String id;

	
	private String identite;
	
	@Embedded
	private Naissance naissance;
	
	private String url;
	
	private Float height;
	
	@OneToMany(mappedBy = "acteur", fetch = FetchType.LAZY)
	private List<Role> roles = new ArrayList<>();

	public String getid() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getIdentite() {
		return identite;
	}

	public void setIdentite(String identite) {
		this.identite = identite;
	}

	public Naissance getnaissance() {
		return naissance;
	}

	public void setNaissance(Naissance naissance){
		this.naissance = naissance;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public Float getHeight() {
		return height;
	}

	public void setHeight(Float height) {
		this.height = height;
	}

	public List<Role> getRoles() {
		return roles;
	}

	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}

	@Override
	public String toString() {
		return "\n Acteur [identite de l'acteur= " + identite + "] \n";
	}
}
