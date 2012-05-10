package travelPlanner;

import java.awt.Graphics;
import java.awt.Image;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;
import javax.swing.JPanel;


import travelPlanner.ErrorHandler;

/**
 * ImagePanel is a JPanel with an image drawn as background of the panel.
 * @author Joakim Candefors
 *
 */
public class ImagePanel extends JPanel{

	private Image backgroundImage;

	/**
	 * Constructs a ImagePanel.
	 * @param fileName The filename for the image to set as background of the panel.
	 */
	public ImagePanel(String fileName){		   
		setBackground(fileName);
	}
	
	/**
	 * Sets the background of the panel from the filename given.
	 * @param fileName The path and filename for the image to be used as background.
	 */
	public void setBackground(String fileName){
			InputStream input = getClass().getResourceAsStream(fileName);
		try{
			backgroundImage = ImageIO.read(input);
			
		}catch (IOException e){
			ErrorHandler.printError(e, this.getClass().toString());
		}   	
		paintComponent(backgroundImage.getGraphics());
	}
	/**
	 * Paints the background image on the panel.
	 * @Overide
	 */	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);

		g.drawImage(backgroundImage, 0, 0, null);
	}

}