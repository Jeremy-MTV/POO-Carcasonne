import java.util.*;

public class Start{
    
    public static void main(String[] args){
        Plateau main = new Plateau();
        int tourN;
        int nbTour;
        int nbBot;
        Joueur[] joueurs;


        System.out.println("DOMINO - by Jay and Samy");
        System.out.println();
        System.out.println("Bienvenue sur notre Domino ! \n Avant de commencer à jouer, vous allez devoir choisir le nombre de joueur et le nombre de bot pour votre partie \n Puis, vous allez devoir choisir le nombre de tour que vous voulez effectué (avec un minimum de 5 tours)");
        System.out.println();

        Scanner sc = new Scanner(System.in);
        System.out.println("Choisissez le nombre de Joueur");
        int nbJoueur = Integer.parseInt(sc.nextLine());
        joueurs = new Joueur[nbJoueur];
        
        System.out.println();
        System.out.println("Choissisez le nombre de Bot parmi les Joueurs");
        nbBot = Integer.parseInt(sc.nextLine());
        while(nbBot < nbJoueur || nbBot > nbJoueur){
            System.out.println("Le nombre de bot doit être parmis le nombre de joueur, il ne peut être supérieur ou inférieur");
            System.out.println("Saisissez un nombre correct : ");
            nbBot = Integer.parseInt(sc.nextLine());
        }

        for(int j=0; j<nbJoueur-nbBot; j++){
            joueurs[j] = new Joueur();
        }
        for(int k=nbJoueur-nbBot; k<nbJoueur; k++){
            joueurs[k] = new JoueurB();
        }

        System.out.println();
        System.out.println("Combien de tour voulez-vous ? (5 tours minimum)");
        if(!sc.nextLine().isEmpty()){
            tourN = Integer.parseInt(sc.nextLine());
            while(tourN < 5){
                System.out.println();
                System.out.println("Veuillez choisir un nombre plus grand ou égal à 5");
                System.out.println();
                System.out.println("Combien de tour voulez-vous ? (5 tours minimum)");    
                tourN = Integer.parseInt(sc.nextLine());
            }
            nbTour = tourN;
        }else{
            while(sc.nextLine().isEmpty()){
                System.out.println();
                System.out.println("Valeur incorrecte");    
                System.out.println();
                System.out.println("Combien de tour voulez-vous ? (5 tours minimum)");    
                tourN = Integer.parseInt(sc.nextLine());
                nbTour = tourN;
            }
        }
        
    }


}

