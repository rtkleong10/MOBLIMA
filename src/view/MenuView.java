package view;

import java.util.List;

import model.LabelledItem;

/**
 * This class handles the menu display and input
 */
public class MenuView {
	
	/**
	 * This method display the numbered list of options and prompts the user choose an option. The numbering starts from 1.
	 * @param title the title of the menu
	 * @param options the options
	 * @return the selected option
	 */
	public static int getMenuOption(String title, String... options) {
		IOController.displayMessage("");
		IOController.displayTitle(title);
		
		for (int i = 0; i < options.length; i++) {
			IOController.displayMessage((i + 1) + ": " + options[i]);
		}
		
		int option;
		
		while (true) {
			option = IOController.readInt("Option: ");
			
			if (option >= 1 && option <= options.length)
				break;
			else
				IOController.displayMessage("Invalid option selected!");
		}
		
		IOController.displayMessage("");
		
		return option;
	}
	
	/**
	 * This method display the numbered list of options and prompts the user choose an option. The numbering starts from 1.
	 * @param <T> a class that implements the {@code LabelledItem} interface
	 * @param title the title of the menu
	 * @param labelledItems the options of type {@code T}
	 * @return the selected {@code T} object
	 */
	public static <T extends LabelledItem> T getLabelledItem(String title, T[] labelledItems) {
		String[] options = new String[labelledItems.length];
		
		for (int i = 0; i < labelledItems.length; i++)
			options[i] = labelledItems[i].getLabel();
		
		int option = getMenuOption(title, options);
		
		return labelledItems[option - 1];
	}
	
	/**
	 * This method display the numbered list of options and prompts the user choose an option. The numbering starts from 1.
	 * @param <T> a class that implements the {@code LabelledItem} interface
	 * @param title the title of the menu
	 * @param labelledItems the options of type {@code T}
	 * @return the selected {@code T} object
	 */
	public static <T extends LabelledItem> T getLabelledItem(String title, List<T> labelledItems) {
		int size = labelledItems.size();
		String[] options = new String[size];
		
		int i = 0;
		
		for (T labelledItem: labelledItems) {
			options[i] = labelledItem.getLabel();
			i++;
		}
		
		int option = getMenuOption(title, options);
		
		return labelledItems.get(option - 1);
	}
}
