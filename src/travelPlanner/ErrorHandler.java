package travelPlanner;


import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import javax.swing.JFrame;
import javax.swing.JOptionPane;



/**
 * A class to handle errors in the application Travel Planner.
 * @author Joakim Candefors
 *
 */
public class ErrorHandler {	

/**
 * Prints the error received to system.error which is to file.
 * @param e The exception thrown.
 * @param classname	The class which the exception was thrown by.
 */
	public static void printError(Exception e, String classname){
		JFrame frame = new JFrame();
		System.err.printf("%s%n", classname, e); //to be improved
		e.printStackTrace();					
		JOptionPane.showMessageDialog(frame, "Ett fel har uppstått i programmet: "
				+ e.getMessage() + ". Vänligen se felloggen för mer information.", "TravelPlanner Error" ,JOptionPane.WARNING_MESSAGE);

	}

	/**
	 * Sets the default error output stream to be to a file named "TPerror.txt".
	 */
	public static void setErrorOut(){
		try{
			OutputStream output = new FileOutputStream("TPError.txt", true);
			PrintStream printOut = new PrintStream(output);
			System.setErr(printOut);
		}catch (FileNotFoundException e){
			//do nothing
		}
	}

}
