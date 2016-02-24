package measure;

import java.util.Random;
import java.util.Scanner;

public class MeasureHandler {
	
	public float takeNewMeasure(){
		Random rnd = new Random();
		int rndWeight = rnd.nextInt(10000)+50;
		return rndWeight;
	}
	
	public float getNetto(float brutto, float tara){
		return brutto-tara;
	}
	
	public static void runMeasurement(Scanner scanner){
		System.out.println();
		System.out.println("Welcome to Measurement Application");
		boolean isRunning = true;
		MeasureHandler measurer = new MeasureHandler();
		while(isRunning){
			System.out.println("1. Take measurement");
			System.out.println("2. Exit measurement");
			try{
				int choice = scanner.nextInt();
				if(choice == 1){
					System.out.print("Type tara in kilograms: ");
					float tara = scanner.nextFloat();
					System.out.println();
					System.out.print("Type brutto in kilograms: ");
					float brutto = scanner.nextFloat();
					System.out.println();
					System.out.println("Netto weight is: "+measurer.getNetto(brutto, tara)+" kg.");
				}
				else{
					System.out.println("Exiting measurement");
					isRunning = false;
				}
			} catch(Exception e){
				System.out.println("Error!");
			}
		}
	}
}