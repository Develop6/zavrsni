package net.util;

import java.util.Random;

public class Utils {

	public static Integer generateRandomNumber(int min, int max) {
		
		Random rand = new Random();

	    // nextInt is normally exclusive of the top value,
	    // so add 1 to make it inclusive
	    int randomNum = rand.nextInt((max - min) + 1) + min;		
		
		return randomNum;
	}
}
