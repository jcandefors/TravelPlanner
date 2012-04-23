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
//			ArrayList<UserData> tempRegister= new ArrayList<UserData>();
//			String temp = "kalleanka";
//			tempRegister.add(new UserData("test", temp.hashCode()));/	
		programFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		new LogInWindow(programFrame);
	}	
		
}
