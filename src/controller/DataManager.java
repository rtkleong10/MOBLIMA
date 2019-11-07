package controller;

import java.io.*;

public abstract class DataManager {
	protected static Object readSerializedObject(String fileName) {
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

	protected static void writeSerializedObject(String fileName, Object obj) {
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
