package DAO;

import model.Role;
import model.Film;
import model.Acteur;

import java.util.List;

import executable.App;

import java.util.ArrayList;

import jakarta.persistence.TypedQuery;
import jakarta.persistence.EntityManager;

public class ActeurDAO{
    public static EntityManager em = App.em;

    /**
     * RETOURNE UN ACTEUR POUR UNE IDENTITE
     * @param em EntityManager
     * @param identite String
     * @return Acteur
     */
    public static Acteur getActeur(String identite){
        return em
            .createNamedQuery("Acteur.findActeur", Acteur.class)
            .setParameter("identite",  "%" + identite + "%")
            .getResultList()
            .stream()
            .findFirst()
            .orElse(null);
    }

    //RETOURNE LA FILMOGRAPHIE POUR UNE IDENTITE D ACTEUR
    public static List<Film> getFilmographieForActor(String identite){
        Acteur acteur = getActeur(identite);
        List<Role> roles = RoleDAO.getRolesForActor(acteur);
        List<Film> films = new ArrayList<Film>();
        for (Role role : roles){
            films.add(role.getFilm());
        }
        return films;
    }

    // RETURN LIST OF COMMON ACTORS BETWEEN TWO MOVIES
    public static List<Acteur> getCommonActorsForFilms(String title1, String title2){
        List<Acteur> acteursCommun = new ArrayList<>();

        List<Role> rolesFilm2 = FilmDAO.getFilm(title2).getRoles();  
        List<Role> rolesFilm1 = FilmDAO.getFilm(title1).getRoles(); 

        for(Role role1 : rolesFilm1){
            for(Role role2 : rolesFilm2){
                if(role1.getActeur() == role2.getActeur()) acteursCommun.add(role1.getActeur());
            }
        }
        
        return acteursCommun;
    }
    
}