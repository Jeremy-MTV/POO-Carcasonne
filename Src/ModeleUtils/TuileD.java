package Src.ModeleUtils;

import java.util.Random;

import Src.ViewUtils.TuileDView;

public class TuileD extends Tuile{
    private int [][] faces ;
        
    public TuileD(int[][] faces){
        this.faces = faces;
    }
    
    public int[][] getFaces() {
        return faces;
    }

    public static TuileD [] genSac () {
        Random rd = new Random() ; 
        TuileD [] sac = new TuileD[40] ; 
        int [][] faces = new int [4][3] ; 
        for (int i = 0 ; i<4 ; i++) {
            for (int k = 0 ; k<3 ; k++) {
                faces[i][k] = rd.nextInt(10) ; 
            }
        }
        sac[0] = new TuileD(faces) ; 
        for (int i = 1 ; i<40 ; i++) {
            sac[i] = genTuileD(sac[i-1] ,rd) ; 
        }
        return sac ; 
    }

    private static TuileD genTuileD (TuileD dom , Random rd) {
        TuileD res = new TuileD(cloneTab(dom.faces)) ; 
        res.faces[rd.nextInt(4)] = genFace() ; 
        return res ; 
    }

    private static int [][] cloneTab (int [][] t) {
        int [][] res = new int [t.length][t[0].length] ; 
        for (int i = 0 ; i<res.length ; i++) {
            for (int k = 0 ; k<res[i].length ; k++) {
                res[i][k] = t[i][k] ; 
            }
        }
        return res ; 
    }

    private static int [] genFace () {
        int [] res = new int [3] ; 
        Random rd = new Random() ; 
        for (int i = 0 ; i<3 ; i++) {
            res[i] = rd.nextInt(10) ; 
        }
        return res ; 
    }
    
    @Override
    public int nbPointsScored (int face) {
        int acc = 0 ; 
        for (int x : faces[face]) {
            acc+=x ; 
        }
        return acc ;
    }

    @Override
    public boolean possible(Tuile domP, int face) {
        for(int i = 0 ; i < faces[face].length; i++){
            if(faces[face][i] != ((TuileD) domP).faces[(face+2)%4][i]) return false ; 
        }
        return true ; 
    }

    @Override
    public void tourner(boolean droite){
        int[] tmp ;
        if (droite) {
            tmp = faces[faces.length-1] ; 
            for (int i = faces.length-1 ; i>0 ; i--) {
                faces[i] = faces[i-1] ; 
            }        
            faces[0] = tmp ; 
        }else{
            tmp = faces[0] ; 
            for (int i = 0 ; i<faces.length-1 ; i++) {
                faces[i] = faces[i+1] ; 
            }
            faces[faces.length-1] = tmp ; 
        }
    } 
    
    public String toString (int i) { //ligne par ligne, pour un affichage sur terminal
        switch(i){
            case 1 : return "* " + this.faces[0][0] + " " + this.faces[0][1] + " " + this.faces[0][2] + " *";
            case 2 : return this.faces[3][0] + " * * * " + this.faces[1][0]; 
            case 3 : return this.faces[3][1] + " * * * " + this.faces[1][1];
            case 4 : return this.faces[3][2] + " * * * " + this.faces[1][2];
            case 5 : return "* " + this.faces[2][0] + " " + this.faces[2][1] + " " + this.faces[2][2] + " *";
            default : return null;
        }
    }

    public static String toStringVide () {
        return " ".repeat(9) ; 
    }

    @Override
    public TuileDView giveView() {
        return new TuileDView(this) ; 
    }

}