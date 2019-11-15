package model;

import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class Serialization {
	/**
	 * Reads serialized object from the file 
	 * @param fileName name of file containing serialized object to be read
	 * @return serialized object
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
	 * Writes serialized object to file 
	 * @param fileName name of the file to write serialized object to
	 * @param obj serialized object 
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
