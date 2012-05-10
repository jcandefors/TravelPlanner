package travelPlanner;

import java.util.ArrayList;
/**
 * This class represents all text information about different Destination headlines (For example Living Arrival and 
 * Departure).
 * This class and the enum class destination headline are the only one that have to be changed if
 * you want to add a new Destination headline.
 * To modify the subheadlines or remove a Destination headline only this class have to be modified.
 * @author ragnhild
 *
 */

public class DestinationInfo {

	// content
	private static String ARRIVALTITLE = "Inresa";
	private static String DEPARTURETITLE = "Utresa";
	private static String LIVINGTITLE = "Boende";
	
	private static String ARRIVALFILENAME = "Arrival";
	private static String DEPARTUREFILENAME = "Departure";
	private static String LIVINGFILENAME = "Living";
	
	
	private final static String ARRIVALDATE = "Ankomstdatum";
	private final static String DEPARTUREDATE = "Avgångsdatum";
	private final static String DEPARTURETIME = "Avgångstid";
	private final static String REFERENSNUMBER = "Bokningsnummer";
	private final static String NAME = "Namn";
	private final static String ARRIVALTIME = "Ankomsttid";
	private final static String STATIONAIRPORT = "Station/Flygplats";
	private final static String ADDRESS = "Adress";
	private final static String COVERNAME = "Täcknamn";
	
	

	// Arrays
	private final static String[] Living = new String[] { NAME, ADDRESS,
			COVERNAME };
	private final static String[] Arrival = new String[] { ARRIVALDATE,
			ARRIVALTIME, REFERENSNUMBER, STATIONAIRPORT };
	private final static String[] Departure = new String[] { DEPARTUREDATE,
			DEPARTURETIME, REFERENSNUMBER, STATIONAIRPORT };
	//private final static String [] Sights = new String {
		


	public DestinationInfo() {

	}
	
	/**
	 * Return an array with the subheadline for the specified Destination headline
	 *
	 */

	public static ArrayList<String> getDestinationInfoArrayList(
			DestinationHeadline headline) {
		ArrayList<String> returnArrayList = new ArrayList<String>();

		switch (headline) {
		case ARRIVAL:
			for (int i = 0; i < Arrival.length; i++) {
				returnArrayList.add(i, Arrival[i]);
			}
			break;

		case DEPARTURE:
			for (int i = 0; i < Departure.length; i++) {
				returnArrayList.add(i, Departure[i]);
			}
			break;

		case LIVING:
			for (int i = 0; i < Living.length; i++) {
				returnArrayList.add(i, Living[i]);
			}
			break;
		}

		return returnArrayList;

	}

	/**
	 * Return the title for the specified Headline
	 * 
	 * @param headlie
	 * @return
	 */
	public static String getTitle(DestinationHeadline headline) {
		String returnTitle = null;
		switch (headline) {
		case ARRIVAL:
			returnTitle = ARRIVALTITLE;
			break;

		case DEPARTURE:
			returnTitle = DEPARTURETITLE;
			break;

		case LIVING:
			returnTitle = LIVINGTITLE;
			break;
		}
		return returnTitle;

	}
	
	/**
	 * Return a filename corresponding to the specified destination headline
	 */
	public static String getFilename(DestinationHeadline headline) {
		String returnTitle = null;
		
		switch (headline) {
		case ARRIVAL:
			returnTitle = ARRIVALFILENAME;
			break;

		case DEPARTURE:
			returnTitle = DEPARTUREFILENAME;
			break;

		case LIVING:
			returnTitle = LIVINGFILENAME;
			break;
		}
		return returnTitle;

	}
	
	/**
	 * Return an Array with the existing DestinationHeadlines
	 * If a new Destination headline is added or a old one removed this list have to be modified.
	 * @return
	 */

	public static ArrayList<DestinationHeadline> getExistingDestinationInfoTypes() {

		ArrayList<DestinationHeadline> exisitingTypes = new ArrayList<DestinationHeadline>();

		exisitingTypes.add(DestinationHeadline.ARRIVAL);
		exisitingTypes.add(DestinationHeadline.DEPARTURE);
		exisitingTypes.add(DestinationHeadline.LIVING);

		return exisitingTypes;

	}

}