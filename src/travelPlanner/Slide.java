package travelPlanner;

import java.awt.Color;
import java.awt.ComponentOrientation;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

/**
 * The superclass of the both slide types Destination and Travelproject.
 * Slide together with TravelProject or Destiantion holds information to be 
 * viewed in each destination of for the project.
 * @author Joakim Candefors
 *
 */

public class Slide {


	protected ArrayList<String> destinations;		//serialized
	protected LayoutHandler layoutHandler;
	protected String userName;
	protected String title;

	/**
	 * Contructor of class Slide. 
	 * @param layoutHandler The layoutHandler to used layout all components.
	 * @param userName the user name.
	 */
	public Slide(LayoutHandler layoutHandler, String userName){
		this.layoutHandler = layoutHandler;
		this.userName = userName;
	}

	/**
	 * Loads the list of destinations from disk.
	 */
	public void loadDestinations(){
		try{
			destinations = (ArrayList<String>) ObjectIO.loadObject(userName, "destinations");
		}catch (ClassNotFoundException e){
			ErrorHandler.printError(e, this.getClass().toString());
		}catch (IOException e){
			ErrorHandler.printError(e, this.getClass().toString());
		}
	}


	/**
	 * Adds a destination to the projects list of destinations.
	 */
	public void addDestination(String destinationTitle){		
		destinations.add(destinationTitle);
		saveDestinations();
	}

	/**
	 * Saves the list of destinations to disk.
	 */
	public void saveDestinations(){
		try{															
			ObjectIO.saveObject(destinations, userName, "destinations");
		}catch (IOException e){
			ErrorHandler.printError(e, this.getClass().toString());
		}		
	}

	/**
	 * A destination button used from both Destination and TravelProject to load or initiate creation of destinations. 
	 * @author Joakim Candefors
	 */
	public class DestinationButton extends JButton implements ActionListener{

		private int actionType;
		public static final int OPEN = 1;
		public static final int NEW = 2;
		private JTextField destinationField;
		private JDialog dialog;
		private final String NEWBUTTONTEXT = "Skapa en ny destination", OPENBUTTONTEXT = "�ppna destinationen ", 
		CREATEDIALOGTITLE = "Skapa Destination", TEXTFIELDTEXT = "destinationens namn", CREATEBUTTONTEXT = "Skapa",
		DESTINATIONLABELTEXT = "Destinationens titel:", TEXTFIELDINPUTEXISTERROR = "Destinationsnamnet finns redan i reseprojektet.",
		TEXTFIELDINPUTSIZEERROR = "Destinationsnamnet m�ste vara minst 1 tecken och max 25 tecken.";

		/**
		 * Constructor of a DestinationButton.  
		 * @param destinationTitle	The title of the destination.
		 * @param actionType the type of button.
		 */
		public DestinationButton(String title, int actionType){
			super(title);			
			this.actionType = actionType;
			super.setCursor(new Cursor(Cursor.HAND_CURSOR));
			super.setBorderPainted(false);
			if(actionType == OPEN){
				super.setToolTipText(OPENBUTTONTEXT + title);
			}else if(actionType == NEW){
				super.setToolTipText(NEWBUTTONTEXT);
			}			
			super.addActionListener(this);
		}
		public void actionPerformed(ActionEvent e) {
			switch (actionType){
			case OPEN : new Destination(layoutHandler, userName, super.getText(), false); 			//Open destination
			break;
			case NEW : showCreateDialog();														//create destination			
			}								
		}


		/**
		 * Shows a dialog after firing a destination button. Asks user for title of the destination to be created. 
		 */
		public void showCreateDialog(){			
			dialog = new JDialog(layoutHandler.getFrame());
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);			
			dialog.setPreferredSize(new Dimension(300, 100));
			dialog.setLocation(300, 300);
			dialog.setAlwaysOnTop(true);
			dialog.setFocusable(true);			
			dialog.setResizable(false);		
			dialog.setBackground(Color.BLUE);
			dialog.setLayout(new FlowLayout(FlowLayout.LEFT,2,2));
			dialog.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
			dialog.setTitle(CREATEDIALOGTITLE);
			destinationField = new JTextField(TEXTFIELDTEXT);
			JButton createButton = new JButton(CREATEBUTTONTEXT);
			createButton.addActionListener(new ActionListener() {				
				@Override
				public void actionPerformed(ActionEvent e) {
					String input = checkInput();
					if(input != null)
						createDestination(input);					
				}
			});
			dialog.add(new JLabel(DESTINATIONLABELTEXT));
			dialog.add(destinationField);
			dialog.add(createButton);
			dialog.setVisible(true);
			dialog.pack();
		}	

		/**
		 * Checks the input in the textfield to make sure the destination is not already in the project
		 *  and that it is in the correct length.
		 *  @return the input string.
		 */
		private String checkInput(){
			String input = destinationField.getText();
			if(input.length() < 1 || input.length()>25 || input.matches("destinationens namn")){
				JOptionPane.showMessageDialog(dialog, TEXTFIELDINPUTSIZEERROR);
				return null;
			}else if(destinations.contains(input)){
				JOptionPane.showMessageDialog(dialog, TEXTFIELDINPUTEXISTERROR);
				return null;
			}else{			
				return input;
			}
		}
		/**
		 * Creates a new destination with the title set in the dialog in showCreateDialog().
		 * @param destinationTitle the title of the new destination.
		 */
		private void createDestination(String destinationTitle){

			new Destination(layoutHandler, userName, destinationTitle, true);	//Create destination
			dialog.dispose();													//close dialog
		}

	}
}





