package Src;

import Src.ModeleUtils.Joueur;
import Src.ModeleUtils.JoueurB;
import Src.ModeleUtils.JoueurH;
import Src.ModeleUtils.Plateau;
import Src.ModeleUtils.Tuile;

public class GameModel {
    private Plateau plateau ; 
    private Joueur [] joueurs ;
    private Joueur joueurActuel ; 

    public GameModel (char jeu , int nbJoueurH , int nbJoueurB , String [] pseudos) {
        this.plateau = new Plateau(jeu); 
        joueurs = new Joueur[nbJoueurB+nbJoueurH] ; 
        for (int i = 0 ; i<nbJoueurH ; i++) {
            joueurs[i] = new JoueurH(plateau, pseudos[i] , i) ; 
        }
        for (int i = nbJoueurH ; i<joueurs.length ; i++) {
            joueurs[i] = new JoueurB(plateau, i) ; 
        }
        joueurActuel = joueurs[joueurs.length-1] ; 
    }

    public Joueur getWinner(){
        int acc = 0;
        int j = 0;
        for (int i = 0 ; i<joueurs.length ; i++) {
            if(joueurs[i].getNbPoints() > acc) acc = joueurs[i].getNbPoints(); j=i;
            acc++;
        }
        return joueurs[j];
    }

    public Tuile getCase (int x , int y) {
        return plateau.getCase(x, y) ; 
    }

    public int [] getLastPos () {
        return plateau.getLastPos() ; 
    }

    public Plateau getPlateau () {
        return this.plateau ; 
    }

    public Joueur getJoueurActuel () {
        return this.joueurActuel ; 
    }

    public void addScore (int x , int y) {
        joueurActuel.addPoints(plateau.nbPtsScored(x, y, joueurActuel.getTuile()));
    }

    public Joueur prochainJoueur () {
        joueurActuel = joueurs[(joueurActuel.getId()+1)%joueurs.length] ; 
        return joueurActuel ; 
    }

    private boolean top1 () {
        int acc = 0 ; 
        for (Joueur x: joueurs) {
            if (!x.getAbandonne()) {
                acc++ ; 
            }
        }
        return acc == 1 ; 
    }

    public boolean finGame () {
        return plateau.finDeGame() || top1() ; 
    }

    public boolean peutPoser (int x , int y , Tuile dom) {
        return plateau.peutPoser(x, y, dom) ; 
    }

    public int [] getLimite () {
        return plateau.getLimite() ; 
    }

    public void pose (int x , int y , Tuile dom) {
        plateau.pose(x, y, dom);
    }

}