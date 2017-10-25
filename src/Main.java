

	import java.util.ArrayList;
	import java.util.Arrays;
	import java.util.Scanner;
	import java.io.File;
	import java.io.IOException;
	import javax.imageio.ImageIO;
	import java.awt.image.BufferedImage;

	public class Main 
	{
		public static void main(String[] args)
	    { 
	        System.out.println("Entrer le nom de l'image à charger :");
	        String filename = new Scanner(System.in).nextLine();
	        
	        try{
	            File pathToFile = new File(filename);
	            BufferedImage img = ImageIO.read(pathToFile);

	            int imgHeight = img.getHeight();
	            int imgWidth  = img.getWidth();
	            BufferedImage res_img = new BufferedImage(imgWidth, imgHeight, img.getType());
	            BufferedImage id_img = new BufferedImage(imgWidth, imgHeight, img.getType());
	            
	            ArrayList<Point3> pixels = new ArrayList<Point3>();
	            for (int y = 0; y < imgHeight; y++) {
	                for (int x = 0; x < imgWidth; x++) {
	                    
	                    int Color = img.getRGB(x,y);
	                    int R = (Color >> 16) & 0xff;
	                    int G = (Color >> 8) & 0xff;
	                    int B = Color & 0xff;
	                    pixels.add(new Point3(R,G,B));
	                }
	            }
	            
	            ArbreBinaire(point3)  = (3, pixels, 3);
	            
	            for (int y = 0; y < imgHeight; y++) {
	            	for (int x =0; x < imgWidth; x++) {
	            		Point3 key = (Point3) kdTree.getNN(pixels.get(x+y));
	            		int resR = key.get(0), resG = key.get(1), resB = key.get(2);

	                    int cRes = 0xff000000 | (resR << 16)
	                                          | (resG << 8)
	                                          | resB;
	                    res_img.setRGB(x,y,cRes);
	                }
	            }
	/////////////////////////////////////////////////////////////////

	            ImageIO.write(id_img, "jpg", new File("ResId.jpg"));
	            ImageIO.write(res_img, "jpg", new File("ResColor.jpg"));
	/////////////////////////////////////////////////////////////////
	        } catch (IOException e) {
	            System.err.println(e.getMessage());
	        }
	    }
	}

