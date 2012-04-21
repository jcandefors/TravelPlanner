package travelPlanner;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import javax.swing.JButton;
import javax.swing.JLabel;

/**
 * The class TravelProject manages data for travelprojects in the application 
 * "TravelPlanner" and create GUI-components from this data. 
 * for the user to view and use. 
 * @author Joakim Candefors
 *
 */

public class TravelProject{

	private ArrayList<String> projectInfo; //serialized
	private LayoutHandler layoutHandler;		
	private String user;
	private ArrayList<String> destinations;		//serialized
	private String[] labels;



	/**
	 * Constructor of class TravelProject. Should only be called when user is created.
	 * @param layoutHandler The layoutHandler used for laying out components in the frame.
	 * @param title	The title of this project.
	 * @param firstTime True if new user and project to be created. Else false.
	 */
	public TravelProject(LayoutHandler layouthandler, String user, boolean firstTime) {
		this.layoutHandler = layoutHandler;
		this.user = user;
		labels = new String[]{"Reseprojekt:","Startdatum:","Slutdatum"};
		if(firstTime){
			new File(user).mkdir();			
			projectInfo = new ArrayList<String>();
			destinations = new ArrayList<String>();
			editTravelProject();
			saveDestinations();
		}else{
			loadTravelProjectData();
			prepareLayout();
		}
	}	

	public void prepareLayout(){
		//layoutHandler.clearAll();
		generalProjectLayout();
		destinationLayout();
		projectInfoLayout();
	}


	/**
	 * Creates general components common for every TravelProject.
	 */
	public void generalProjectLayout(){
		layoutHandler.updateTitle("Resenär: " + user);
		JLabel menuLabel = new JLabel("Destinationer:");
		menuLabel.setSize(100, 30);
		layoutHandler.addToMenuLow(menuLabel);
		layoutHandler.addToMenuUp(new ProjectButton("Redigera reseprojekt", 1));
		layoutHandler.addToMenuUp(new ProjectButton("Skapa destination", 2));
	}

	/**
	 * Creates components from about.txt (super.txtData) and adds them to the frame.
	 */
	public void projectInfoLayout(){
		for(int index = 0; index < labels.length; index++){
			layoutHandler.addToMain(new JLabel(labels[index]));
		}
		Iterator<String> iterator = projectInfo.iterator();
		while (iterator.hasNext()){
			layoutHandler.addToMain(new JLabel(iterator.next()));
		}
	}

	/**
	 * Creates components (DestinationButton) for all the destinations in the TravelProject.
	 */
	public void destinationLayout(){
		Iterator<String> iterator = destinations.iterator();
		while (iterator.hasNext()){
			layoutHandler.addToMenuLow(new DestinationButton(user, iterator.next()));
		}
	}
	/**
	 * Creates a new EditTravelProject and then updates the data in the layout.
	 */
	public void editTravelProject(){
		new EditTravelProject(this, projectInfo);		
	}

	public void updateEditInfo(ArrayList<String> editedProjectInfo){		
		projectInfo = editedProjectInfo;
		saveProjectInfo();
	}

	/**
	 * Saves the projectInfo data to disk.
	 */
	public void saveProjectInfo(){
		try{															
			ObjectIO.saveObject(projectInfo, user, "projectInfo");
		}catch (IOException e){
			ErrorHandler.printError(e, this.getClass().toString());
		}
	}

	public void saveDestinations(){
		try{															
			ObjectIO.saveObject(destinations, user, "destinations");
		}catch (IOException e){
			ErrorHandler.printError(e, this.getClass().toString());
		}		
	}
	/**
	 * Loads the data from disk.
	 */
	public void loadTravelProjectData(){
		try{
			projectInfo = (ArrayList<String>) ObjectIO.loadObject(user, "projectInfo");
			destinations = (ArrayList<String>) ObjectIO.loadObject(user, "destinations");
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
	}


	/**
	 * ProjectButton is a JButtons specifically for TravelProjects.
	 */
	public class ProjectButton extends JButton implements ActionListener{
		private int actionType;

		/**
		 * Constructor of a project button.
		 * @param text	The text of the ProjectButton.
		 * @param actionType
		 */
		public ProjectButton(String text, int actionType){
			super(text);
			this.actionType = actionType;
			super.addActionListener(this); 
		}
		/**
		 * 		
		 * @param e Action when button fired.
		 */
		public void actionPerformed(ActionEvent e) {
			if(this.actionType == 1){			
				editTravelProject();
				try{
					ObjectIO.saveObject(this, user, user);
				}catch (IOException i){
					ErrorHandler.printError(i, this.getClass().toString());
				}
			}else if(actionType == 2){
				new Destination(layoutHandler, user);

			}
		}
	}

}
