package DAO;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import model.Acteur;
import model.Film;
import model.Role;

public class RoleDAO {
    
    /**
     * RETOURNE LA LISTE DES ROLES DUN ACTEUR
     * @param em EntityManager
     * @param acteur Acteur
     * @return List<Role>
     */
    public static List<Role> getRolesForActor(EntityManager em, Acteur acteur){
        TypedQuery<Role> queryRole = em.createNamedQuery("Role.getRolesForActorId", Role.class);
        queryRole.setParameter("acteur", acteur);
        List<Role> roles = queryRole.getResultList();
        return roles;
    }


    //RECHERCHE ACTEURS POUR UN FILM 
    public static List<Acteur> getActorsForFilm(EntityManager em, String filmTitle){
        Film film = FilmDAO.getFilm(em, filmTitle);
        TypedQuery<Role> queryRole = em.createNamedQuery("Role.getRolesForFilm", Role.class);
        queryRole.setParameter("film", film);
        List<Role> roles = queryRole.getResultList();
        List<Acteur> acteurs = new ArrayList<>();
        for(Role role: roles){
            acteurs.add(role.getActeur());
        }
        return acteurs;
    }

}
