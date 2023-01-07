package Src.ModeleUtils;

public class JoueurB extends Joueur {

    public JoueurB (Plateau plateau , int id) {
        super(plateau, id) ; 
    }

    @Override
    public boolean isBot() {
        return true ; 
    }

    @Override
    public void playTerminal() {
        play() ; 
    }

    @Override
    public boolean play() {
        pioche();
        int [] limite = getPlateau().getLimite() ; 
        boolean posePossible = false ; 
        int maxPts = 0 ; 
        int [] pos = {0,0} ; // (x;y)
        int pts , nbTurned =0; 
        for (int i = limite[1] ; i<=limite[3] ; i++) {
            for (int k = limite[0] ; k<=limite[2] ; k++) {
                for (int j = 0 ; j<4 ; j++) {
                    if (getPlateau().peutPoser(k, i, getTuile())) {
                        pts = getPlateau().nbPtsScored(k, i, getTuile());
                        if (maxPts <= pts ) {
                            pos[0] = k ; 
                            pos[1] = i ; 
                            maxPts = pts ; 
                            nbTurned = j ; 
                            posePossible = true ; 
                        }
                    }
                    getTuile().tourner(true);
                }
            }
        }
        if (!posePossible) {
            setTuile(null);
            return false; 
        }
        for (int i = 0 ; i<nbTurned ; i++) {
            getTuile().tourner(true);
        }
        getPlateau().pose(pos[0], pos[1], getTuile());
        addPoints(maxPts);
        return true ; 
    }
}