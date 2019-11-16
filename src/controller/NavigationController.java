package controller;

import java.util.Stack;

/**
 * This method controls the navigation between the controller classes
 */
public class NavigationController {
	/**
	 * A stack containing all the controller class objects. The top contains the currently active controller class object. As you go down the stack, you go to the previous controller class objects.
	 */
	private static Stack<Controller> stack = new Stack<Controller>();
	
	/**
	 * This method loads the controller class by pushing it to the stack and calling its start method
	 * @param controller the controller class to be loaded
	 */
	public static void load(Controller controller) {
		stack.push(controller);
		controller.start();
	}
	
	/**
	 * This method goes back by one level.
	 */
	public static void goBack() {
		goBack(1);
	}
	
	/**
	 * This method goes back by the given number of levels
	 * @param level the number of levels to go back
	 */
	public static void goBack(int level) {
		Controller controller = null;
		
		// Pop off the current controller too
		level++;

		if (level <= stack.size()) {
			for (int i = 0; i < level; i++)
				controller = stack.pop();
			
			load(controller);
		}
	}
}
