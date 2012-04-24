package travelPlanner;

import java.awt.Dimension;
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
public class ImagePanel extends JPanel{
	private Image originalImage;
	private Image backgroundImage;
	
/**
 * Constructs a background panel.
 * @param file The file with the image to set as background.
 */
	public ImagePanel(File file){		   
			setBackground(file);
	}
	
	public void setBackground(File file){
		
		try{
			backgroundImage = ImageIO.read(file);
			originalImage = backgroundImage;
		}catch (IOException e){
			ErrorHandler.printError(e, this.getClass().toString());
		}   	
		paintComponent(backgroundImage.getGraphics());
	}

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