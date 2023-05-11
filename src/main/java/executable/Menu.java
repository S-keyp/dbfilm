package executable;

import java.util.List;

import Utils.JPAUtils;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import model.Film;

public class Menu {
    public static void main(String[] args) {
        
        
        // public void traiter(Recensement rec, Scanner scanner) {
		
        //     System.out.println("Quel est le code du département recherché ? ");
        //     String choix = scanner.nextLine();
            
        //     if (!NumberUtils.isDigits(choix)) {
        //         throw new RuntimeException("Le département doit être un entier.");
        //     }
            
        //     List<Ville> villes = rec.getVilles();
        //     int somme = 0;
        //     for (Ville ville: villes){
        //         if (ville.getCodeDepartement().equalsIgnoreCase(choix)){
        //             somme+=ville.getPopulation();
        //         }
        //     }
        //     if (somme>0){
        //         System.out.println("Population du département " + choix + " : " + somme);
        //     }
        //     else {
        //         System.out.println("Département "+choix+ " non trouvé.");
        //     }
        // }
    }
}
