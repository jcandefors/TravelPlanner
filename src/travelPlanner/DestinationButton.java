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
	private int actionType;
	public static final int OPEN = 1;
	public static final int NEW = 1;

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
		if(actionType == 1){
		super.setToolTipText("Öppna destinationen " + title);
		}else if(actionType == 2){
			super.setToolTipText("Skapa en ny destination");
		}
		this.travelProject = travelProject;
		super.addActionListener(this);
	}
	public void actionPerformed(ActionEvent e) {
		switch (actionType){
			
		case 1 : new Destination(layoutHandler, travelProject, super.getText(), false); //Open destination
		break;
		case 2 : new Destination(layoutHandler, travelProject, super.getText(), true);	//Create destination
		}
		
	}	
}