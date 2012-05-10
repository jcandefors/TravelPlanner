package travelPlanner;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;
import javax.swing.JPanel;


import travelPlanner.ErrorHandler;

/**
 * 
 * @author Joakim Candefors
 *
 */
public class ImagePanel extends JPanel{
	private Image originalImage;
	private Image backgroundImage;

	/**
	 * Constructs a background panel.
	 * @param fileName The file name for the image to set as background of the panel.
	 */
	public ImagePanel(String fileName){		   
		setBackground(fileName);
	}
	
	/**
	 * 
	 * @param file
	 */
	public void setBackground(String fileName){
			InputStream input = getClass().getResourceAsStream(fileName);
		try{
			backgroundImage = ImageIO.read(input);
			originalImage = backgroundImage;
		}catch (IOException e){
			ErrorHandler.printError(e, this.getClass().toString());
		}   	
		paintComponent(backgroundImage.getGraphics());
	}
	/**
	 * 
	 * @Overide
	 */	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);

		g.drawImage(backgroundImage, 0, 0, null);
	}

	public void scaleImage(Dimension frameSize){			
		int height= frameSize.height;
		int width = frameSize.width;
		backgroundImage = originalImage.getScaledInstance(width, height, Image.SCALE_FAST);
	}


}