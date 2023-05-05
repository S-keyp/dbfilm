package executable;

import model.Film;
import jakarta.persistence.EntityManager;

public class App {
    public static void main(String[] args) {
        EntityManager em = JPAUtils.getEntityManager();

        Film film = new Film();
        film.setTitle("tchoinnnnnn");

        em.getTransaction().begin();

        em.persist(film);

        em.getTransaction().commit();

        em.close();
    }
}
