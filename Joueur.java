import java.util.ArrayList;
import java.util.Iterator;

public abstract class Joueur{
    private Plateau plateau;
    private ArrayList<Domino> sac;
    private boolean apioche, joue;
    private int nbPoint;
    private int id ; 

    public Domino choisir(int pos){// oki
        if (pos > sac.size()) return null ; 
        return sac.get(pos-1) ; 
    }

    public String toString(){
        String res = "Voici les pieces que vous possedez Joueur "+id+" : \n" ;
        Iterator<Domino> dominos = sac.iterator() ; 
        for (int i = 0 ; i<4 ; i++) {
            while(dominos.hasNext()) {
                res+=dominos.next().toString(i) + " " ; 
            }
            res+="\n" ; 
            dominos = sac.iterator() ; 
        }
        return res ; 
    }

    public void piocheSac(){

    }

    public void poser(int x, int y, Domino dom){

    }

    public abstract void play() ; 

}