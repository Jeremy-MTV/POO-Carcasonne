package Src.ModeleUtils;

import java.util.Random;

public class Plateau {
    private Tuile[][] plateau ; 
    private int [] limite ;  
    private Tuile [] sac ;
    private int [] lastPos ; 
    private final char jeu ; 

    public Plateau (char jeu) {
        this.plateau = new Tuile[80][80] ; 
        this.jeu = jeu ; 
        limite = new int [4] ; 
        // x0 y0 x1 y1 
        this.sac = (this.jeu == 'D')?TuileD.genSac():TuileC.genSac();
        plateau[40][40] = pioche() ; 
        limite[0] = 39 ;//la premiere colone vide vers la gauche a partir de (40;40)
        limite[1] = 39 ;//la premiere ligne vide vers le haut a partir de (40;40) 
        limite[2] = 41 ;//la premiere colone vide vers la droite a partir de (40;40) 
        limite[3] = 41 ;//la premiere ligne vide vers le bas a partir de (40;40)
        lastPos = new int [2] ; 
        lastPos[0] = 40 ; 
        lastPos[1] = 40 ;  
    }

    public int [] getLastPos () {
        return lastPos ; 
    }

    public Tuile[][] getPlateau () {
        return plateau ; 
    }

    public int [] getLimite () {
        return limite ; 
    }

    public Tuile pioche () {
        int lastDom = lastDomino(0, sac.length) ;           
        int pos = (new Random()).nextInt(lastDom+1) ; 
        Tuile pioche = sac[pos] ; 
        sac[pos] = null ; 
        arrange(sac, pos, lastDom);
        return pioche ;
    }

    private int lastDomino (int l , int r ) {
        int mid = (l+r)/2 ; 
        if (sac[mid] == null) {
            return lastDomino(l, mid-1 ) ;
        }   
        if (mid+1 >= sac.length || sac[mid+1] == null) return mid ;   
        return lastDomino(mid+1, r) ; 
    }

    private void arrange (Tuile [] tab , int pos , int fin) {
        for (int i = pos ; i<fin ; i++) {
            tab[i] = tab[i+1] ; 
        }
        sac[fin] = null ; 
    }

    public boolean finDeGame () {
        return sac[0] == null ; 
    }

    private boolean dansLimites (int x , int y ){
        return y>=0 && y < plateau.length && x >0 && x < plateau[y].length ; 
    }

    public Tuile getCase (int x , int y) {
        return plateau[y][x] ; 
    }
    
    public boolean peutPoser (int x , int y , Tuile dom) {
        if (!dansLimites(x, y) || plateau[y][x] != null) return false ; 
        boolean [] adja = {false , false , false , false} ; 
        //               haut(0)  droite(1) bas(2) gauche(3)
        int nbNulls = 0 ;

        for (int i = 0; i < 2; i++) {
            if (dansLimites(x, y-1+2*i)) { // i = 0 (x,y-1) et i = 1 (x,y+1)
                // verifie haut et bas 
                if (plateau[y-1+2*i][x] == null) {
                    nbNulls++ ;
                    adja[2*i] = true ; 
                }else{
                    adja[2*i] = dom.possible(plateau[y-1+2*i][x], 2*i) ; 
                }
            }else{
                adja[2*i] = true ; 
                nbNulls++ ; 
            }
            
            if (dansLimites(x+1-2*i, y)) { // i = 0 (x+1,y) et i = 1 (x-1,y)
                // verifie gauche et droite
                if (plateau[y][x+1-2*i] == null) {
                    nbNulls++ ; 
                    adja[2*i+1] = true ; 
                }else{
                    adja[2*i+1] = dom.possible(plateau[y][x+1-2*i], 2*i+1) ; 
                }
            }else{
                adja[2*i+1] = true ;  
                nbNulls++ ; 
            }
        }
        return adja[0] && adja[1] && adja[2] && adja[3] && nbNulls != 4 ; 
    }

    public void pose (int x , int y , Tuile dom) {
        plateau[y][x] = dom ; 
        // on mets a jour l'espace de jeu
        limite[0] = (limite[0]<x)?limite[0]:x-1 ; 
        limite[1] = (limite[1]<y)?limite[1]:y-1 ; 
        limite[2] = (limite[2]>x)?limite[2]:x+1 ; 
        limite[3] = (limite[3]>y)?limite[3]:y+1 ; 
        lastPos[0] = x ; 
        lastPos[1] = y ; 
    }

    public int nbPtsScored (int x , int y , Tuile dom) {
        int total = 0 ; 
        for (int i = 0; i < 2; i++) {
            if (dansLimites(x, y-1+2*i) && plateau[y-1+2*i][x] != null) {
                total += dom.nbPointsScored(2*i); 
            }
            
            if (dansLimites(x+1-2*i, y) && plateau[y][x+1-2*i] != null) {
                total += dom.nbPointsScored(2*i+1) ; 
            }
        }
        return total ; 
    }

    public String toString () {
        String res = " ".repeat((limite[2]-limite[0]+1)*10/2)+"| LE PLATEAU DE JEU |\n" ; 
        res += "-".repeat((limite[2]-limite[0]+2)*10)+"\n     " ; 
        for (int i = limite[0] ; i<=limite[2] ; i++) {
            res+= "    "+i+"    " ; 
        }
        res+="\n" ; 
        for (int i = limite[1] ; i<=limite[3] ; i++) {
            res+= printLine(i) ; 
        }
        res+="\n"+"-".repeat((limite[2]-limite[0]+2)*10) ; 
        return res ; 
    }

    private String printLine (int line) {
        String res = "" ; 
        for (int i = 1 ; i<6 ; i++) {
            res+=((i==3)?line+"| ":" ".repeat(String.valueOf(line).length()+2)) ; 
            for (int k = limite[0] ; k<=limite[2] ; k++) {
                if (plateau[line][k] == null) {
                    res+= TuileD.toStringVide() + " "; 
                }else{
                    res+= plateau[line][k].toString(i) + " " ; 
                }
            }
            res+="\n" ; 
        }
        return res ; 
    }
}