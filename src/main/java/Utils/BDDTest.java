package Utils;

import model.Film;
import model.Lieu;
import model.Naissance;
import model.Pays;
import model.Role;
import model.Genre;
import model.Acteur;
import model.Realisateur;

import java.util.List;
import java.util.GregorianCalendar;
import java.util.Arrays;
import java.util.ArrayList;
import jakarta.persistence.EntityManager;


public class BDDTest {
    public static void main (String[] args){
        EntityManager em = JPAUtils.getInstance().getEntityManager();     


        //CREATION FILM
        Film film = new Film();
        film.setId("tt89df5");
        film.setTitle("The Dictator");
        film.setPlot("Un dictateur fou découvre l'amour en Amérique");
        film.setUrl("http://thedictator.com");
        film.setLangue("EN");
        // film.setLangue("EN");
        // film.setTitle("The Dictator");
        // film.setUrl("http://thedictator.com");
        // film.setPlot("Un dictateur fou découvre l'amour en Amérique");

        String dateSortieString = "2012";
        film.setAnneeSortie(dateSortieString);
        // int dateSortieInt = Integer.parseInt(dateSortieString);
        // Calendar cal = Calendar.getInstance();
        // cal.set(Calendar.YEAR, dateSortieInt);
        // cal.set(Calendar.MONTH, 0);
        // cal.set(Calendar.DAY_OF_MONTH, 1);
        // film.setAnneeSortie(cal.getTime());        


        //CREATION LISTE GENRES ASSOCIES A FILM
        Genre genreHumour = new Genre();
        genreHumour.setNom("Humour");
        Genre genreSatyre = new Genre();
        genreSatyre.setNom("Satyre");
        Genre genreAbsurd = new Genre();
        genreAbsurd.setNom("Absurde");
        List<Genre> genres = new ArrayList<>(
            Arrays.asList(
                genreHumour, 
                genreSatyre, 
                genreAbsurd
            )
        );
        film.setGenres(genres);


        //CREATION ACTEURS
        Acteur acteurSacha = new Acteur();
        acteurSacha.setId("TFFME56");
        acteurSacha.setIdentite("Sacha Baron Cohen");
        GregorianCalendar sachaCal = new GregorianCalendar(1971,9,13);
        Naissance naissanceSacha = new Naissance();
        naissanceSacha.setLieuNaissance("Hammersmith, Londres, Royaume-Uni");
        naissanceSacha.setDateNaissance(sachaCal.getTime().toString());
        acteurSacha.setNaissance(naissanceSacha);
        Acteur acteurJason = new Acteur();
        acteurJason.setId("FDURK98");
        acteurJason.setIdentite("Jason Mantzoukas");
        GregorianCalendar jasonCal = new GregorianCalendar(1972,11,18);
        Naissance naissanceJason = new Naissance();
        naissanceJason.setLieuNaissance("Nahant, Massachusetts, États-Unis");
        naissanceJason.setDateNaissance(jasonCal.getTime().toString());
        acteurJason.setNaissance(naissanceJason);


        //CREATION LIST ROLES
        Role roleDictateur = new Role();
        roleDictateur.setNomPersonnage("Le Dictateur de la Whalidya");
        roleDictateur.setActeur(acteurSacha);
        roleDictateur.setFilm(film);
        Role roleScientifique = new Role();
        roleScientifique.setNomPersonnage("Le scientifique & conseiller du Dictateur");
        roleScientifique.setActeur(acteurJason);
        roleScientifique.setFilm(film);
        List<Role> roles = new ArrayList<>();
        roles.add(roleDictateur);
        roles.add(roleScientifique);
        film.setRoles(roles);


        //CREATION LIEU & PAYS
        Lieu lieu = new Lieu();
        lieu.setEtatDept("New York");
        lieu.setPays("Etats-uni");
        lieu.setVille("New York");
        film.setLieu(lieu);
        Pays pays = new Pays();
        pays.setNom("Etats-uni");
        pays.setUrl("http://www.USA.com");
        film.setPays(pays);


        //CREATION REALISATEUR
        Realisateur realLarry = new Realisateur();
        realLarry.setIdentite("Larry Charles");
        realLarry.setUrl("https://larry-charles.com");
        Realisateur realScenario = new Realisateur();
        realScenario.setIdentite("Sacha Baron Cohen");
        realScenario.setUrl("https://sacha-baron-cohen");
        Realisateur realisateur3 = new Realisateur();
        realisateur3.setIdentite("Realisateur test list");
        realisateur3.setUrl("https://real.com");     
        List<Realisateur> realList = new ArrayList<>(
            Arrays.asList(
                realLarry, realScenario, realisateur3
            )
        );
        film.setRealisateurs(realList);      
        
        
        //TRANSACTIONS DB
        em.getTransaction().begin();


        em.merge(film);


        em.getTransaction().commit();
        em.close();

    }
}
