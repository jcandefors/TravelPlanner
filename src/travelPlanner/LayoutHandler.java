
package travelPlanner;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.io.File;
import javax.swing.*;
import javax.swing.border.EtchedBorder;



/**
 * The class LayoutHandler manages the layout of the slides in the application TravelPlanner.
 * It receives components from other classes and places them in the frame.
 * 
 * @author Joakim Candefors
 *
 */
public class LayoutHandler {

	private JFrame frame;
	private ImagePanel background;
	private Container contentPane;
	private JPanel top;
	private JPanel menuUp;
	private JPanel menuLow;
	private JPanel map;
	private JPanel main;
	private JMenu topMenu;
	private JLabel title;
	private Dimension screenSize;

	/**
	 * Constructor of LayoutHandler
	 * @param frame the main frame to be used to present on.
	 */
	public LayoutHandler(JFrame frame){
		screenSize = frame.getToolkit().getScreenSize();
		this.frame = frame;	
		frame.setSize(screenSize.width*2/3, screenSize.height*2/3);
		contentPane = frame.getContentPane();			
		background = new ImagePanel(new File("img/main.jpg"));
		background.setPreferredSize(frame.getSize());
		background.setLayout(new BorderLayout(2,2));
		contentPane.add(background);
		setUpTop();
		setUpMenu();
		setUpMain();
		frame.pack();
		frame.setVisible(true);
	}

	/**
	 * Sets up the top area in the top of the layout.
	 */
	public void setUpTop(){
		top = new JPanel(new GridLayout(1, 5, 5, 5));
		top.setBackground(Color.LIGHT_GRAY);		
		topMenu = new JMenu("Meny");									//TBC needs action
		topMenu.setMnemonic(KeyEvent.VK_A);
		JMenuItem menuItem = new JMenuItem("Avsluta", KeyEvent.VK_F4);
		topMenu.add(menuItem);											//TBC - needs action
		topMenu.add(new JMenuItem("Byt Användare"));
		title = new JLabel();
		title.setSize(100, 36);											//TBC size?
		title.setFont(Font.getFont("Calibri"));
		top.add(topMenu);
		top.add(title);
		background.add(top,BorderLayout.NORTH);	
	}

	/**
	 * Sets up the menu in the west area of the layout.
	 */
	public void setUpMenu(){		
		JPanel menu = new JPanel(new FlowLayout(FlowLayout.CENTER,2,2));
		menu.setPreferredSize(new Dimension(screenSize.width/6,screenSize.height-50));	
		menu.setOpaque(false);
		menuUp = new JPanel();	
		menuUp.setLayout(new BoxLayout(menuUp, BoxLayout.Y_AXIS));
		menuUp.setOpaque(false);
		menuUp.setPreferredSize(new Dimension(screenSize.width/6,screenSize.height*2/5));
		menuUp.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.RAISED));
		menuLow = new JPanel();
		menuLow.setLayout(new BoxLayout(menuLow, BoxLayout.Y_AXIS));		
		menuLow.setOpaque(false);
		menuLow.setPreferredSize(new Dimension(screenSize.width/6,screenSize.height*3/5));
		menuLow.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.RAISED));
		menuLow.add(Box.createRigidArea(new Dimension(10, 10)));
		menu.add(menuUp);
		menu.add(menuLow);
		background.add(menu, BorderLayout.WEST);	
	}

	/**
	 * Sets up the main area in the center of the layout.
	 */
	public void setUpMain(){
		map = new JPanel(new FlowLayout());			//perhaps (layoutmgr, true) = double buffered - less flickering, more memory usage.
		map.setPreferredSize(new Dimension(screenSize.width*5/6,screenSize.height/3));
		map.setOpaque(false);
		map.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.RAISED));
		main = new JPanel(new GridLayout(0,2,10,10));		
		main.setOpaque(false);
		JPanel outerMain = new JPanel();
		outerMain.setLayout(new FlowLayout(FlowLayout.CENTER,2,2));
		outerMain.setOpaque(false);
		JPanel innerMain = new JPanel();
		innerMain.setLayout(new FlowLayout(FlowLayout.LEFT));
		innerMain.setOpaque(false);
		innerMain.setPreferredSize(new Dimension(screenSize.width*5/6,screenSize.height*2/3));
		innerMain.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.RAISED));		
		innerMain.add(main);
		outerMain.add(map);
		outerMain.add(innerMain);
		background.add(outerMain,BorderLayout.CENTER);
	}

	/**
	 * Adds component to the upper menu area of the layout.
	 * @param component The component to be added.
	 */
	public void addToMenuUp(Component component){
		menuUp.add(component);
		menuUp.add(Box.createRigidArea(new Dimension(2, 3)));
		menuUp.revalidate();
	}

	/**
	 * Adds component to the lower menu area of the layout.
	 * @param component The component to be added.
	 */
	public void addToMenuLow(Component component){
		menuLow.add(component);
		menuLow.add(Box.createRigidArea(new Dimension(2, 3)));
		menuLow.revalidate();

	}

	/**
	 * Adds component to the main area of the layout.
	 * @param component The component to be added.
	 */
	public void addToMain(Component component){

		main.add(component);
		main.revalidate();

	}

	/**
	 * Adds component to the main area of the layout.
	 * @param component The component to be added.
	 */
	public void addToMap(Component component){

		map.add(component);
		map.revalidate();

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
		title.revalidate();
	}

	public void setBackground(File file){		
		background.setBackground(file);

	}
	/**
	 * Returns the frame used by this layouthandler.
	 * @return The frame used by this LayoutHandler.
	 */
	public JFrame getFrame(){
		return frame;
	}

}	


