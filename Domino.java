import java.util.*;

public class Domino {

    private int [][] faces = new int[4][3] ;
        
    Domino(int[][] faces){
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

    public static Domino[] genSac(){// oki
            int nb = 40; //nombre de Domino par defaut
            Domino[] sac = new Domino[nb]; //on crée le sac de domino avec le nombre de domino choisi.
            int place = 0; //variable qui permettra de placer les dominos dans le sac, la variable est utilisé par genDuoDomino
            while(nb != 0){
                genDuoDomino(sac, place);
                place = place+2; //on incrémente place de 2
                nb = nb-2; //on enlève 2 à nb car on vient de créer et insérer deux dominos dans le sac            
            }
            melanger(sac);
            return sac;
    }

    private static void melanger(Domino[] sac){
        //cette fonction va permettre de melanger les dominos dans le sac afin de ne pas avoir les dominos duo qui se suivent.
        Domino acc;
        for(int j=0; j<3; j++){
            for(int i=0; i<sac.length-1; i++){
                acc = sac[i];
                sac[i] = sac[i+1];
                sac[i + 1] = acc;
            }
        }
    }

    private static void genDuoDomino(Domino[] sac, int place){// oki
        Random random = new Random() ; 
        int[][] facesA = new int[4][3];
        int[][] facesB = new int[4][3];

        Domino a = new Domino(facesA); //on crée les deux dominos qui seront des paires avec un seul coté identique
        Domino b = new Domino(facesB);

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


    // private static void genDuoDomino(Domino[] sac, int place){
    //     int[][] f = new int[4][3];
    //     Domino a = new Domino(f); //on crée les deux dominos qui seront des paires avec un seul coté identique
    //     Domino b = new Domino(f);
    //     for(int i=0; i<faces.length-1; i++){
    //         for(int j=0; j<faces[i].length; j++){
    //             a.faces[i][j]= (new Random()).nextInt(10); //on créé les valeurs des autres cotés qui ne seront pas forcément identique 
    //             b.faces[i][j] = (new Random()).nextInt(10);
    //         }
    //     }

    //     for(int k=0; k<faces[4].length; k++){ //on s'occupe du même coté des deux dominos qui, par défaut, est le dernier coté
    //         int rd = (new Random()).nextInt(10);
    //         a.faces[4][k] = rd; //ici, c'est la création du coté qui sera identique pour les deux dominos
    //         b.faces[4][k] = rd;
    //     }

    //     if(place+1 <= sac.length){ //on inspecte que sac[place+1] existe bien et que ce n'est pas en dehors de la taille du sac
    //         sac[place] = a; 
    //         sac[place+1] = b;
    //     }
    // }
    
    public String toString (int i) { //ligne par ligne
        //pour i=1 ou 5, espace à 1 et 4
        return null;
    }

    public static String toStringVide () {// oki
        return " ".repeat(5) ;  
    }
}
