package travelPlanner;
import java.awt.Color;
import java.awt.ComponentOrientation;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.LayoutManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.InputVerifier;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

/**
 * 
 * @author Joakim Candefors
 *
 */
public class DestinationButton extends JButton implements ActionListener{
	private String travelProject; 
	private LayoutHandler layoutHandler;
	private int actionType;
	public static final int OPEN = 1;
	public static final int NEW = 2;	

	/**
	 * Constructor of a destination-button. This component takes the user to the destination referenced in this button. 
	 * @param layoutHandler A LayoutHandler to lay out this component in the frame.
	 * @param travelProject	A the name of the travelProject the destination belongs to.
	 * @param destinationTitle	The title of the destination.
	 * @param actionType
	 */
	public DestinationButton(LayoutHandler layoutHandler, String travelProject, String title, int actionType){
		super(title);
		this.layoutHandler = layoutHandler;
		this.actionType = actionType;
		super.setCursor(new Cursor(Cursor.HAND_CURSOR));
		super.setBorderPainted(false);
		if(actionType == OPEN){
			super.setToolTipText("Öppna destinationen " + title);
		}else if(actionType == NEW){
			super.setToolTipText("Skapa en ny destination");
		}
		this.travelProject = travelProject;
		super.addActionListener(this);
	}
	public void actionPerformed(ActionEvent e) {
		switch (actionType){

		case 1 : new Destination(layoutHandler, travelProject, super.getText(), false); //Open destination
		break;
		case 2 : new showCreateDialog();
		}		
	}	
/**
 * Class description
 *
 */
	private class showCreateDialog{
		JFrame dialogFrame;
		JTextField destinationField;
		JDialog dialog;

		public showCreateDialog(){
			dialog = new JDialog(layoutHandler.getFrame());
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setAlwaysOnTop(true);
			dialog.setFocusable(true);
			dialog.setPreferredSize(new Dimension(290, 100));
			dialog.setMaximumSize(new Dimension(300, 100));
			dialog.setBackground(Color.BLUE);
			dialog.setLayout(new FlowLayout(FlowLayout.LEFT,2,2));
			dialog.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
			dialog.setTitle("Skapa Destination");
			JButton createButton = new JButton("Skapa");
			createButton.addActionListener(new ActionListener() {public void actionPerformed(ActionEvent e) {
				String input = destinationField.getText();
				if(input.length() < 1 && input.length()>25){
					JOptionPane.showMessageDialog(dialog, "Destinationsnamnet måste vara minst 1 tecken och max 25 tecken!");
				}else{
					createDestination(input);
				}
			}});
			destinationField = new JTextField("destinationens namn");
			dialog.add(new JLabel("Destinationens titel:"));
			dialog.add(destinationField);
			dialog.add(createButton);
			dialog.setVisible(true);
			dialog.pack();
			}	
		private void createDestination(String destinationTitle) {

			new Destination(layoutHandler, travelProject, destinationTitle, true);	//Create destination
			dialogFrame.dispose();
		}
		}

		
	}