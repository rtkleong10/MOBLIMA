package view;

import java.util.ArrayList;

import model.LabelledItem;

public class ListView {
	public static void displayList(String title, ArrayList<String> stringList, String emptyString) {
		System.out.println();
		
		System.out.println(title);
		IOController.displayLine(title.length());
		
		if (stringList.size() == 0) {
			System.out.println(emptyString);
			
		} else {
			
			for (String string: stringList) {
				System.out.println(string);
			}
		}
		
		IOController.pressEnterToContinue();
		System.out.println();
	}
	
	public static <T extends LabelledItem> void displayLabelledItemList(String title, ArrayList<T> labelledItemList, String emptyString) {
		ArrayList<String> stringList = new ArrayList<String>();
		
		for (LabelledItem item: labelledItemList)
			stringList.add(item.getLabel());
		
		displayList(title, stringList, emptyString);
	}
}
