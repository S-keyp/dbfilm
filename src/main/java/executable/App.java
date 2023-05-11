package executable;

import DAO.FilmDAO;

import java.util.Scanner;

import utils.Menu;
import utils.JPAUtils;

import jakarta.persistence.EntityManager;


public class App {

    public static EntityManager em = JPAUtils.getInstance().getEntityManager(); 
    public static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        // BDDTest.test();
        try{
            FilmDAO.init();
        }catch(Exception e){
            e.printStackTrace();
        }

        Menu.main(args);
    }
}
