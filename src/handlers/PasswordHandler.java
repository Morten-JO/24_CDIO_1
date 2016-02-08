package handlers;

/**
 * Date: 08/02/2016
 *
 * Project: 24_CDIO_1
 *
 * File: PasswordHandler.java
 *
 * Created by: Morten Jørvad
 *
 * Github: https://github.com/Mortenbaws
 *
 * Email: morten2094@gmail.com
 */

public class PasswordHandler {

	public boolean checkValidPassword(String str, String username) {
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
					System.out.println("User is fine");
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
