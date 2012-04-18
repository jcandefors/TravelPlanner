package travelPlanner;

import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JPanel;


import travelPlanner.ErrorHandler;

/**
 * @author j
 *
 */
public class BackgroundPanel extends JPanel {

	private Image backgroundImage;
	
/**
 * Constructs a background panel.
 * @param file The file with the image to set as background.
 */
	public BackgroundPanel(File file){		   
			setBackground(file);
	}
	
	public void setBackground(File file){
		
		try{
			backgroundImage = ImageIO.read(file);
		}catch (IOException e){
			ErrorHandler.printError(e);
		}   	
		paintComponent(backgroundImage.getGraphics());
	}

		public void paintComponent(Graphics g) {
			super.paintComponent(g);
			
			g.drawImage(backgroundImage, 0, 0, null);
		}
	}