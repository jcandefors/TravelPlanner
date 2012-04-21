package travelPlanner;

/**
 * This class check that a username is valid.
 * 
 * @author ragnhild
 *
 */

public class UserNameHandler {
	private static final int minimalLengthOfUsernamne = 5;
	private static final int maximalLengthOfUsername = 8;
	
	public UserNameHandler() {
	}
	public int getMinimalLengthOfUsername(){
		return minimalLengthOfUsernamne;
	}
	
	public int getMaximalLengthOfUsername(){
		return maximalLengthOfUsername;
	}
	
	public boolean validUsername(String username){
		if (username == null) {
			return false;
		}
		
		//Check if the password have the specified length

		if ((username.length() >= minimalLengthOfUsernamne)
				&& (username.length() <= maximalLengthOfUsername)) {
			return true;
		} 
		else {
			return false;

		}
	}
	

}
