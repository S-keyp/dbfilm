package DAO;

import model.Role;
import model.Film;
import model.Acteur;

import java.util.List;
import java.util.ArrayList;

import jakarta.persistence.TypedQuery;
import jakarta.persistence.EntityManager;

public class ActeurDAO{
    //RETOURNE UN ACTEUR POUR UNE IDENTITE
    public static Acteur getActeur(EntityManager em, String identite){
        TypedQuery<Acteur> queryActor = em.createNamedQuery("Acteur.findActeur", Acteur.class);
        identite = "%" + identite + "%";
        queryActor.setParameter("identite", identite);
        List<Acteur> acteur = queryActor.getResultList();
        return acteur.get(0);
    }

    //RETOURNE LA FILMOGRAPHIE POUR UNE IDENTITE D ACTEUR
    public static List<Film> getFilmographieForActor(EntityManager em, String identite){
        Acteur acteur = getActeur(em, identite);
        List<Role> roles = RoleDAO.getRolesForActor(em, acteur);
        List<Film> films = new ArrayList<Film>();
        for (Role role : roles){
            films.add(role.getFilm());
        }
        return films;
    }

    // RETURN LIST OF COMMON ACTORS BETWEEN TWO MOVIES
    public static List<Acteur> getCommonActorsForFilms(EntityManager em, String title1, String title2){
        List<Acteur> acteursCommun = new ArrayList<>();

        List<Role> rolesFilm2 = FilmDAO.getFilm(em, title2).getRoles();  
        List<Role> rolesFilm1 = FilmDAO.getFilm(em, title1).getRoles(); 

        for(Role role1 : rolesFilm1){
            for(Role role2 : rolesFilm2){
                if(role1.getActeur() == role2.getActeur()) acteursCommun.add(role1.getActeur());
            }
        }
        
        return acteursCommun;
    }
    
}