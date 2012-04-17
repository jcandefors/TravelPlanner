package travelPlanner;

import java.util.Iterator;

/**
 * The class TravelProject is a subclass of the class Slide, meant to manage data 
 * for travel projects in the application "TravelPlanner" and create gui components from this data 
 * for the user to view and use. 
 * @author Joakim Candefors
 *
 */

public class TravelProject extends Slide {	
	
	/**
	 * Constructor of class TravelProject.
	 * @param layoutHandler
	 * @param title
	 */

	public TravelProject(LayoutHandler layoutHandler, String title) {
		
		super(layoutHandler, title);
	
	}
	
	public void subPlacesLayout(){
		Iterator<String> iterator = super.subPlaces.iterator();
		while (iterator.hasNext())
			new DestinationButton(super.layoutHandler, iterator.next());
	}
	
	public void generalProjectComponents(){
		//super.layoutHandler.addToMenu(new ProjectButton("Redigera reseprojekt"));
		//super.layoutHandler.addToMenu(new ProjectButton("Skapa destination"));
	}

}
