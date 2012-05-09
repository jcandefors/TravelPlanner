package travelPlanner;

import java.util.ArrayList;

public class DestinationInfo {

	// content
	private static String ARRIVALTITLE = "Inresa";
	private static String DEPARTURETITLE = "Utresa";
	private static String LIVINGTITLE = "Boende";
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
	 * Return an Array with the existing DestinationHeadlines
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