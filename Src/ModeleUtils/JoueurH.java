package Src.ModeleUtils;

import java.util.Scanner;

public class JoueurH extends Joueur {
    
    public JoueurH (Plateau plateau ,String pseudo , int id) {
        super(plateau, pseudo , id) ; 
    }

    @Override
    public boolean play() {
        pioche();
        return false ; 
    }

    public void playTerminal() {
        pioche();
        Scanner sc = new Scanner(System.in) ; 
        String rep ;
        int x = -1 , y = -1 ; 
        boolean fin = false ; 
        System.out.println(getPlateau());
        System.out.println("Tour de :"+getPseudo());
        System.out.println("Voici votre Domino\n"+toString());
        do {
            System.out.println("Que Voulez vous faire ?\n(*)Passer Votre tour (S)\n"+
            "(*)Abandonner (A)\n(*)Poser votre Domino (P)\n(*)Tourner votre Domino (TD) pour la droite et (TG) pour la gauche");
            rep = sc.next() ; 
            switch (rep.toUpperCase()) {
                case "S" : 
                    fin = true ; 
                    break ; 
                case "A" :
                    abandonne();
                    fin = true ; 
                    break ; 
                case "P" :
                    System.out.println("Veuillez selectionner une position sur le plateau !\nCommençons par le 'X' !");
                    try {
                        x = sc.nextInt() ; 
                    } catch (Exception e) {
                        System.out.println("Entree non valide");
                        break ; 
                    }
                    System.out.println("Passons au 'Y' maintenant");
                    try {
                        y = sc.nextInt() ; 
                    } catch (Exception e) {
                        System.out.println("Entree non valide");
                        break ; 
                    }
                    if (!poser(x,y)) {
                        System.out.println("Impossible de poser le domino\nChoisissez d'autres coordonnes");
                        break ; 
                    }
                    fin = true ; 
                    break ; 
                case "TD" :
                    getTuile().tourner(true);
                    System.out.println("Vous avez tourné votre domino\n"+toString());
                    break ; 
                case "TG" :
                    getTuile().tourner(false);
                    System.out.println("Vous avez tourné votre domino\n"+toString());
                    break ;                     
                default :
                    System.out.println("Entree non valide");
                    break ;     
            }
        }while (!fin);
        sc.close();
    }
    
}