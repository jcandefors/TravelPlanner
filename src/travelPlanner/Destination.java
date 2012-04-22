package travelPlanner;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.JButton;
import javax.swing.JLabel;

import travelPlanner.TravelProject.ProjectButton;

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
			loadDestinations();
			editDestination();
			addDestination(title);
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
		layoutHandler.updateTitle("Destination: " + mainInfo[0]);
		JLabel menuLabel = new JLabel("Destinationer:");
		menuLabel.setSize(10, 30);				//TBC
		layoutHandler.addToMenuLow(menuLabel);
		JButton editButton = new JButton("Redigera destination");
		editButton.addActionListener(new ActionListener() {public void actionPerformed(ActionEvent arg0) {editDestination();}});
		layoutHandler.addToMenuUp(editButton);
		layoutHandler.addToMenuUp(new DestinationButton(layoutHandler,"Skapa destination", userName, 2));
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
	
	private void loadDestinations() {
		try{
			destinations = (ArrayList<String>) ObjectIO.loadObject(userName, "destinations");
		}catch (ClassNotFoundException e){
			ErrorHandler.printError(e, this.getClass().toString());
		}catch (IOException e){
			ErrorHandler.printError(e, this.getClass().toString());
		}
		
	}	
}
