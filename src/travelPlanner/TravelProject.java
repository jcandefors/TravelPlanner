package travelPlanner;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
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

public class TravelProject extends Slide{

	/**
	 * Constructor of class TravelProject. firstTime = true should only be called with when user is created.
	 * @param layoutHandler The layoutHandler used for laying out components in the frame.
	 * @param title	The title of this project.
	 * @param firstTime true if new user and project to be created. Else false.
	 */
	public TravelProject(LayoutHandler layoutHandler, String userName, boolean firstTime) {
		super(layoutHandler, userName);
		super.title = userName;													//perhaps should be the title set in EditTravelProject.
		super.labels = new String[]{"Reseprojekt:","Startdatum:","Slutdatum"};
		if(firstTime){
			new File("data/"+userName).mkdir();
			super.mainInfo = new String[MAININFOSIZE];
			super.destinations = new ArrayList<String>();
			editTravelProject();
			saveDestinations();
		}else{
			super.loadData("mainInfo", "destinations");
			prepareLayout();
		}
	}	
	
	/**
	 * Calls the layout help methods to create and send components to layoutHandler for layout.
	 */
	public void prepareLayout(){
		layoutHandler.clearAll();
		generalProjectLayout();
		destinationLayout();
		super.mainLayout();
	}

	/**
	 * Creates general components common for every TravelProject.
	 */
	public void generalProjectLayout(){
		layoutHandler.updateTitle("Resenär: " + title);
		JLabel menuLabel = new JLabel("Destinationer:");
		menuLabel.setSize(10, 30);				//TBC
		layoutHandler.addToMenuLow(menuLabel);
		layoutHandler.addToMenuUp(new ProjectButton("Redigera reseprojekt", 1));
		layoutHandler.addToMenuUp(new ProjectButton("Skapa destination", 2));
	}


	/**
	 * Creates components (DestinationButton) for all the destinations in the TravelProject.
	 */
	public void destinationLayout(){
		Iterator<String> iterator = destinations.iterator();
		while (iterator.hasNext()){
			layoutHandler.addToMenuLow(new DestinationButton(layoutHandler, userName, iterator.next()));
		}
	}
	
	/**
	 * Creates a new EditTravelProject and then updates the data in the layout.
	 */
	public void editTravelProject(){
		new EditTravelProject(this, mainInfo);		
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

			}else if(actionType == 2){
				new Destination(layoutHandler, userName, true);
			}
		}
	}
}
