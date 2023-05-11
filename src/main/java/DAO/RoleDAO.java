package DAO;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import model.Acteur;
import model.Film;
import model.Role;

public class RoleDAO {
    
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
