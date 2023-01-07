package Src.Menus;
import java.io.IOException;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

import Src.Controller;
import Src.GameModel;
import Src.GameView;
import Src.ViewUtils.ImagePanel;

public class MenuInterne extends JFrame{
    private JButton mute;
    private JButton Dbutton;
    private JButton Cbutton;
    private boolean sonON;

    public MenuInterne(ImagePanel background, boolean sonON, JButton mute) throws UnsupportedAudioFileException, IOException, LineUnavailableException{
        this.sonON = sonON;
        this.mute = mute;
        setSize(1200, 800);
        setLayout(null);            
        setResizable(false);                         
        ImageIcon d = new ImageIcon("Pictures/DominoButton.png");
        ImageIcon c = new ImageIcon("Pictures/CarcasonneButton.png");
        Dbutton = new JButton(d);
        Cbutton = new JButton(c);
        Dbutton.setBounds(250, 400, d.getIconWidth(), d.getIconHeight());
        Cbutton.setBounds(700, 400, c.getIconWidth(), c.getIconHeight());
        mute.setBounds(1000, 50, 35, 35);
        Cbutton.setVisible(true);
        Dbutton.setVisible(true);
        mute.setVisible(true);
        
        add(Cbutton);
        add(Dbutton);
        add(mute);
        add(background);
        setVisible(true);
        setActionButtons();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public void setActionButtons(){
        mute.addActionListener(ev ->{
			ImageIcon icon2;
			if (sonON == true) {
				icon2 = new ImageIcon("Pictures/muteON.png");
				Menu.clip.start();
				sonON = false;
			}else {
				icon2 = new ImageIcon("Pictures/muteOFF.png");
				Menu.clip.stop();
				sonON = true;
			}
			mute.setIcon(icon2);
		});

        Dbutton.addActionListener(ev ->{
            String nbH = JOptionPane.showInputDialog("Choisissez le nombre de Joueur (Maximum 5 Joueurs)", "1");
            if(nbH == null || Integer.parseInt(nbH) > 5 || Integer.parseInt(nbH) < 0) return;
            String nbB = JOptionPane.showInputDialog("Choisissez le nombre de Bot", "0");
            if(nbB == null || Integer.parseInt(nbB) > Integer.parseInt(nbH) || Integer.parseInt(nbB) < 0) return;
            int nbHumain = Integer.parseInt(nbH) - Integer.parseInt(nbB);
            int nbBot = Integer.parseInt(nbB);
            String[] pseudo = new String[nbHumain];
            for(int i = 0; i < nbHumain ; i++){
                String ps = JOptionPane.showInputDialog("Choissisez le pseudo du joueur " + (i+1));
                if(ps.equals(null) || ps.length() > 8) return;
                pseudo[i] = ps;
            }
            this.dispose();
            try {
                GameModel model = new GameModel('D', nbHumain, nbBot, pseudo) ; 
                GameView gameView;
                gameView = new GameView(nbHumain, pseudo, 'D');
                @SuppressWarnings("unused")  
                Controller c = new Controller(model, gameView) ; 
                 gameView.setVisible(true);
            } catch (LineUnavailableException | IOException | UnsupportedAudioFileException e) {
            } 
            
        });

        Cbutton.addActionListener(ev ->{
            String nbH = JOptionPane.showInputDialog("Choisissez le nombre de Joueur (Maximum 5 Joueurs)", "1");
            if(nbH == null || Integer.parseInt(nbH) > 5 || Integer.parseInt(nbH) < 0) return;
            String nbB = JOptionPane.showInputDialog("Choisissez le nombre de Bot", "0");
            if(nbB == null || Integer.parseInt(nbB) > Integer.parseInt(nbH) || Integer.parseInt(nbB) < 0) return;
            int nbHumain = Integer.parseInt(nbH) - Integer.parseInt(nbB);
            int nbBot = Integer.parseInt(nbB);
            String[] pseudo = new String[nbHumain];
            for(int i = 0; i < nbHumain ; i++){
                String ps = JOptionPane.showInputDialog("Choissisez le pseudo du joueur " + (i+1));
                if(ps.equals(null) || ps.length() > 8) return;
                pseudo[i] = ps;
            }
            this.dispose();
            try {
                GameModel model = new GameModel('C', nbHumain, nbBot, pseudo) ; 
                GameView gameView;
                gameView = new GameView(nbHumain, pseudo, 'C');
                @SuppressWarnings("unused")  
                Controller c = new Controller(model, gameView) ; 
                 gameView.setVisible(true);
            } catch (LineUnavailableException | IOException | UnsupportedAudioFileException e) {
            } 
        });
    }

}