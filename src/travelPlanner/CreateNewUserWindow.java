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
	private static String USERNAME = "Anv�ndarnamn";
	private static String PASSWORD = "L�senord";
	private static String PASSWORDCOPY = "Repetera l�senord";
	private final static String PROGRAMNAME = "Reseplaneraren";
	private static String PROBLEMFRAMETITLE = "Problem";
	private static String INSTRUCTIONS = "<html>V�lkommen till "
			+ PROGRAMNAME
			+ ".<br> F�r att skapa ny anv�ndare, <br> skriv in anv�ndarnamn och l�senord i f�lten nedan<br> v�lj sedan skapa ny anv�ndare,<br> L�senord och anv�ndarnman f�r innh�lla min"
			+ PassWordHandler.getMinimalLengthOfPassword() + "och max"
			+ PassWordHandler.getMaximalLengthOfPassword() + "tecken. </html>"; // TODO
	// FIXA
	// ATT
	// DET
	// SKER
	// GENOM
	// ACTIONHANDLER
	private static String QUITBUTTONTEXT = "Avbryt";
	private static String CREATENEWUSER = "Skapa ny anv�ndare";
	private static String WINDOWTEXT = "Skapa ny anv�dnare till " + PROGRAMNAME;
	private static String EMPTYLOGINFIELDDIALOG = "Du m�ste fylla i b�de anv�ndarnamn och l�senordsf�lten";
	private static String MISSMATCHPASSWORDFIELDDIALOG = "L�senorden matchade ej f�rs�k igen";
	private static String NEWUSERCREATEDDIALOG = "En ny anv�ndare har nu lagts till";
	private static String NOTVALIDUSERNAMEPASSWORD = "Anv�ndarnamn upptaget alternativ ej godk�nd l�ngd p� anv�ndarnamn / l�senord ";

	public CreateNewUserWindow(JFrame frame) {
		createNewUserFrame = frame;
		actionHandler = new LogInActionHandler();
		makeFrame();
		createNewUserFrame.pack();
		createNewUserFrame.setVisible(true);
	}

	public void quitButtonAction() {
		createNewUserFrame.dispose(); // TODO check it is right

	}

	public void createNewUserButtonAction() {

		// pick up information
		String username = userNameField.getText();
		char[] passwordArray = passwordField.getPassword();
		char[] passwordCopyArray = passwordCoypField.getPassword();

		StringBuilder sb = new StringBuilder();

		for (int i = 0; i < passwordArray.length; i++) {
			sb.append(passwordArray[i]);
		}
		String password = sb.toString();

		StringBuilder sb1 = new StringBuilder();

		for (int i = 0; i < passwordCopyArray.length; i++) {
			sb1.append(passwordArray[i]);
		}
		
		String passwordCopy = sb1.toString();

		// Check if the password match passwordcopy

		if (!(password.equals(passwordCopy))) {
			JFrame frame = new JFrame();
			JOptionPane.showMessageDialog(frame, MISSMATCHPASSWORDFIELDDIALOG,
					PROBLEMFRAMETITLE, JOptionPane.INFORMATION_MESSAGE);
			return;

		}

		
		// Check that they are potential valid username and password Strings

		if (!(validInput(username, password, passwordCopy))) {
			JFrame frame = new JFrame();
			JOptionPane.showMessageDialog(frame, EMPTYLOGINFIELDDIALOG,
					PROBLEMFRAMETITLE, JOptionPane.INFORMATION_MESSAGE);
			return;

		}

		if (actionHandler.createNewUser(username, password)) {
			JFrame frame = new JFrame();
			JOptionPane.showMessageDialog(frame, NEWUSERCREATEDDIALOG,
					PROBLEMFRAMETITLE, JOptionPane.INFORMATION_MESSAGE);

			createNewUserFrame.dispose(); // TODO check it is right

		} else {
			JFrame frame = new JFrame();
			JOptionPane.showMessageDialog(frame, NOTVALIDUSERNAMEPASSWORD,
					PROBLEMFRAMETITLE, JOptionPane.INFORMATION_MESSAGE);
			return;

		}
	}

	// A method for checking that password nor userword is null or a empty
	// string

	private boolean validInput(String username, String password,
			String passwordCopy) {

		if ((username == null) || (password == null) || (passwordCopy == null)) {

			return false;
		}
		if ((username.length() == 0) || (password.length() == 0)
				|| (passwordCopy.length() == 0)) {
			return false;
		}
		return true;
	}

	private void makeFrame() {
		initialiseComponents();

		Container contentPane = createNewUserFrame.getContentPane();
		GroupLayout layout = new GroupLayout(contentPane);
		contentPane.setLayout(layout);

		layout.setAutoCreateGaps(true);
		layout.setAutoCreateContainerGaps(true);

		layout
				.setHorizontalGroup(layout
						.createSequentialGroup()
						.addGroup(
								layout
										.createParallelGroup(
												GroupLayout.Alignment.LEADING)
										.addComponent(
												createNewUserInInstructionLabel)
										.addComponent(userNameLabel)
										.addComponent(passWordLabel)
										.addComponent(passWordCopyLabel))
						.addGroup(
								layout.createParallelGroup(
										GroupLayout.Alignment.LEADING)
										.addComponent(userNameField)
										.addComponent(passwordField)
										.addComponent(passwordCoypField))
						.addGroup(
								layout
										.createSequentialGroup()
										.addGroup(
												layout
														.createParallelGroup(GroupLayout.Alignment.LEADING))
										.addComponent(quitButton)).addGroup(
								layout.createParallelGroup(
										GroupLayout.Alignment.LEADING)
										.addComponent(createNewUserButton)));

		layout.linkSize(SwingConstants.HORIZONTAL, quitButton,
				createNewUserButton);

		layout.setVerticalGroup(layout.createSequentialGroup().addComponent(
				createNewUserInInstructionLabel).addGroup(
				layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
						.addComponent(userNameLabel)
						.addComponent(userNameField)).addGroup(
				layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
						.addComponent(passWordLabel)
						.addComponent(passwordField)).addGroup(
				layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
						.addComponent(passWordCopyLabel).addComponent(
								passwordCoypField)).addGroup(
				layout.createParallelGroup(GroupLayout.Alignment.BASELINE))
				.addGroup(
						layout.createParallelGroup(
								GroupLayout.Alignment.BASELINE).addComponent(
								createNewUserButton).addComponent(quitButton)));

	}

	private void initialiseComponents() {
		// Create Labels
		createNewUserInInstructionLabel = new JLabel();
		userNameLabel = new JLabel();
		passWordLabel = new JLabel();
		passWordCopyLabel = new JLabel();

		// Initialise text in labels
		createNewUserInInstructionLabel.setText(INSTRUCTIONS);
		userNameLabel.setText(USERNAME);
		passWordLabel.setText(PASSWORD);
		passWordCopyLabel.setText(PASSWORDCOPY);

		// Create Buttons
		createNewUserButton = new JButton();
		quitButton = new JButton();

		// Initialise text in Buttons
		createNewUserButton.setText(CREATENEWUSER);
		quitButton.setText(QUITBUTTONTEXT);

		// Initialise ActionListerners for buttons

		createNewUserButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				createNewUserButtonAction();
			}
		});

		quitButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				quitButtonAction();

			}
		});

		// Create Fields

		userNameField = new JTextField();
		passwordField = new JPasswordField(10);
		passwordCoypField = new JPasswordField(10);

	}

}
