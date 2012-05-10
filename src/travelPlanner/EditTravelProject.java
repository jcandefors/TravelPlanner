
package travelPlanner;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import net.miginfocom.swing.MigLayout;
import javax.swing.*;

/**
 * EditTravelProject is used to edit the travel project information via a popup presented to the user.
 * @author Joakim Candefors
 *
 */
public class EditTravelProject {

	private TravelProject travelProject;
	private JFrame frame;
	private String[] projectInfo;
	private String[] labels;
	private	ImagePanel panel;
	private JTextField[] textFields; 
	private final String CANCELBUTTONTEXT = "Avbryt" , SAVEBUTTONTEXT = "Spara" , FRAMETITLE = "Redigera reseprojekt";


	/**
	 * Constructor of EditTravelProject
	 * @param travelProject The project which is edited.
	 * @param projectInfo An array with the information about the project.
	 * @param labels An array with all the labels for the project information.
	 */
	public EditTravelProject(TravelProject travelProject, String[] projectInfo, String[] labels) {
		this.travelProject = travelProject;
		this.projectInfo =  projectInfo;
		this.labels = labels;
		panel = new ImagePanel("img/EditProject.jpg");
		textFields = new JTextField[labels.length]; 
		createPopUp();

	}

	/**
	 * Constructs a popup to be displayed to the user.
	 */
	public void createPopUp(){

		frame = new JFrame(FRAMETITLE);
		frame.setSize(300, 600);
		frame.setMinimumSize(new Dimension(200, 600));
		Container contentPane = frame.getContentPane();
		frame.getContentPane().setLayout(new BorderLayout());
		panel.setPreferredSize(new Dimension(300,600));
		panel.setLayout(new MigLayout("", "[49px][5px][30px][5px][63px][90.00px][69px][71px]", "[22px][22px][22px][22px][19.00px][25px][][][][][][]"));
		contentPane.add(panel,BorderLayout.CENTER);		
		fillPanel();

		JPanel bottom = new JPanel(new FlowLayout(FlowLayout.RIGHT,10,10));
		bottom.setOpaque(false);
		JButton save = new JButton(SAVEBUTTONTEXT);
		save.addActionListener(new ActionListener() { 
			public void actionPerformed(ActionEvent arg0) {	
				saveProjectInfo();
				travelProject.updateProjectInfo(projectInfo);
				frame.dispose();
				travelProject.prepareLayout();

			}
		});
		JButton cancel = new JButton(CANCELBUTTONTEXT);
		cancel.addActionListener(new ActionListener() { 
			public void actionPerformed(ActionEvent arg0) { frame.dispose();}
		});		
		bottom.add(save);
		bottom.add(cancel);
		contentPane.add(bottom , BorderLayout.SOUTH);
		frame.setLocation(400,50);
		frame.pack();
		frame.setVisible(true);


	}
/**
 * Fills the pop up with the labels and possible previous data to be edited. 
 */
	public void fillPanel(){

		for(int index = 0; index < labels.length; index++){
			panel.add(new JLabel(labels[index]), "cell 2 "+ index+1 + ",alignx right,aligny center");
		}

		for(int index = 0; index < labels.length; index++){
			if (projectInfo[index] != null){
				textFields[index] = new JTextField(projectInfo[index]);
			}else{
				textFields[index] = new JTextField("");
			}
			panel.add(textFields[index], "cell 4 "+ index+1 +" 2 1,growx,aligny top");
		}
	}

	/**
	 * Saves the users input in the textfields to the array.
	 */
	public void saveProjectInfo(){
		for(int index= 0; index < projectInfo.length; index++){
			projectInfo[index] = textFields[index].getText();			
		}
	}	
	
	/**
	 * Returns the changes made to the travelProject.
	 * @return An array with the travel project information.
	 */
	public String[] getChanges(){
		return projectInfo;

	}
}
