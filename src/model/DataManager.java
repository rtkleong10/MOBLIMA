package model;

/**
 * This class manages the data object of the application
 */
public class DataManager {
	
	/**
	 * The filename of the moblima data
	 */
	private static final String FILENAME = "res/data/moblima.dat";
	/**
	 * The data store
	 */
	private static DataStore dataStore;
	
	/**
	 * Creates a {@DataManager} object
	 */
	private DataManager() {}
	
	/**
	 * This method returns the data store
	 * @return the data store
	 */
	public static DataStore getDataStore() {
		return dataStore;
	}
	
	/**
	 * This method initialises an empty data store
	 */
	public static void initialise() {
		dataStore = new DataStore();
	}
	
	/**
	 * This method loads the data store from the file if possible. Otherwise, it initialises an empty data store.
	 */
	public static void load() {
		Object obj = Serialization.readSerializedObject(FILENAME);
		
		if (obj == null || !(obj instanceof DataStore))
			initialise();
		else
			dataStore = (DataStore) obj;
	}
	
	/**
	 * This method writes the data store to the file to store the updated data.
	 */
	public static void update() {
		Serialization.writeSerializedObject(FILENAME, dataStore);
	}
}
