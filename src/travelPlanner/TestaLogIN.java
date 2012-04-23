package travelPlanner;
import javax.swing.JFrame;
import java.util.ArrayList;
import javax.swing.WindowConstants;

public class TestaLogIN {

	public TestaLogIN() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
	ErrorHandler.setErrorOut();
		JFrame programFrame = new JFrame();
//		ArrayList<UserData> tempRegister= new ArrayList<UserData>();
//		String temp = "kalleanka";
//		tempRegister.add(new UserData("test", temp.hashCode()));/	
	programFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
	new LogInWindow(programFrame);
		
	

   

	}
	ArrayList<UserData> tempRegister= new ArrayList<UserData>();

	
}
