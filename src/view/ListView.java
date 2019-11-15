package view;

import java.util.ArrayList;
import java.util.List;

import model.LabelledItem;

public class ListView {
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
	
	public static <T extends LabelledItem> void displayLabelledItemList(String title, List<T> labelledItemList, String emptyString) {
		List<String> stringList = new ArrayList<String>();
		
		for (LabelledItem item: labelledItemList)
			stringList.add(item.getLabel());
		
		displayList(title, stringList, emptyString);
	}
}
