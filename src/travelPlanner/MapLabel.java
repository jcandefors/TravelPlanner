package travelPlanner;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.SocketTimeoutException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

import sun.awt.image.GifImageDecoder;
import sun.awt.image.URLImageSource;

public class MapLabel extends JLabel{
	private Image mapImage = null;
	private final int TIMEOUT = 1000;
	private String hometown;

	/**
	 * 
	 * @param destinations
	 */
	public MapLabel(String hometown, ArrayList<String> destinations){
		this.hometown = hometown;
		if(!buildProjectMap( destinations)){
			loadNoMap();
		}	
	}	

	/**
	 * 
	 * @param destination
	 */
	public MapLabel(String destination){

		if(!buildDestinationMap(destination)){
			loadNoMap();
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
	 * 
	 * @param destinations
	 * @return
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
	 * 
	 * @param destinations
	 * @return
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
