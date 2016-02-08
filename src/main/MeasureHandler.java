package main;

import java.util.Random;

public class MeasureHandler {
	
	public float takeNewMeasure(){
		Random rnd = new Random();
		int rndWeight = rnd.nextInt(10000)+50;
		return rndWeight;
	}

}
