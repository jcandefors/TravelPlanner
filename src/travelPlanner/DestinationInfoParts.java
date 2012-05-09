
package travelPlanner;

import java.io.DataInput;
import java.util.ArrayList;

import javax.swing.JTextField;

/**
 * This class represent the parts that is used to display a destination headline,
 * For example the subheadlines
 * and the corresponding textfields
 * 
 * @author ragnhild
 *
 */


public class DestinationInfoParts {


	private ArrayList<JTextField> textFields;
	private ArrayList<String> subHeadlines;
	private ArrayList<String> dataOfsubHeadlines;
	private String title;
	private final int TEXTFIELWIDTH = 25;

	public DestinationInfoParts(String title) {
		this.title = title;
		subHeadlines = new ArrayList<String>();
		dataOfsubHeadlines = new ArrayList<String>();
		textFields = new ArrayList<JTextField>();
		
	}

	/**
	 * Save the specified data list as subheadlines for this destinationHeadline
	 */

	public void setSubHeadlines(ArrayList<String> data) {
		subHeadlines = data;
	}
	
    /**
     * Return the subheadlines for this destinationheadline
     *
     * @return
     */

    public ArrayList<String> getSubHeadlines() {
            return subHeadlines;

    }
    
	/**
	 * Save the specified list data to the subheadlines of this
	 * dastination headline.
	 */
	public void setDataOfHeadlines(ArrayList<String> data){
	dataOfsubHeadlines = data;
		
	}
	/**
	 * Return the data corresponding to the subheadlines
	 */
	public ArrayList<String> getSubheadlinesData(){
		return dataOfsubHeadlines;
	}
	

	/**
	 * Create a list of JTextfields with the same length as data. If firtstTime
	 * is true the textfields will be filled with empty string else the
	 * specified data in "data" will be showed in the textfields.
	 */

	public void setTextfields(ArrayList<String> data, boolean firstTime) {

		if (firstTime) {
			for (int i = 0; i < data.size(); i++) {
				textFields.add(i, new JTextField("", TEXTFIELWIDTH));
				dataOfsubHeadlines.add("");
			}			

			}
		else {
            for (int i = 0; i < data.size(); i++) {
                    textFields.add(i, new JTextField(data.get(i), TEXTFIELWIDTH));
            }
    }
}
			
		
	/**
	 * Return the text typed in to the textFields of this destination headline
	 * 
	 * @return
	 */
	public ArrayList<String> getTextFieldsText() {
		ArrayList<String> returnList = new ArrayList<String>();

		for (int i = 0; i < textFields.size(); i++) {
			returnList.add(i, textFields.get(i).getText());
		}
		return returnList;

	}


/**
 * Return the title of this destination headline
 */
public String getTitle(){

	return title;

}
/**
 * Return the textifield arraylist saved in textfields
 */
public ArrayList<JTextField> getTextFields(){

	return textFields;
}

}

