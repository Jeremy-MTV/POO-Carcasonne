package Src.ModeleUtils;

import javax.swing.JLabel;

public abstract class Tuile {

    public abstract boolean possible(Tuile domP, int face);
    
    public abstract void tourner(boolean droite);

    public abstract JLabel giveView () ; 

    public abstract String toString (int i);

    public int nbPointsScored (int face) {
        return 0 ; 
    }

}