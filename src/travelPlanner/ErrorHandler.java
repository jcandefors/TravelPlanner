package travelPlanner;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.io.PrintStream;

import javax.swing.JFrame;
import javax.swing.JOptionPane;




public class ErrorHandler {	


	public static void printError(Exception e){
		JFrame frame = new JFrame();
		System.err.printf("%s%n", e);
		JOptionPane.showMessageDialog(frame, "Ett fel har uppst�tt i programmet som kommer beh�va avslutas: "
		+ e.getMessage() + ". V�nligen se felloggen f�r mer information.", "TravelPlanner Error" ,JOptionPane.WARNING_MESSAGE);
		quit();
				
	}

	/**
	 * 
	 */
	public static void setErrorOut(){
		try{
		OutputStream output = new FileOutputStream("TPError.txt");
		PrintStream printOut = new PrintStream(output);
		System.setErr(printOut);
		}catch (FileNotFoundException e){
			//do nothing
		}
	}
	
	/**
	 * Quits the application, closing files.
	 */
	public static void quit(){
		//TBC to be nicely done.
		System.exit(-1);
	}
}
