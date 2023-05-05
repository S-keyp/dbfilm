package executable;

import model.Film;
import model.Lieu;
import model.Pays;
import model.Realisateur;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import jakarta.persistence.EntityManager;

public class App {
    public static void main(String[] args) {
        EntityManager em = JPAUtils.getInstance().getEntityManager();

        Film film = new Film();
        film.setTitle("tchoinnnnnn");
        film.setUrl("http://sdfqsdf");
        
        
        Lieu lieu = new Lieu();
        lieu.setEtatDept("EtatDept");
        lieu.setPays("Le Schlaguistant");
        lieu.setVille("La ville du schlaguistant");


        Pays pays = new Pays();
        pays.setNom("France");
        pays.setUrl("this is an url incredible");


        Realisateur real1 = new Realisateur();
        real1.setIdentite("John");
        real1.setUrl("https://John");
        Realisateur real2 = new Realisateur();
        real2.setIdentite("Jack");
        real2.setUrl("https://Jack");
        Realisateur real3 = new Realisateur();
        real3.setIdentite("Jul");
        real3.setUrl("https://Jul");     

        List<Realisateur> realList = new ArrayList<>(
            Arrays.asList(
                real1, real2, real3
            )
        );
        film.setRealisateurs(realList);      
        

        
        film.setPays(pays);
        film.setLieu(lieu);
        

        em.getTransaction().begin();

        em.persist(pays);
        em.persist(lieu);
        em.persist(real1);
        em.persist(real2);
        em.persist(real3);
        em.persist(film);

        em.getTransaction().commit();
        em.close();
    }
}
