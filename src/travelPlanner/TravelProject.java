package travelPlanner;

import java.util.ArrayList;
import java.util.Iterator;
import javax.swing.JButton;
import javax.swing.JLabel;

/**
 * The class TravelProject is a subclass of the class Slide, meant to manage data 
 * for travel projects in the application "TravelPlanner" and create GUI-components from this data 
 * for the user to view and use. 
 * @author Joakim Candefors
 *
 */

public class TravelProject extends Slide {

	private ArrayList<String> destinations;	//holds the data loaded from the index.txt
	private ArrayList<String> labels;


	/**
	 * Constructor of class TravelProject.
	 * @param layoutHandler
	 * @param title
	 */
	public TravelProject(LayoutHandler layoutHandler, String title) {

		super(layoutHandler,(title+"/"), title);
		destinations = super.loadDataFromFile(super.indexFile);
//		labels = new ArrayList<String>(){"Reseprojekt:";"Startdatum:";"Slutdatum"};
		generalProjectLayout();
		destinationLayout();
//		projectInfoLayout(labels.iterator());
//		projectInfoLayout(txtData.iterator());


	}	


	/**
	 * Creates components (DestinationButtons) for all the destinations in the TravelProject.
	 */

	public void destinationLayout(){
		Iterator<String> iterator = destinations.iterator();
		while (iterator.hasNext()){
			layoutHandler.addToMenuLow(new DestinationButton(super.layoutHandler,super.title, iterator.next()));
		}

	}

	/**
	 * Creates general components common for every TravelProject.
	 */
	public void generalProjectLayout(){
		layoutHandler.updateTitle("Resen�r: " + super.title);
		JLabel menuLabel = new JLabel("Destinationer:");
		menuLabel.setSize(100, 30);
		layoutHandler.addToMenuLow(menuLabel);
		//layoutHandler.addToMenuUp(new ProjectButton("Redigera reseprojekt", typ 1));
		//layoutHandler.addToMenuUp(new ProjectButton("Skapa destination", typ 2));
	}

	/**
	 * Creates components from about.txt (super.txtData) and adds them to the frame.
	 */
	public void projectInfoLayout(Iterator<String> InputIterator){
		Iterator<String> iterator = InputIterator;
		while (iterator.hasNext()){
			layoutHandler.addToMain(new JLabel(iterator.next()));

		}
	}

}
