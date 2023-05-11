package DAO;

import model.Role;
import model.Film;
import Utils.Parser;
import executable.App;
import model.Acteur;
import Utils.JPAUtils;

import java.util.List;
import java.util.Calendar;
import java.util.ArrayList;

import jakarta.persistence.TypedQuery;
import jakarta.persistence.EntityManager;

public class FilmDAO {
    public static EntityManager em = App.em;
    
    public static void init() throws Exception {
        Film[] films = Parser.parse();
        
        for(Film film :films){
            try {
                em.getTransaction().begin();

                em.merge(film);

                em.getTransaction().commit();
            }catch(Exception e){
                e.printStackTrace();
            }
        }
        
        // TEST QUERIES
        // System.out.println("Filmographie: " + ActeurDAO.getFilmographieForActor("Chris"));
        // System.out.println("Film: " + getFilm(em, "Ninja"));
        // System.out.println("Aceturs pour le film: " + RoleDAO.getActorsForFilm(em, "Ninja"));
        // System.out.println("Film entre 2014 et 2019: " + getFilmsBetweenYears(em, 2014, 2019));
        // System.out.println("Film entre 2012 et 2019 pour Pratt: " + getMoviesForActorBetweenYears(em, 2012, 2019, "Pratt"));
        // System.out.println("Film communs à Chris et Millie: " + getCommonFilmsForActors(em, "Chris", "Millie"));
        // System.out.println("Film acteurs communs à Electric et Ninja: " + ActeurDAO.getCommonActorsForFilms("Electric", "Ninja"));;
        
        
        // em.close();

    }
    
   
    //RECHERCHER FILM
    public static Film getFilm(String filmTitle){
        TypedQuery<Film> queryRole = em.createNamedQuery("Film.findFilm", Film.class);
        filmTitle = "%" + filmTitle + "%";
        queryRole.setParameter("title", filmTitle);
        Film film = queryRole.getResultList().get(0);
        return film;
    }

    // RETURN FILMS BETWEEN 2 YEARS
    public static List<Film> getFilmsBetweenYears(int yearStart, int yearEnd){
        Calendar cal1 = Calendar.getInstance();
        cal1.set(Calendar.YEAR, yearStart);
        cal1.set(Calendar.MONTH, 0);
        cal1.set(Calendar.DAY_OF_MONTH, 1);  
        Calendar cal2 = Calendar.getInstance();
        cal2.set(Calendar.YEAR, yearEnd);
        cal2.set(Calendar.MONTH, 0);
        cal2.set(Calendar.DAY_OF_MONTH, 1);  

        TypedQuery<Film> query = em.createNamedQuery("Film.findFilmBetweenYears", Film.class);
        query.setParameter("date1", cal1.getTime());
        query.setParameter("date2", cal2.getTime());
        return query.getResultList();
    }

    // RETURN FILMS BETWEEN 2 YEARS WITH SPECIFIED ACTOR
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

    // RETURN LIST OF COMMON MOVIES BETWEEN TWO ACTORS
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
