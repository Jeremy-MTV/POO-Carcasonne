public abstract class Joueur{
    private Plateau plateau;
    private Domino domino ; 
    private boolean abandonne;
    private int nbPoints;
    private int id ; 

    Joueur (Plateau plateau , int id) {
        this.plateau = plateau ; 
        this.id = id ; 
        this.domino= null ; 
    }

    public void poser(int x, int y){// oki   
        if (plateau.peutPoser(x, y, domino)) {
            plateau.pose(x, y, domino);
            domino = null ; 
            addPoints(plateau.nbPtsScored(x, y, domino)) ; // a revoir  
        }
    }

    public void addPoints (int pts) {
        nbPoints+= pts ; 
    }

    public Plateau getPlateau() {// oki
        return plateau;
    }

    public void setDomino (Domino dom) {
        this.domino = dom ; 
    }

    public Domino getDomino () {
        return this.domino ; 
    }

    public void pioche () {
        this.domino = plateau.pioche() ; 
    }


    public int getNbPoints () {// oki
        return nbPoints ; 
    }
    
    public int getId () {// oki
        return id ; 
    }

    public boolean getAbandonne () {// oki
        return abandonne ; 
    }

    public void abandonne () {
        abandonne = true ; 
    }

    public String toString () {
        String res = "" ; 
        for (int i = 0 ; i<5 ; i++) {
            res += domino.toString(i)+"\n" ; 
        }
        return res ; 
    }

    public abstract void play() ; 

}