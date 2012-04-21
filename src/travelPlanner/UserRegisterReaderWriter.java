package travelPlanner;


import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

/**
 * UserRegisterReaderWriter is responsible for writing and reading
 * usernames and responding password codes to the programs user register
 * 
 * @author Ragnhild
 *
 */


public class UserRegisterReaderWriter {
private ArrayList<UserData> userRegister;



public UserRegisterReaderWriter(){

}

/**
 * Check if the specified user exist in the userRegister and
 * return the users password code.
 * Return 0 if the user does not exist in the userregister
 * @throws IOException if the userregister could not be opend, read och closed properly.  
 */

public int getPassWordCode(String username){//TODO
	boolean foundMatch = false;
	UserData match;
	
	int passWordCode = 0;
	int index = 0;
	
	if(userRegister.isEmpty()){
		return passWordCode;
	}
	
	while(index < userRegister.size() && ! foundMatch ){
		match =  userRegister.get(index);
	if(match.getUsername().equals(username)){
		foundMatch = true;
	}
	else{
	index ++;	
	}
	}
	
	if(foundMatch){
//		passWordCode = match.getPasswordCode();
	//TODO!
		
		return passWordCode;
	}
	else{
	return passWordCode;
	}	
}
/**
 * Return true if the specified username exists in the userregister
 * @param username
 * @return
 */
 
public boolean containUserName(String username){ 
	boolean foundMatch = false;
	UserData match;
	int index = 0;
	
	while(index < userRegister.size() && ! foundMatch ){
		match =  userRegister.get(index);
	if(match.getUsername().equals(username)){
		foundMatch = true;
	}
	else{
	index ++;	
	}
	}
	
	return foundMatch;
	}
	
/**
 * Check if the user register contain the specified username, if not, the user and password code is
 * saved in the user register. 
 * Return true if the user was successfully added to the user register otherwise false.
 * 
 * @throws IOException if the userregister could not be opened, written to, read or closed properly 
 */
 public boolean addUser(String username, int passwordcode){

	
	return true;
}
/**
 * Check if the user register contains the specified username, if so, the username and responding password
 * code is removed from the user register.
 * Return true if the user was succesfully removed from the userregister otherwice false.
 * 
 * @throws IOException if the userregister could not be opened, written to, read or closed properly 
 * 
 */
public boolean removeUser(String username){
return true;


}

// A method for reading userRegister.txt.

private  void readInUserRegister(){

}
}