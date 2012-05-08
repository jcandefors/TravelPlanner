package travelPlanner;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.event.KeyEvent;
import java.awt.event.WindowEvent;
import java.awt.event.WindowStateListener;
import java.io.File;
import javax.swing.*;
import javax.swing.border.EtchedBorder;

import com.sun.java.swing.plaf.motif.MotifBorders.BevelBorder;

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
	private JScrollPane scrollPane;
	private Container contentPane;
	private MapLabel mapLabel;
	private JPanel top;
	private JPanel leftMenu;
	private JPanel menuUp;
	private JPanel menuLow;
	private JPanel map;
	private JPanel main;
	private JLabel title;
	private Dimension frameSize;

	/**
	 * Constructor of LayoutHandler
	 * @param frame the main frame to be used to present on.
	 */
	public LayoutHandler(JFrame frame){
		this.frame = frame;
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frameSize = new Dimension(frame.getToolkit().getScreenSize().width*2/3,frame.getToolkit().getScreenSize().height*2/3);
		frame.setSize(frameSize);
		 frame.addComponentListener(new ComponentListener(){
		 @Override
		 public void componentResized(ComponentEvent event) {
		 Component resizedFrame = (Component) event.getSource();
		 frameSize = resizedFrame.getSize();
		 resizePanels();
		 }
		 @Override
		 public void componentHidden(ComponentEvent e) {}
		 @Override
		 public void componentMoved(ComponentEvent e) {}
		 @Override
		 public void componentShown(ComponentEvent e) {}
		 });
		 frame.addWindowStateListener(new WindowStateListener() {
		 @Override
		 public void windowStateChanged(WindowEvent event) {
		
		 if((event.getNewState() & JFrame.MAXIMIZED_BOTH) == JFrame.MAXIMIZED_BOTH){
		
		 Component resizedFrame = (Component) event.getSource();
		 resizedFrame.repaint();
		 frameSize = resizedFrame.getSize();
		 resizePanels();}
		 }
		
		 });
		contentPane = frame.getContentPane();
		background = new ImagePanel(new File("img/main.jpg"));

		background.setPreferredSize(frameSize);
		background.scaleImage(frameSize);
		background.setLayout(new BorderLayout(4,4));
		scrollPane = new JScrollPane(background);
		scrollPane.setViewportBorder(BorderFactory.createBevelBorder(1));
		scrollPane.setMinimumSize(frameSize);
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
		top = new JPanel(new GridLayout(0, 5, 0, 0));
		top.setBackground(Color.LIGHT_GRAY);
		JMenuBar topMenuBar = new JMenuBar();
		topMenuBar.setPreferredSize(new Dimension(40,20));
		topMenuBar.setBorder(null);
		topMenuBar.setBackground(Color.LIGHT_GRAY);
		JMenu topMenu = new JMenu("Meny");
		topMenu.setMnemonic(KeyEvent.VK_A);
		topMenuBar.add(topMenu);
		JMenuItem menuItem = new JMenuItem("Avsluta", KeyEvent.VK_F4);
		//TBC - needs action
		JMenuItem menuItem_1 = new JMenuItem("Logga ut");
		menuItem_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
				JFrame newFrame = new JFrame();
				newFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				newFrame.setLocationByPlatform(true);
				new LogInWindow(newFrame);
			}});
		topMenu.add(menuItem_1);
		menuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int answer = JOptionPane.showConfirmDialog(frame, "Vill du verkligen avsluta?", "Avsluta TravelPlanner", JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE);
				if (answer == 0){System.exit(0);}}});
		topMenu.add(menuItem);
		title = new JLabel();
		title.setHorizontalAlignment(SwingConstants.CENTER);
		title.setFont(new Font("DejaVu Sans", Font.PLAIN, 16));
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
		leftMenu = new JPanel(new FlowLayout(FlowLayout.CENTER,2,2));
		leftMenu.setPreferredSize(new Dimension(frameSize.width/6,frameSize.height-50));
		leftMenu.setOpaque(false);
		menuUp = new JPanel();
		menuUp.setLayout(new BoxLayout(menuUp, BoxLayout.Y_AXIS));
		menuUp.setOpaque(false);
		menuUp.setPreferredSize(new Dimension(frameSize.width/6,frameSize.height*2/5));
		menuUp.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.RAISED));
		menuLow = new JPanel();
		menuLow.setLayout(new BoxLayout(menuLow, BoxLayout.Y_AXIS));
		menuLow.setOpaque(false);
		menuLow.setPreferredSize(new Dimension(frameSize.width/6,frameSize.height*3/5));
		menuLow.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.RAISED));
		leftMenu.add(menuUp);
		leftMenu.add(menuLow);
		background.add(leftMenu, BorderLayout.WEST);
	}

	/**
	 * Sets up the main area in the center of the layout.
	 */
	public void setUpMain(){
		map = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 0)); //perhaps (layoutmgr, true) = double buffered - less flickering, more memory usage.
		map.setPreferredSize(new Dimension(frameSize.width*5/6,frameSize.height*2/5));
		map.setOpaque(false);
		map.setAlignmentY(map.CENTER_ALIGNMENT);
		map.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.RAISED));
		JPanel outerMain = new JPanel();
		outerMain.setLayout(new FlowLayout(FlowLayout.CENTER,2,2));
		outerMain.setOpaque(false);
		main = new JPanel();
		main.setLayout(new FlowLayout(FlowLayout.LEFT,10,10));
		main.setOpaque(false);
		main.setPreferredSize(new Dimension(frameSize.width*5/6,frameSize.height*3/5));
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
		if(component instanceof MapLabel){
			mapLabel = (MapLabel) component;
			mapLabel.resizeImage(map.getSize());
			map.add(component);
		}else{
			map.add(component);
		}
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

	public void resizePanels(){
		background.scaleImage(frameSize);
		background.setPreferredSize(new Dimension(frameSize.width, frameSize.height));
		leftMenu.setPreferredSize(new Dimension(frameSize.width/6,frameSize.height-50));
		menuUp.setPreferredSize(new Dimension(leftMenu.getWidth(),leftMenu.getHeight()/3));
		menuLow.setPreferredSize(new Dimension(leftMenu.getWidth(),leftMenu.getHeight()*2/3));
		main.setPreferredSize(new Dimension(frameSize.width*5/6,frameSize.height*2/3));
		map.setPreferredSize(new Dimension(frameSize.width*5/6,frameSize.height/3));
		mapLabel.resizeImage(map.getSize());
		frame.repaint();
	}
} 