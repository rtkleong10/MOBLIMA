package model;

import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 * This class contains utility methods for serialisation
 */
public class Serialization {
	/**
	 * This method reads the serialized object from the file 
	 * @param fileName the name of file containing the serialized object to be read
	 * @return the serialized object
	 */
	public static Object readSerializedObject(String fileName) {
		Object obj = null;
		
		try {
			FileInputStream file = new FileInputStream(fileName);
			ObjectInputStream in = new ObjectInputStream(file);
			
			obj = in.readObject();
			
			in.close();
			file.close();
			
		} catch (EOFException e) {
            return null;
            
        } catch (IOException ex) {
			ex.printStackTrace();
			
		} catch (ClassNotFoundException ex) {
			ex.printStackTrace();
		}
		
		return obj;
	}
	
	/**
	 * This method writes the serialized object to the file 
	 * @param fileName the name of the file to write the serialized object to
	 * @param obj the serialized object 
	 */
	public static void writeSerializedObject(String fileName, Object obj) {
		try {
			FileOutputStream file = new FileOutputStream(fileName);
			ObjectOutputStream out = new ObjectOutputStream(file);
			
			out.writeObject(obj);
			
			out.close();
			file.close();
			
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}
}
