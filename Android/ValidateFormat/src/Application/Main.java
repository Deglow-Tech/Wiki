package Application;

import Entities.SignalManager;

public class Main {

	public static void main(String[] args) {
		//Example for information from Band
		String input = "100;95;22.5";
		SignalManager.validFormatFromBand(input);
		
		//Example for information from Baby girdle
		input = "100;0";
		SignalManager.validFormatFromBabyGirdle(input);
	}

}
