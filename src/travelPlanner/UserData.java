package travelPlanner;
import java.io.Serializable;



/**
 * This class represent the data that is saved about a user in the user register,
 * 
 * 
 * @author ragnhild
 *
 */

public class UserData implements Serializable{

	
	private static final long serialVersionUID = -1082811726888267675L;
	private final String username;
	private int passwordCode;
	private boolean firstTime; //True iff the user never have logged in to the program 
	
	
	/**
	 * Create a user with the specified username and passwordcode
	 * @param username
	 */
	public UserData(String username, int passwordCode){
		this.username = username;	
		this.passwordCode = passwordCode;
		firstTime = true;
	}
	/**
	 * Change the password code to the specified code
	 */
	
	public void setPasswordCode(int passwordCode){
	this.passwordCode = passwordCode;
	}
	
	/**
	 * Return the users username
	 */
	
	public String getUsername(){
	return username;
	}
	/**
	 * Return the users password code
	 */
	
	public int getPasswordCode(){
	return passwordCode;
	}
	
	/**
	 * Return true iff the user never have logged in to the program otherwise false 
	 */
	
	public boolean getfirstTime(){
		return firstTime;
	}

	/**
	 * Set the status of first time
	 * The first time the user have logged in to the program set to false  
	 */
	
	public void setFirstTime(boolean firstTime){
	this.firstTime = firstTime;	
	}
}
