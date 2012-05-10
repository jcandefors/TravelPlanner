package travelPlanner;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.Iterator;
import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
/**
 * The class of the destinations in the application TravelPlanner. Destinations are responsible to hold and handle destination information
 *  to be used to create components displayed to the user via the LayoutHandler. * 
 * @author Joakim Candefors
  */

public class Destination extends Slide {

	protected final int MAININFOSIZE = 6;
	private DestinationWindow destinationWindow;
	private final String EDITDESTINATIONTEXT = "Redigera destination", REMOVEBUTTONTEXT = "Ta bort destination", REMOVEPROMPTTEXT = "Vill du verkligen ta bort destinationen ", 
			REMOVEPROMPTTITLE = "Verifiera borttagning av destination", CREATEBUTTONTEXT = "Skapa destination", DESTINATIONSLABELTEXT = "Destinationer:";

	/**
	 * Constructor of class Destination
	 * 
	 * @param layoutHandler The layoutHandler to be used for laying out components.
	 * @param user The user connected with this destination.
	 * @param title The name of the destination.
	 */
	public Destination(LayoutHandler layoutHandler, String user, String title,
			Boolean firstTime) {
		super(layoutHandler, user);
		super.title = title;
		loadDestinations();
		destinationWindow = new DestinationWindow(this, title, userName,
				userName, firstTime);
		if (firstTime) {
			new File("data/" + user + "/" + title).mkdir();
			editDestination(firstTime);
			addDestination(title);
		} else {
			prepareLayout();
		}
	}

	/**
	 * Calls help methods to prepare components and send them LayoutHandler for layout. 
	 */
	public void prepareLayout() {
		layoutHandler.clearAll();
		generalDestinationLayout();
		destinationLayout();
		mainLayout();
	}

	/**
	 * Creates the general components for all Destinations.
	 */
	public void generalDestinationLayout() {
		layoutHandler.addToMenuUp(Box.createRigidArea(new Dimension(20, 10)));
		layoutHandler.updateTitle("Destination: " + title);
		JButton editButton = new JButton(EDITDESTINATIONTEXT);
		editButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				editDestination(false);
			}
		});
		JButton removeButton = new JButton(REMOVEBUTTONTEXT);
		removeButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int answer = JOptionPane.showConfirmDialog(null,
						REMOVEPROMPTTEXT + title + "?", REMOVEPROMPTTITLE, JOptionPane.YES_NO_OPTION);
				if (answer == 0) {
					removeDestination();
					toProject();
				}
			}
		});
		JButton toProjectButton = new JButton("Till reseprojektet");
		toProjectButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				toProject();
			}
		});
		layoutHandler.addToMenuUp(toProjectButton);
		layoutHandler.addToMenuUp(editButton);
		layoutHandler.addToMenuUp(removeButton);
		layoutHandler
				.addToMenuUp(new DestinationButton(CREATEBUTTONTEXT, 2));
		layoutHandler.addToMap(new MapLabel(title));
	}

	/**
	 * Creates components (DestinationButton) for all the destinations in the
	 * TravelProject except for the current one.
	 */
	public void destinationLayout() {
		layoutHandler.addToMenuLow(Box.createRigidArea(new Dimension(20, 10)));
		JLabel menuLabel = new JLabel(DESTINATIONSLABELTEXT);
		menuLabel.setFont(new Font("Tahoma", Font.BOLD, 16));
		layoutHandler.addToMenuLow(menuLabel);
		Iterator<String> iterator = destinations.iterator();
		while (iterator.hasNext()) {
			String nextDestination = iterator.next();
			if (!nextDestination.matches(title)) {
				layoutHandler.addToMenuLow(new DestinationButton(
						nextDestination, DestinationButton.OPEN));
			}
		}
	}

	/**
	 * Creates components from mainInfo and labels and adds them to the frame.
	 */
	public void mainLayout() {

		layoutHandler.addToMain(destinationWindow.getMainPanel());

	}

	/**
	 * Takes the user to the project slide.
	 */
	private void toProject() {
		new TravelProject(layoutHandler, userName, false);
	}

	/**
	 * Creates a new EditDestination object which asks the user to edit the
	 * destination information and then updates the information in the layout.
	 */
	public void editDestination(boolean firstTime) {
		destinationWindow.getEditDestinationPopUp(firstTime);
	}

	/**
	 * Removes the destination from the travelproject and its associated files from the disk.
	 */
	private void removeDestination() {
		destinations.remove(title);
		File folder = new File("data/" + userName + "/" + title);
		File[] filelist = folder.listFiles();
		for(int index = 0 ; index < filelist.length; index ++){
			System.out.println(filelist[index].delete());
		}
		folder.delete();
		saveDestinations();
	}
}
