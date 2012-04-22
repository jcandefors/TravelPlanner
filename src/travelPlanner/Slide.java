package travelPlanner;

import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JLabel;

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
		}
		for(int index = 0; index < mainInfo.length; index++){
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
}
