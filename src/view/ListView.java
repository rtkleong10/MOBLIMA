package view;

import java.util.ArrayList;
import java.util.List;

import model.LabelledItem;

/**
 * This class handles the display of a list of items
 */
public class ListView {
	/**
	 * This method displays a list of strings
	 * @param title the title of the list
	 * @param stringList the strings to be displayed as a list
	 * @param emptyString the string to display if the list is empty
	 */
	public static void displayList(String title, List<String> stringList, String emptyString) {
		IOController.displayMessage("");
		
		IOController.displayTitle(title);
		
		if (stringList.size() == 0) {
			IOController.displayMessage(emptyString);
			
		} else {
			
			for (String string: stringList) {
				IOController.displayMessage(string);
			}
		}
		
		IOController.pressEnterToContinue();
		IOController.displayMessage("");
	}
	
	/**
	 * This method displays a list of {@code LabelledItem} objects
	 * @param <T> a class that implements the {@code LabelledItem} interface
	 * @param title the title of the list
	 * @param labelledItemList the list of items of type {@code T}
	 * @param emptyString the string to display if the list is empty
	 */
	public static <T extends LabelledItem> void displayLabelledItemList(String title, List<T> labelledItemList, String emptyString) {
		List<String> stringList = new ArrayList<String>();
		
		for (LabelledItem item: labelledItemList)
			stringList.add(item.getLabel());
		
		displayList(title, stringList, emptyString);
	}
}
