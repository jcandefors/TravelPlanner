package travelPlanner;

import javax.swing.JFrame;
import javax.swing.WindowConstants;

/**
 * This is the main class of the application TravelPlanner
 * @author j
 *
 */
public class TravelPlanner extends JFrame{

	
	public static void main(String[] args) {
		ErrorHandler.setErrorOut();
			JFrame programFrame = new JFrame();
			programFrame.setLocationByPlatform(true);
		programFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		new LogInWindow(programFrame);
	}	
		
}
