package travelPlanner;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import javax.swing.*;


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
		frame.setSize(600, 700);
		Container contentPane = frame.getContentPane();		
		contentPane.setLayout(new BorderLayout(10, 1));		
		labelPanel.setPreferredSize(new Dimension(290,500));
		labelPanel.setLayout(new GridLayout(7, 1, 20 , 20));
		contentPane.add(labelPanel, BorderLayout.WEST);		
		fillLabelPanel();
		
		editPanel.setLayout(new GridLayout(7, 1, 20 , 20));
		editPanel.setPreferredSize(new Dimension(290,500));		
		contentPane.add(editPanel, BorderLayout.EAST);
		fillEditPanel();
		
		JPanel bottom = new JPanel(new FlowLayout(FlowLayout.RIGHT,10,10));
		bottom.setOpaque(false);
		JButton save = new JButton("Spara");
		save.addActionListener(new ActionListener() { 
			public void actionPerformed(ActionEvent arg0) {
				try{writeToFile();}
				catch (FileNotFoundException e){
					ErrorHandler.printError(e, this.getClass().toString());	}}});
		JButton cancel = new JButton("Avbryt");
		cancel.addActionListener(new ActionListener() { 
			public void actionPerformed(ActionEvent arg0) {
				//TBC
				}});		
		bottom.add(save);
		bottom.add(cancel);
		contentPane.add(bottom, BorderLayout.SOUTH);
		frame.setLocation(400,50);
		frame.pack();
		frame.setVisible(true);
		
		
	}
	
	public void fillLabelPanel(){
		
		for(int index = 0; index < labels.length; index++){
			labelPanel.add(new JLabel(labels[index]));
			labelPanel.add(Box.createRigidArea(new Dimension(5,40)));	
			}
	}
	
	public void fillEditPanel(){
		for(int index = 0; index < txtData.length; index++){
			editPanel.add(new JTextField(txtData[index]));
			editPanel.add(Box.createRigidArea(new Dimension(5,10)));
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
