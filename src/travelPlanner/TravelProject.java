package travelPlanner;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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

public class TravelProject implements Serializable {
	private ArrayList<String> projectInfo;
	private LayoutHandler layoutHandler = null;
	private String title;
	private ArrayList<String> destinations;
	private String[] labels;


	/**
	 * Constructor of class TravelProject.
	 * @param layoutHandler
	 * @param title
	 */
	public TravelProject(LayoutHandler layouthandler, String title) {
		this.layoutHandler = layoutHandler;
		this.title = title;
		destinations = new ArrayList<String>();
		labels = new String[]{"Reseprojekt:","Startdatum:","Slutdatum"};
		editTravelProject();
	}	

	public void prepareLayout(LayoutHandler layoutHandler){
		this.layoutHandler = layoutHandler; // "new" current Layouthandler.
		layoutHandler.clearAll();
		generalProjectLayout();
		destinationLayout();
		projectInfoLayout();
	}


	/**
	 * Creates general components common for every TravelProject.
	 */
	public void generalProjectLayout(){
		layoutHandler.updateTitle("Resenär: " + title);
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
			layoutHandler.addToMenuLow(new DestinationButton(title, iterator.next()));
		}
	}
	/**
	 * Creates a new EditTravelProject and then updates the data in the layout.
	 */
	public void editTravelProject(){
		new EditTravelProject(title);			
		prepareLayout(layoutHandler);	
	}
	
	/**
	 * Adds a destination to the projects list of destinations.
	 */
	
	public void addDestination(String destination){		
		destinations.add(destination);
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
				new Destination(layoutHandler, title);

			}
		}
	}

}
