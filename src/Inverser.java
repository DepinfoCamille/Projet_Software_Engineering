import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.BoxLayout;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Color ; 

import java.lang.Object ; 

public class Inverser extends JFrame /*implements ActionListener*/
{
	private DisplayedImage inputImage = new DisplayedImage(); 
	private DisplayedImage ouputImage = new DisplayedImage();
	private JButton buttonAction = new JButton("Action");

	private JMenuBar menuBar = new JMenuBar();
	private JMenu fileMenu = new JMenu("File");

	private JMenuItem itemClose = new JMenuItem("Close");

	public Inverser () {
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
		this.fileMenu.add(itemClose);  

		this.menuBar.add(fileMenu);
		this.setJMenuBar(menuBar);

		this.setVisible(true);
	}
	


	/**
	 * Class listening to a given button
	 */
	class ButtonListener implements ActionListener{
		

		public void actionPerformed(ActionEvent arg0)
		{	for(int x =0 ; x<1000 ; x++) {
				for(int y = 0 ; y<400 ; y++){
					
					Color c = new Color(inputImage.getRGB(x,y)) ; // on récupère les informations sur le pixel de l'ancienne image
					int red = 255-c.getRed() ; 
					int green = 255-c.getGreen() ; 
					int blue = 255-c.getBlue() ; 
					

					int new_pixel = (255<<24) | (red<<16) | (green<<8) | blue ; // on crée un nouveau pixel de couleur inverse à celle de l'ancienne image
					
					outputImage.setRGB(x,y,new_pixel) ; // on modifie le pixel de l'image qui va être affichée 
				}
		}
			System.out.println("Action Performed");
		}
	}
}