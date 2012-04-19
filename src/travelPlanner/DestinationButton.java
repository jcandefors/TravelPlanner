package travelPlanner;
import java.awt.Cursor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import travelPlanner.LayoutHandler;
/**
 * 
 * @author Joakim Candefors
 *
 */
public class DestinationButton extends JButton{
	private LayoutHandler layoutHandler;
	private String travelProject; 
	
/**
 * Constructor of a destination-button. This component takes the user to the destination referenced in this button. 
 * @param layoutHandler A LayoutHandler to lay out this component in the frame.
 * @param travelProject	A the name of the travelProject the destination belongs to.
 * @param destinationTitle	The title of the destination.
 */
	public DestinationButton(LayoutHandler layoutHandler, String travelProject, String destinationTitle){

		super(destinationTitle);
		super.setCursor(new Cursor(Cursor.HAND_CURSOR));
		super.setBorderPainted(false);
		super.setToolTipText("Öppna destinationen " + destinationTitle);
		this.layoutHandler = layoutHandler;
		this.travelProject = travelProject;
		super.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) { openDestination();	} });	

	}

	public void openDestination(){
		new travelPlanner.Destination(layoutHandler, travelProject, super.getText());		
	}
}