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

                }
            }
            sac[cote] = a;
            cote++;
            nb--; 
        }

        return sac;
    }

    private void genDuoDomino(Domino[] sac){
        int[][] f = new int[4][5];
        Domino a = new Domino(f);
        Domino b = new Domino(f);
        for(int i=0; i<faces.length-1; i++){
            for(int j=0; j<faces[i].length; j++){
                a.faces[i][j]= (new Random()).nextInt(10);
                b.faces[i][j] = (new Random()).nextInt(10);
            }
        }
        for(int k=0; k<faces[faces.length].length; k++){
            int rd = (new Random()).nextInt(10);
            a.faces[faces.length][k] = rd;
            b.faces[faces.length][k] = rd;
        }

        
                
    }
    
}
