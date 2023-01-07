package Src;
import java.util.Scanner;

import Src.ModeleUtils.Joueur;
import Src.ModeleUtils.JoueurB;
import Src.ModeleUtils.JoueurH;
import Src.ModeleUtils.Plateau;

public class StartTerminal{

    public static void start () {
        Plateau plateau = new Plateau('D');
        int nbBot = -1;
        Joueur[] joueurs;
        int acc = -1 ; 


        System.out.println(" ".repeat(30)+"| DOMINO - by Jay and Samy |");
        System.out.println();
        System.out.println("Bienvenue sur notre Domino ! \n Avant de commencer à jouer, vous allez devoir choisir le nombre de joueur et le nombre de bot pour votre partie");
        System.out.println();

        Scanner sc = new Scanner(System.in);
        System.out.println("Choisissez le nombre de Joueur");
        int nbJoueur = -1 ; 
        while (nbJoueur <2) {
            System.out.println("Il doit y avoir au moins deux joueurs !");
            try {
                nbJoueur = sc.nextInt();
            } catch (Exception e) {
                System.out.println("Entree Non Valide !");
                nbJoueur = -1 ;
            }
        }
        joueurs = new Joueur[nbJoueur];
        System.out.println();
        System.out.println("Choissisez le nombre de Bot parmi les Joueurs");
        try {
            nbBot = sc.nextInt();
        } catch (Exception e) {
            System.out.println("Entree Non Valide !");
        }
        while(nbBot < 0 || nbBot > nbJoueur){
            System.out.println("Le nombre de bot doit être parmis le nombre de joueur, il ne peut être supérieur");
            System.out.println("Saisissez un nombre correct : ");
            try {
                nbBot = sc.nextInt();
            } catch (Exception e) {
                System.out.println("Entree Non Valide !");
                nbBot = -1 ; 
            }
        }
        String rep = "" ; 
        for(int j=0; j<nbJoueur-nbBot; j++){
            System.out.println("Veuillez choisir un pseudo pour le joueur :"+(j+1));
            System.out.println("Attention , il ne doit pas contenir plus de 10 caracteres");
            while(rep.isEmpty() || rep.isBlank() || rep.length() > 10) {
                rep = sc.next() ; 
            }
            joueurs[j] = new JoueurH(plateau,rep,j);
            rep = "" ; 
        }
        for(int k=nbJoueur-nbBot; k<nbJoueur; k++){
            joueurs[k] = new JoueurB(plateau,k);
        }

        while(joueurs[(acc+1)%joueurs.length] != joueurs[(acc+2)%joueurs.length] && !plateau.finDeGame()){
            if(!joueurs[(++acc)%joueurs.length].getAbandonne()) {
                joueurs[(acc)%joueurs.length].playTerminal();
            }
        }
        int idWinner = -1 ; 
        int maxPts = -1 ; 
        for (Joueur x : joueurs) {
            if (!x.getAbandonne() && x.getNbPoints() > maxPts) {
                maxPts = x.getNbPoints() ; 
                idWinner = x.getId() ; 
            }
        }
        sc.close();
        System.out.println("Le gagnant est : LE JOUEUR :" + idWinner + " , avec un total de points de :" +
        maxPts);
    }
    
    public static void main(String[] args){
        start();
    }
}

