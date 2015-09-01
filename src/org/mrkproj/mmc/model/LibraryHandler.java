package org.mrkproj.mmc.model;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.file.Path;


/**
 * Class to handle saving and loading of a user's library of videos
 * 
 * @author Matt Kormann
 *
 */

public class LibraryHandler {

	public LibraryHandler() {
		
	}
	
	/**
	 * Save given array of Paths to disk under the given filename.
	 * 
	 * @param filename name of file to store path[] under
	 * @param library array of Paths referring to user's library
	 * @throws IOException
	 */
	public static void saveLibrary(String filename, Path[] library) throws IOException {
		ObjectOutputStream oos = null;
		try {
			oos = new ObjectOutputStream(new FileOutputStream(filename));
			oos.writeObject(library);
		}
		catch (IOException ioe) {
			//TODO
			System.err.println("Could not save library to disk.");
		}
		finally {
			if (oos != null) {
				oos.close();
			}
		}
	}
	
	/**
	 * Loads a user's library from disk from the given filename.
	 * 
	 * @param filename File to be loaded
	 * @return an array of Paths referring to user's library
	 * @throws IOException
	 */
	public static Path[] loadLibrary(String filename) throws IOException {
		ObjectInputStream ois = null;
		Path[] library = null;
		try {
			ois = new ObjectInputStream(new FileInputStream(filename));
			library = (Path[])ois.readObject();
		}
		catch (FileNotFoundException e) {
			//TODO
			System.err.println("Library with given name does not exist.");
			e.printStackTrace();
		}
		catch (IOException e) {
			//TODO
			System.err.println("Could not load library from disk.");
			e.printStackTrace();
		}
		catch (ClassNotFoundException e) {
			//TODO
			System.err.println("No such class found.");
			e.printStackTrace();
		}
		finally {
			if (ois != null) {
				ois.close();
			}
		}
		return library;
	}
	
}
