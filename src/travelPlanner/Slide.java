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

public class Slide {

	protected String[] mainInfo; 				//serialized
	protected ArrayList<String> destinations;		//serialized
	protected String[] labels;	
	protected LayoutHandler layoutHandler;
	protected String userName;
	protected String title;

	/**
	 * Contructor of class Slide. 
	 * @param layoutHandler The layoutHandler to used layout all components.
	 * @param userName the username (and logic projectName).
	 */
	public Slide(LayoutHandler layoutHandler, String userName){
		this.layoutHandler = layoutHandler;
		this.userName = userName;
	}

	/**
	 * Creates components from mainInfo and labels and adds them to the frame.
	 */
	public void mainLayout(){
		for(int index = 0; index < labels.length; index++){
			layoutHandler.addToMain(new JLabel(labels[index]));
			layoutHandler.addToMain(new JLabel(mainInfo[index]));
		}
	}

	/**
	 * Loads the Slide information data and destination list from disk.
	 */
	public void loadData(String filename1, String Filename2){
		try{
			mainInfo = (String[]) ObjectIO.loadObject(userName, title);
			destinations = (ArrayList<String>) ObjectIO.loadObject(userName, "destinations");
		}catch (ClassNotFoundException e){
			ErrorHandler.printError(e, this.getClass().toString());
		}catch (IOException e){
			ErrorHandler.printError(e, this.getClass().toString());
		}
	}

	/**
	 * Takes the edited information and applies it to current project or destination and saves to disk. 
	 * @param editedProjectInfo The array with the main information edited in EditTravelProject or EditDestination.
	 */
	public void updateMainInfo(String[] editedMainInfo){		
		mainInfo = editedMainInfo;
		saveMainInfo();
	}

	/**
	 * Adds a destination to the projects list of destinations.
	 */
	public void addDestination(String destinationTitle){		
		destinations.add(destinationTitle);
		saveDestinations();
	}

	/**
	 * Saves the project information data to disk.
	 */
	public void saveMainInfo(){
		try{															
			ObjectIO.saveObject(mainInfo, userName, title);
		}catch (IOException e){
			ErrorHandler.printError(e, this.getClass().toString());
		}
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
	 * 
	 * @author Joakim Candefors
	 */
	public class DestinationButton extends JButton implements ActionListener{

		private int actionType;
		public static final int OPEN = 1;
		public static final int NEW = 2;
		private JTextField destinationField;
		private JDialog dialog;

		/**
		 * Constructor of a destination-button. This component takes the user to the destination referenced in this button. 
		 * @param destinationTitle	The title of the destination.
		 * @param actionType
		 */
		public DestinationButton(String title, int actionType){
			super(title);			
			this.actionType = actionType;
			super.setCursor(new Cursor(Cursor.HAND_CURSOR));
			super.setBorderPainted(false);
			if(actionType == OPEN){
				super.setToolTipText("Öppna destinationen " + title);
			}else if(actionType == NEW){
				super.setToolTipText("Skapa en ny destination");
			}			
			super.addActionListener(this);
		}
		public void actionPerformed(ActionEvent e) {
			switch (actionType){
			case 1 : new Destination(layoutHandler, userName, super.getText(), false); 			//Open destination
			break;
			case 2 : showCreateDialog();														//create destination			
			}								
		}


		/**
		 * Shows a dialog after firing destination button. Asks user for title of the destination to be created. 
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
			dialog.setTitle("Skapa Destination");
			destinationField = new JTextField("destinationens namn");
			JButton createButton = new JButton("Skapa");
			createButton.addActionListener(new ActionListener() {				
				@Override
				public void actionPerformed(ActionEvent e) {
					String input = checkInput();
					if(input != null)
						createDestination(input);					
				}
			});
			dialog.add(new JLabel("Destinationens titel:"));
			dialog.add(destinationField);
			dialog.add(createButton);
			dialog.setVisible(true);
			dialog.pack();
		}	

		/**
		 * 
		 */
		private String checkInput(){
			String input = destinationField.getText();
			if(input.length() < 1 || input.length()>25 || input.matches("destinationens namn")){
				JOptionPane.showMessageDialog(dialog, "Destinationsnamnet måste vara minst 1 tecken och max 25 tecken.");
				return null;
			}else if(destinations.contains(input)){
				JOptionPane.showMessageDialog(dialog, "Destinationsnamnet finns redan i reseprojektet.");
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





