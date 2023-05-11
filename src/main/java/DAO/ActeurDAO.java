package DAO;

import java.util.List;
import java.util.ArrayList;

import model.Role;
import model.Film;
import model.Acteur;

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

    // RETOURNE LA LISTE DES ROLES DUN ACTEUR
    public static List<Role> getRolesForActor(EntityManager em, Acteur acteur){
        TypedQuery<Role> queryRole = em.createNamedQuery("Role.getRolesForActorId", Role.class);
        queryRole.setParameter("acteur", acteur);
        List<Role> roles = queryRole.getResultList();
        return roles;
    }

    //RETOURNE LA FILMOGRAPHIE POUR UNE IDENTITE D ACTEUR
    public static List<Film> getFilmographieForActor(EntityManager em, String identite){
        Acteur acteur = getActeur(em, identite);
        List<Role> roles = getRolesForActor(em, acteur);
        List<Film> films = new ArrayList<Film>();
        for (Role role : roles){
            films.add(role.getFilm());
        }
        return films;
    }
    
}