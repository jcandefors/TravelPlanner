package travelPlanner;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;


public class Slide {

	protected String title;
	protected LayoutHandler layoutHandler;
	protected String[] txtData;		//holds the data loaded from the about.txt 
	protected String[] subPlaces;	//holds the data loaded from the index.txt
	protected BufferedReader fileReader = null;
	protected File aboutFile;
	protected File indexFile; 
	protected String filePath;
	//(MapPanel map;)


	/**
	 * Contructor of class Slide, the superclass of the "places" in the application "TravelPlanner". 
	 * @param title The title of this "place"
	 */
	public Slide(LayoutHandler layoutHandler, String title){
		this.layoutHandler = layoutHandler;
		filePath = "/"+ title +"/";
		aboutFile = new File("" + filePath + "about.txt"); //textfilen att med all data.
		aboutFile = new File("" + filePath + "index.txt"); //textfilen för att se underliggande "platser".
		txtData =  new String[]{};
		subPlaces = new String[]{};

		loadDataFromFile();		 
		listSubPlaces();

	}

	/**
	 * Listar projekt i portfolion, resmål i projektet från index.txt och returnerar i en String[].
	 */
	
	//Kanske de två följande bör slås ihop till en = mindre kod?
	
	private void listSubPlaces() {
		try{
			fileReader = new BufferedReader(new InputStreamReader(new FileInputStream(indexFile), "UTF-8"));
		}catch (UnsupportedEncodingException e){
			ErrorHandler.printError(e);
		}catch (FileNotFoundException e){
			ErrorHandler.printError(e);}
		
		String line;
		
		try{
			for(int row = 0; (line = fileReader.readLine()) != null; row++){

				subPlaces[row] = line;
			}
		}catch (IOException e){
			ErrorHandler.printError(e);
		}
	}
	

	/**
	 * //Läser filen about och returnerar en string[] med informationen.
	 * @return
	 */
	private void loadDataFromFile() {
		try{
			fileReader = new BufferedReader(new InputStreamReader(new FileInputStream(aboutFile), "UTF-8"));
		}catch (UnsupportedEncodingException e){
			ErrorHandler.printError(e);
		}catch (FileNotFoundException e){
			ErrorHandler.printError(e);}
		
		String line;
		
		try{
			for(int row = 0; (line = fileReader.readLine()) != null; row++){

				txtData[row] = line;
			}
		}catch (IOException e){
			ErrorHandler.printError(e);
		}
	}
}
