package travelPlanner;

import java.io.Serializable;
import java.util.ArrayList;

public class Destination extends Slide{
	private static final long serialVersionUID = -4109178658296408100L;
	private String title;
	private ArrayList<String> destinationInfo;
	private LayoutHandler layoutHandler = null;	
	
	private ArrayList<String> otherDestinations;
	



	/**
	 * Constructor of class Destination
	 * @param layoutHandler the layoutHandler to be used for laying out components.
	 * @param title The name of the destination.
	 */
	public Destination(LayoutHandler layoutHandler, String user, Boolean firstTime) {	
		super(layoutHandler, user);
		
		//super.title = ""; //s�tter anv�ndaren genom editDestination. 
		
		
	}


/**
 * 
 */
	public void prepareLayout(){
		//destinationInfoLayout();...
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

/**
 * 
 */
	public void listOtherDestinations(){

		//listar andra destinationer �n den man �r vid och presenterar i menuLow.
	}
}
