package controller;

import model.DataManager;
import model.PricingScheme;
import view.MenuView;
import view.PricingSchemeEditView;

public class PricingSchemeEditController implements Controller {

	@Override
	public void start() {
		
		PricingScheme pricingScheme = DataManager.getDataStore().getPricingScheme();
		
		while (true) {
			int option = MenuView.getMenuOption(
				"What would you like to do?",
				"Modify base price",
				"Modify multipliers",
				"Modify holiday list",
				"Exit"
			);
			
			switch (option) {
				case 1:
					PricingSchemeEditView.updateBasePrice(pricingScheme);
					break;
					
				case 2:
					PricingSchemeEditView.updateMultipliers(pricingScheme);
					break;
					
				case 3:
					PricingSchemeEditView.updateHolidays(pricingScheme);
					break;
					
				case 4:
					NavigationController.goBack();
					return;
			}
		}
	}	            		
}
