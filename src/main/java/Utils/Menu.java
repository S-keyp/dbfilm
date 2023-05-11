package utils;

import executable.App;

import DAO.RoleDAO;
import DAO.FilmDAO;
import DAO.ActeurDAO;

public class Menu {
    public static void main(String[] args) {

        mainLoop : for(;;){
            System.out.println("--------------");
            afficherMenu();
            int input = Integer.parseInt(App.scanner.nextLine().trim());

            switch(input){
                case 1:{
                    System.out.println("Saisir le nom de l'acteur: ");
                    String inputSecondaire = App.scanner.nextLine();
                    action1(inputSecondaire);
                    break;
                }
                case 2:{
                    System.out.println("Saisir le film dont vous souhaitez affichez le casting:");
                    String inputSecondaire = App.scanner.nextLine();
                    action2(inputSecondaire);
                    break;
                }
                case 3:{
                    System.out.println("Saisir la première date: (inclu)");
                    String inputSecondaire = App.scanner.nextLine();
                    System.out.println("Saisir la seconde date: (exclu)");
                    String inputTertiaire = App.scanner.nextLine();
                    action3(inputSecondaire, inputTertiaire);
                    break;
                }
                case 4:{
                    System.out.println("Saisir le nom du premier acteur:");
                    String inputSecondaire = App.scanner.nextLine();
                    System.out.println("Saisir le nom du second acteur:");
                    String inputTertiaire = App.scanner.nextLine();
                    action4(inputSecondaire, inputTertiaire);
                    break;
                }
                case 5:{
                    System.out.println("Saisir le nom du premier film:");
                    String inputSecondaire = App.scanner.nextLine();
                    System.out.println("Saisir le nom du second film:");
                    String inputTertiaire = App.scanner.nextLine();
                    action5(inputSecondaire, inputTertiaire);
                    break;
                }
                case 6:{
                    System.out.println("Saisir la première date: (inclu)");
                    String inputSecondaire = App.scanner.nextLine();
                    System.out.println("Saisir la seconde date: (exclu)");
                    String inputTertiaire = App.scanner.nextLine();
                    System.out.println("Saisir le nom de l'acteur cherché:");
                    String inputQuaternaire = App.scanner.nextLine();
                    action6(inputSecondaire, inputTertiaire, inputQuaternaire);
                    break;
                }
                case 7:
                    break mainLoop;
                default: 
                    break;
            }

        }

    }

    public static void action1(String inputSecondaire){
        // Affichage de la filmographie d'un acteur
        System.out.println(String.format(" Affichage de la filmographie de: %s", inputSecondaire));
        System.out.println(ActeurDAO.getFilmographieForActor(inputSecondaire));;
    }

    public static void action2(String inputSecondaire){
        // Affichage du casting d'un film donné
        System.out.println(RoleDAO.getActorsForFilm(inputSecondaire));;
    }

    public static void action3(String inputSecondaire, String inputTertiaire){
        // Affichage des films sortisentre 2 années données
        int intInputSecondaire = Integer.parseInt(inputSecondaire);
        int intInputTertiaire = Integer.parseInt(inputTertiaire);
        System.out.println(FilmDAO.getFilmsBetweenYears(intInputSecondaire, intInputTertiaire));
    }

    public static void action4(String inputSecondaire, String inputTertiaire){
        // Affichage des films communs à 2 acteurs/actrices donnés
        System.out.println(FilmDAO.getCommonFilmsForActors(inputSecondaire, inputTertiaire));
    }

    public static void action5(String inputSecondaire, String inputTertiaire){
        // Affichage des acteurs communs à 2 films donnés
        System.out.println(ActeurDAO.getCommonActorsForFilms(inputSecondaire, inputTertiaire));
    }

    public static void action6(String inputSecondaire, String inputTertiaire, String inputQuaternaire){
        // Affichage des films sortis entre 2 années données et qui ont un acteur/actrice donné au casting
        int intInputSecondaire = Integer.parseInt(inputSecondaire);;
        int intInputTertiaire = Integer.parseInt(inputTertiaire);;
        System.out.println(FilmDAO.getMoviesForActorBetweenYears(intInputSecondaire, intInputTertiaire, inputQuaternaire));;
    }

    public static void afficherMenu(){
        System.out.println("1.Affichage de la filmographie d'un acteur donné");
        System.out.println("2.Affichage du casting d'un film donné");
        System.out.println("3.Affichage des films sortisentre 2 années données");
        System.out.println("4.Affichage des films communs à 2 acteurs/actrices donnés.");
        System.out.println("5.Affichage des acteurs communs à 2 films donnés");
        System.out.println("6.Affichage des films sortis entre 2 années données et qui ont un acteur/actrice donné au casting");
        System.out.println("7.Fin de l'application");
        System.out.print("> ");
    }
}
