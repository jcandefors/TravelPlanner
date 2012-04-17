package travelPlanner;

import java.util.ArrayList;
import java.util.Iterator;

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

		super(layoutHandler, title);
		destinations = super.loadDataFromFile(super.indexFile);

	}	


	/**
	 * Creates components (DestinationButtons) for all the destinations in the TravelProject.
	 */

	public void destinationLayout(){
		Iterator<String> iterator = destinations.iterator();
		while (iterator.hasNext()){
			new DestinationButton(super.layoutHandler, iterator.next());
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

}
