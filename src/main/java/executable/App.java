package executable;

import model.Acteur;
import model.Film;
import model.Genre;
import model.Lieu;
import model.Pays;
import model.Realisateur;
import model.Role;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import jakarta.persistence.EntityManager;

public class App {
    public static void main(String[] args) {
        EntityManager em = JPAUtils.getInstance().getEntityManager();     
        
        Film film = new Film();
        film.setTitle("tchoinnnnnn");
        film.setUrl("http://sdfqsdf");
        
        List<Film> filmList = new ArrayList<>(
            Arrays.asList(
                film, film
            )
        );
        
        Genre genre = new Genre();
        genre.setNom("Ce genre de tchoin");
        // genre.setFilms(filmList);
        
        List<Genre> genres = new ArrayList<>(
            Arrays.asList(
                genre, genre
            )
        );
        film.setGenres(genres);


        Acteur acteur = new Acteur();
        acteur.setIdentite("Sacha Baron Cohen");
        acteur.setDateNaissance(new Date());
        acteur.setLieuNaissance("La zone sur Saone");


        Role role = new Role();
        role.setNomPersonnage("Luke Skywalker");
        role.setActeur(acteur);
        role.setFilm(film);
        Role role2 = new Role();
        role2.setNomPersonnage("Dark Vador");
        role2.setActeur(acteur);
        role2.setFilm(film);
        List<Role> roles = new ArrayList<>();
        roles.add(role);
        roles.add(role2);

        film.setCasting(roles);

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

        em.persist(film);

        em.getTransaction().commit();
        em.close();
    }
}
