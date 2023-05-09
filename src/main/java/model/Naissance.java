package model;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

@Embeddable
public class Naissance {
    @Column(name = "lieuNaissance")
    private String lieuNaissance;
    @Column(name = "dateNaissance")
    private String dateNaissance;

    // Getters and Setters
    public String getLieuNaissance() {
        return lieuNaissance;
    }
    
    public String getDateNaissance() {
        return dateNaissance;
    }
    
    public void setLieuNaissance(String lieuNaissance) {
        this.lieuNaissance = lieuNaissance;
    }
    
    public void setDateNaissance(String dateNaissance) {
        this.dateNaissance = dateNaissance;
    }
}
