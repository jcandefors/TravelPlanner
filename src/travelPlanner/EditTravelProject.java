package travelPlanner;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.ArrayList;

import javax.swing.*;


public class EditTravelProject {
	
	private JFrame frame;
	private ArrayList<String> projectInfo;
	private String[] labels;
	private	JPanel labelPanel;
	private	JPanel editPanel;
	private JTextField[] textFields; 



	/**
	 * 
	 * @param title
	 */
	public EditTravelProject(ArrayList<String> projectInfo, String title) {
		this.projectInfo =  projectInfo;
		labels = new String[]{"Reseprojekt:","Startdatum:","Slutdatum:"};
		labelPanel = new ImagePanel(new File("img/labelPanel.jpg"));
		editPanel = new ImagePanel(new File("img/editPanel.jpg"));
		textFields = new JTextField[labels.length]; 
		createPopUp();
		
	}

	public void createPopUp(){
		
		frame = new JFrame("Redigera reseprojekt");
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
				saveProjectInfo();
				frame.dispose();
				}
			});
		JButton cancel = new JButton("Avbryt");
		cancel.addActionListener(new ActionListener() { 
			public void actionPerformed(ActionEvent arg0) { frame.dispose();}  //handle first time set up?
			});		
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
		for(int index = 0; index < labels.length; index++){
			if(!projectInfo.isEmpty()){
			textFields[index] = new JTextField(projectInfo.get(index));
			editPanel.add(textFields[index]);
			}else{
				textFields[index] = new JTextField("");
				editPanel.add(textFields[index]);
			}
			editPanel.add(Box.createRigidArea(new Dimension(5,10)));
		}
	}
	
	public void saveProjectInfo(){
		for(int index= 0; index < projectInfo.size(); index++){
			projectInfo.set(index, textFields[index].getText());			
		}
	}		
}
