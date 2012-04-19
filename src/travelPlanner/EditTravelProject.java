package travelPlanner;

import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.io.*;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;


public class EditTravelProject {

	private File aboutFile;
	private String[] txtData;
	private String[] labels;
	private	JPanel labelPanel;
	private	JPanel editPanel;
	


	/**
	 * 
	 * @param title
	 */
	public EditTravelProject(String title) {
		aboutFile = new File(title + "/about.txt");
		txtData =  new String[4];
		labels = new String[]{"Reseprojekt:","Startdatum:","Slutdatum:","Packlista:"};
		labelPanel = new ImagePanel(new File("img/labelPanel.jpg"));
		editPanel = new ImagePanel(new File("img/editPanel.jpg"));
		try{
			readFile();
		}catch (UnsupportedEncodingException e){		//replace with (Exception e) also throws security exception.
			ErrorHandler.printError(e, this.getClass().toString());
		}catch (FileNotFoundException e){
			ErrorHandler.printError(e, this.getClass().toString());
		}catch (IOException e){
			ErrorHandler.printError(e, this.getClass().toString());}
		createPopUp();
	}

	public void readFile() throws FileNotFoundException, IOException{
		BufferedReader fileReader = new BufferedReader(new InputStreamReader(new FileInputStream(aboutFile), "UTF-8"));
		String line;
		for(int index = 0;((line = fileReader.readLine()) != null); index++){
			txtData[index] = line;
		}
	}
	
	public void createPopUp(){
		JFrame frame = new JFrame("Redigera reseprojekt");
		frame.setSize(600, 900);
		Container contentPane = frame.getContentPane();		
		contentPane.setLayout(new FlowLayout(FlowLayout.CENTER, 3, 3));		
		labelPanel.setPreferredSize(new Dimension(290,900));
		labelPanel.setLayout(new BoxLayout(labelPanel, BoxLayout.Y_AXIS));
		contentPane.add(labelPanel);
		labelPanel.add(Box.createRigidArea(new Dimension(5,5)));	
		fillLabelPanel();
		editPanel.setLayout(new BoxLayout(editPanel, BoxLayout.Y_AXIS));
		editPanel.setPreferredSize(new Dimension(290,900));
		editPanel.add(Box.createRigidArea(new Dimension(5,5)));
		contentPane.add(editPanel);
		fillEditPanel();
		frame.pack();
		frame.setVisible(true);
		
		
	}
	
	public void fillLabelPanel(){
		
		for(int index = 0; index < labels.length; index++){
			labelPanel.add(new JLabel(labels[index]));
			labelPanel.add(Box.createHorizontalStrut(1));
		}
	}
	
	public void fillEditPanel(){
		for(int index = 0; index < txtData.length; index++){
			editPanel.add(new JTextField(txtData[index]));
			editPanel.add(Box.createHorizontalStrut(1));
		}
		
	}



	public void writeToFile() throws FileNotFoundException{
		PrintWriter txtWriter = new PrintWriter(new FileOutputStream(aboutFile));
		for(int i = 0; i < txtData.length; i++){
			txtWriter.println(txtData[i]);
			txtWriter.flush();
		}

	}

}
