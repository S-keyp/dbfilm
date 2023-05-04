import jakarta.persistence.EntityManager;
import model.Film;

public class App {
    public static void main(String[] args) {
        EntityManager em = JPAUtils.getEntityManager();

        Film film = new Film();
        film.setName("tchoinnnnnn");

        em.getTransaction().begin();

        em.persist(film);

        em.getTransaction().commit();

        em.close();
    }
}
