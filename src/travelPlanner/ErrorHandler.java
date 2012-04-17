package travelPlanner;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;

public class ErrorHandler {	
	
	
	public static void printError(Exception e){
		try{
		PrintWriter file = new PrintWriter(new FileOutputStream("TravelPlannerError.txt", true));
		file.println("" + System.currentTimeMillis() + "%s: %s%n" + e);
		file.flush();
		}catch (FileNotFoundException r){
			//do nothing - error not logged. TBC			
		}		
	}
}
