package travelPlanner;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

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

public class LogInWindow {
	
		private JFrame loginFrame;
		private LogInActionHandler actionHandler;
		private JTextField userNameField;
		private JPasswordField passwordField;
		private JLabel logInInstructionLabel;
		private JLabel userNameLabel;
		private JLabel passWordLabel;
		private JButton logInButton;
		private JButton createNewUserButton;
		private final static String   PROGRAMNAME = "Reseplaneraren";
		private static String LOGININSTRUCTIONS = "<html>Välkommen till "+ PROGRAMNAME +".<br>Skriv in anvädnarnamn och lösenord <br> eller välj skapa ny användare</html>";
		private static String EMPTYLOGINFIELDDIALOG = "Du måste fylla i både användarnamnfältet och lösenordsfältet för att logga in ";
		private static String LOGIN = "Logga in";
		private static String CREATENEWUSER = "Skapa ny användare";
		private static String USERNAME = "Användarnamn";
		private static String PASSWORD = "Lösenord";
		

		/**
		 * Create a LogInWindow on the specified JFrame.
		 * In the Window created the user can type in username and pasword for logging in
		 * or press a "create new user" - button 
		 */
		public LogInWindow(JFrame programFrame) {
				loginFrame = programFrame;
				actionHandler = new LogInActionHandler();
				loginFrame.setTitle("Log in to: " + PROGRAMNAME);
				makeLogInFrame();
				loginFrame.pack();
				loginFrame.setVisible(true);					
			}
//	private void logInButtonAction(){
//		String username = userNameField.getSelectedText(); //TODO handle Exception
//		String password = passwordField.getSelectedText();
//		
//		if(checkPasswordAndUsername(userName){
//			PassWordHandler passWordHandler = new PassWordHandler();
//			int passwordcode = passWordHandler.getHashCode(password);
//				
//		}
//		
//		else{
//			JOptionPane dialog = new JOptionPane(EMPTYLOGINFIELDDIALOG, JOptionPane.INFORMATION_MESSAGE, JOptionPane.OK_OPTION);
//	        dialog.showMessageDialo;//TODO
//		}
//	
//	}
	// A method for checking that password nor userword is null och a empty string
	
//	private boolean checkPasswordAndUsername(String userName String password){
//				
//		if((userName==null)||(password == null)){
//		
//		return false;
//		}
//		if((userName.length() = 0)||(password.length()=0)){
//		validVariabels = false;
//		return false;
//		}
//		return true;
//		
//	}
	
	/**
	 * Intisialise all components for logInwindow		
	 */
		
	private void initialiseComponents(){
		//Create Labels	
		logInInstructionLabel = new JLabel();
		userNameLabel = new JLabel();
		passWordLabel = new JLabel();
		
		// Initialise text in labels
		logInInstructionLabel.setText(LOGININSTRUCTIONS);
		userNameLabel.setText(USERNAME);
		passWordLabel.setText(PASSWORD);
		
		//Create Buttons
		logInButton = new JButton();
		createNewUserButton = new JButton();
		
		// Initialise text in Buttons
		logInButton.setText(LOGIN);
		createNewUserButton.setText(CREATENEWUSER);
		
		//Initialise ActionListerners for buttons
		logInButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
			//logInButtonAction();
			}
			});
		
//		createNewUserButton.addActionListener(new ActionListener(){
//			public void actionPerformed(ActionEvent e){
//				createNewUserActionPerformed(e);
//				}
//				});	
		
		//Create Fields
		
		userNameField = new JTextField();
		passwordField = new JPasswordField(10);
			
	}

	private void makeLogInFrame(){
		
		initialiseComponents();
		
		Container contentPane = loginFrame.getContentPane();
		GroupLayout layout = new GroupLayout(contentPane);
		contentPane.setLayout(layout);
		
		layout.setAutoCreateGaps(true);
		layout.setAutoCreateContainerGaps(true);
		
		layout.setHorizontalGroup(layout.createSequentialGroup()
				.addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
				.addComponent(logInInstructionLabel)
				.addComponent(userNameLabel)
				.addComponent(passWordLabel))
						.addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
						.addComponent(userNameField)
						.addComponent(passwordField))
						.addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
						.addComponent(logInButton))
						.addGroup(layout.createSequentialGroup()
								.addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING))
								.addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)								
										.addComponent(createNewUserButton))
										));
						
						
		layout.linkSize(SwingConstants.HORIZONTAL, logInButton, createNewUserButton);
		
		layout.setVerticalGroup(layout.createSequentialGroup()
				.addComponent(logInInstructionLabel)
			.addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
					.addComponent(userNameLabel)
					.addComponent(userNameField)
					.addComponent(logInButton))
				.addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
					.addComponent(passWordLabel)
					.addComponent(passwordField))
				.addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE))
				.addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
					 .addComponent(createNewUserButton))
					);
						    		    
	}
	
}


