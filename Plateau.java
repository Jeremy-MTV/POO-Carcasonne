import java.util.Random;

public class Plateau {
    private Domino [][] plateau ; 
    private Domino [] sac ;

    Plateau () {
        this.plateau = new Domino[40][40] ; 
        this.sac = Domino.genSac(40) ; //à cause de la fonction genDuoDomino dans la classe Domino, on ne peut donner qu'une valeur paire à genSac
    }

    public Domino pioche () {
        int pos = (new Random()).nextInt(40) ; 
        Domino pioche = sac[pos] ; 
        sac[pos] = null ; 
        arrange(sac, pos);
        return pioche ;
    }

    private void arrange (Domino [] tab , int pos) {
        for (int i = pos ; i<tab.length-1 ; i++) {
            tab[i] = tab[i+1] ; 
        }
    }

    public boolean isEmptySac () {
        return sac[0] == null ; 
    }

    public boolean finDeGame () {
        return isEmptySac() ; 
    }
    
}