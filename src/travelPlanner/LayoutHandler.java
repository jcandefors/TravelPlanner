
package travelPlanner;

import java.awt.*;

import javax.swing.*;

/**
 * The class LayoutHandler manages the layout of the slides in the application TravelPlanner.
 * It receives components from other classes and places them in the frame.
 * 
 * @author Joakim Candefors
 *
 */
public class LayoutHandler {

	private JFrame frame;
	private Container contentPane;
	private JPanel top;
	private JPanel menuUp;
	private JPanel menuLow;
	private JPanel map;
	private JPanel main;
	private JMenu topMenu;
	private JLabel title;
	/**
	 * Constructor of LayoutHandler
	 * @param frame the main frame to be used to present on.
	 */
	public LayoutHandler(JFrame frame){
		this.frame = frame;
		contentPane = frame.getContentPane();
		contentPane.setLayout(new BorderLayout(2,2));
		top = new JPanel(new FlowLayout(FlowLayout.LEFT, 5, 5));
		top.setBackground(Color.BLUE);		
		
		JPanel menu = new JPanel(new FlowLayout(FlowLayout.CENTER,0,2));
		menu.setPreferredSize(new Dimension(300, 900));	
		menuUp = new JPanel();	
		menuUp.setLayout(new BoxLayout(menuUp, BoxLayout.Y_AXIS));
		menuUp.setBackground(Color.red);
		menuUp.setPreferredSize(new Dimension(290, 400));
				
		menuLow = new JPanel();	
		menuLow.setLayout(new BoxLayout(menuLow, BoxLayout.Y_AXIS));	
		menuLow.setBackground(Color.PINK);
		menuLow.setPreferredSize(new Dimension(290, 500));
		
		menu.add(menuUp);
		menu.add(menuLow);
		
		map = new JPanel(new FlowLayout());			//perhaps (layoutmgr, true) = double buffered - less flickering, more memory usage.
		map.setPreferredSize(new Dimension(1000,300));
		map.setBackground(Color.YELLOW);
		main = new JPanel(new FlowLayout());		// set size perhaps?
		main.setBackground(Color.GREEN);
		main.setPreferredSize(new Dimension(1000, 800));
		JPanel tempMain = new JPanel();
		tempMain.setLayout(new BoxLayout(tempMain, BoxLayout.Y_AXIS));
		tempMain.add(map);
		tempMain.add(main);
				
		topMenu = new JMenu("Meny");
		topMenu.add(new JMenuItem("Avsluta"));		//TBC - needs action
		topMenu.add(new JMenuItem("Byt Användare"));
		title = new JLabel();

		contentPane.add(menu,BorderLayout.WEST);
		contentPane.add(tempMain,BorderLayout.CENTER);
		contentPane.add(top,BorderLayout.NORTH);

		top.add(topMenu);
		top.add(topMenu);
		frame.pack();
		frame.setVisible(true);
	}

	/**
	 * Adds component to the upper menu area of the layout.
	 * @param component The component to be added.
	 */
	public void addToMenuUp(Component component){
		menuUp.add(component);
		menuUp.add(Box.createHorizontalStrut(5));
	}

	/**
	 * Adds component to the lower menu area of the layout.
	 * @param component The component to be added.
	 */
	public void addToMenuLow(Component component){
		menuLow.add(component);
		menuLow.add(Box.createHorizontalStrut(5));

	}

	/**
	 * Adds component to the main area of the layout.
	 * @param component The component to be added.
	 */
	public void addToMain(Component component){

		main.add(component);

	}
		
	/**
	 * Adds component to the main area of the layout.
	 * @param component The component to be added.
	 */
	public void addToMap(Component component){

		map.add(component);

	}

	/**
	 *  Removes all components from the menu panel.
	 */
	public void clearMenu(){

		menuUp.removeAll();
		menuLow.removeAll();
		
	}
	/**
	 * Removes all components from the main panel.
	 */
	public void clearMain(){

		main.removeAll();
	}
	/**
	 * Removes all components from the map panel.
	 */
	public void clearMap(){

		map.removeAll();
	}
	/**
	 * Removes all components from all the panels.
	 */
	public void clearAll(){
		menuUp.removeAll();
		menuLow.removeAll();
		main.removeAll();
		map.removeAll();
	}
	/**
	 * Updates the title of the slide
	 * @param slideTitle
	 */
	public void updateTitle(String slideTitle){
		title.setText(slideTitle);
		title.repaint();
	}
	
	public void setBackground(Image image){
		//TBC
	}



}
