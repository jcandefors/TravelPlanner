
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
	private JPanel menu;
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
		contentPane.setLayout(new BorderLayout(5,5));
		top = new JPanel(new FlowLayout(FlowLayout.LEFT, 5, 5));
		top.setBackground(Color.BLUE);
		menu = new JPanel();
		new BoxLayout(menu, BoxLayout.Y_AXIS);			//TBC
		menu.setBackground(Color.PINK);
		menu.setPreferredSize(new Dimension(400, 900));	
		map = new JPanel(new FlowLayout());			//perhaps (layoutmgr, true) = double buffered - less flickering, more memory usage.
		map.setPreferredSize(new Dimension(800,300));
		map.setBackground(Color.YELLOW);
		main = new JPanel(new FlowLayout());		// set size perhaps?
		main.setBackground(Color.GREEN);
		main.setPreferredSize(new Dimension(800, 800));
		main.revalidate();
		JPanel tempMain = new JPanel(new FlowLayout(FlowLayout.CENTER,0,2));
		tempMain.add(map);
		tempMain.add(main);
		
		topMenu = new JMenu("Meny");
		topMenu.add(new JMenuItem("Avsluta"));		//TBC - needs action
		topMenu.add(new JMenuItem("Byt Anv�ndare"));
		title = new JLabel();

		contentPane.add(menu,BorderLayout.WEST);
		contentPane.add(tempMain,BorderLayout.CENTER);
		contentPane.add(top,BorderLayout.NORTH);

		top.add(topMenu);
		top.add(topMenu);

		frame.setVisible(true);
	}

	/**
	 * Adds component to the main area of the layout.
	 * @param component The component to be added.
	 */
	public void addToMenu(Component component){
		menu.add(component);

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

		menu.removeAll();
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
		menu.removeAll();
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
