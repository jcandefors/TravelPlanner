package travelPlanner;



import java.awt.Container;

	import java.awt.event.ActionListener;

	import javax.swing.JButton;
	import javax.swing.JFrame;
	import javax.swing.JLabel;
	import javax.swing.JTextField;
	import javax.swing.WindowConstants;

	import java.awt.*;
	import java.awt.event.*;
	import java.awt.image.*;
import java.util.ArrayList;

	import javax.swing.*;
import javax.swing.border.*;

	/**
	 * This class will start a pop-Up window where it is possible to 
	 * login to a program by typing in a username and a password that will be matched 
	 * against the user register for the program.
	 * The pop-Up window also contain a LINK for creating new users. 
	 * 
	 * @author ragnhild
	 * @version 0.1
	 *
	 */
public class TestLogIn {
	
	public static void main(String[] args) {
		JFrame programFrame = new JFrame("Test");
		//ArrayList<UserData> tempRegister= new ArrayList<UserData>();
		//String temp = "kalleanka";
		//tempRegister.add(new UserData("test", temp.hashCode()));
		programFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		new LogInWindow(programFrame);
	}
   
}

	
	