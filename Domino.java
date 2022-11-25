import java.util.*;

public class Domino {

    static int [][] faces = new int[4][3] ;
    
    Domino(int[][] faces){
        this.faces = faces;
    }
    
    public void tourner(){

    }


    public static Domino[] genSac(int nb){ 
        if(nb%2 == 0){ //nb devra forcément être paire à cause de genDuoDomino
            Domino[] sac = new Domino[nb]; //on crée le sac de domino avec le nombre de domino choisi.
            int place = 0; //variable qui permettra de placer les dominos dans le sac, la variable est utilisé par genDuoDomino
            while(nb != 0){
                if(place <= sac.length){
                    genDuoDomino(sac, place);
                    place = place+2; //on incrémente place de 2
                    nb = nb-2; //on enlève 2 à nb car on vient de créer et insérer deux dominos dans le sac
                }
            }
            return sac;
        }
        System.out.println("Veuillez saisir une valeur paire");
        return null;
    }

    private static void genDuoDomino(Domino[] sac, int place){
        int[][] f = new int[4][3];
        Domino a = new Domino(f); //on crée les deux dominos qui seront des paires avec un seul coté identique
        Domino b = new Domino(f);
        for(int i=0; i<faces.length-1; i++){
            for(int j=0; j<faces[i].length; j++){
                a.faces[i][j]= (new Random()).nextInt(10); //on créé les valeurs des autres cotés qui ne seront pas forcément identique 
                b.faces[i][j] = (new Random()).nextInt(10);
            }
        }

        for(int k=0; k<faces[4].length; k++){ //on s'occupe du même coté des deux dominos qui, par défaut, est le dernier coté
            int rd = (new Random()).nextInt(10);
            a.faces[4][k] = rd; //ici, c'est la création du coté qui sera identique pour les deux dominos
            b.faces[4][k] = rd;
        }

        if(sac[place+1] != null){ //on inspecte que sac[place+1] existe bien et que ce n'est pas en dehors de la taille du sac
            sac[place] = a; //Au lieu de sac[place+1] != null, j'hesite à mettre "if(place+1 <= sac.length)"
            sac[place+1] = b;
        }
        
                
    }
    
}
