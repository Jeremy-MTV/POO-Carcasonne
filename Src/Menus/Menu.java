package Src.Menus;
import java.awt.*;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;

import Src.ViewUtils.ImagePanel;

import java.io.File;
import java.io.IOException;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

public class Menu extends JFrame{

    private JButton play ; 
    private JButton options; 
    private JButton quitter;
    private JButton mute;
    static boolean sonON;
	File file = new File("Sounds/8BIT-1.wav");
    public static File file2 = new File("Sounds/GameSong.wav");
    ImagePanel background = new ImagePanel("Pictures/MenuBack.png");
    AudioInputStream ais = AudioSystem.getAudioInputStream(file);
    public static AudioInputStream game;
 
    public static Clip clip;
    public Menu() throws UnsupportedAudioFileException, IOException, LineUnavailableException{
        super();
        clip = AudioSystem.getClip();
        if(clip.isActive() || clip.isRunning()){
            clip.stop();
            sonON = true;
        }else{
            clip.open(ais);
            sonON = false;
        }
        resetAcc("Pictures/Carcasonne/Acc");
        resetAcc("Pictures/Domino/Acc");
        
        setSize(1200, 800);
        getContentPane().setLayout(null);
        setResizable(false);
        clip.start();
        ImageIcon p = new ImageIcon("Pictures/play.png");
        ImageIcon o = new ImageIcon("Pictures/options.png");
        ImageIcon q = new ImageIcon("Pictures/quitter.png");
        ImageIcon m = new ImageIcon("Pictures/muteON.png");
        play = new JButton(p);
        options = new JButton(o);
        quitter = new JButton(q);
        mute = new JButton(m);
        play.setBounds(405, 387, p.getIconWidth(), p.getIconHeight());
        options.setBounds(405, 517, o.getIconWidth(), o.getIconHeight());
        quitter.setBounds(405, 627, q.getIconWidth(), q.getIconHeight());
        mute.setBounds(1000, 50, 35, 35);
        play.setBorderPainted(false);
        play.setFocusPainted(false);
        add(mute);
        add(play);
        add(options);
        add(quitter);

        ImagePanel background = new ImagePanel("Pictures/MenuBack.png");
        background.setSize(new Dimension(1200, 800));
        add(background);
        setVisible(true);
        setActionButtons();
        clip.loop(Clip.LOOP_CONTINUOUSLY);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public void resetAcc(String path){
        File f = new File(path);
        if(!f.exists()) return;
        File[] fichier = f.listFiles();
        for(File x : fichier){
            x.delete();
        }
    }

    public void setActionButtons(){
        play.addActionListener(ev -> {
            this.dispose();
            try {
                @SuppressWarnings("unused")
                MenuInterne a = new MenuInterne(background, sonON, mute);
            }catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
                e.printStackTrace();
            }
        });

        mute.addActionListener(ev ->{
			ImageIcon icon2;
			if (sonON == true) {
				icon2 = new ImageIcon("Pictures/muteON.png");
				clip.start();
				sonON = false;
			}else {
				icon2 = new ImageIcon("Pictures/muteOFF.png");
				clip.stop();
				sonON = true;
			}
			mute.setIcon(icon2);
		});

        quitter.addActionListener(ev->{
            System.exit(0);
        });
    }
    
    public static void main(String[] args) throws UnsupportedAudioFileException, IOException, LineUnavailableException{
        @SuppressWarnings("unused")
        Menu background = new Menu();
    }
}
