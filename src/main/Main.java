package main;

import java.util.*;

public class Main {
	private static Scanner scanner = new Scanner(System.in);
	private static Operator currentUser = null;
	private static OperatorDAO oprDAO = new OperatorDAO();
	private static MeasureHandler measureHandler = new MeasureHandler();
	private static boolean exit = false;

	public static void main(String[] args) throws DALException {
		promptLogin();//contains a "while"
		
		while(!exit){
			presentOptions();
			
			while(!handleOptionPick());
			
				
		}
		
		System.out.println("Program has been terminated..");

	}
	
	private static void promptLogin(){
		String cpr, password;
		System.out.println("*** LOGIN *** \nEnter your cpr: ");
		cpr = scanner.next();
		System.out.println("Enter your password: ");
		password = scanner.next();
		
		handleLogin(cpr, password);
		
		while(currentUser == null){
			System.out.println("Wrong login, please try again!");
			System.out.println("Enter your cpr: ");
			cpr = scanner.next();
			System.out.println("Enter your password: ");
			password = scanner.next();
			
			handleLogin(cpr, password);	
		}
		
		
	}
	
	private static void handleLogin(String cpr, String pass) {
		List<Operator> oprList;
		try {
			oprList = oprDAO.getOperatorList();
			for(Operator o : oprList){
				if(o.getCpr().equals(cpr) && o.getPassword().equals(pass))
					currentUser = o;
			}
		} catch (DALException e) {
			e.printStackTrace();
		}
		
	}
	
	private static void presentOptions(){
		System.out.println("Use number menu to navigate:");
		
		if(currentUser.getCpr().equals("sysadmin")){
			System.out.println("1. Create new user");
			System.out.println("2. Show list of operators");
			System.out.println("3. Take measurement");
			System.out.println("4. Exit");
		}else{
			System.out.println("1. Take measurement");
			System.out.println("2. Change password");
			System.out.println("3. Exit");
		}
	}
	
	//returns false if user entered a number not on list and will return
	private static boolean handleOptionPick() throws DALException{
		//HACK TO CLEAR CONSOLE: for(int clear = 0; clear < 1000; clear++){ System.out.println("\b") ;} 
		int optionInt = scanner.nextInt();
		if(currentUser.getCpr().equals("sysadmin")){
			if(optionInt==1)
				handleNewUser();
			else if (optionInt==2) {
				List<Operator> oprList = oprDAO.getOperatorList();
				for(int i = 0;i<oprList.size();i++){
					System.out.println(i+". CPR: "+oprList.get(i).getCpr());
				}
			}else if (optionInt==3) {
				System.out.println("New measure: " + measureHandler.takeNewMeasure());
				
			}else if (optionInt==4) {
				exit = true;
			}else{
				return false;
			}
		}else{
			if(optionInt==1)
				System.out.println("New measure: " + measureHandler.takeNewMeasure());
			else if (optionInt==2) {
				//handle change password
				System.out.println("Auto-generated password suggestion: " + PasswordHandler.generatePassword());
				promptPasswordChange();
			}else if (optionInt==3) {
				exit = true;
			}else {
				return false;
			}
		}
		return true;
		
	}
	
	private static void promptPasswordChange(){
		System.out.println("Type your current password: ");
		String currPass = scanner.next();
		String newPass;
		if(currentUser.getPassword().equals(currPass)){
			System.out.println("Type new password: ");
			newPass = scanner.next();
			System.out.println("Type new password again: ");
			String passChecker = scanner.next();
			if(passChecker.equals(newPass)){
				currentUser.setPassword(newPass);
				System.out.println("Password has been changed!");
			
			}else{
				System.err.println("Passwords dont match!");
			}
		}else{
			System.err.println("Wrong password!");
		}
	}
	
	private static void handleNewUser(){
		Operator newOpr;
		
		String name;
		String cpr;
		
		System.out.println("Enter name: ");
		name = scanner.next();
		
		System.out.println("Enter CPR number: ");
		cpr = scanner.next();
		
		//parse cprString to int removing all non-numeric and checking length (other rules can be applied)
		//int will prolly be a prob due to possible zero-precedence => octal interpretation
		
		//initialize new opr obj
		newOpr = new Operator(name, cpr);
		
		try {
			oprDAO.createOperator(newOpr);
		} catch (DALException e) {
			e.printStackTrace();
		}
		
	}

}
