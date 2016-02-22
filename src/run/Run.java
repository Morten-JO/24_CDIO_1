package run;

import java.io.Console;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import controllers.OperatorDAO;
import controllers.PasswordHandler;
import data.OperatorDTO;
import exception.DALException;
import measure.MeasureHandler;


public class Run {

	private Scanner scanner;
	private OperatorDTO currentUser;
	private OperatorDAO oprDAO;
	private boolean exit;
	
	public Run() throws DALException{
		scanner = new Scanner(System.in);
		oprDAO = new OperatorDAO();
	}
	
	public void start() throws DALException{
		promptLogin();
		while(!exit){
			presentOptions();
			
			while(!handleOptionPick());
		}
		
		System.out.println("Program has been terminated..");

	}
	
	private void promptLogin(){
		currentUser = null;
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
	
	private void handleLogin(String cpr, String pass) {
		List<OperatorDTO> oprList;
		try {
			oprList = oprDAO.getOperatorList();
			for(OperatorDTO o : oprList){
				if(o.getCpr().equals(cpr) && o.getPassword().equals(pass))
					currentUser = o;
			}
		} catch (DALException e) {
			e.printStackTrace();
		}
		
	}
	
	private void presentOptions(){
		System.out.println("Use number menu to navigate:");
		
		if(currentUser.getCpr().equals("sysadmin")){
			System.out.println("1. Create new user");
			System.out.println("2. Show list of operators");
			System.out.println("3. Take measurement");
			System.out.println("4. Change user");
			System.out.println("5. Delete user");
			System.out.println("6. Exit");
		}else{
			System.out.println("1. Take measurement");
			System.out.println("2. Change password");
			System.out.println("3. Change user");
			System.out.println("4. Exit");
		}
	}
	
	//returns false if user entered a number not on list and will return
	private boolean handleOptionPick() throws DALException{
		//HACK TO CLEAR CONSOLE: for(int clear = 0; clear < 1000; clear++){ System.out.println("\b") ;} 
		int optionInt = 0;
		try{
			optionInt = scanner.nextInt();
		} catch(InputMismatchException e){
			System.out.println("Type a number corresponding to the menu numbers!");
			scanner.next();
		}
		if(currentUser.getCpr().equals("sysadmin")){
			if(optionInt==1)
				handleNewUser();
			else if (optionInt==2) {
				List<OperatorDTO> oprList = oprDAO.getOperatorList();
				for(int i = 0;i<oprList.size();i++){
					System.out.println(i+". CPR: "+oprList.get(i).getCpr()+", Password: "+oprList.get(i).getPassword()+ " ID: " +oprList.get(i).getID());
				}
			}else if (optionInt==3) {
				MeasureHandler.runMeasurement(scanner);
			}else if (optionInt==4) {
				promptLogin();
			}else if(optionInt==5){
				promptDeleteUser();
			}else if(optionInt==6){
				exit = true;
			}else{
				return false;
			}
		}else{
			if(optionInt==1){
				MeasureHandler.runMeasurement(scanner);
			}
			else if (optionInt==2) {
				//handle change password
				System.out.println("Auto-generated password suggestion: " + PasswordHandler.generatePassword());
				promptPasswordChange();
			}else if (optionInt==3) {
				promptLogin();
			}else if(optionInt==4){
				exit = true;
			}else{
				return false;
			}
		}
		return true;
		
	}
	
	private void promptPasswordChange(){
		System.out.println("Type your current password: ");
		String currPass = scanner.next();
		String newPass;
		if(currentUser.getPassword().equals(currPass)){
			System.out.println("Type new password: ");
			newPass = scanner.next();
			System.out.println("Type new password again: ");
			String passChecker = scanner.next();
			if(passChecker.equals(newPass)){
				if(PasswordHandler.changePassword(currentUser, currPass, newPass, passChecker)){
					System.out.println("Password has been changed!");
				}
				else{
					System.out.println("Password doesnt match standard, check: https://password.dtu.dk/");
				}
			
			}else{
				System.err.println("Passwords dont match!");
			}
		}else{
			System.err.println("Wrong password!");
		}
	}
	
	private void handleNewUser() throws DALException{
		OperatorDTO newOpr;
		
		String name;
		String cpr;
		
		System.out.println("Enter name: ");
		name = scanner.next();
		while(!(name.length() > 1 && name.length() < 21)){
			System.out.println("Name must be minimum 2 letters, and max 20 letters.");
			System.out.println("Enter name: ");
			name = scanner.next();
		}
		
		System.out.println("Enter CPR number: ");
		cpr = scanner.next();
		
		for (int j = 0; j < oprDAO.getOperatorList().size(); j++) {
			if (oprDAO.getOperatorList().get(j).getCpr().equals(cpr)){	
				System.err.println("This CPR already exists in the system! User not created.");
				return;
			}		
		}
		
		try {
			newOpr = new OperatorDTO(name, cpr);
			oprDAO.createOperator(newOpr);
		} catch (DALException e) {
			e.printStackTrace();
		}
		
		
		//initialize new opr obj
		
		
	
	}
	
	private void promptDeleteUser() throws DALException{
		List<OperatorDTO> oprList = oprDAO.getOperatorList();
		for(int i = 0;i<oprList.size();i++){
			System.out.println(i+". CPR: "+oprList.get(i).getCpr());
		}
		System.out.print("Type CPR of user you want to delete: ");
		String cpr = scanner.next();
		if(!cpr.equals(currentUser.getCpr())){
			for(int i = 0; i < oprList.size(); i++){
				if(cpr.equals(oprList.get(i).getCpr())){
					oprList.remove(i);
					System.out.println("User deleted!");
					return;
				}
			}
		}
		System.out.println("Cant delete that user!");
	}
	
}
