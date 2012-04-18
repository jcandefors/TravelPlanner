package travelPlanner;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;

import javax.swing.JFrame;


public class EditTravelProject {

	protected File aboutFile;
	protected String[] txtData;


	/**
	 * 
	 * @param title
	 */
	public EditTravelProject(String title) {
		aboutFile = new File(title + "/about.txt");
		txtData =  new String[10];
		try{
			readFile();
		}catch (UnsupportedEncodingException e){		//replace with (Exception e) also throws security exception.
			ErrorHandler.printError(e);
		}catch (FileNotFoundException e){
			ErrorHandler.printError(e);
		}catch (IOException e){
			ErrorHandler.printError(e);}
	}

	public void readFile() throws FileNotFoundException, IOException{
		BufferedReader fileReader = new BufferedReader(new InputStreamReader(new FileInputStream(aboutFile), "UTF-8"));
		String line;
		for(int index = 0;((line = fileReader.readLine()) != null); index++){
			txtData[index] = line;
		}
	}
	
	public void createPopUp(){
		JFrame frame = new JFrame("Skapa projekt");
		
		
		frame.setVisible(true);
		
		
	}



	public void writeToFile() throws FileNotFoundException{
		PrintWriter txtWriter = new PrintWriter(new FileOutputStream(aboutFile));
		for(int i = 0; i < txtData.length; i++){
			txtWriter.println(txtData[i]);
			txtWriter.flush();
		}

	}

}
