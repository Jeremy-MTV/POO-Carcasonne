package Src.ViewUtils;
import javax.imageio.ImageIO;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

import Src.ModeleUtils.TuileD;

import java.awt.image.*;
import java.io.File;
import java.awt.*;



public class TuileDView extends JLabel {

    private BufferedImage dom;
	private BufferedImage domV;
	private Graphics2D g2d;
	private static int numero = 0; //Ce sera l'indice de l'image de la piece qui est associé à chaque domino

    public TuileDView(TuileD domino){ //numero est le numero de la piece généré par le constructeur, numero va s'incrémenter lors de l'appel du constructeur

		//La taille d'un domino est la même que celle de son image, cad 254x215
        this.setSize(254,215); 

		//Initialisation des BufferedImage ainsi que du Graphic2D
        try{
            dom = ImageIO.read(new File("Pictures/Domino/domino.png"));
			domV = ImageIO.read(new File("Pictures/Domino/domVide.png"));
			g2d = (Graphics2D) dom.getGraphics(); 
        }catch(Exception e){
            System.out.println("Le fichier est introuvable ou n'existe pas");
        }

		
		//On prend les faces du domino
        int [][] f = domino.getFaces();

		//Cleaning de la piece 
        g2d.drawImage(domV, null, 0, 0); //On remplace la piece par sa version vierge . 


		//Editing de la piece avec les numeros de cases
			g2d.setColor(Color.BLACK); 
			
			//case 1
			g2d.setFont(new Font("Purisa", Font.PLAIN, 30)); 
			g2d.drawString(String.valueOf(f[0][0]),78,25);

			//case 2 
			g2d.setFont(new Font("Serif",Font.PLAIN,30)); 
			g2d.drawString(String.valueOf(f[0][1]),127,25);

			//case 3 
			g2d.setFont(new Font("Serif",Font.PLAIN,30)); 
			g2d.drawString(String.valueOf(f[0][2]),177,25);

			//case 4
			g2d.setFont(new Font("Serif",Font.PLAIN,30)); 
			g2d.drawString(String.valueOf(f[1][0]),228,71);

			//case 5
			g2d.setFont(new Font("Serif",Font.PLAIN,30)); 
			g2d.drawString(String.valueOf(f[1][1]),228,110);

			//case 6
			g2d.setFont(new Font("Serif",Font.PLAIN,30)); 
			g2d.drawString(String.valueOf(f[1][2]),228,149);

			//case 7
			g2d.setFont(new Font("Serif",Font.PLAIN,30)); 
			g2d.drawString(String.valueOf(f[2][2]),178,192);

			//case 8
			g2d.setFont(new Font("Serif",Font.PLAIN,30)); 
			g2d.drawString(String.valueOf(f[2][1]),128,192);

			//case 9
			g2d.setFont(new Font("Serif",Font.PLAIN,30)); 
			g2d.drawString(String.valueOf(f[2][0]),79,192);

			//case 10
			g2d.setFont(new Font("Serif",Font.PLAIN,30)); 
			g2d.drawString(String.valueOf(f[3][2]),28,147);

			//case 11
			g2d.setFont(new Font("Serif",Font.PLAIN,30)); 
			g2d.drawString(String.valueOf(f[3][1]),28,109);

			//case 12
			g2d.setFont(new Font("Serif",Font.PLAIN,30)); 
			g2d.drawString(String.valueOf(f[3][0]),28,68);

			g2d.dispose();


		String num = String.valueOf(numero);

		try {   
			ImageIO.write(dom, "png", new File("Pictures/Domino/Acc/domino" + num + ".png"));
			BufferedImage image = ImageIO.read(new File("Pictures/Domino/Acc/domino" + num + ".png"));
			this.setI(new ImageIcon(image));
		} catch(Exception ex) {   
				ex.printStackTrace();   
		}  

		numero++;
		setVisible(true);

    }   


    private void setI(ImageIcon a){ //Vu que la méthode SetIcon de JLabel est static , je la fais moi même pour que je puisse le faire dans le constructeur
        this.setIcon(a);
    }

	public Icon getImageIcon(){
		return this.getIcon();
	}

	public TuileDView getTuileDView(){
		return this;
	}
    
    /*
        Cette classe produira l'affichage et seulement l'affichage des tuiles des TuilesD
     */
}
