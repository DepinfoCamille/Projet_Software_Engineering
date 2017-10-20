import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.filechooser.FileNameExtensionFilter;

import com.sun.javafx.tk.Toolkit;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.imageio.ImageIO;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Container;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.MediaTracker;
import java.awt.Panel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.Serializable;

public class ImageViewer extends JFrame /*implements ActionListener*/
{
	private DisplayedImage inputImage = new DisplayedImage(); 
	private DisplayedImage ouputImage = new DisplayedImage();
	private JButton buttonAction = new JButton("Action");
	


	private JMenuBar menuBar = new JMenuBar();
	private JMenu fileMenu = new JMenu("File");
	
	private JMenuItem itemCharger = new JMenuItem("Charger"); //Ajouter "Charger" dans le menu

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
		

		
		// Defines action associated to buttons
		buttonAction.addActionListener(new ButtonListener());

		JPanel output = new JPanel();
		output.setLayout(new BoxLayout(output, BoxLayout.PAGE_AXIS));
		output.add(ouputImage); 

		JPanel global = new JPanel();
		global.setLayout(new BoxLayout(global, BoxLayout.LINE_AXIS));
		global.add(input);
		global.add(action);
		global.add(output);

		this.getContentPane().add(global);

		this.fileMenu.addSeparator();
		itemClose.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0) {
				System.exit(0);
			} 
			
		
		});
		
		itemCharger.addActionListener(new Chargement());
		
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
			System.out.println("Action Performed");
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
		
			    
			}
		}

		

     }
	}

		
	
	