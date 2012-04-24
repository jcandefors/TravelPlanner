package travelPlanner;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;


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
		mainLayout();
	}

	/**
	 * Creates the general components for all Destinations. 
	 */
	public void generalDestinationLayout(){
		layoutHandler.updateTitle("Destination: " + title);
		JLabel menuLabel = new JLabel("Destinationer:");
		menuLabel.setSize(10, 30);				//TBC
		layoutHandler.addToMenuLow(menuLabel);
		JButton editButton = new JButton("Redigera destination");
		editButton.addActionListener(new ActionListener() {public void actionPerformed(ActionEvent arg0) {editDestination();}});
		JButton removeButton = new JButton("Ta bort destination");
		editButton.addActionListener(new ActionListener() {public void actionPerformed(ActionEvent arg0) {
			int answer = JOptionPane.showConfirmDialog(null, "Vill du verkligen ta bort destinationen "+ title + "?",
				    "Verifiera borttagning av destination",
				    JOptionPane.YES_NO_OPTION);
			if(answer == 0){
			removeDestination();}}});
		layoutHandler.addToMenuUp(editButton);
		layoutHandler.addToMenuUp(removeButton);
		layoutHandler.addToMenuUp(new DestinationButton("Skapa destination", 2));
		layoutHandler.addToMap(new MapLabel(title));
	}

	/**
	 * Creates components (DestinationButton) for all the destinations in the TravelProject except for the current one.
	 */
	public void destinationLayout(){
		layoutHandler.addToMenuLow(Box.createRigidArea(new Dimension(20, 10)));
		Iterator<String> iterator = destinations.iterator();
		while (iterator.hasNext()){
			String nextDestination = iterator.next();
			if(nextDestination != title){	
				layoutHandler.addToMenuLow(new DestinationButton(nextDestination, DestinationButton.OPEN));
			}
		}
	}
	
	/**
	 * Creates components from mainInfo and labels and adds them to the frame.
	 */
	public void mainLayout(){
		
		//example of adding a panel.
		JPanel panel = new JPanel(new GridLayout(0,2));
		for(int index = 0; index < labels.length; index++){
			panel.add(new JLabel(labels[index]));
			panel.add(new JLabel(mainInfo[index]));
			
			layoutHandler.addToMain(panel);
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
	/**
	 * 
	 */
	private void removeDestination(){
		destinations.remove(title);
		File removeFile = new File(userName +"/" + title);
		removeFile.delete(); 								//returns true if succeded - handle???
		saveDestinations();
	}
}
