package travelPlanner;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;


public class Slide {

	protected String title;
	protected LayoutHandler layoutHandler;
	protected ArrayList<String> txtData;		//holds the data loaded from the about.txt 	
	protected BufferedReader fileReader = null;
	protected File aboutFile;
	protected File indexFile; 
	protected String filePath;
	//(MapPanel map;)


	/**
	 * Contructor of class Slide, the superclass of the "places" in the application "TravelPlanner". 
	 * @param title The title of this "place"
	 */
	public Slide(LayoutHandler layoutHandler, String filePath, String title){
		this.layoutHandler = layoutHandler;
		this.filePath = filePath;														//TBC - own method?
		this.title = title;
		aboutFile = new File("" + filePath + "about.txt"); //textfilen att med all data.
		indexFile = new File("" + filePath + "index.txt"); //textfilen för att se underliggande "platser".
		txtData =  new ArrayList<String>();


		loadDataFromFile(aboutFile);		

	}

	/**
	 * //Läser filen about och returnerar en string[] med informationen.
	 * @return
	 */
	public ArrayList<String> loadDataFromFile(File file) {
		try{
			fileReader = new BufferedReader(new InputStreamReader(new FileInputStream(file), "UTF-8"));
		}catch (UnsupportedEncodingException e){
			ErrorHandler.printError(e);
		}catch (FileNotFoundException e){
			ErrorHandler.printError(e);}

		ArrayList<String> dataArray = new ArrayList<String>();
		String line;

		try{
			while ((line = fileReader.readLine()) != null){

				dataArray.add(line);
			}
			return dataArray;
		}catch (IOException e){
			ErrorHandler.printError(e);
		}
		return null;
	}

}
