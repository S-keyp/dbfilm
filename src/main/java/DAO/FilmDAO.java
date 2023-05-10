package DAO;

import Utils.JPAUtils;
import Utils.Parser;
import jakarta.persistence.EntityManager;
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
        em.close();

    }
}
