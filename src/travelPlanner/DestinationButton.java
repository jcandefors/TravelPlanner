package travelPlanner;
import java.awt.Cursor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;

/**
 * 
 * @author Joakim Candefors
 *
 */
public class DestinationButton extends JButton implements ActionListener{
	private String travelProject; 
	private LayoutHandler layoutHandler;

	/**
	 * Constructor of a destination-button. This component takes the user to the destination referenced in this button. 
	 * @param layoutHandler A LayoutHandler to lay out this component in the frame.
	 * @param travelProject	A the name of the travelProject the destination belongs to.
	 * @param destinationTitle	The title of the destination.
	 */
	public DestinationButton(LayoutHandler layoutHandler, String travelProject, String destinationTitle){
		super(destinationTitle);
		this.layoutHandler = layoutHandler;
		super.setCursor(new Cursor(Cursor.HAND_CURSOR));
		super.setBorderPainted(false);
		super.setToolTipText("Öppna destinationen " + destinationTitle);
		this.travelProject = travelProject;
		super.addActionListener(this);
	}
	public void actionPerformed(ActionEvent e) {
		new Destination(layoutHandler, travelProject, false);
	}	
}