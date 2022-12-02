import java.util.ArrayList;
import java.util.Iterator;

public abstract class Joueur{
    private Plateau plateau;
    private ArrayList<Domino> sac;
    private boolean apioche, abandonne;
    private int nbPoints;
    private int id ; 

    Joueur (Plateau plateau , int id) {
        this.plateau = plateau ; 
        sac = new ArrayList<Domino>() ; 
        this.id = id ; 
    }

    public Domino choisir(int pos){// oki
        if (pos > sac.size()) return null ; 
        return sac.get(pos-1) ; 
    }

    public String toString(){// oki not tested
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

    public void piocheSac(){// oki
        if (!apioche) {
            sac.add(plateau.pioche()) ; 
            apioche = true ;
        }
    }

    private void retirer (Domino dom) {// oki
        sac.remove(dom) ; 
    }

    public void poser(int x, int y, Domino dom){// oki   
        if (plateau.peutPoser(x, y, dom)) {
            plateau.pose(x, y, dom);
            retirer(dom);
            nbPoints += plateau.nbPtsScored(x, y, dom) ; 
        }
    }

    public int getNbPoints () {// oki
        return nbPoints ; 
    }
    
    public int getId () {// oki
        return id ; 
    }

    public boolean getAbandonne () {
        return abandonne ; 
    }

    public void abandonne () {
        abandonne = true ; 
    }

    public abstract void play() ; 

}