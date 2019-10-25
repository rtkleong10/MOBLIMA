package Controller;

import java.util.ArrayList;

import Model.*;

public class Main {
	public static void main(String[] args) {
		CineplexManager.initialize();
		PricingScheme pricingScheme = CineplexManager.getPricingScheme();
		//	pricingScheme.setBasePrice(12.0);
		//	CineplexManager.setPricingScheme(pricingScheme);

		System.out.println(pricingScheme.getBasePrice());

		CineplexManager.update();
		CineplexManager.initialize();
		System.out.println(pricingScheme.getBasePrice());
	}
}
