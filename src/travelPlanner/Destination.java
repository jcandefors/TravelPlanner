package travelPlanner;

import java.io.File;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;

public class Destination extends Slide{


	protected final int MAININFOSIZE = 6;


	/**
	 * Constructor of class Destination
	 * @param layoutHandler the layoutHandler to be used for laying out components.
	 * @param title The name of the destination.
	 */
	public Destination(LayoutHandler layoutHandler, String user, String title, Boolean firstTime) {	
		super(layoutHandler, user);
		super.title = title;
		super.labels = new String[]{"Destination:","Inresedatum:","Utresedatum","Flygplats/station:","Bokningsnummer:", "Boendeinformation:"};
		if(firstTime){
			super.mainInfo = new String[MAININFOSIZE];
			editDestination();
		}else{
			super.loadData("mainInfo", "destinations");
			prepareLayout();
		}


	}


	/**
	 * 
	 */
	public void prepareLayout(){
		layoutHandler.clearAll();
		generalDestinationLayout();
		destinationLayout();
		super.mainLayout();
	}

	/**
	 * Creates the general components for all Destinations. 
	 */
	public void generalDestinationLayout(){

	}

	/**
	 * Creates components (DestinationButton) for all the destinations in the TravelProject except for the current one.
	 */
	public void destinationLayout(){
		Iterator<String> iterator = destinations.iterator();
		while (iterator.hasNext()){
			String nextDestination = iterator.next();
			if(nextDestination != title){	
				layoutHandler.addToMenuLow(new DestinationButton(layoutHandler, userName, nextDestination, DestinationButton.OPEN));
			}
		}
	}

	/**
	 * Creates a new EditDestination object which asks the user to edit the destiantion information 
	 * and then updates the information in the layout.
	 */
	public void editDestination(){
		new EditDestination(this, mainInfo);
		saveMainInfo();
	}
}
