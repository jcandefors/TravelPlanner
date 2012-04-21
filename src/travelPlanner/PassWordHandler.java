package travelPlanner;

/**
 * This class handle passwords for logging in to a program. It could be used for
 * checking if a chosen password have the specified length, create a code
 * for a password and validate if a password match a saved code.
 * 
 * @author ragnhild
 * 
 */
public class PassWordHandler {
	private static final int minimalLengthOfPassword = 5;
	private static final int maximalLengthOfPassword = 8;
//	public static final String allowedCharackter = " a_z, A-Z, 0-9";

	/**
	 * Create a instance of PasswordHandler
	 */
	public PassWordHandler() {

	}
	public int getMinimalLengthOfPassword(){
		return minimalLengthOfPassword;
	}
	
	public int getMaximalLengthOfPassword(){
		return maximalLengthOfPassword;
	}
	
//	public String getAllowedCharackter() {
//		return allowedCharackter;		
//	}


	/**
	 * Returns true for password match the criteria specified in the static field of this class.
	 * 
	 */
	public boolean validPassword(String password) {
		if (password == null) {
			return false;
		}
		
		//Check if the password have the specified length

		if ((password.length() >= minimalLengthOfPassword)
				&& (password.length() <= maximalLengthOfPassword)) {
			return true;
		} 
		else {
			return false;

		}
	}

	/**
	 * Return a code that match the specified password
	 */
	public int getHashCode(String password) {
		int hashcode = password.hashCode();
		return hashcode;

	}

	/**
	 * Return true if the specified password match the specified code 
	 */

	public boolean matchSavedPassword(String password, int code) {
		boolean match;
		match = false;
		if (password.hashCode() == code) {
			match = true;
		}
		return match;
	}
}
