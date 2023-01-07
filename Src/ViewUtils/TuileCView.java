package Src.ViewUtils;
import javax.imageio.ImageIO;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

import Src.Exceptions.NoSuchImageException;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.awt.image.*;


public class TuileCView extends JLabel {
    private BufferedImage tuile;
    private Graphics2D g2d;

    public TuileCView (String nom , int pion) {
        setSize(254, 215);
        try {
            tuile = ImageIO.read(new File("Pictures/Carcasonne/Map1/" + nom + ".png"));
        }catch (IOException e) {
            throw new NoSuchImageException();
        }
        
        if(pion != -1){
            g2d = (Graphics2D) tuile.getGraphics();
            g2d.setColor(Color.red);
            g2d.drawOval(35, 30, 15, 15);
            g2d.setColor(Color.black);
            g2d.drawString(String.valueOf(pion), 40, 43);
            g2d.dispose();
            String pionS = "P";
            try {
                ImageIO.write(tuile, "png", new File("Pictures/Carcasonne/Acc/" + nom + pionS + ".png"));
                BufferedImage image = ImageIO.read(new File("Pictures/Carcasonne/Acc/" + nom + pionS + ".png"));
		        Image n = image.getScaledInstance(248, 209, Image.SCALE_SMOOTH);
                this.setI(new ImageIcon(n));
            } catch (IOException e) {
                e.printStackTrace();
            }
            
        }else{
            Image n = tuile.getScaledInstance(248, 209, Image.SCALE_SMOOTH);
            this.setI(new ImageIcon(n));
        }
    }

    private void setI(ImageIcon a){
        this.setIcon(a);
    }

	public Icon getImageIcon(){
		return this.getIcon();
	}

	public TuileCView getTuileCView(){
		return this;
	}
    
}