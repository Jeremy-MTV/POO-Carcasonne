import java.util.Random;

public class Plateau {
    private Domino[][] plateau ; 
    private Domino [] sac ;

    Plateau () {
        this.plateau = new Domino[40][40] ; 
        this.sac = Domino.genSac() ;
    }

    public Domino pioche () {
        int pos = (new Random()).nextInt(lastDomino(0, sac.length)+1) ; 
        Domino pioche = sac[pos] ; 
        sac[pos] = null ; 
        arrange(sac, pos);
        return pioche ;
    }

    private int lastDomino (int l , int r ) {// oki
        int mid = (l+r)/2 ; 
        if (sac[mid] == null) {
            return lastDomino(l, mid-1 ) ;
        }   
        if (mid+1 > sac.length || sac[mid+1] == null) return mid ;   
        return lastDomino(mid+1, r) ; 
    }

    private void arrange (Domino [] tab , int pos) {// oki
        for (int i = pos ; i<tab.length-1 ; i++) {
            tab[i] = tab[i+1] ; 
        }
    }

    public boolean isEmptySac () {// oki
        return sac[0] == null ; 
    }

    public boolean finDeGame () {// oki
        return isEmptySac() ; 
    }

    private boolean dansLimites (int x , int y ){// oki
        return y>=0 && y < plateau.length && x >0 && x < plateau[0].length ; 
    }    

    public boolean peutPoser (int x , int y , Domino dom) {// oki not tested

        if (!dansLimites(x, y)) return false ; 
        boolean [] adja = {false , false , false , false} ; 
        //               haut(0)  droite(1) bas(2) gauche(3)
        int nbNulls = 0 ; 

        for (int i = 0; i < 2; i++) {

            if (dansLimites(x, y-1+2*i)) { // i = 0 (x,y-1) et i = 1 (x,y+1)
                // verifie haut et bas 

                if (plateau[x][y-1+2*i] == null) {
                    nbNulls++ ;
                    adja[2*i] = true ; 
                }else{
                    adja[2*i] = possible(dom, plateau[x][y-1+2*i], 2*i) ; 
                }
            }else{
                adja[2*i] = true ; 
            }
            
            if (!dansLimites(x+1-2*i, y)) { // i = 0 (x+1,y) et i = 1 (x-1,y)
                // verifie gauche et droite

                if (plateau[x+1-2*i][y] == null) {
                    nbNulls++ ; 
                    adja[2*i+1] = true ; 
                }else{
                    adja[2*i+1] = possible(dom, plateau[x+1-2*i][y], 2*i+1) ; 
                }
            }else{
                adja[2*i+1] = true ;  
            }
        }

        return adja[0] && adja[1] && adja[2] && adja[3] && nbNulls != 4 ; 

    }

    private boolean possible (Domino domJ , Domino domP , int face) {// oki not tested
        int [][] facesJ = domJ.getFaces() ; 
        int [][] facesP = domP.getFaces() ; 
        for (int i = 0 ; i<facesJ.length ; i++) {
            if (facesJ[face][i] != facesP[face][i]) return false ; 
        }
        return true ; 
    }
    
    public int nbPtsScored (int x , int y , Domino dom) {// oki not tested
        int total = 0 ; 
        for (int i = 0; i < 2; i++) {
            if (dansLimites(x, y-1+2*i) && plateau[x][y-1+2*i] != null) {
                total += sommeTab(dom, 2*i) ; 
            }
            
            if (!dansLimites(x+1-2*i, y) && plateau[x+1-2*i][y] != null) {
                total += sommeTab(dom, 2*i+1) ; 
            }
        }
        return total ; 
    }

    private int sommeTab (Domino dom , int i) {// oki
        int acc = 0 ; 
        for (int x : dom.getFaces()[i]) {
            acc+=x ; 
        }
        return acc ; 
    }

    public String toString () {
        return "" ; 
    }
}