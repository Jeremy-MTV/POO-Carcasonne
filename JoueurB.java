public class JoueurB extends Joueur {
    
    JoueurB (Plateau plateau , int id) {
        super(plateau, id) ; 
    }

    @Override
    public void play() {
        pioche();
        boolean [] ligneNonVide = getPlateau().getligneNonVide() ; 
        Domino[][]plateau = getPlateau().getPlateau() ; 
        boolean posePossible = false ; 
        int maxPts = 0 ; 
        int [] pos = {0,0} ; // (x;y)
        for (int i = 0 ; i<ligneNonVide.length ; i++) {
            if (ligneNonVide[i]) {
                for (int k = 0 ; k<plateau[i].length ; k++) {
                    /*
                     * Test if possible de poser domino aux pos (k,i)
                     * If (true) {
                     *      on change posePossible a true
                     *      on compte le nombre de points que sa marque
                     *      si c'est plus grand que maxPts
                     *      on met a jour maxPts
                     *      on met a jour int [] pos avec les new pos  
                     * }
                     */
                }
            }
        }
        if (!posePossible) {
            setDomino(null);
            return ; 
        }
        getPlateau().pose(pos[0], pos[1], getDomino());
        
    }
}
