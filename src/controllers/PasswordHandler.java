package controllers;

import java.util.Random;

import data.OperatorDTO;

import java.math.*;

public class PasswordHandler {
	public static String generatePassword(){
		//char container categories
		char[] lowcaseLetters = new char[] { 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o','p', 'q', 'r', 's', 't', 'u', 'v', 'x', 'y', 'z' };
		char[] uppercaseLetters = new char[] { 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N','O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'X', 'Y', 'Z' };
		char[] specialSigns = { '.', '-', '_', '+', '!', '?', '=' };
		char[] numbers = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9' };
		char[][] charCats = { lowcaseLetters, uppercaseLetters, specialSigns, numbers };

		Random rnd = new Random();

		// get random password length val between 6 and 10
		int passLength = rnd.nextInt(4) + 6;

		int charCat = 0;
		char[] passChars = new char[passLength];

		// temp use of charCat to hold cat to be skipped (to accomplish 3/4 cats
		// being used)
		charCat = rnd.nextInt(4);

		// random positioning of at least 3 chars from different cats
		for (int i = 0; i < 4; i++) {
			int currentElement = rnd.nextInt(passLength);
			if (!(i == charCat)){
				
				if(passChars[currentElement] == '\u0000')
					passChars[currentElement] = charCats[charCat][rnd.nextInt(charCats[charCat].length)];
				else{
					boolean didFindNullASCI = false;
					//search right (using this method to deal with theoretical endless loops and enforce unpredictability)
					for(int j = currentElement+1; j<passChars.length;j++){
						if(passChars[j] == '\u0000'){
							didFindNullASCI = true;
							passChars[currentElement] = charCats[charCat][rnd.nextInt(charCats[charCat].length)];
						}
					}
					//search left if still not found u0000
					if(!didFindNullASCI){
						for (int j = currentElement-1; j < 0; j--) {
							if(passChars[j] == '\u0000'){
								didFindNullASCI = true;
								passChars[currentElement] = charCats[charCat][rnd.nextInt(charCats[charCat].length)];
							}
						}
					}
				}
			}
		}
		// fill out rest of password with random chars
		for (int i = 0; i < passLength; i++) {
			if ((passChars[i] == '\u0000')) {
				charCat = rnd.nextInt(4);
				passChars[i] = charCats[charCat][rnd.nextInt(charCats[charCat].length)];
			}
		}

		// return the password converted to regular String obj
		return new String(passChars);
	}

	public static boolean changePassword(OperatorDTO operator, String currentPass, String newPass, String confirmPass) {
		if(currentPass.equals(operator.getPassword())){
			if(checkValidPassword(newPass, operator.getName())){
				if(newPass.equals(confirmPass)){
					operator.setPassword(newPass);
					return true;
				}
			}
		}
		return false;
	}
			
			
	public static boolean checkValidPassword(String str, String username) {
		boolean smallLetters = false;
		boolean bigLetters = false;
		boolean numbers = false;
		boolean specialLetters = false;
		int categoriesFullFilled = 0;
		int[] validCharSpecielNumbers = new int[]{46, 45, 95, 43, 33, 63, 61};
		if(str.length() >= 6){
			if(!str.equals(username)){
				for(int i = 0; i < str.length(); i++){
					if(str.charAt(i) >= 48 && str.charAt(i) <= 57){
						if(!numbers){
							numbers = true;
							categoriesFullFilled++;
						}
					}
					else if(str.charAt(i) >= 97 && str.charAt(i) <= 122){
						if(!smallLetters){
							smallLetters = true;
							categoriesFullFilled++;
						}
					}
					else if(str.charAt(i) >= 65 && str.charAt(i) <= 90){
						if(!bigLetters){
							bigLetters = true;
							categoriesFullFilled++;
						}
					}
					else{
						for(int x = 0; x < validCharSpecielNumbers.length; x++){
							if(str.charAt(i) == validCharSpecielNumbers[x]){
								if(!specialLetters){							
									specialLetters = true;
									categoriesFullFilled++;
								}
							}
						}
					}
				}
				if(categoriesFullFilled >= 3){
					System.out.println("Password has all requirements, and are considered okay.");
					return true;
				}
				else{
					System.out.println("Password isnt strong enough!");
				}
			}
		}
		else{
			System.out.println("Password must be over 6 letters");
		}
		return false;
	}
	

}
