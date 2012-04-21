package travelPlanner;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;


public class CreateNewUserWindow {
	
	private JFrame createNewUserFrame;
	private LogInActionHandler actionHandler;
	private JTextField userNameField;
	private JPasswordField passwordField;
	private JPasswordField passwordCoypField;
	private JLabel createNewUserInInstructionLabel;
	private JLabel userNameLabel;
	private JLabel passWordLabel;
	private JLabel passWordCopyLabel;
	private JButton createNewUserButton;
	private JButton quitButton;
	private static String USERNAME = "Användarnamn";
	private static String PASSWORD = "Lösenord";
	private static String PASSWORDCOPY = "Repetera lösenord";
	private final static String   PROGRAMNAME = "Reseplaneraren";
	private static String INSTRUCTIONS = "<html>Välkommen till "+ PROGRAMNAME +".<br> För att skapa ny användare, <br> skriv in användarnamn och lösenord i fälten nedan<br> välj sedan skapa ny användare,<br> Godkännda tecken är siffror 0-9 och bokstäver A - O. <br> Lösenord och användarnman får innhålla min 5 max 8 tecken. </html>"; //TODO
	private static String QUITBUTTONTEXT = "Avbryt";
	private static String CREATENEWUSER = "Skapa ny användare";
	private static String WINDOWTEXT = "Skapa ny anvädnare till "+ PROGRAMNAME;
	private static String EMPTYLOGINFIELDDIALOG = "Du måste fylla i både användarnamn och lösenordsfältet";
	
public CreateNewUserWindow(JFrame frame){
	createNewUserFrame = frame;
	actionHandler = new LogInActionHandler();
	makeFrame();
	createNewUserFrame.pack();
	createNewUserFrame.setVisible(true);					
}



private void  makeFrame(){
	initialiseComponents();
	
	Container contentPane = createNewUserFrame.getContentPane();
	GroupLayout layout = new GroupLayout(contentPane);
	contentPane.setLayout(layout);

	layout.setAutoCreateGaps(true);
	layout.setAutoCreateContainerGaps(true);
	
	layout.setHorizontalGroup(layout.createSequentialGroup()
			.addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
			.addComponent(createNewUserInInstructionLabel)
			.addComponent(userNameLabel)
			.addComponent(passWordLabel)
			.addComponent(passWordCopyLabel))
					.addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
					.addComponent(userNameField)
					.addComponent(passwordField)
					.addComponent(passwordCoypField))
					.addGroup(layout.createSequentialGroup()
							.addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING))
							.addComponent(quitButton))
							.addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)								
									.addComponent(createNewUserButton))							
									);
					
					
	layout.linkSize(SwingConstants.HORIZONTAL, quitButton, createNewUserButton);
	
	layout.setVerticalGroup(layout.createSequentialGroup()
			.addComponent(createNewUserInInstructionLabel)
		.addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
				.addComponent(userNameLabel)
				.addComponent(userNameField))
			.addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
				.addComponent(passWordLabel)
				.addComponent(passwordField))
			.addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
				.addComponent(passWordCopyLabel)
				.addComponent(passwordCoypField))
			.addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE))
			.addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
				 .addComponent(createNewUserButton)
				 .addComponent(quitButton))
				);
	
}

private void initialiseComponents(){
	//Create Labels	
	createNewUserInInstructionLabel = new JLabel();
	userNameLabel = new JLabel();
	passWordLabel = new JLabel();
	passWordCopyLabel = new JLabel();
	
	
	// Initialise text in labels
	createNewUserInInstructionLabel.setText(INSTRUCTIONS);
	userNameLabel.setText(USERNAME);
	passWordLabel.setText(PASSWORD);
	passWordCopyLabel.setText(PASSWORDCOPY);
	
	//Create Buttons
	createNewUserButton = new JButton();
	quitButton = new JButton();
	
	// Initialise text in Buttons
	createNewUserButton.setText(CREATENEWUSER);
	quitButton.setText(QUITBUTTONTEXT);
	
	//Initialise ActionListerners for buttons
	
//	createNewUserButton.addActionListener(new ActionListener(){
//		public void actionPerformed(ActionEvent e){
//			createNewUserActionPerformed(e);
//			}
//			});	
	
	
	//Create Fields
	
	userNameField = new JTextField();
	passwordField = new JPasswordField(10);
	passwordCoypField = new JPasswordField(10);
		

}

	



}
