package travelPlanner;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


import javax.print.attribute.standard.Destination;
import javax.swing.JButton;


public class DestinationButton extends JButton{
	private LayoutHandler layoutHandler;
	

	public DestinationButton(LayoutHandler layoutHandler, String title){
		
		super(title);
		this.layoutHandler = layoutHandler;
		super.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) { openDestination();	} });	
		
	}
	
	public void openDestination(){
		new travelPlanner.Destination(layoutHandler, super.getText());		
	}
}
