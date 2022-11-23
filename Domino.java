import java.util.*;

public class Domino {


    static int [][] faces = new int[4][5] ;
    
    Domino(int[][] faces){
        this.faces = faces;
    }
    
    public void tourner(){

    }


    public static Domino[] genSac(int nb){
    
        Domino[] sac = new Domino[nb]; //on crée le sac de domino avec le nombre de domino choisi.
        int[][] f = new int[4][5];
        int cote = 0; //pour savoir à quel case du sac, on est 


        while(nb != 0){
            Domino a = new Domino(f);

            for(int i = 0; i<faces.length; i++){
                for(int j=0; j<faces[i].length; j++){
                f[i][j] = (new Random()).nextInt(10);
                }
            }
            sac[cote] = a;
            cote++;
            nb--; 
        }

        return sac;
    }

    private void genDuoDomino(ArrayList<Domino> sac){

    }

}
