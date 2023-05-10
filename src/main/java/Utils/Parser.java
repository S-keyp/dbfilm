package Utils;

import java.io.File;

import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.persistence.EntityManager;
import model.Film;
import model.Role;

public class Parser {
    public static void main(String[] args) {
        EntityManager em = JPAUtils.getInstance().getEntityManager();   

        ObjectMapper mapper = new ObjectMapper();

        try{
            Film[] films = mapper.readValue(new File("film.json"), Film[].class);
            em.getTransaction().begin();
            
            for(Film film : films) {
                // System.out.println(film);
                for(Role role : film.getRoles()){
                    role.setFilm(film);
                }
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
