
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.awt.image.Raster;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JPanel;

 
@SuppressWarnings("serial")

public class DisplayedImage extends JPanel {
	
   
	private BufferedImage image;
    
    
    public DisplayedImage() {
    		try {
    			image = ImageIO.read(new File("img.png"));
        	} catch (IOException e) {
        		e.printStackTrace();
        	}                
    }
    
    public BufferedImage getImage() {
    	return image;
    }
    
    public void setImage(BufferedImage res) {
    	image = res;
    }
    
    public void paintComponent(Graphics g){
    		//g.drawImage(image, 0, 0, this); // draw as much as possible
    		g.drawImage(image, 0, 0, this.getWidth(), this.getHeight(), this); // draw full image

    	    
                       

	  
   }


   

    }



