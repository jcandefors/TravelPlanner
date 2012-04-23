package travelPlanner;




/**
 * This class is responsible for execute all action in the LogIn program.
 * 
 * @author ragnhild
 * 
 */
public class LogInActionHandler {
	private UserRegisterReaderWriter userRegister;

	public LogInActionHandler() {
		userRegister = new UserRegisterReaderWriter();

	}

	/**
	 * Check if a user is allowed to logIn to the program by controlling if the
	 * specified username exist in the programs user register and compare the
	 * password with the password saved for that user. Return true iff the logIn
	 * was successful otherwise false.
	 * 
	 * @return
	 */
	public boolean logIn(String username, String password) {
		PassWordHandler passWordHandler = new PassWordHandler();
		int codeForRecievedPassword = passWordHandler.getHashCode(password);
		int savedPasswordCode = userRegister.getPassWordCode(username);

		if (codeForRecievedPassword == savedPasswordCode) {
			return true;
		} else {
			return false;
		}
	}
	/**
	 * Return true if the specified user never have logged in to the program before
	 * return false if the user have logged in before or does not exist in the register
	 */
	public boolean getIsFirstTime(String username){
		return userRegister.getFirstTimeStatus(username);
				
	}
	/**
	 * Return true if the user exist in the register otherwise false
	 * @param username
	 * @return result
	 */
	
	public boolean setFirstTimeStatusToFalse(String username){
	
		return userRegister.setFirstTimeStatusToFalse(username);
	}

	/**
	 * Save a new user with the specified username and password in to the
	 * programs user register.
	 * 
	 * @param userName
	 * 
	 * @return True if the program was able to create a new user with specified
	 *         username and password
	 */

	public boolean createNewUser(String username, String password) {
		//Check that userName and password are valid
		UserNameHandler uNH = new UserNameHandler();
		PassWordHandler pH = new PassWordHandler();
		
		if(!(uNH.validUsername(username))){
		return false;	
		}
		
		if(!(pH.validPassword(password))){
		return false;
		}
		
		//Valid input
		int passwordCode = pH.getHashCode(password);
		
		boolean result = userRegister.addUser(username, passwordCode);
		
		return result;
		}
		
}
		