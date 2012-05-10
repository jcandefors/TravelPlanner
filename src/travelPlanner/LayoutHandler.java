package travelPlanner;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
	private JPanel background;
	private JPanel top;	
	private JPanel menuUp;
	private JPanel menuLow;
	private ImagePanel map;
	private JPanel main;
	private JLabel title;
	private Dimension frameSize;
	private Dimension screenSize;

	/**
	 * Constructor of LayoutHandler
	 * @param frame the main frame to be used to present on.
	 */
	public LayoutHandler(JFrame frame){
		this.frame = frame;
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		screenSize = new Dimension(frame.getToolkit().getScreenSize());
		frameSize = new Dimension(frame.getToolkit().getScreenSize().width*2/3,frame.getToolkit().getScreenSize().height*2/3);
		background = new JPanel(new BorderLayout(0,0));
		frame.setContentPane(background);
		background.setPreferredSize(frameSize);
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
		top = new JPanel(new GridLayout(0, 5, 0, 0));
		top.setBackground(Color.LIGHT_GRAY);
		JMenuBar topMenuBar = new JMenuBar();
		topMenuBar.setPreferredSize(new Dimension(40,20));
		topMenuBar.setBorder(null);
		topMenuBar.setBackground(Color.LIGHT_GRAY);
		JMenu topMenu = new JMenu("Meny");
		topMenu.setMnemonic(KeyEvent.VK_A);
		topMenuBar.add(topMenu);
		
		JMenuItem menuItem_1 = new JMenuItem("Logga ut"); 			//create log out-button
		menuItem_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
				JFrame newFrame = new JFrame();
				newFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				newFrame.setLocationByPlatform(true);
				new LogInWindow(newFrame);
			}});
		topMenu.add(menuItem_1);
		
		JMenuItem menuItem = new JMenuItem("Avsluta", KeyEvent.VK_F4);		// create exit-button
		menuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int answer = JOptionPane.showConfirmDialog(frame, "Vill du verkligen avsluta?", "Avsluta TravelPlanner", JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE);
				if (answer == 0){System.exit(0);}}});
		topMenu.add(menuItem);
		title = new JLabel();
		title.setHorizontalAlignment(SwingConstants.CENTER);
		title.setFont(new Font("DejaVu Sans", Font.BOLD, 18));
		top.add(topMenuBar);
		Component horizontalStrut = Box.createHorizontalStrut(20);
		top.add(horizontalStrut);
		top.add(title);
		Component horizontalStrut_1 = Box.createHorizontalStrut(20);
		top.add(horizontalStrut_1);
		background.add(top,BorderLayout.NORTH);

	}

	/**
	 * Sets up the menu in the west area of the layout.
	 */
	public void setUpMenu(){
		ImagePanel leftMenu = new ImagePanel(new File("img/menu.png"));
		leftMenu.setLayout(new FlowLayout(FlowLayout.CENTER,2,2));		
		leftMenu.setPreferredSize(new Dimension(frameSize.width/5, frameSize.height-50));
		menuUp = new JPanel();
		menuUp.setLayout(new BoxLayout(menuUp, BoxLayout.Y_AXIS));
		menuUp.setOpaque(false);
		menuUp.setPreferredSize(new Dimension(frameSize.width/5,frameSize.height*3/5));
		menuUp.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.RAISED));
		menuLow = new JPanel();
		menuLow.setLayout(new BoxLayout(menuLow, BoxLayout.Y_AXIS));
		menuLow.setOpaque(false);
		menuLow.setPreferredSize(new Dimension(frameSize.width/5,frameSize.height*4/5));
		menuLow.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.RAISED));
		leftMenu.add(menuUp);
		leftMenu.add(menuLow);
		background.add(leftMenu, BorderLayout.WEST);
	}

	/**
	 * Sets up the main area in the center of the layout.
	 */
	public void setUpMain(){
		map = new ImagePanel(new File("img/mappanel.png"));
		map.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 30));
		map.setPreferredSize(new Dimension(screenSize.width*5/6,360));
		map.setAlignmentY(JPanel.CENTER_ALIGNMENT);
		map.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.RAISED));
		JPanel outerMain = new JPanel(new FlowLayout(FlowLayout.CENTER,4,2));
		main = new ImagePanel(new File("img/mainmap.png"));
		main.setLayout(new FlowLayout(FlowLayout.CENTER,30,40));
		main.setPreferredSize(new Dimension(screenSize.width*5/6,screenSize.height*3/5));
		main.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.RAISED));
		outerMain.add(map);
		outerMain.add(main);
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
	 * Removes all components from all the panels.
	 */
	public void clearAll(){
		menuUp.removeAll();
		menuLow.removeAll();
		main.removeAll();
		map.removeAll();
		frame.repaint();
	}
	/**
	 * Updates the title of the slide
	 * @param slideTitle
	 */
	public void updateTitle(String slideTitle){
		title.setText(slideTitle);
		title.revalidate();
	}	

	/**
	 * Returns the frame used by this layouthandler.
	 * @return The frame used by this LayoutHandler.
	 */
	public JFrame getFrame(){
		return frame;
	}
} 