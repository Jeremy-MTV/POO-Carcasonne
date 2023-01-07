package Src;

import Src.ModeleUtils.Joueur;
import Src.ModeleUtils.TuileC;

public class Controller {
    private GameModel model ; 
    private GameView gameView ; 

    public Controller (GameModel model , GameView gameView) {
        this.model = model ; 
        this.gameView = gameView ;
        gameView.setController(this);
        gameView.setActionButtons();
        gameView.firstStep(model.getCase(40, 40).giveView(), model.getLimite());
        next();
    }

    public void poser(int x, int y) {
        Joueur j = model.getJoueurActuel() ; 
        if (model.peutPoser(x, y,j.getTuile())) {
            model.pose(x, y, j.getTuile());
            model.addScore(x, y);
            gameView.updatePlateauView(j.getTuile().giveView(), model.getLimite());
            next();
        }
    }

    public void abandonne() {
        model.getJoueurActuel().abandonne();
    }

    public void pion (int x , int y , int id) {
        TuileC tmp = ((TuileC)model.getCase(x, y)) ; 
        if (tmp.getPion() == -1 && model.getJoueurActuel().hasPions()) {
            tmp.setPion(id);
            model.getJoueurActuel().enlevePion();
            gameView.updatePlateauView(x, y, tmp.giveView(), model.getLimite());
            return ; 
        }
        if (tmp.getPion() == id) {
            tmp.setPion(-1);
            model.getJoueurActuel().addPion();
            gameView.updatePlateauView(x, y, tmp.giveView(), model.getLimite());
        }
    }

    public void next() {
        if (model.finGame()) {
            gameView.finishGame(model.getWinner(), model.getWinner().isBot());  
            return;         
        } 
        Joueur next = model.prochainJoueur() ; 
        if (next.getAbandonne()) {
            next();
            return ; 
        }
        if (next.isBot()) {
            if (next.play()) {
                int [] lastPos = model.getLastPos() ; 
                gameView.updatePlateauView(lastPos[0], lastPos[1], next.getTuile().giveView(), model.getLimite());
            }
            next() ; 
            return ;             
        }
        next.play() ; 
        gameView.updateJoueurView(next.getNbPoints(), next.getTuile().giveView(), next.getId());
        gameView.showJoueurView(next.getId());
    }

    public void tourner(boolean droite) {
        Joueur act = model.getJoueurActuel() ; 
        act.getTuile().tourner(droite);
        gameView.updateJoueurView(act.getNbPoints(), act.getTuile().giveView(), act.getId());
    }

}