package travelPlanner;

import java.io.File;
import java.util.ArrayList;

public class Destination extends Slide{
	protected ArrayList<String> otherDestinations;
	private String travelProject;



	/**
	 * Constructor of class Destination
	 * @param layoutHandler the layoutHandler to be used for laying out components.
	 * @param title The name of the destination.
	 */
	public Destination(LayoutHandler layoutHandler, String travelProject, String title) {		
		super(layoutHandler, (travelProject + "/" + title +"/"), title);
		this.travelProject = travelProject;
		
	}




	/**
	 * Creates all the components with travel info from the text file and calls the LayoutHandler to place them in the frame.
	 */	
	public void destinationInfoLayout(){

	}

	/**
	 * Creates the general components for all Destinations. 
	 */
	public void generalDestinationLayout(){

	}


	public void listOtherDestinations(){

		File projectIndexFile = new File(travelProject + "/index.txt"); //("/" + user + "/index.txt")
		otherDestinations = super.loadDataFromFile(projectIndexFile);//listar andra destinationer än den man är vid och presenterar i menuLow.
	}
}
