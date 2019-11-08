package controller;

public class DataManager {
	
	private static final String FILENAME = "res/data/moblima.dat";
	private static DataStore dataStore;
	
	private DataManager() {}
	
	public static DataStore getDataStore() {
		return dataStore;
	}
	
	public static void initialise() {
		dataStore = new DataStore();
	}
	
	public static void load() {
		Object obj = Serialization.readSerializedObject(FILENAME);
		
		if (obj == null || !(obj instanceof DataStore))
			dataStore = new DataStore();
		else
			dataStore = (DataStore) obj;
	}
	
	public static void update() {
		Serialization.writeSerializedObject(FILENAME, dataStore);
	}
}
