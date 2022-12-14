import java.util.*;

public class TuileD extends Domino{

    private int [][] faces = new int[4][3] ;
        
    TuileD(int[][] faces){
        this.faces = faces;
    }
    
    public void tourner(boolean droite){ // oki a optimiser
        int[] acc = new int[3];
        if(droite){
            //on va echanger les faces entre elle pour pouvoir tourner dans un sens ou un autre. 
            for(int i=3; i>=1; i--){
                acc = this.faces[i]; //on avance de la face 4 à la face 1, on inverse la valeur de 4 et 3, puis de 3 et 2, puis de 2 et 1, puis de 1 et 4
                this.faces[i] = this.faces[i-1];
                this.faces[i-1] = acc;
            }
        }else{
            for(int j=0; j<=2; j++){
                acc = this.faces[j];//le sens inverse
                this.faces[j] = this.faces[j+1];
                this.faces[j+1] = acc;
            }
        }
    } 

    public int[][] getFaces() {//oki
        return faces;
    }

    public static TuileD[] genSac(){// oki
            int nb = 40; //nombre de TuileD par defaut
            TuileD[] sac = new TuileD[nb]; //on crée le sac de domino avec le nombre de domino choisi.
            int place = 0; //variable qui permettra de placer les dominos dans le sac, la variable est utilisé par genDuoDomino
            while(nb != 0){
                genDuoDomino(sac, place);
                place = place+2; //on incrémente place de 2
                nb = nb-2; //on enlève 2 à nb car on vient de créer et insérer deux dominos dans le sac            
            }
            melanger(sac);
            return sac;
    }

    private static void melanger(TuileD[] sac){
        //cette fonction va permettre de melanger les dominos dans le sac afin de ne pas avoir les dominos duo qui se suivent.
        TuileD acc;
        for(int j=0; j<3; j++){
            for(int i=0; i<sac.length-1; i++){
                acc = sac[i];
                sac[i] = sac[i+1];
                sac[i + 1] = acc;
            }
        }
    }

    private static void genDuoDomino(TuileD[] sac, int place){// oki
        Random random = new Random() ; 
        int[][] facesA = new int[4][3];
        int[][] facesB = new int[4][3];

        TuileD a = new TuileD(facesA); //on crée les deux dominos qui seront des paires avec un seul coté identique
        TuileD b = new TuileD(facesB);

        for(int i=0; i<4; i++){
            for(int j=0; j<3; j++){
                a.faces[i][j] = random.nextInt(10); //on créé les valeurs des autres cotés qui ne seront pas forcément identique                                
                b.faces[i][j] = random.nextInt(10);
            }
        }

        int similar = random.nextInt(4) ; 
        for(int i=0; i<similar; i++){
            a.faces[i] = b.faces[i];
        }

        sac[place] = a; 
        sac[place+1] = b;
    }
    
    @Override
    public boolean possible(Domino domP, int face) {
        for(int i = 0 ; i < faces[face].length; i++){
            if(faces[face][i] != ((TuileD) domP).faces[face][i]) return false ; 
        }
        return true ; 
    }

    public String toString (int i) { //ligne par ligne 
            switch(i){
                case 1 : return "* " + this.faces[0][0] + " " + this.faces[0][1] + " " + this.faces[0][2] + " *"; //pour la ligne 1 du domino

                case 2 : return this.faces[3][0] + " * * * " + this.faces[1][0]; 

                case 3 : return this.faces[3][1] + " * * * " + this.faces[1][1];

                case 4 : return this.faces[3][2] + " * * * " + this.faces[1][2];

                case 5 : return "* " + this.faces[2][0] + " " + this.faces[2][1] + " " + this.faces[2][2] + " *";

                default : return null;
            }
    }

    //  * 1 2 3 *   Ligne 1
    //  1 * * * 1
    //  2 * * * 2
    //  3 * * * 3
    //  * 1 2 3 *   Ligne 5

    public static String toStringVide () {// oki
        return " ".repeat(9) ;  
    }
}
