package DAO;

import java.util.List;

import executable.App;

import java.util.ArrayList;

import model.Role;
import model.Film;
import model.Acteur;

import jakarta.persistence.TypedQuery;
import jakarta.persistence.EntityManager;

public class RoleDAO {
    public static EntityManager em = App.em;

    /**
     * RETOURNE LA LISTE DES ROLES DUN ACTEUR
     * @param em EntityManager
     * @param acteur Acteur
     * @return List<Role>
     */
    public static List<Role> getRolesForActor(Acteur acteur){
        TypedQuery<Role> queryRole = em.createNamedQuery("Role.getRolesForActorId", Role.class);
        queryRole.setParameter("acteur", acteur);
        List<Role> roles = queryRole.getResultList();
        return roles;
    }

    //RECHERCHE ACTEURS POUR UN FILM 
    public static List<Acteur> getActorsForFilm(String filmTitle){
        Film film = FilmDAO.getFilm(filmTitle);
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
