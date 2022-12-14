import java.util.Random;

public class Plateau {
    private Domino [][] plateau ; 
    private Domino [] sac = new Domino[40];

    Plateau () {
        this.plateau = new Domino[40][40] ; 
        this.sac = TuileD.genSac() ; //genSac va générer 40 dominos par defaut qui seront dans le sac
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