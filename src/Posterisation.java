
import java.awt.Color;
import java.awt.image.BufferedImage;

public class Posterisation {
	
	private BufferedImage image_entrante;

	public Posterisation(BufferedImage image) {
		this.image_entrante = image;
	}
	
	public BufferedImage posteriser() {

		int image_h = this.image_entrante.getHeight();
	    int image_w  = this.image_entrante.getWidth();
	
		BufferedImage image_sortante = new BufferedImage(image_w, image_h, this.image_entrante.getType());
		
		for (int j = 0; j < image_h; j++) {
    	    for (int i = 0; i < image_w; i++) {
    	    	Color couleur = new Color(this.image_entrante.getRGB(i,j));

    	    	int rouge = couleur.getRed();
    	    	int vert = couleur.getGreen();
    	    	int bleu = couleur.getBlue();
    	    	
    	    	Color couleur2 = new Color(255 - rouge,255 - vert,255 - bleu);
    	    	int rgb = couleur2.getRGB();
    	    	image_sortante.setRGB(i,j,rgb);
    	    	
    	    }
		}
		
		return image_sortante;
	}


}

import java.awt.Color;
import java.awt.image.BufferedImage;

public class Posterisation {
	
	private BufferedImage image_entrante;

	public Posterisation(BufferedImage image) {
		this.image_entrante = image;
	}
	
	public BufferedImage posteriser() {

		int image_h = this.image_entrante.getHeight();
	    int image_w  = this.image_entrante.getWidth();
	
		BufferedImage image_sortante = new BufferedImage(image_w, image_h, this.image_entrante.getType());
		
		for (int j = 0; j < image_h; j++) {
    	    for (int i = 0; i < image_w; i++) {
    	    	Color couleur = new Color(this.image_entrante.getRGB(i,j));

    	    	int rouge = couleur.getRed();
    	    	int vert = couleur.getGreen();
    	    	int bleu = couleur.getBlue();
    	    	
    	    	Color couleur2 = new Color(255 - rouge,255 - vert,255 - bleu);
    	    	int rgb = couleur2.getRGB();
    	    	image_sortante.setRGB(i,j,rgb);
    	    	
    	    }
		}
		
		return image_sortante;
	}

}

}

