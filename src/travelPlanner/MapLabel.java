package travelPlanner;

import java.awt.Image;
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

/**
 * MapLabel is a JLabel with an icon of a image loaded from the GoogleMaps static API.
 * @author Joakim Candefors
 */
public class MapLabel extends JLabel{
	private Image mapImage = null;
	private final int TIMEOUT = 1000;
	private String hometown;
	private final String TOOLTIP = "Maps provided by Google.";

	/**
	 * Constructor of class MapLabel for project maps. Builds a URL and map with all the destinations in the project.
	 *@param destinations An array list of all the destinations in the travel project.
	 */
	public MapLabel(String hometown, ArrayList<String> destinations){
		super.setToolTipText(TOOLTIP);
		this.hometown = hometown;
		if(!buildProjectMap( destinations)){
			loadNoMap();
		}	
	}	

	/**
	 * Constructor of class MapLabel for destination maps. Creates a URL and map of the destination.
	 * @param destination The destination to be centered on the map.
	 */
	public MapLabel(String destination){
		super.setToolTipText(TOOLTIP);
		if(!buildDestinationMap(destination)){
			loadNoMap();
		}
	}

	/**
	 * Builds a destination map consisting of a single destination.
	 * @param destination The destination to be centered on the map.
	 * @return true if succeeded, else false.
	 */
	public boolean buildDestinationMap(String destination){
		URL u = null; 
		try{
			URI uri = new URI("http", "maps.googleapis.com", "/maps/api/staticmap","center="  + destination + "&size=640x300&maptype=roadmap/&zoom=4&scale=1&markers=size:mid|color:red|"+destination+"&format=gif&sensor=false" , null);
			String url = uri.toASCIIString();
			u = new URL(url);
		}catch (MalformedURLException e) {
			ErrorHandler.printError(e, this.getClass().toString());		
		}catch (NumberFormatException e) {
			ErrorHandler.printError(e, this.getClass().toString());
		}catch (URISyntaxException e) {
			ErrorHandler.printError(e, this.getClass().toString());
		}			
		try{
			JFrame frame = new JFrame();		
			URLConnection con = u.openConnection();
			con.setReadTimeout(TIMEOUT);									
			mapImage = frame.getToolkit().createImage(new URLImageSource(u, con));
		}catch (Exception e){
			ErrorHandler.printError(e, this.getClass().toString());
			return false;
		}
		super.setIcon(new ImageIcon(mapImage));
		return true;	
	}


	/**
	 * Builds a project map consisting of multiple destinations.
	 * @param destinations The destinations in the travelproject to placed on the map as a path.
	 * @return true if succeeded, else false.
	 */
	public boolean buildProjectMap(ArrayList<String> destinations){
		try{
			JFrame frame = new JFrame();
			URL u = buildURL(destinations);	
			URLConnection con = u.openConnection();
			con.setReadTimeout(TIMEOUT);								
			mapImage = frame.getToolkit().createImage(new URLImageSource(u, con));
		}catch (Exception e){	
			ErrorHandler.printError(e, this.getClass().toString());		
			return false;
		}
		super.setIcon(new ImageIcon(mapImage));
		return true;

	}

	/**
	 * Builds the URL with all the destinations as path-members and markers.
	 * @param destinations The destinations in the travel project to placed on the map as a path.
	 * @return The URL to be used to connect and get the image.
	 */
	private URL buildURL(ArrayList<String> destinations){		
		StringBuilder sb = new StringBuilder();
		sb.append("size=640x300&maptype=roadmap/&path=");
		if(hometown != null){ sb.append(hometown+"|");}
		for(int i = 0; i< destinations.size(); i++){
			sb.append(destinations.get(i) + "|");
		}
		if(hometown != null){ sb.append(hometown+"|");}
		sb.setLength(sb.length()-1);
		sb.append("&markers=size:mid|color:red");
		if(hometown != null){ sb.append("|" + hometown);}
		for(int i = 0; i< destinations.size(); i++){
			sb.append("|" + destinations.get(i));
		}
		sb.append("&format=gif&sensor=false");
		try{
			URI uri = new URI("http", "maps.googleapis.com", "/maps/api/staticmap", sb.toString().replace(" ", "+"), null);
			String url = uri.toASCIIString();
			return new URL(url);
		}catch (MalformedURLException e) {
			ErrorHandler.printError(e, this.getClass().toString());
		}catch (NumberFormatException e) {
			ErrorHandler.printError(e, this.getClass().toString());
		}catch (URISyntaxException e) {
			ErrorHandler.printError(e, this.getClass().toString());
		}
		return null;
	}
	
	/**
	 * Loads an error picture instead of the map.
	 */
	public void loadNoMap(){	
		super.setIcon(new ImageIcon("img/map.png"));	

	}
}
