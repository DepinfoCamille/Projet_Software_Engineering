import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.filechooser.FileNameExtensionFilter;

import javax.swing.JButton;

import javax.swing.JFileChooser;
import javax.imageio.ImageIO;
import javax.swing.BoxLayout;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import java.util.ArrayList;



@SuppressWarnings("serial")

public class ImageViewer extends JFrame /*implements ActionListener*/
{
	private DisplayedImage inputImage = new DisplayedImage(); 
	private DisplayedImage ouputImage = new DisplayedImage();
	
	private JButton buttonHistogramme = new JButton("Histogramme");
	private JButton buttonPosteriser = new JButton("Posteriser");
	private JButton buttonPalette = new JButton("Palette");
	private JButton buttonQuantifier = new JButton("Quantifier");
	
	private JMenuBar menuBar = new JMenuBar();
	private JMenu fileMenu = new JMenu("File");
	private JMenuItem itemCharger = new JMenuItem("Charger");
	private JMenuItem itemSave = new JMenuItem("Save");
	private JMenuItem itemClose = new JMenuItem("Close");
	
	public ImageViewer () {
		
		this.setTitle("Traitement d'images");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(1000, 400);

		JPanel input = new JPanel();
		input.setLayout(new BoxLayout(input, BoxLayout.PAGE_AXIS));
		input.add(inputImage);

		JPanel action = new JPanel();
		action.setLayout(new GridLayout(4,1));
		action.add(buttonHistogramme);
		action.add(buttonPosteriser);
		action.add(buttonPalette);
		action.add(buttonQuantifier);
		
		// Defines action associated to buttons
		buttonHistogramme.addActionListener(new ButtonHistogram());
		buttonPosteriser.addActionListener(new ButtonPosterize());
		buttonPalette.addActionListener(new ButtonPalette());
		buttonQuantifier.addActionListener(new ButtonQuantization());
		
		JPanel output = new JPanel();
		output.setLayout(new BoxLayout(output, BoxLayout.PAGE_AXIS));
		output.add(ouputImage); 

		JPanel global = new JPanel();
		global.setLayout(new BoxLayout(global, BoxLayout.LINE_AXIS));
		global.add(input);
		global.add(action);
		global.add(output);
		
		this.getContentPane().add(global);

		itemSave.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0) {
                try {
					ImageIO.write(ouputImage.getImage(), "png", new File("image_finale.png"));
				} catch (IOException e) {
					e.printStackTrace();
				}
            }
		});
		
		itemClose.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0) {
				System.exit(0);
			} 
		});

		itemCharger.addActionListener(new Chargement());

		this.fileMenu.add(itemCharger);
		this.fileMenu.addSeparator();
		this.fileMenu.add(itemSave);
		this.fileMenu.addSeparator();
		this.fileMenu.add(itemClose);
		
		this.menuBar.add(fileMenu);
		this.setJMenuBar(menuBar);

		this.setVisible(true);
		
	}

	/**
	 * Class listening to a given button
	 */
	
	class ButtonHistogram implements ActionListener{
		public void actionPerformed(ActionEvent arg0) 
		{
			 EventQueue.invokeLater(() -> {
		            new Histogramme().display();
		        });
		}
	}
	
	class ButtonPosterize implements ActionListener{
		public void actionPerformed(ActionEvent arg0) 
		{
			BufferedImage image_entrante = inputImage.getImage();
			Posterisation p = new Posterisation(image_entrante);
			BufferedImage image_sortante = p.posteriser();
		    ouputImage.setImage(image_sortante);
			repaint();
			System.out.println("Posterisation achev�e");
		}
	}
	
	class ButtonPalette implements ActionListener{
		public void actionPerformed(ActionEvent arg0) 
		{
			
			BufferedImage image_entrante = inputImage.getImage();
			ArrayList<Point3> palette_couleurs = new ArrayList<Point3>();
			
			Point3 col1 = new Point3(54,100,92);
			Point3 col2 = new Point3(154,50,75);
			Point3 col3 = new Point3(200,100,150);
			
			palette_couleurs.add(col1);
			palette_couleurs.add(col2);
			palette_couleurs.add(col3);
			
			BufferedImage image_palette = new BufferedImage(palette_couleurs.size()*10, 10, image_entrante.getType());

			for (int i = 0; i < palette_couleurs.size(); i++) {
				
				Color couleur = new Color(palette_couleurs.get(i).getCoord(0),palette_couleurs.get(i).getCoord(1),palette_couleurs.get(i).getCoord(2));

			    for (int y = 0; y < 10; y++) {
			        for (int x = 0; x < 10; x++) {
			        	int rgb = couleur.getRGB();
			            image_palette.setRGB(x+i*10,y,rgb);
			         }
			    }
			}
			
			ouputImage.setImage(image_palette);
			repaint();
			System.out.println("Palette affich�e");
			
		}
	}
	
	
     class Chargement implements ActionListener{
		public void actionPerformed(ActionEvent arg0)
		{  	
			
			JFileChooser ImageChooser = new JFileChooser();  //Selectionner
			FileNameExtensionFilter filter = new FileNameExtensionFilter("IMAGE" , "jpg" , "png" , "gif"); //D�finir l'extention de l'image
			ImageChooser.addChoosableFileFilter(filter); //Lien entre  le ImageChooser et le Filter pour definir l'extention que L'imageChooser doit avoir
			int result = ImageChooser.showSaveDialog(null); //resultat de l'op�ration
			
			if (result == JFileChooser.APPROVE_OPTION) // Si on obtient le resultat souhait�
			{
				File SelectedImage = ImageChooser.getSelectedFile(); //Stocker l'image choisie dans un fichier
			//	String Path = SelectedImage.getAbsolutePath();  chemin de l'image selectionn�e
			
				BufferedImage image = inputImage.getImage();
				try {
					image = ImageIO.read(SelectedImage);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				inputImage.setImage(image);
				repaint();
			}
		}
	}
     
     class ButtonQuantization implements ActionListener{
    	 
		public void actionPerformed(ActionEvent arg0){
			
			BufferedImage image_entrante = inputImage.getImage();
			
			int image_h = image_entrante.getHeight();
		    int image_w  = image_entrante.getWidth();
		
			BufferedImage image_sortante = new BufferedImage(image_w, image_h, image_entrante.getType());
			
			ArbreBinaire arbre = null;
			
			ArrayList<Point> tous_les_pixels = new ArrayList<Point>();
			
			for (int j = 0; j < image_h; j++) {
	    	    for (int i = 0; i < image_w; i++) {
	    	    	
	    	    	Color couleur = new Color(image_entrante.getRGB(i,j));

	    	    	int rouge = couleur.getRed();
	    	    	int vert = couleur.getGreen();
	    	    	int bleu = couleur.getBlue();
	    	    	
	    	    	int tab[] = {rouge,vert,bleu};
	    	    	Point point = new Point(tab);
	    	    	
	    	    	tous_les_pixels.add(point);
	    	    	
	    	    	/*
	    	    	if (i == 0 && j == 0) {
	    	    		arbre = new ArbreBinaire(point);
	    	    	}
	    	    	else {
	    	    		arbre.addPoint(point);
	    	    	}
	    	    	*/

	    	    }
			}
			
			ArrayList<Integer> nouvelles_couleurs = new ArrayList<Integer>();
			
			for (int i = 0; i < tous_les_pixels.size(); i++) {
				
				Point nouveau_point = arbre.getNN(tous_les_pixels.get(i));
				
				int nouveau_rouge = nouveau_point.getCoord(0);
    	    	int nouveau_vert = nouveau_point.getCoord(1);
    	    	int nouveau_bleu = nouveau_point.getCoord(2);
    	    	
    	    	Color nouvelle_couleur = new Color(nouveau_rouge,nouveau_vert,nouveau_bleu);
    	    	int rgb = nouvelle_couleur.getRGB();
    	    	nouvelles_couleurs.add(rgb);
				
			}
			
			int k = 0;
			for (int j = 0; j < image_h; j++) {
	    	    for (int i = 0; i < image_w; i++) {
	    	    	image_sortante.setRGB(i,j,nouvelles_couleurs.get(k));
	    	    	k++;
	    	    }
			}
	    	    	
		    ouputImage.setImage(image_sortante);
			repaint();
			System.out.println("Quantification achev�e");
			
		}
		
	}
     
}
			
				
    
     