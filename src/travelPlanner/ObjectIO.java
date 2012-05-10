package travelPlanner;


import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

/**
 * This class is handling serialized objects. It saves and loads files to/from the file system to the application TravelPlanner.
 */
public class ObjectIO {

	/**
	 * Loads the object into runtime TravelPlanner from a ".data"-file in the project folder in the file system.
	 * @param folder The name of the folder that holds the file.
	 * @param fileName The filename of the object to be loaded/restored.
	 * @return The object to be loaded into TravelPlanner
	 */
	public static Object loadObject(String folder, String fileName)throws IOException, ClassNotFoundException{

		// Read from disk using FileInputStream
		FileInputStream fileIn = new FileInputStream("data/"+folder + "/" + fileName + ".data");
		// Read object using ObjectInputStream
		ObjectInputStream object_in = new ObjectInputStream(fileIn);
		// Read an object
		Object returnObject = object_in.readObject();
		object_in.close();
		if (returnObject instanceof ArrayList<?> || returnObject instanceof String[] || returnObject instanceof String){
			return returnObject;
		}
		throw new IOException("Mismatch during load of object: " + fileName + ". The object cannot be loaded");
	}

	/**
	 * Saves the object as a ".data"-file in the project folder in the file system. 
	 * @param object The object that is to be saved to disk.
	 * @param folder The folder path to the file, subfolders are separated by "/". No need to include final "/".
	 * @param fileName The name of the Destination/TravelProject which is set to the filename.
	 */
	public static void saveObject(Object object, String folder, String filename)throws IOException{
			// Write to disk with FileOutputStream
			FileOutputStream fileout = new FileOutputStream("data/"+ folder + "/" + filename + ".data");

			// Write object with ObjectOutputStream
			ObjectOutputStream travelObjectOut = new ObjectOutputStream(fileout);
			
			// Write object out to disk
			travelObjectOut.writeObject(object);
			travelObjectOut.close();
	}

}


