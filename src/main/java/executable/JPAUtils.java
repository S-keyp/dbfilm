package executable;

import jakarta.persistence.Persistence;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;

public class JPAUtils {
    private static JPAUtils INSTANCE = new JPAUtils();

    private JPAUtils(){}
  
    private final static EntityManagerFactory EMF = Persistence.createEntityManagerFactory("FormationJPAPU");
    private final static EntityManager EM = EMF.createEntityManager();

    public static JPAUtils getInstance(){
        return INSTANCE;
    }

    public static EntityManager getEntityManager(){
        return EM;
    }
    

}
