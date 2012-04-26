package travelPlanner;



import java.io.IOException;
import java.util.ArrayList;

/**
 * This class read and save different kind of destinationInfo
 *
 * @author Ragnhild Karlsson & Joakim Candefors
 *
 */
public class DestinationReaderWriter {
        private final String destinationTitle;
        private final String username;
        private final String travelProjectName;
 
       
        public DestinationReaderWriter(String destinationTitle, String username,
                        String travelProjectName) {
                this.destinationTitle = destinationTitle;
                this.username = username;
                this.travelProjectName = travelProjectName; 					//to be implemented
 
        }
 
       
       
        /**
         * Return an arrayList of String representing the subheadlines of the secified DestinationHeadline
         */
       
        public ArrayList<String> getShortDestinationInformationHeadlines(
                        DestinationHeadline headline) {
        	String filename = null;
        	switch (headline){
        	case ARRIVAL: filename = "Arrival";
        	break;
        	case DEPARTURE : filename = "Departure";
        	break;
        	case LIVING : filename = "Living";
        	}
        	ArrayList<String> shortDestinationData;
        	try{
        	 shortDestinationData = (ArrayList<String>) ObjectIO.loadObject(username+"/"+destinationTitle, filename + "_headline");
        	}catch (ClassNotFoundException e){
        		ErrorHandler.printError(e, this.getClass().toString());
        		return null;
        	}catch (IOException ex){
        		ErrorHandler.printError(ex, this.getClass().toString());
        		return null;
        	}
 
                return shortDestinationData;
        }
       
        /**
         * Save an ArrayList of Strings representing the subheadlines for short Information about the
         * specified DestinationHeadline
         */
        public void saveShortDestinationInformationHeadlines(ArrayList<String> headlinedata, DestinationHeadline headline){
        	String filename = null;
        	switch (headline){
        	case ARRIVAL: filename = "Arrival";
        	break;
        	case DEPARTURE : filename = "Departure";
        	break;
        	case LIVING : filename = "Living";
        	}
        	try{
        	ObjectIO.saveObject(headlinedata, username + "/" + destinationTitle, filename+"_headline");
        	}catch (IOException e){
        		ErrorHandler.printError(e, this.getClass().toString());
        	}
               
        }
 
       
//      /**
//       * Return an ArrayList with DestinationHeadlines that represents the headlines that existed when the
//       * destination was created
//       */
//      public ArrayList<DestinationHeadline> getExistingDataTypes(){
//     
//              return new ArrayList<DestinationHeadline>();
//      }
       
//      /**
//       * Save an ArrayList with DestinationHeadlines that represents the headlines that existed when the
//       * destination was created
//       */
//      public void saveExistingDataTypes(ArrayList<DestinationHeadline> existingDataTypes){
//     
//             
//      }
//     
       
 
       
        /**
         * Return an ArrayList with strings representing saved short information
         * data associated with the specified DestinationHeadline. For exampel
         * ARRIVAL will return a Array with strings representing the saved data
         * associated whith the headlines: Date, Arrivaltime, Station/Airport,
         * Booking number The array will match the respondning headlineArray by
         * order
         *
         * @param headline
         * @return
         */
 
        public ArrayList<String> getShortDestinationInformationData( DestinationHeadline headline) {
        	
        	String filename = null;
        	switch (headline){
        	case ARRIVAL: filename = "Arrival";
        	break;
        	case DEPARTURE : filename = "Departure";
        	break;
        	case LIVING : filename = "Living";
        	}
        	ArrayList<String> shortDestinationData;
        	try{
        	 shortDestinationData = (ArrayList<String>) ObjectIO.loadObject(username+"/"+destinationTitle, filename);
        	}catch (ClassNotFoundException e){
        		ErrorHandler.printError(e, this.getClass().toString());
        		return null;
        	}catch (IOException ex){
        		ErrorHandler.printError(ex, this.getClass().toString());
        		return null;
        	}
 
                return shortDestinationData;
        }
 
        /**
         * Save an ArrayList with strings representing saved short information
         * data associated with the specified DestinationHeadline.
         * @param shortInformationDestinationdata
         * @param headline
         * @return
         */
 
       
        public void saveShortDestinationInformationData(ArrayList<String> data, DestinationHeadline headline) {
        	
        	String filename = null;
        	switch (headline){
        	case ARRIVAL: filename = "Arrival";
        	break;
        	case DEPARTURE : filename = "Departure";
        	break;
        	case LIVING : filename = "Living";
        	}
        	try{
        	ObjectIO.saveObject(data, username + "/" + destinationTitle, filename);
        	}catch (IOException e){
        		ErrorHandler.printError(e, this.getClass().toString());
        	}
 
               
        }
       
       
       
//      /**
//       * Return a string with the text that the user have saved under free notes about the specific
//       * DestinationHeadling
//       * @return
//       */
//     
//      public String getMoreInformationText(DestinationHeadline headline){
//     
//      String returnString = "";
//      return returnString;
//     
//      }
}