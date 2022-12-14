public class JoueurB extends Joueur {
    
    JoueurB (Plateau plateau , int id) {
        super(plateau, id) ; 
    }

    @Override
    public String toString() {
        return "" ; 
    }

    @Override
    public void play() {
        pioche();
        int [] limite = getPlateau().getLimite() ; 
        boolean posePossible = false ; 
        int maxPts = 0 ; 
        int [] pos = {0,0} ; // (x;y)
        int pts ; 
        for (int i = limite[1] ; i<=limite[3] ; i++) {
            for (int k = limite[0] ; k<=limite[2] ; k++) {
                if (getPlateau().peutPoser(k, i, getDomino())) {
                    pts = getPlateau().nbPtsScored(k, i, getDomino());
                    if (maxPts <pts ) {
                        pos[0] = k ; 
                        pos[1] = i ; 
                        maxPts = pts ; 
                    }
                }
            }
        }
        if (!posePossible) {
            setDomino(null);
            return ; 
        }
        getPlateau().pose(pos[0], pos[1], getDomino());
        addPoints(maxPts);
    }
}
