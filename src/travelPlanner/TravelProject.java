package travelPlanner;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Iterator;
import javax.swing.JButton;

/**
 * The class TravelProject is a subclass of the class Slide, meant to manage data 
 * for travel projects in the application "TravelPlanner" and create gui components from this data 
 * for the user to view and use. 
 * @author Joakim Candefors
 *
 */

public class TravelProject extends Slide {

	protected ArrayList<String> destinations;	//holds the data loaded from the index.txt


	/**
	 * Constructor of class TravelProject.
	 * @param layoutHandler
	 * @param title
	 */
	public TravelProject(LayoutHandler layoutHandler, String title) {

		super(layoutHandler,(title+"/"), title);
		destinations = super.loadDataFromFile(super.indexFile);
		destinationLayout();

	}	


	/**
	 * Creates components (DestinationButtons) for all the destinations in the TravelProject.
	 */

	public void destinationLayout(){
		
		Iterator<String> iterator = destinations.iterator();
		while (iterator.hasNext()){
			layoutHandler.addToMenuLow(new DestinationButton(super.layoutHandler,title, iterator.next()));
		}
		// to be placed in the main layout area?
	}

	/**
	 * Creates general components common for every TravelProject.
	 */
	public void generalProjectLayout(){
		
		//super.layoutHandler.addToMenuUp(new ProjectButton("Redigera reseprojekt", typ 1));
		//super.layoutHandler.addToMenuUp(new ProjectButton("Skapa destination", typ 2));
	}
	
	/**
	 * Creates components from about.txt (super.txtData) and adds them to the frame.
	 */
	public void projectInfoLayout(){
		
	}
	
	
	
	public class DestinationButton extends JButton{
		private LayoutHandler layoutHandler;
		private String travelProject; 

		public DestinationButton(LayoutHandler layoutHandler, String travelProject, String destinationTitle){
			
			super(destinationTitle);
			this.layoutHandler = layoutHandler;
			this.travelProject = travelProject;
			super.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) { openDestination();	} });	
			
		}
		
		public void openDestination(){
			new travelPlanner.Destination(layoutHandler, travelProject, super.getText());		
		}
	}

}
