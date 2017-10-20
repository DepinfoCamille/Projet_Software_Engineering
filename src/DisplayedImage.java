import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.filechooser.FileNameExtensionFilter;


import javafx.scene.image.Image;
 
public class DisplayedImage extends JPanel {
	
   
	private BufferedImage image;
    
    
    public DisplayedImage() {
    		try {
    			image = ImageIO.read(new File("img.png"));
        	} catch (IOException e) {
        		e.printStackTrace();
        	}                
    }
    
    public void paintComponent(Graphics g){
    		//g.drawImage(image, 0, 0, this); // draw as much as possible
    		g.drawImage(image, 0, 0, this.getWidth(), this.getHeight(), this); // draw full image
    	    
    }                   
    
   public void setImage(BufferedImage res)
   {
	   image = res;
    }
   
   public BufferedImage getImage() {
   	return image;
   }
	  
   }
   
