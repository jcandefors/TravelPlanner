package travelPlanner;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.LayoutManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.InputVerifier;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JLabel;
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

	private class showCreateDialog{
		
		JTextField destinationField;
		
		public showCreateDialog(){

			JDialog dialog = new JDialog(layoutHandler.getFrame());
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setAlwaysOnTop(true);
			dialog.setFocusable(true);		
			dialog.setPreferredSize(new Dimension(300, 100));
			dialog.setBackground(Color.BLUE);
			dialog.setLayout(new GridLayout(2,2));
			dialog.setTitle("Skapa Destination");
			JButton create = new JButton("Skapa");
			create.addActionListener(new ActionListener() {public void actionPerformed(ActionEvent e) {
				createDestination(destinationField.getText());}});
			destinationField = new JTextField("destinationens namn");
			destinationField.setInputVerifier(new InputVerifier() {
				public boolean verify(JComponent destinationField) {
					JTextField field = (JTextField) destinationField;
					String input = field.getText();
					if(!input.matches("destinationens namn") && input.length() > 1 && input.length()<25){
						return true;}
					return false;}
			});
			dialog.add(new JLabel("Destinationens titel:"));
			dialog.add(destinationField);
			dialog.add(create);
			dialog.setVisible(true);

		}		
	}

	private void createDestination(String destinationTitle) {

		new Destination(layoutHandler, travelProject, destinationTitle, true);	//Create destination

	}
}