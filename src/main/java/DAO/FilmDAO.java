package DAO;

import java.util.List;

import Utils.JPAUtils;
import Utils.Parser;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import model.Acteur;
import model.Film;

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
        

        // Get movie by id
        // TypedQuery<Film> query = em.createNamedQuery("Film.findAll", Film.class);
        // query.setParameter("id", "tt1801045");
        // List<Film> results = query.getResultList();
  
        // System.out.println(results);

        TypedQuery<Acteur> queryActor = em.createNamedQuery("Acteur.findActeur", Acteur.class);
        queryActor.setParameter("identite", "Chris" + "%");
        List<Acteur> acteur = queryActor.getResultList();
        System.out.println(acteur);



        
        em.close();

    }
}
