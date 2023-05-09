package Utils;

import java.io.File;

import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.persistence.EntityManager;
import model.Film;

public class Parser {
    public static void main(String[] args) {
        EntityManager em = JPAUtils.getInstance().getEntityManager();   

        ObjectMapper mapper = new ObjectMapper();

        try{
            Film[] films = mapper.readValue(new File("films.json"), Film[].class);
            em.getTransaction().begin();
            
            for(Film film : films) {
                // System.out.println(film);
                em.merge(film);
            }

            em.getTransaction().commit();
            em.close();
        } catch (Exception e) {
            e.printStackTrace();
            em.close();
        }

    }
}
