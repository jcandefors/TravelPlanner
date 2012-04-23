package travelPlanner;


import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

/**
 * UserRegisterReaderWriter is responsible for writing and reading usernames and
 * responding password codes to the programs user register
 * 
 * @author Ragnhild
 * 
 */

public class UserRegisterReaderWriter {
	private ArrayList<UserData> userRegister;

	public UserRegisterReaderWriter() {

	}
	

	/**
	 * Check if the specified user exist in the userRegister and return the
	 * users password code. Return 0 if the user does not exist in the
	 * userregister
	 * 
	 * 
	 */

	public int getPassWordCode(String username) {// TODO
		readInUserRegister();

		boolean foundMatch = false;
		UserData match =  new UserData("deafault", 0); //An object that never should be read  

		int passWordCode = 0;
		int index = 0;

		if (userRegister.isEmpty()) {
			return passWordCode;
		}

		while (index < userRegister.size() && !foundMatch) {
			match = userRegister.get(index);
			if (match.getUsername().equals(username)) {
				foundMatch = true;
			} else {
				index++;
			}
		}

		if (foundMatch) {
		 passWordCode = match.getPasswordCode();
			return passWordCode;
		} else {
			return passWordCode;
		}
	}

	/**
	 * Return true if the specified user never have logged in to the program before
	 * return false if the user have logged in before or does not exist in the register
	 */
	public boolean getFirstTimeStatus(String username){
	UserData match = getUserData(username);
	if(match == null){
	return false;
	}
	else{
	return match.getfirstTime();
	}
	}
	
	/**
	 * Return true if the user exist in the register otherwise false
	 * @param username
	 * @return result
	 */
	public boolean setFirstTimeStatusToFalse(String username){
		readInUserRegister();
		
		boolean foundMatch = false;
		
		int index = 0;

		while (index < userRegister.size() && !foundMatch) {
			
			if (userRegister.get(index).getUsername().equals(username)) {
				
				userRegister.get(index).setFirstTime(false);
				
				foundMatch = true;
			
			} else {
				index++;
			}
		}
		if(foundMatch){
			saveUserRegister();
			return true;		
				
		}
		else{
		return false;
		}
		

		}

	
	/**
	 * Return true if the specified username exists in the userregister
	 * 
	 * @param username
	 * @return
	 */

	public boolean containUserName(String username) {
	if(getUserData(username) == null){
		return false;
	}
	else{
	
		return true;
	
	}
	}
	

	/**
	 * Check if the user register contain the specified username, if not, the
	 * user and password code is saved in the user register. Return true if the
	 * user was successfully added to the user register otherwise false.
	 * 
	 * @throws IOException
	 *             if the userregister could not be opened, written to, read or
	 *             closed properly
	 */
	public boolean addUser(String username, int passwordcode){
		readInUserRegister();
		
		if(containUserName(username)){
			return false;
		}
		
		else{
		UserData newUser = new UserData(username, passwordcode);
		userRegister.add(newUser);
		saveUserRegister();	
		return true;
		}
		
	}

	// /**
	// * Check if the user register contains the specified username, if so, the
	// username and responding password
	// * code is removed from the user register.
	// * Return true if the user was succesfully removed from the userregister
	// otherwice false.
	// *
	// * @throws IOException if the userregister could not be opened, written
	// to, read or closed properly
	// *
	// */
	// public boolean removeUser(String username){
	// return true;
	//
	//
	// }

	// A method for reading in userRegister.data

	
	private void readInUserRegister() {
		try {
			
			userRegister = (ArrayList<UserData>) ObjectIO.loadObject("topSecret",
					"userRegister");
		}
		catch(FileNotFoundException e){
			//User register have never been activated create a new register with the testUser
			//username: kalleAnka
			//password: filur123
			
			String password = "filur123";
			
			int passwordCode = password.hashCode();
			UserData testUser = new UserData("kalleAnka", passwordCode);
			
			userRegister = new ArrayList<UserData>();
			
			userRegister.add(testUser);
			try{
				new File("data/topSecret").mkdirs();
				ObjectIO.saveObject(userRegister, "topSecret", "userRegister");
			}
			catch (IOException ex) {
				ErrorHandler.printError(ex, this.getClass().toString());
			}	
			
		}
		catch (ClassNotFoundException e) {
								
			ErrorHandler.printError(e, this.getClass().toString());
		} 
		catch (IOException e) {
			ErrorHandler.printError(e, this.getClass().toString());
		}
	}
	
	//A method for overwrite existing data in userRegister.data
	
	private void saveUserRegister(){
		try {
			ObjectIO.saveObject(userRegister, "topSecret", "userRegister");
		}
		 catch (IOException e) {
				ErrorHandler.printError(e, this.getClass().toString());
			}		
	}
	

    // A method for find and return a userdata from userRegister
	//return null if no user data with the specified username exists in the register.
	
	private UserData getUserData(String username){
		readInUserRegister();
		boolean foundMatch = false;
		UserData match = null;
		int index = 0;

		while (index < userRegister.size() && !foundMatch) {
			match = userRegister.get(index);
			if (match.getUsername().equals(username)) {
				foundMatch = true;
			} else {
				index++;
			}
		}
		if(foundMatch){

		return match;
		}
		else{
		match = null;
		return match;
		}
		}
	
	}

