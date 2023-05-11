package executable;

import DAO.FilmDAO;
import Utils.JPAUtils;
import Utils.Menu;

import java.util.Scanner;
import java.io.IOException;

import jakarta.persistence.EntityManager;


public class App {

    public static EntityManager em = JPAUtils.getInstance().getEntityManager(); 
    public static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        // BDDTest.main(args);
        try{
            FilmDAO.init();
        }catch(Exception e){
            e.printStackTrace();
        }

        Menu.main(args);
    }
}
