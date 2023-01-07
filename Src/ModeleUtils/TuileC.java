package Src.ModeleUtils;

import java.util.Random;

import Src.Exceptions.NoSuchImageException;
import Src.ViewUtils.TuileCView;

public class TuileC extends Tuile {
    private char [] faces ; 
    private int pion ; 
    private boolean abbaye ; 
    private int id ; 

    public TuileC (char [] faces , boolean abbaye ) {
        this.faces = faces ; 
        this.abbaye = abbaye ; 
        pion = -1 ; 
    }

    public char [] getFaces () {
        return faces ; 
    }

    public void setPion (int id) {
        this.pion = id ; 
    }

    public int getPion () {
        return pion ; 
    }

    public static TuileC [] genSac () {
        Random rd = new Random();
        TuileC [][] map = new TuileC[5][8] ;
        TuileC [] sac = new TuileC[40] ;
        int pos = 0 ; 
        for (int i = 0 ; i<5 ; i++) {
            for (int k = 0 ; k<8 ; k++) {
                map[i][k] = genTuile(k, i, map ,rd) ;
                sac[pos++]=map[i][k] ; 
            }
        }
        return sac ; 
    }

    private static TuileC genTuile (int x , int y , TuileC [][] map , Random rd) {
        char []faces = new char [4] ;
        for (int i = 0 ; i<2 ; i++) {
            if (y-1+2*i < 0 || y-1+2*i > 4 ) {// gen faces[0] && faces[2] (y-1) et (y+1)
                faces[2*i] = genFace() ; 
            }else{
                if (map[y-1+2*i][x] == null) {
                    if (faces[0] == 'R' && faces[1] == 'R' || faces[0] != 'R' && faces[1] != 'R') {
                        faces[2*i] = genFace() ;    
                    }else{
                        faces[2*i] = (rd.nextInt(2) == 0)?'R':'V' ; 
                    }
                }else{
                    faces[2*i] = map[y-1+2*i][x].faces[(2*i+2)%4] ; 
                }
            }

            if (x+1-2*i < 0 || x+1-2*i > 7) {// gen faces[1] && faces[3] (x+1) et (x-1)
                faces[2*i+1] = genFace() ;  
            }else{
                if (map[y][x+1-2*i] == null) {
                    if (faces[2] == 'R') {
                        faces[2*i+1] = 'R' ; 
                    }else{
                        faces[2*i+1] = genFace() ; 
                    }
                }else{
                    faces[2*i+1] = map[y][x+1-2*i].faces[(2*i+1+2)%4] ; 
                }
            }
        }
        TuileC res = new TuileC(faces, genAbbaye(faces)) ; 
        try {
            res.giveView() ; 
        } catch (NoSuchImageException e) {
            return genTuile(x, y, map, rd) ;   
        }
        return res ; 
    }

    private static boolean genAbbaye (char [] faces) {
        String res = "" ; 
        for (char x : faces) {
            res += x ; 
        }
        if (res.equals("CCCC")) return true ; 
        if (res.equals("CCRC") || res.equals("CRCR") || res.equals("CRRR") || res.equals("VVVV")) {
            return (new Random().nextInt(4) == 1) ; 
        }
        return false ; 
    }

    private static char genFace() {
        Random rd = new Random () ;   
        switch(rd.nextInt(3)) {
            case 0 : return 'C' ; 
            case 1 : return 'V' ; 
            default: return 'R' ; 
        }   
    }

    @Override
    public boolean possible(Tuile domP, int face) {
        return ((TuileC)domP).faces[(face+2)%faces.length] == faces[face] ; 
    }

    @Override
    public void tourner(boolean droite) {
        if (abbaye){
            return ; 
        }
        char tmp ;
        if (droite) {
            tmp = faces[faces.length-1];
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

    @Override
    public TuileCView giveView() {
        Random rd = new Random() ; 
        int id = rd.nextInt(3) ; 
        String abbaye = (this.abbaye)?"A":"" ; 
        TuileCView res ; 
        if (this.id != -1) {
            return new TuileCView(""+faces[0]+faces[1]+faces[2]+faces[3]+abbaye+this.id, pion) ; 
        }

        try{
            res = new TuileCView(""+faces[0]+faces[1]+faces[2]+faces[3]+abbaye+id, pion) ; 
            this.id = id ; 
            return res ; 
        }catch(Exception e) {
            id = (id+1)%3 ; 
        }

        try{
            res = new TuileCView(""+faces[0]+faces[1]+faces[2]+faces[3]+abbaye+id, pion) ; 
            this.id = id ; 
            return res ; 
        }catch(Exception e) {
            id = (id+1)%3 ; 
        }

        try{
            res = new TuileCView(""+faces[0]+faces[1]+faces[2]+faces[3]+abbaye+id, pion) ; 
            this.id = id ; 
            return res ; 
        }catch(Exception e) {
            id = (id+1)%3 ; 
        }
        
        throw new NoSuchImageException() ; 
    }

    @Override
    public String toString(int i) {
        return "" ; 
    }
    
}