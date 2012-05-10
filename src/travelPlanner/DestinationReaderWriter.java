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
         * Return an ArrayList with DestinationHeadlines that represents the headlines that existed when the
         * destination was created
         */
        public ArrayList<DestinationHeadline> getExistingDataTypes(){
      	  
      	  ArrayList<DestinationHeadline> existingDataTypes= new ArrayList<DestinationHeadline>();
      	  
      	  try{
           	existingDataTypes = (ArrayList<DestinationHeadline>) ObjectIO.loadObject(username+"/"+destinationTitle , "existingDataTypes");
           	}
      	  	catch (ClassNotFoundException e){
           		ErrorHandler.printError(e, this.getClass().toString());
           		return null;
           	}catch (IOException ex){
           		ErrorHandler.printError(ex, this.getClass().toString());
           		return null;
           	}
    
                   return existingDataTypes;
    
        }
         
        /**
         * Save an ArrayList with DestinationHeadlines that represents the headlines that existed when the
         * destination was created
         */
        public void saveExistingDataTypes(ArrayList<DestinationHeadline> existingDataTypes){
      	  
      	  try{
            	ObjectIO.saveObject(existingDataTypes, username + "/" + destinationTitle, "existingDataTypes");
            	}
      	  catch (IOException e){
            		ErrorHandler.printError(e, this.getClass().toString());
            	}
                
               
        }
        
        /**
         * Return a title corresponding to the specified destination headline
         */
      
        public String getTitle(DestinationHeadline headline){
        	String filename = getFileName(headline);
        	String title;
        	try{
           	 title = (String) ObjectIO.loadObject(username+"/"+destinationTitle, filename + "_title");
           	}catch (ClassNotFoundException e){
           		ErrorHandler.printError(e, this.getClass().toString());
           		return null;
           	}catch (IOException ex){
           		ErrorHandler.printError(ex, this.getClass().toString());
           		return null;
           	}
    
                   return title;
    	
        }
        
        /**
         * Save a title corresponding to the specified destination headline
         */
        
        public void saveTitle(DestinationHeadline headline, String title){
        	String filename = getFileName(headline);       	
        	try{
        	ObjectIO.saveObject(title, username + "/" + destinationTitle, filename + "_title");
        	}catch (IOException e){
        		ErrorHandler.printError(e, this.getClass().toString());
        	}
            
        }
       
        /**
         * Return an arrayList of String representing the subheadlines of the secified DestinationHeadline
         */
       
        public ArrayList<String> getShortDestinationInformationHeadlines(
                        DestinationHeadline headline) {
        	String filename = getFileName(headline);
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
        	String filename = getFileName(headline);       	
        	try{
        	ObjectIO.saveObject(headlinedata, username + "/" + destinationTitle, filename+"_headline");
        	}catch (IOException e){
        		ErrorHandler.printError(e, this.getClass().toString());
        	}
               
        }
 
       
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
        	
        	String filename = getFileName(headline);       	
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
         * data( the text that the user have typed in)
         * associated with the specified DestinationHeadline.
         * @param shortInformationDestinationdata
         * @param headline
         * @return
         */
 
       
        public void saveShortDestinationInformationData(ArrayList<String> data, DestinationHeadline headline) {
        	
        	String filename = getFileName(headline);
        	try{
        	ObjectIO.saveObject(data, username + "/" + destinationTitle, filename);
        	}catch (IOException e){
        		ErrorHandler.printError(e, this.getClass().toString());
        	}
 
               
        }
        
        private String getFileName(DestinationHeadline headline){
        	String filename = DestinationInfo.getFilename(headline);
        	return filename;
        	
        }
       
       
 
}
