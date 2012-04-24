package travelPlanner;
import java.awt.Dimension;
import java.awt.Image;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

import sun.awt.image.URLImageSource;

public class MapLabel extends JLabel{
	Image image = null;

	/**
	 * 
	 * @param destinations
	 */
	public MapLabel(ArrayList<String> destinations){

		if(!buildProjectMap(destinations)){
			buildProjectMap(destinations);
		}	
	}	
	/**
	 * 
	 * @param destination
	 */
	public MapLabel(String destination){
		if(!buildDestinationMap(destination)){
			buildDestinationMap(destination);
		}

	}
	/**
	 * 
	 * @param destination
	 * @return
	 */
	public boolean buildDestinationMap(String destination){
		URL u = null; 
		try{
			URI uri = new URI("http", "maps.googleapis.com", "/maps/api/staticmap","center="  + destination + "&size=640x300&maptype=roadmap/&zoom=4&scale=1&markers=size:mid|color:red|"+destination+"&format=gif&sensor=false" , null);
			String url = uri.toASCIIString();
			u = new URL(url);
		}catch (MalformedURLException e) {
			System.out.println("Error!" + e );			//errorHandler show "karta kan inte visas".
		}catch (NumberFormatException e) {
			System.out.println("Error!" + e );
		}catch (URISyntaxException e) {
			System.out.println("Error!" + e );
		}			
		try{
			JFrame frame = new JFrame();		
			URLConnection con = u.openConnection();
			image = frame.getToolkit().createImage(new URLImageSource(u, con));
		}catch (IOException e){
			ErrorHandler.printError(e, this.getClass().toString());
		}
		super.setIcon(new ImageIcon(image));
		//error check
		if(this.getIcon() == null){
			return false;
		}
		return true;	
	}


	/**
	 * 
	 * @param destinations
	 * @return
	 */
	public boolean buildProjectMap(ArrayList<String> destinations){
		try{
			JFrame frame = new JFrame();
			URL u = buildURL(destinations);	
			URLConnection con = u.openConnection();
			image = frame.getToolkit().createImage(new URLImageSource(u, con));
		}catch (Exception e){
			ErrorHandler.printError(e, this.getClass().toString());
		}
		super.setIcon(new ImageIcon(image));
		//error check
		if(this.getIcon() == null){
			return false;
		}
		return true;

	}

	/**
	 * 
	 * @param destinations
	 * @return
	 */
	private URL buildURL(ArrayList<String> destinations){		
		StringBuilder sb = new StringBuilder();
		sb.append("size=640x300&maptype=roadmap/&path=");
		for(int i = 0; i< destinations.size(); i++){
			sb.append(destinations.get(i) + "|");
		}
		sb.setLength(sb.length()-1);
		sb.append("&markers=size:mid|color:red");
		for(int i = 0; i< destinations.size(); i++){
			sb.append("|" + destinations.get(i));
		}
		sb.append("&format=gif&sensor=false");
		try{
			URI uri = new URI("http", "maps.googleapis.com", "/maps/api/staticmap", sb.toString().replace(" ", "+"), null);
			String url = uri.toASCIIString();
			return new URL(url);
		}catch (MalformedURLException e) {
			System.out.println("Error!" + e );
		}catch (NumberFormatException e) {
			System.out.println("Error!" + e );
		}catch (URISyntaxException e) {
			System.out.println("Error!" + e );
		}
		return null;
	}

	public void reSize(Dimension mapPanelsize){
		Dimension imageSize = mapPanelsize;
		image=image.getScaledInstance(-1, (imageSize.height-15), Image.SCALE_FAST);		
	}
}
