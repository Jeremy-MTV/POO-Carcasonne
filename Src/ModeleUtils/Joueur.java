package Src.ModeleUtils;
public abstract class Joueur{
    private Plateau plateau;
    private Tuile tuile ; 
    private String pseudo ; 
    private boolean abandonne;
    private int nbPoints;
    private int nbPions ; 
    private int id ; 

    Joueur (Plateau plateau , int id) {
        this.plateau = plateau ; 
        this.id = id ; 
        this.tuile= null ;
        nbPions = 10 ;  
    }

    Joueur (Plateau plateau , String pseudo , int id) {
        this(plateau, id) ; 
        this.pseudo = pseudo ; 
    }
    
    public boolean poser(int x, int y){ 
        if (plateau.peutPoser(x, y, tuile)) {
            plateau.pose(x, y, tuile);
            addPoints(plateau.nbPtsScored(x, y, tuile)) ;
            return true ; 
        }
        return false ; 
    }

    public boolean hasPions () {
        return nbPions != 0 ; 
    }

    public void enlevePion () {
        nbPions-- ; 
    }

    public void addPion () {
        nbPions++ ; 
    }

    public void addPoints (int pts) {
        nbPoints+= pts ; 
    }

    public String getPseudo () {
        return pseudo ; 
    }

    public Plateau getPlateau() {
        return plateau;
    }

    public void setTuile (Tuile dom) {
        this.tuile = dom ; 
    }

    public boolean isBot () {
        return false ; 
    }

    public Tuile getTuile () {
        return this.tuile ; 
    }

    public void pioche () {
        this.tuile = plateau.pioche() ; 
    }

    public int getNbPoints () {
        return nbPoints ; 
    }
    
    public int getId () {
        return id ; 
    }

    public boolean getAbandonne () {
        return abandonne ; 
    }

    public void abandonne () {
        abandonne = true ; 
    }

    public String toString () {
        String res = "" ; 
        for (int i = 1 ; i<6 ; i++) {
            res += ((TuileD)tuile).toString(i)+"\n" ; 
        }
        return res ; 
    }

    public abstract boolean play() ; 

    public abstract void playTerminal() ; 

}