import java.util.Scanner;

public class JoueurH extends Joueur {
    
    JoueurH (Plateau plateau , int id) {
        super(plateau, id) ; 
    }

    @Override
    public void play() {
        Scanner sc = new Scanner(System.in) ; 
        String rep ;
        int x = -1 , y = -1 ; 
        boolean fin = false ; 
        do {
            System.out.println("Que Voulez vous faire ?\n (*)Passer Votre tour (S)\n"+
            "(*)Abandonner (A)\n(*)Poser votre Domino (P)");
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
                    System.out.println("Veuillez selectionner une position sur le plateau !\nCommençons par le 'x' !");
                    try {
                        x = sc.nextInt() ; 
                    } catch (Exception e) {
                        System.out.println("Entree non valide");
                        break ; 
                    }
                    System.out.println("Passons au 'y' maintenant");
                    try {
                        y = sc.nextInt() ; 
                    } catch (Exception e) {
                        System.out.println("Entree non valide");
                        break ; 
                    }
                    poser(x+40, y+40);
                    if (getDomino() != null) {
                        System.out.println("Impossible de poser le domino\nChoisissez d'autres coordonnes");
                        break ; 
                    }
                    fin = true ; 
                    break ; 
                default :
                    System.out.println("Entree non valide");
                    break ;     
            }
        }while (!fin);
        sc.close(); 
    }
}
