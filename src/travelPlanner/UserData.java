package travelPlanner;

/**
 * This class represent the data that is saved about a user in the user register,
 * 
 * 
 * @author ragnhild
 *
 */

public class UserData {

	private final String username;
	private int passwordCode;
	
	
	/**
	 * Create a user with the specified username and passwordcode
	 * @param username
	 */
	public UserData(String username, int passwordCode){
		this.username = username;	
		this.passwordCode = passwordCode;
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
	
}
