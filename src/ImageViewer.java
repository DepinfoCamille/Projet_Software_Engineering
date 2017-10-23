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
import java.awt.Graphics2D;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.awt.image.IndexColorModel;
import java.io.File;
import java.io.IOException;


@SuppressWarnings("serial")


public class ImageViewer extends JFrame /*implements ActionListener*/
{
	private DisplayedImage inputImage = new DisplayedImage(); 
	private DisplayedImage ouputImage = new DisplayedImage();

	private JButton buttonAction = new JButton("Action");
	


    /*
    int image_h = inputImage.getHeight();
    int image_w  = inputImage.getWidth();
    
    BufferedImage image_entrante = new BufferedImage(image_h, image_w, inputImage.getType());
    BufferedImage image_sortante = new BufferedImage(image_h, image_w, inputImage.getType());
    */
	
	private JButton buttonPosteriser = new JButton("Posteriser");
	private JButton buttonCompresser = new JButton("Compresser");


	private JMenuBar menuBar = new JMenuBar();
	private JMenu fileMenu = new JMenu("File");
	

	private JMenuItem itemCharger = new JMenuItem("Charger"); //Ajouter "Charger" dans le menu


	private JMenuItem itemSave = new JMenuItem("Save");

	private JMenuItem itemClose = new JMenuItem("Close");
	
	public ImageViewer () {
		this.setTitle("Image Viewer");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(1000, 400);

		JPanel input = new JPanel();
		input.setLayout(new BoxLayout(input, BoxLayout.PAGE_AXIS));
		input.add(inputImage);


		JPanel action = new JPanel();
		action.setLayout(new BoxLayout(action, BoxLayout.PAGE_AXIS));
		action.add(buttonAction);
		

		

		JPanel post = new JPanel();
		post.setLayout(new BoxLayout(post, BoxLayout.PAGE_AXIS));
		post.add(buttonPosteriser);
		// Defines action associated to buttons
		buttonPosteriser.addActionListener(new ButtonListener());
		
		JPanel compress = new JPanel();
		compress.setLayout(new BoxLayout(compress, BoxLayout.PAGE_AXIS));
		compress.add(buttonCompresser);

		// Defines action associated to buttons
		buttonCompresser.addActionListener(new ButtonListener());

		JPanel output = new JPanel();
		output.setLayout(new BoxLayout(output, BoxLayout.PAGE_AXIS));
		output.add(ouputImage); 

		JPanel global = new JPanel();
		global.setLayout(new BoxLayout(global, BoxLayout.LINE_AXIS));
		global.add(input);
		global.add(post);
		global.add(compress);
		global.add(output);
		
		this.getContentPane().add(global);

		this.fileMenu.addSeparator();
		itemSave.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0) {
				BufferedImage image_a_sauver = new BufferedImage(output.getWidth(), output.getHeight(), BufferedImage.TYPE_INT_RGB);
                Graphics2D g = image_a_sauver.createGraphics();
                output.printAll(g);
                try {
					ImageIO.write(image_a_sauver, "png", new File("image_finale.png"));
				} catch (IOException e) {
					e.printStackTrace();
				}
            }
		});
		
		this.fileMenu.addSeparator();
		itemClose.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0) {
				System.exit(0);
			} 
			
		
		});
		

		itemCharger.addActionListener(new Chargement());
		

		this.fileMenu.add(itemSave);

		this.fileMenu.add(itemClose);  
		this.menuBar.add(fileMenu);
		this.fileMenu.add(itemCharger);  
		this.setJMenuBar(menuBar);

		this.setVisible(true);
		
	}

	/**
	 * Class listening to a given button
	 */
	class ButtonListener implements ActionListener{
		public void actionPerformed(ActionEvent arg0) 
		{
			if (arg0.getSource() == buttonPosteriser) {
				BufferedImage image_entrante = inputImage.getImage();
				Posterisation p = new Posterisation(image_entrante);
				BufferedImage image_sortante = p.posteriser();
			    ouputImage.setImage(image_sortante);
				repaint();
				System.out.println("Posterisation achevée");
			}
			
			else if (arg0.getSource() == buttonCompresser) {
				System.out.println("Compression achevée");
			}
			
		}
	}
	
	
     class Chargement implements ActionListener{
		public void actionPerformed(ActionEvent arg0)
		{  	
				
			JFileChooser ImageChooser = new JFileChooser();  //Selectionner
			FileNameExtensionFilter filter = new FileNameExtensionFilter("IMAGE" , "jpg" , "png" , "gif"); //Définir l'extention de l'image
			ImageChooser.addChoosableFileFilter(filter); //Lien entre  le ImageChooser et le Filter pour definir l'extention que L'imageChooser doit avoir
			int result = ImageChooser.showSaveDialog(null); //resultat de l'opération
			
			if (result == JFileChooser.APPROVE_OPTION) // Si on obtient le resultat souhaité
			{
				File SelectedImage = ImageChooser.getSelectedFile(); //Srocker l'image choisie dans un fichier
				//String Path = SelectedImage.getAbsolutePath();  chemin de l'image selectionnée
			
				BufferedImage image = inputImage.getImage();
				try {
					image = ImageIO.read(SelectedImage);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				inputImage.setImage(image);
				repaint();
				
				

		
			    //
			}
		}

		

     }
     public Color[] generateColors(int n)
     {
         Color[] cols = new Color[n];
         for(int i = 0; i < n; i++)
         {
             cols[i] = Color.getHSBColor((float) i / (float) n, 0.85f, 1.0f);
         }
         return cols;
     }
	}

		
	
	