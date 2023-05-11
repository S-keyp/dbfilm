package DAO;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import Utils.JPAUtils;
import Utils.Parser;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import model.Acteur;
import model.Film;
import model.Role;

public class FilmDAO {
    public static void main(String[] args) throws Exception {
        EntityManager em = JPAUtils.getInstance().getEntityManager();   

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
        

       

        // TEST FILMOGRAPHIE
        // System.out.println(ActeurDAO.getFilmographieForActor(em, "Chris"));
        // System.out.println(getFilm(em, "Ninja"));
        // System.out.println(getActorsForFilm(em, "Ninja"));
        // System.out.println(getFilmsBetweenYears(em, 2014, 2019));
        System.out.println(getMoviesForActorBetweenYears(em, 2012, 2019, "Pra"));
        
        em.close();

    }



    























    
   
    //RECHERCHER FILM
    public static Film getFilm(EntityManager em, String filmTitle){
        TypedQuery<Film> queryRole = em.createNamedQuery("Film.findFilm", Film.class);
        filmTitle = "%" + filmTitle + "%";
        queryRole.setParameter("title", filmTitle);
        Film film = queryRole.getResultList().get(0);
        return film;
    }

    // RETURN FILMS BETWEEN 2 YEARS
    public static List<Film> getFilmsBetweenYears(EntityManager em, int yearStart, int yearEnd){
        //CONVERT STRING TO INT THEN DATE
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
    public static List<Film> getMoviesForActorBetweenYears(EntityManager em, int yearStart, int yearEnd, String identite){
        Calendar cal1 = Calendar.getInstance();
        cal1.set(Calendar.YEAR, yearStart);
        cal1.set(Calendar.MONTH, 0);
        cal1.set(Calendar.DAY_OF_MONTH, 1);  
        Calendar cal2 = Calendar.getInstance();
        cal2.set(Calendar.YEAR, yearEnd);
        cal2.set(Calendar.MONTH, 0);
        cal2.set(Calendar.DAY_OF_MONTH, 1);  

        List<Film> films = ActeurDAO.getFilmographieForActor(em, identite);

        for(Film film : films){
            if(cal1.after(film.getAnneeSortie())) films.remove(film);
            if(cal2.before(film.getAnneeSortie())) films.remove(film);
        }

        return films;
    }


}
