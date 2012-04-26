package travelPlanner;

/** 
 * Destination produce GUI for viewing and editing Destination info 
 * 
 * 
 */

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;
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

	// Single Label texts
	private static String ARRIVALTITLE = "Inresa";
	private static String DEPARTURETITLE = "Utresa";
	private static String LIVINGTITLE = "Boende";
	private static String QUITBUTTONTEXT = "Avbryt";
	private static String SAVEBUTTONTEXT = "Spara";
	private static String EDITDESTINATININSTRUCTION = "Fyll i fälten nedan. Välj Spara eller Avbryt";

	// Subheadlines strings
	private ArrayList<String> ARRIVALSUBHEADLINES;
	private ArrayList<String> DEPARTURESUBHAEDLINES;
	private ArrayList<String> LIVINGSUBHEADLINES;

	// Korrespondoning datastings
	private ArrayList<String> ARRIVALDATA;
	private ArrayList<String> DEPARTUREDATA;
	private ArrayList<String> LIVINGDATA;

	// Data
	private ArrayList<JTextField> ARRIVALTEXTFIELDS;
	private ArrayList<JTextField> DEPARTURETEXTFIELDS;
	private ArrayList<JTextField> LIVINGTEXTFIELDS;

	private ArrayList<DestinationHeadline> existingdata;
	private final int TEXTFIELWIDTH = 25;

	/**
	 * Create a instance of Destination that primely could be used to get an
	 * editDestination pop up, and Jpanel for viewing saved destination info
	 * 
	 * @param destinationTitle
	 * @param username
	 * @param travelProjectName
	 */

	public DestinationWindow(String destinationTitle, String username,
			String travelProjectName, boolean firstTime) {
		this.destinationTitle = destinationTitle;
		this.username = username;
		this.travelProjectName = travelProjectName;
		dataArchive = new DestinationReaderWriter(destinationTitle, username,
				travelProjectName);
		this.firstTime = firstTime;
		editDestinationFrame = new JFrame("Redigera: "+destinationTitle);//TODO SLäng up i variabel
		editDestinationFrame.setAlwaysOnTop(true);
		// Initalise instance variabels with saved data

		// ERSÄTT MED STORLOOP SOM LOOPAR EXISTING DATA TODO!
		
		ARRIVALTEXTFIELDS = new ArrayList<JTextField>();
		DEPARTURETEXTFIELDS = new ArrayList<JTextField>();
		LIVINGTEXTFIELDS = new ArrayList<JTextField>();
		

		if (firstTime) {

			ARRIVALSUBHEADLINES = DestinationInfo
					.getDestinationInfoArrayList(DestinationHeadline.ARRIVAL);

			for (int i = 0; i < ARRIVALSUBHEADLINES.size(); i++) {
				ARRIVALTEXTFIELDS.add(i, new JTextField("",TEXTFIELWIDTH));
			}
			ARRIVALDATA = new ArrayList<String>();

			DEPARTURESUBHAEDLINES = DestinationInfo
					.getDestinationInfoArrayList(DestinationHeadline.DEPARTURE);
			for (int i = 0; i < DEPARTURESUBHAEDLINES.size(); i++) {
				DEPARTURETEXTFIELDS.add(i, new JTextField("",TEXTFIELWIDTH));
			}
			DEPARTUREDATA = new ArrayList<String>();

			LIVINGSUBHEADLINES = DestinationInfo
					.getDestinationInfoArrayList(DestinationHeadline.LIVING);
			for (int i = 0; i < LIVINGSUBHEADLINES.size(); i++) {
				LIVINGTEXTFIELDS.add(i, new JTextField("",TEXTFIELWIDTH));
			}
			LIVINGDATA = new ArrayList<String>();

		}

		if (!(firstTime)) {

			ARRIVALSUBHEADLINES = dataArchive
					.getShortDestinationInformationHeadlines(DestinationHeadline.ARRIVAL);
			DEPARTURESUBHAEDLINES = dataArchive
					.getShortDestinationInformationHeadlines(DestinationHeadline.DEPARTURE);
			LIVINGSUBHEADLINES = dataArchive
					.getShortDestinationInformationHeadlines(DestinationHeadline.LIVING);

			ARRIVALDATA = dataArchive
					.getShortDestinationInformationData(DestinationHeadline.ARRIVAL);
			DEPARTUREDATA = dataArchive
					.getShortDestinationInformationData(DestinationHeadline.DEPARTURE);
			LIVINGDATA = dataArchive
					.getShortDestinationInformationData(DestinationHeadline.LIVING);

			// Initiera TEXTFÄLT ARRAYS

			for (int i = 0; i < DEPARTURESUBHAEDLINES.size(); i++) {
				DEPARTURETEXTFIELDS
						.add(i, new JTextField(DEPARTUREDATA.get(i),TEXTFIELWIDTH));
			}

			for (int i = 0; i < ARRIVALSUBHEADLINES.size(); i++) {
				ARRIVALTEXTFIELDS.add(i, new JTextField(ARRIVALDATA.get(i), TEXTFIELWIDTH));
			}

			for (int i = 0; i < LIVINGSUBHEADLINES.size(); i++) {
				LIVINGTEXTFIELDS.add(i, new JTextField(LIVINGDATA.get(i),TEXTFIELWIDTH));
			}

		}

	}

	public void savefunction() {
		// LOPPA EFTER EXISTINGDATA

		updateShortInformationDataArrays(DestinationHeadline.ARRIVAL);
		updateShortInformationDataArrays(DestinationHeadline.DEPARTURE);
		updateShortInformationDataArrays(DestinationHeadline.LIVING);

		// Save data
		dataArchive.saveShortDestinationInformationData(ARRIVALDATA,
				DestinationHeadline.ARRIVAL);
		dataArchive.saveShortDestinationInformationData(DEPARTUREDATA,
				DestinationHeadline.DEPARTURE);
		dataArchive.saveShortDestinationInformationData(LIVINGDATA,
				DestinationHeadline.LIVING);

		// Save headlines
		dataArchive.saveShortDestinationInformationHeadlines(
				ARRIVALSUBHEADLINES, DestinationHeadline.ARRIVAL);
		dataArchive.saveShortDestinationInformationHeadlines(DEPARTURESUBHAEDLINES,
				DestinationHeadline.DEPARTURE);
		dataArchive.saveShortDestinationInformationHeadlines(
				LIVINGSUBHEADLINES, DestinationHeadline.LIVING);

	}

	public void updateShortInformationDataArrays(DestinationHeadline headline) {
		// LOOPA EXISTINGDATA TODO!

		switch (headline) {
		case ARRIVAL:
			for (int i = 0; i < ARRIVALTEXTFIELDS.size(); i++)
				ARRIVALDATA.add(i, ARRIVALTEXTFIELDS.get(i).getText());
			break;

		case DEPARTURE:
			for (int i = 0; i < DEPARTURETEXTFIELDS.size(); i++)
				DEPARTUREDATA.add(i, DEPARTURETEXTFIELDS.get(i).getText());
			break;

		case LIVING:
			for (int i = 0; i < LIVINGTEXTFIELDS.size(); i++)
				LIVINGDATA.add(i, LIVINGTEXTFIELDS.get(i).getText());

			break;
		}

	}

	private void quitEditDestinationButtonAction() {
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

	public void getEditDestinationPopUp(boolean firstTime) { // TODO
		// Utvigda till ExistingDATA LOOP TODO!
		if (firstTime) {
			savefunction();
		}

		ImagePanel background = new ImagePanel(new File("img/editDestBackground.jpg"));
		background.add(buildDestinationInfoPanel(TypeOfPanel.EDITING));
		editDestinationFrame.add(background);
		editDestinationFrame.setLocationByPlatform(true);
		editDestinationFrame.pack();
		background.scaleImage(editDestinationFrame.getSize());		//rescale image to fit frame size.
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

		returnPanel.add(buildDestinationInfoBlock(DestinationHeadline.ARRIVAL,
				typeOfPanel));
		returnPanel.add(buildDestinationInfoBlock(
				DestinationHeadline.DEPARTURE, typeOfPanel));
		returnPanel.add(buildDestinationInfoBlock(DestinationHeadline.LIVING,
				typeOfPanel));

		if (typeOfPanel.equals(TypeOfPanel.EDITING)) {
			// TODO ADDA SAVE OCH AVBRYTKNAPPAR...

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

		ArrayList<String> headlinesOfShortInformation = null; 
		ArrayList<String> dataOfShortInformation = null ;
		ArrayList<JTextField> textFieldsWithShortInformation = null; 

		// Initialise headline for the block

		String headlineOfBlock = null;

		switch (headline) {
		case ARRIVAL:
			headlineOfBlock = ARRIVALTITLE;
			headlinesOfShortInformation = ARRIVALSUBHEADLINES;
			dataOfShortInformation = ARRIVALDATA;
			textFieldsWithShortInformation = ARRIVALTEXTFIELDS;
			break;

		case DEPARTURE:
			headlineOfBlock = DEPARTURETITLE;
			headlinesOfShortInformation = DEPARTURESUBHAEDLINES;
			dataOfShortInformation = DEPARTUREDATA;
			textFieldsWithShortInformation = DEPARTURETEXTFIELDS;
			break;

		case LIVING:
			headlineOfBlock = LIVINGTITLE;
			headlinesOfShortInformation = LIVINGSUBHEADLINES;
			dataOfShortInformation = LIVINGDATA;
			textFieldsWithShortInformation = LIVINGTEXTFIELDS;
			break;
		}

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

		// Fill Panel

		returnPanel.add(headlineLabel, "wrap");

		// Viewing -> Fill with labels

		if (TypeOfPanel.VIEWING.equals(editOrView)) {
			for (int i = 0; i < subHeadlinesLabels.size(); i++) {
				returnPanel.add(subHeadlinesLabels.get(i), "gap unrelated");
				returnPanel.add(dataLabels.get(i));
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

// BLANDAT SKRÄP

// // More info Button action
//
// private void moreInfoButtonAction(DestinationHeadline typeOfInformation){
// JFrame MoreInfoFrame = new JFrame();
// String moreInfoString =
// dataArchive.getMoreInformationText(typeOfInformation);
// JOptionPane.showMessageDialog(MoreInfoFrame,moreInfoString,
// "Mer information", JOptionPane.INFORMATION_MESSAGE); //TODO fixa
// hårdkodningen på mer inforamtion
// }
//

// switch (editOrWiev){
// case EDITING:
// moreInfo = new JButton(MOREINFOTITLE);
//			
// break;
// case VIEWING:
// viewOrEdit = new JLabel();
// break;
// }
//

// Components for typeOfPanel: Viewing

// JFrame MoreInfoFrame = new JFrame();
// JButton moreInfoButton = new JButton(MOREINFOTITLE + headlineOfBlock);

// TODO button actionlistener

// moreInfoButton.addActionListener(new ActionListener() {
// public void actionPerformed(ActionEvent e) {
// moreInfoButtonAction(headline);
// }
// });
// //
// components for typeOfPanel Editing,
// JLabel moreInfoLabel = new JLabel(MOREINFOTITLE + headlineOfBlock);

// initalise JtextArea

// JTextArea moreInfoTextArea = new JTextArea(moreInfoText);
// moreInfoTextArea.setLineWrap(true);

// Fill panel

// initialise

// Layout Panel