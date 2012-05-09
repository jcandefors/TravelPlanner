package travelPlanner;

import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import net.miginfocom.swing.MigLayout;

public class DestinationWindow {

	private JFrame editDestinationFrame;

	// Settings
	private final String destinationTitle;
	private final String username;
	private final String travelProjectName;
	private DestinationReaderWriter dataArchive;
	private boolean firstTime;
	private Destination destinationObject;

	// All saved data about the specified destination

	private ArrayList<DestinationHeadline> existingdataTypes;
	private HashMap<DestinationHeadline, DestinationInfoParts> destinationData; // A
	// HashMap
	// that
	// use headlines from existing dataTypes as Keys and instances of
	// DestinationInfoParts as value.
	// The destination Info parts hold the textfields and the destination
	// subheadlines
	// for each destination headline

	// Single Label texts

	private static String QUITBUTTONTEXT = "Avbryt";
	private static String SAVEBUTTONTEXT = "Spara";
	private static String EDITDESTINATININSTRUCTION = "Fyll i f�lten nedan. V�lj Spara eller Avbryt";
	private static String EDITDESTINATIONFRAMETITLE = "Redigera: ";

	/**
	 * Create a instance of Destination that primely could be used to get an
	 * editDestination pop up, and Jpanel for viewing saved destination info
	 *
	 * @param destinationTitle
	 * @param username
	 * @param travelProjectName
	 */

	public DestinationWindow(Destination destinationObject,
			String destinationTitle, String username, String travelProjectName,
			boolean firstTime) {

		this.destinationObject = destinationObject;
		this.destinationTitle = destinationTitle;
		this.username = username;
		this.travelProjectName = travelProjectName;
		dataArchive = new DestinationReaderWriter(destinationTitle, username,
				travelProjectName);
		this.firstTime = firstTime;
		editDestinationFrame = new JFrame(EDITDESTINATIONFRAMETITLE
				+ destinationTitle);
				editDestinationFrame.setAlwaysOnTop(true);
				
		destinationData = new HashMap<DestinationHeadline, DestinationInfoParts>();
						
				// Initalise the hashmap destinationdata with saved data		
				if (firstTime) {
					// Initialise existingDataTypes
					existingdataTypes = DestinationInfo
							.getExistingDestinationInfoTypes();

					for (int i = 0; i < existingdataTypes.size(); i++) { // Initialise
						// the
						// hashmap
						// destinationd
						// data
						// with all avaliable datatyped as keys and there corresponding
						// values

						DestinationInfoParts value = new DestinationInfoParts(
								DestinationInfo
								.getTitle(existingdataTypes.get(i)));

						value.setSubHeadlines(DestinationInfo
								.getDestinationInfoArrayList(existingdataTypes.get(i)));

						value.setTextfields(DestinationInfo
								.getDestinationInfoArrayList(existingdataTypes.get(i)),
								firstTime);

						destinationData.put(existingdataTypes.get(i), value);
					}
				}

				if (!(firstTime)) {
					// Initialise existingDataTypes
					existingdataTypes = dataArchive.getExistingDataTypes();
					for (int i = 0; i < existingdataTypes.size(); i++) { // Load saved
						// data in
						// to the
						// hashmap
						// destination
						// data

						DestinationInfoParts value = new DestinationInfoParts(
								DestinationInfo
								.getTitle(existingdataTypes.get(i)));

						value
						.setSubHeadlines(dataArchive
								.getShortDestinationInformationHeadlines(existingdataTypes
										.get(i)));
						value.setTextfields(dataArchive
								.getShortDestinationInformationData(existingdataTypes
										.get(i)), firstTime);

						destinationData.put(existingdataTypes.get(i), value);

						value.setDataOfHeadlines(dataArchive
								.getShortDestinationInformationData(existingdataTypes
										.get(i)));

					}
				}
	}

	public void savefunction() {
		for (int i = 0; i < existingdataTypes.size(); i++) { // save all text in
			// existing
			// textfields

			DestinationHeadline headline = existingdataTypes.get(i);
			//Save existing Datatypes
			dataArchive.saveExistingDataTypes(existingdataTypes);			
			// Save title
			dataArchive.saveTitle(headline, destinationData.get(headline)
					.getTitle());
			// save textfield text
			dataArchive.saveShortDestinationInformationData(destinationData
					.get(headline).getTextFieldsText(), headline);
			// save subheadlines
			dataArchive.saveShortDestinationInformationData(destinationData
					.get(headline).getSubHeadlines(), headline);
		}
	}

	private void quitEditDestinationButtonAction() {
		destinationObject.prepareLayout();
		editDestinationFrame.dispose();
	}

	// Declare a private Enum class for all accepted type of
	// destinationInfoBlocks

	private enum TypeOfPanel {
		VIEWING, EDITING
	}

	/**
	 * Return a frame for editing destination info.
	 *
	 */

	public void getEditDestinationPopUp(boolean firstTime) {

		editDestinationFrame = new JFrame(EDITDESTINATIONFRAMETITLE
				+ destinationTitle);
		editDestinationFrame.setAlwaysOnTop(true);
		editDestinationFrame.setLocationByPlatform(true);
		if (firstTime) {
			savefunction();
		}

		ImagePanel background = new ImagePanel(new File(
				"img/editDestBackground.jpg"));
		background.add(buildDestinationInfoPanel(TypeOfPanel.EDITING));
		editDestinationFrame.add(background);
		editDestinationFrame.setLocationByPlatform(true);
		editDestinationFrame.pack();
		background.scaleImage(editDestinationFrame.getSize()); // rescale image
		// to fit frame
		// size.
		editDestinationFrame.setVisible(true);

	}

	/**
	 * Return a panel adjusted to fit in Travelplanners mainarea. The panel view
	 * all saved data about the destination.
	 *
	 * @return
	 */

	public JPanel getMainPanel() {
		JPanel returnPanel = buildDestinationInfoPanel(TypeOfPanel.VIEWING);

		returnPanel.setOpaque(false);

		return returnPanel;

	}

	/**
	 * Return a panel with box layouot that containing subPanels representing
	 * all available DestinationHeadlines
	 *
	 * @param typeOfPanel
	 *            : specialise if the panel should be adjusted for editing or
	 *            viewing destination data.
	 */

	private JPanel buildDestinationInfoPanel(TypeOfPanel typeOfPanel) {

		JPanel returnPanel = new JPanel(new MigLayout("wrap 1"));
		returnPanel.setOpaque(false);

		for (int i = 0; i < existingdataTypes.size(); i++) {
			returnPanel.add(buildDestinationInfoBlock(existingdataTypes.get(i),
					typeOfPanel));
		}
		if (typeOfPanel.equals(TypeOfPanel.EDITING)) {
			JButton saveButtonEditDestion = new JButton(SAVEBUTTONTEXT);
			saveButtonEditDestion.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					savefunction();
					quitEditDestinationButtonAction();
				}
			});

			// Buttons
			JButton quitButtonEditDestination = new JButton(QUITBUTTONTEXT);
			quitButtonEditDestination.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					quitEditDestinationButtonAction();
				}
			});

			JPanel bottom = new JPanel(new FlowLayout(FlowLayout.RIGHT, 15, 5));
			bottom.add(saveButtonEditDestion);
			bottom.add(quitButtonEditDestination);
			bottom.setOpaque(false);
			returnPanel.add(bottom);

		}
		return returnPanel;

	}

	/**
	 * Return a panel with the specified Headline, and components associated
	 * with this Headline.
	 *
	 * @param headline
	 *            Specify which kind of information that will be present in the
	 *            panel
	 * @param editOrView
	 *            specify if the data should be presented in editable textFields
	 *            or static Labels
	 * @return
	 *
	 */
	private JPanel buildDestinationInfoBlock(DestinationHeadline headline,
			TypeOfPanel editOrView) {

		JPanel returnPanel = new JPanel(new MigLayout("wrap 4"));
		returnPanel.setOpaque(false);

		ArrayList<String> headlinesOfShortInformation = destinationData.get(headline).getSubHeadlines();
		ArrayList<JTextField> textFieldsWithShortInformation = destinationData.get(headline).getTextFields();
		ArrayList<String> dataOfShortInformation = destinationData.get(headline).getDataOfSubheadlines();

		String headlineOfBlock = destinationData.get(headline).getTitle();

		// Initialise Arrays with Labels representing subHeadlines and Arrays of
		// Labels representing corresponding data

		ArrayList<JLabel> subHeadlinesLabels = new ArrayList<JLabel>();
		ArrayList<JLabel> dataLabels = new ArrayList<JLabel>();

		for (int i = 0; i < headlinesOfShortInformation.size(); i++) {
			JLabel subHeadlineLabel = new JLabel(headlinesOfShortInformation
					.get(i));
			subHeadlinesLabels.add(i, subHeadlineLabel);
		}

		for (int i = 0; i < dataOfShortInformation.size(); i++) {
			JLabel dataLabel = new JLabel(dataOfShortInformation.get(i));
			dataLabels.add(i, dataLabel);
		}

		// Initialise components not representing data saved in arrays

		// HeadLineLabel
		JLabel headlineLabel = new JLabel(headlineOfBlock);
		headlineLabel.setFont(new Font("Tahoma", Font.BOLD, 16));

		// Fill Panel

		returnPanel.add(headlineLabel, "wrap");

		// Viewing -> Fill with labels

		if (TypeOfPanel.VIEWING.equals(editOrView)) {
			for (int i = 0; i < subHeadlinesLabels.size(); i++) {
				JLabel labelToLayout1 = subHeadlinesLabels.get(i);
				labelToLayout1.setFont(new Font("Tahoma", Font.BOLD, 14));
				JLabel labelToLayout2 = dataLabels.get(i);
				labelToLayout2.setFont(new Font("Tahoma", Font.PLAIN, 14));
				returnPanel.add(labelToLayout1, "gapright 10:10");
				returnPanel.add(labelToLayout2, "gapright 10:10");
			}
		}
		// Editing -> Fill with textfields

		if (TypeOfPanel.EDITING.equals(editOrView)) {
			for (int i = 0; i < subHeadlinesLabels.size(); i++) {
				returnPanel.add(subHeadlinesLabels.get(i), "gap unrelated");
				returnPanel.add(textFieldsWithShortInformation.get(i));

			}
		}

		return returnPanel;

	}
}
