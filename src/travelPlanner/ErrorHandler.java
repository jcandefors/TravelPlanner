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
		System.err.printf("%s: %s%n", e);
		JOptionPane.showMessageDialog(frame, "Ett fel har uppstått i programmet som kommer behöva avslutas: %n"
		+ e.getMessage() + "%n Vänligen se felloggen för mer information.", "TravelPlanner Error" ,JOptionPane.WARNING_MESSAGE);
		frame.setVisible(true);
		
		//quit.addActionListener(new ActionListener() {
		//public void actionPerformed(ActionEvent e) { System.exit(-1);	} });				
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
}
