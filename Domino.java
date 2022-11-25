import java.util.*;

public class Domino {

    static int [][] faces = new int[4][3] ;
    
    Domino(int[][] faces){
        this.faces = faces;
    }
    
    public void tourner(String sens){ //On donnera en paramètre le sens de la rotation, cad gauche ou droite
        sens = sens.toLowerCase();
        int[] acc = new int[3];
        if(sens == "droite"){
            //on va echanger les faces entre elle pour pouvoir tourner dans un sens ou un autre. 
            for(int i=3; i>=1; i--){
                acc = this.faces[i];
                this.faces[i] = this.faces[i-1];
                this.faces[i-1] = acc;
            }
        }else if(sens == "gauche"){
            for(int j=0; j<=2; j++){
                acc = this.faces[j];
                this.faces[j] = this.faces[j+1];
                this.faces[j+1] = acc;
            }
        }else{
            System.out.println("Veuillez écrire gauche ou droite");
        }
    } 


    public static Domino[] genSac(){ 
            int nb = 40; //nombre de Domino par defaut
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

        if(place+1 <= sac.length){ //on inspecte que sac[place+1] existe bien et que ce n'est pas en dehors de la taille du sac
            sac[place] = a; 
            sac[place+1] = b;
        }
    }
    
}
