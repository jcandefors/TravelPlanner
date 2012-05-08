package travelPlanner;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.Iterator;
import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

public class Destination extends Slide {

	protected final int MAININFOSIZE = 6;
	private DestinationWindow destinationWindow;

	/**
	 * Constructor of class Destination
	 * 
	 * @param layoutHandler
	 *            the layoutHandler to be used for laying out components.
	 * @param title
	 *            The name of the destination.
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
	 * 
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
		JButton editButton = new JButton("Redigera destination");
		editButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				editDestination(false);
			}
		});
		JButton removeButton = new JButton("Ta bort destination"); // kolla på!
		removeButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int answer = JOptionPane.showConfirmDialog(null,
						"Vill du verkligen ta bort destinationen " + title
								+ "?", "Verifiera borttagning av destination",
						JOptionPane.YES_NO_OPTION);
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
				.addToMenuUp(new DestinationButton("Skapa destination", 2));
		layoutHandler.addToMap(new MapLabel(title));
	}

	/**
	 * Creates components (DestinationButton) for all the destinations in the
	 * TravelProject except for the current one.
	 */
	public void destinationLayout() {
		JLabel menuLabel = new JLabel("Destinationer:");
		menuLabel.setSize(10, 30); // TBC
		layoutHandler.addToMenuLow(menuLabel);
		layoutHandler.addToMenuLow(Box.createRigidArea(new Dimension(20, 10)));
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
	 * Takes the user to the project view.
	 */
	private void toProject() {
		new TravelProject(layoutHandler, userName, false);
	}

	/**
	 * Creates a new EditDestination object which asks the user to edit the
	 * destiantion information and then updates the information in the layout.
	 */
	public void editDestination(boolean firstTime) {
		destinationWindow.getEditDestinationPopUp(firstTime);
	}

	/**
	 * 
	 */
	private void removeDestination() {
		destinations.remove(title);
		File removeFile = new File("data/" + userName + "/" + title);
		removeFile.delete(); // returns true if succeded - handle???
		saveDestinations();
	}
}
