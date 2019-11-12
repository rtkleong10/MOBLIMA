package controller;

import java.util.Stack;

public class NavigationController {
	private static Stack<Controller> stack = new Stack<Controller>();
	
	public static void load(Controller controller) {
		stack.push(controller);
		controller.start();
	}
	
	public static void goBack() {
		goBack(2);
	}
	
	public static void goBack(int level) {
		Controller controller = null;
		
		if (level <= stack.size()) {
			for (int i = 0; i < level; i++)
				controller = stack.pop();
			
			load(controller);
		}
	}
}
