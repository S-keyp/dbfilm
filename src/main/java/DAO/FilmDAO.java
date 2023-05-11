package DAO;

import executable.App;

import utils.Parser;

import model.Role;
import model.Film;
import model.Acteur;

import java.util.List;
import java.util.Calendar;
import java.util.ArrayList;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceException;

public class FilmDAO {
    public static EntityManager em = App.em;
    
    public static void init() throws Exception {
        Film[] films = Parser.parse();
        
        for(Film film :films){
            try {
                em.getTransaction().begin();

                em.merge(film);

                em.getTransaction().commit();
            }catch(PersistenceException e){
                e.printStackTrace();
                em.getTransaction().rollback();
            }
        }
    }
    
    /**
     * RECHERCHER FILM
     * @param filmTitle String
     * @return Film
     */
    public static Film getFilm(String filmTitle){
        return em
            .createNamedQuery("Film.findFilm", Film.class)
            .setParameter("title", "%" + filmTitle + "%")
            .getResultList()
            .stream()
            .findFirst()
            .orElse(null);
    }

    /**
     * RETURN FILMS BETWEEN 2 YEARS
     * @param yearStart int
     * @param yearEnd int
     * @return List<Film>
     */
    public static List<Film> getFilmsBetweenYears(int yearStart, int yearEnd){
        Calendar cal1 = Calendar.getInstance();
        cal1.set(Calendar.YEAR, yearStart);
        cal1.set(Calendar.MONTH, 0);
        cal1.set(Calendar.DAY_OF_MONTH, 1);  
        Calendar cal2 = Calendar.getInstance();
        cal2.set(Calendar.YEAR, yearEnd);
        cal2.set(Calendar.MONTH, 0);
        cal2.set(Calendar.DAY_OF_MONTH, 1);  

        return  em
            .createNamedQuery("Film.findFilmBetweenYears", Film.class)
            .setParameter("date1", cal1.getTime())
            .setParameter("date2", cal2.getTime())
            .getResultList();
    }

    /**
     * RETURN FILMS BETWEEN 2 YEARS WITH SPECIFIED ACTOR
     * @param yearStart int
     * @param yearEnd int 
     * @param identite String
     * @return List<Film>
     */
    public static List<Film> getMoviesForActorBetweenYears(int yearStart, int yearEnd, String identite){
        Calendar cal1 = Calendar.getInstance();
        cal1.set(Calendar.YEAR, yearStart);
        cal1.set(Calendar.MONTH, 0);
        cal1.set(Calendar.DAY_OF_MONTH, 1);  
        Calendar cal2 = Calendar.getInstance();
        cal2.set(Calendar.YEAR, yearEnd);
        cal2.set(Calendar.MONTH, 0);
        cal2.set(Calendar.DAY_OF_MONTH, 1);  

        List<Film> films = ActeurDAO.getFilmographieForActor(identite);

        for(Film film : films){
            if(cal1.after(film.getAnneeSortie())) films.remove(film);
            if(cal2.before(film.getAnneeSortie())) films.remove(film);
        }

        return films;
    }

    /**
     * RETURN LIST OF COMMON MOVIES BETWEEN TWO ACTORS
     * @param identite1 String
     * @param identite2 String
     * @return List<Film>
     */
    public static List<Film> getCommonFilmsForActors(String identite1, String identite2){
        List<Film> filmsCommun = new ArrayList<>();

        List<Film> filmsForActor1 = ActeurDAO.getFilmographieForActor(identite1);
        Acteur acteur2 = ActeurDAO.getActeur(identite2);    
        List<Role> rolesActeur2 = RoleDAO.getRolesForActor(acteur2);

        for(Film film : filmsForActor1){
            for(Role roleFilmsActeur1 : film.getRoles()){
                for(Role role : rolesActeur2){
                    if(roleFilmsActeur1 == role) filmsCommun.add(film);
                }
            }            
        }
        
        return filmsCommun;
    }

}
