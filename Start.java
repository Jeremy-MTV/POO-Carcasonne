
import java.io.IOException;
import java.util.Scanner;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

import Src.StartTerminal;
import Src.Menus.Menu;

public class Start {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in) ; 
        System.out.println("Bienvenue dans notre Jeu Domino & Carcasonne ! Azul Fell-Awen Ansuf Yis-Wen !");
        System.out.println("Pour Jouer au jeu Domino sur le terminal, Veuillez Entrer : Domino_Terminal");
        System.out.println("Sinon, Entrez : Lancer_Jeu");
        System.out.println("Si vous voulez quitter : Exit");
        String rep = "" ; 
        do {
            rep = sc.next() ; 
            switch(rep) {
                case "Domino_Terminal" :
                    System.out.println("Compris ! Qu'il en soit ainsi !\n\n");
                    StartTerminal.start();
                break ; 
                
                case "Lancer_Jeu" :
                    System.out.println("Compris ! Qu'il en soit ainsi !\n\n");
                    try {
                        @SuppressWarnings("unused") 
                        Menu menu = new Menu() ;
                    } catch (UnsupportedAudioFileException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    } catch (LineUnavailableException e) {
                        e.printStackTrace();
                    } 
                break ;   
                
                case "Exit" :
                    System.out.println("Vous nous quittez déjà ://\nBon , A Bientot , Ar Timlilit !");
                    System.exit(0);
                break ; 
                
                default :
                    System.out.println("Pardon , je n'ai pas bien compris votre demande !");
                    rep = "" ; 
                break ; 
            }
        } while (rep.isEmpty());
        sc.close();
    }
}
