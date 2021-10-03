//CS-499 Category One - Driver
//Elijah Hickey
//9.13.21
import java.util.ArrayList;
import java.util.Scanner;
import java.time.format.DateTimeFormatter;  
import java.time.LocalDateTime;    
import java.io.IOException;

public class driver {
	
    private static ArrayList<Client> clientList = new ArrayList();
    private static ArrayList<User> userList = new ArrayList();

	
    //Function to add a mock list to the client arraylist
    //Will later incorporate a database instead of a mock list
	public static void initializeClientList() {
		Client newClient1 = new Client(1, "Bob Jones", 1);
		clientList.add(newClient1);
		
		Client newClient2 = new Client(2, "Sarah Davis", 2);
		clientList.add(newClient2);
		
		Client newClient3 = new Client(3, "Amy Friendly", 1);
		clientList.add(newClient3);
		
		Client newClient4 = new Client(4, "Johnny Smith", 1);
		clientList.add(newClient4);
		
		Client newClient5 = new Client(5, "Carol Spears", 2);
		clientList.add(newClient5);
	}
	
	//like the client list, adds a mock user list to the user array list
	//Will also later be incorporated to the database
	public static void initializeUserList() {
		User newUser1 = new User("Elijah", "Hickey", "snhuEhick", 4255);
		userList.add(newUser1);
		
		User newUser2 = new User("Bob", "Ross", "HappyClouds", 200);
		userList.add(newUser2);
		
		User newUser3 = new User("Jane", "Doe", "unknown person", 0);
		userList.add(newUser3);
	}
	
    
    //Method to ensure that both the username & password match a username/password found within the userList
	public static boolean CheckUserPermissionAccess() {
		boolean match = false;
		boolean partMatch = false;
		String username;
		int password = 0;
    	Scanner scnr = new Scanner(System.in);
    	

		
		System.out.println("Enter your username");
		username = scnr.nextLine();

		//validates data type and size to prevent buffer overflow
		do {
			System.out.println("Enter your password");
		    while(!scnr.hasNextInt()) {
		    	System.out.println("Passwords are integer only! Try again.");
		    	scnr.next();	
		    }
		    password = scnr.nextInt();
		} while(password < 0 || password >= 2000000);
		

	
		//will pass to login record the type of login (sucessful, partial or none,) and the username/password to be logged
		//Currently could have some duplicate entries sent to login recording if there are different users with the same username (however this should never be allowed)
		for(int i = 0; i < userList.size(); i++) {
			if(  (username.equals(userList.get(i).getUserUsername()))  && password == userList.get(i).getUserPassword()) {
				//This prints the matching usernames and passwords, used for testing
				
				/*
				 * 				System.out.println("UN: " + username + " matches DB UN: " + userList.get(i).getUserUsername()
				+ "\r\nPWD: " + password + " matches DB PWD: " + userList.get(i).getUserPassword());
				 */

				match = true;
				try {
					loginRecorder("Successful",username, password);
				} catch (IOException e) {
					System.out.println(e.getMessage());
				}
				
				
				
			} else if(  (username.equals(userList.get(i).getUserUsername()))  && password != userList.get(i).getUserPassword()) {
				
				//This also prints a message, stating the matched usernames, but the mismatched passwords, used for testing
				/*
				 * 				System.out.println("UN: " + username + " matches DB UN: " + userList.get(i).getUserUsername()
				+ "\r\nPWD: " + password + " DOES NOT match DB PWD: " + userList.get(i).getUserPassword());
				
				 */

				
				partMatch = true;
				try {
					loginRecorder("Partial",username, password);
				} catch (IOException e) {
					System.out.println(e.getMessage());
				}
				
			}
		}
		if(match != true && partMatch != true) {
			try {
				loginRecorder("None",username, password);
			} catch (IOException e) {
				System.out.println(e.getMessage());
			}
		}
		
		return match;
		
	}
	
	//records logins by type to a file, documenting if the login failed or succeeded, and what username and password was used.
	//Will create the text file if not made, if not will simply add to it
	//Obviously, a full application should never record login info like this, was done as a proof of concept 
	public static void loginRecorder(String recordType, String usr, int pwd) throws IOException {
		String fileName = "LoginRecorderDoc.txt";
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");  
		LocalDateTime now = LocalDateTime.now();  
		String type = recordType;
		
		String cens = "*****"; //censored pwd should also not indicated the length, so fixed length here
		
		
		//used pwd if wanting to display full password, use cens if wanting a censored password
		
		//Currently will only censor the password found within a successful login
		if(type== "Successful") {
			try {
				WriteToFile data = new WriteToFile( fileName , true );
				data.writeToFile("Successful Login by user: " + usr + " with password: " + cens + " at " + dtf.format(now));
			}
			catch(IOException e) {
				System.out.println(e.getMessage());
			}
		} else if (type == "Partial") {
			try {
				WriteToFile data = new WriteToFile( fileName , true );
				data.writeToFile("Failed Login (Incorrect Password) User: " + usr + " with password: " + pwd + " at " + dtf.format(now));
			}
			catch(IOException e) {
				System.out.println(e.getMessage());
			}
		} else if (type == "None") {
			try {
				WriteToFile data = new WriteToFile( fileName , true );
				data.writeToFile("Failed Login (Incorrect Username) User: " + usr + " with password: " + pwd + " at " + dtf.format(now));
			}
			catch(IOException e) {
				System.out.println(e.getMessage());
			}
			
		} else {
			System.out.println("Login Record System Failure. Contact Admin");
			//System.exit(0);
		}
		
	

	}
	
	


	//method to simply print all clients from the client array list
	public static void DisplayInfo() {
		System.out.println("\r\n   Client's Name     Service Selected ( 1 = Brokerage, 2 = Retirement)");
		
		if (clientList.size() < 1) {
			System.out.println("\nThere are no clients to display.");
			return;
		}
		for(Client eachClient: clientList)
			eachClient.printClientData();
		
	}
	
	//method that will ask the user for the clients number, then the clients new choice. 
	//Will validate for proper data type and ensure its within the expected range.
	public static void ChangeCustomerChoice() {
		
    	Scanner scnr = new Scanner(System.in);
		int changechoice, newservice, cycled;
		cycled = 0;
		changechoice = 0;
		newservice = 0;
		
		System.out.println("Enter the number of the client that you wish to change");
		
		do {
    		if( (changechoice < 1 || changechoice > clientList.size()) && cycled != 0) {
    			System.out.println("That client does not exist. Try again.");
    		}
		    while(!scnr.hasNextInt()) {
		    	System.out.println("Client change options are integers only");
		    	scnr.next();	
		    }
    		cycled = 1;
    		changechoice = scnr.nextInt();
		} while(changechoice < 1 || changechoice > clientList.size());
		
		System.out.println("Enter the client's new service choice (1 = Brokerage, 2 = Retirement)");
		
		cycled = 0;
		
		do {
			if((newservice < 1 || newservice > 2) && cycled != 0) {
				System.out.println("Client choice can only be 1 or 2 (1 = Brokerage, 2 = Retirement)\r\nTry again.");

			}
			
			while(!scnr.hasNextInt()) {
				System.out.println("Client choice are integer only, Try again.");
				scnr.next();
			}
			cycled = 1;
			newservice = scnr.nextInt();
		} while(newservice < 1 || newservice > 2);
		

		clientList.get(changechoice-1).setClientServ(newservice);
		
		
	}

	
	
	public static void main(String[] args) {
		
    	Scanner scnr = new Scanner(System.in);
		int choice;
		boolean unPwdCheck;
		int cycled = 0;
		initializeClientList();
		initializeUserList();
		System.out.println(" -------------------------------------------------------------------"
				+ "\r\n|       CS-499, Category One -- Software Engineering & Design       |"
				+ "\r\n|                    Created by Elijah Hickey                       |"
				+ "\r\n -------------------------------------------------------------------\r\n");
		
    	System.out.println("Hello! Welcome to our investment Company\n");
    	
    	unPwdCheck = CheckUserPermissionAccess();
    	
    	while(unPwdCheck != true) {
    		System.out.println("Invalid Username/Password. Please try again");
    		unPwdCheck = CheckUserPermissionAccess();
    	}
    	
    	choice = 0;
    	
    	
    	//looping menu system, will either call the users choice, exit program, or prompt user to enter a valid entry
    	while(choice != 3) {
    		
    		System.out.println("\r\nWhat would you like to do?"
    				+ "\r\nDISPLAY the client list (enter 1)"
    				+ "\r\nChange a client's choice (enter 2)"
    				+ "\r\nExit the program.. (enter 3)");
    		
    		
    		do {
        		if((choice < 1 || choice > 3) && cycled != 0) {
        			System.out.println("Menu options cannot be less than 1 or greater than 3. Try again.");
        		}
    		    while(!scnr.hasNextInt()) {
    		    	System.out.println("Menu options are integers only");
    		    	scnr.next();	
    		    }
	    		cycled = 1;
    		    choice = scnr.nextInt();
    		} while(choice < 1 || choice > 3);
    		
    		
    		System.out.println("\r\nYou chose " + choice );
    		
    		if(choice == 1) {
    			DisplayInfo();
    		}
    		else if (choice == 2) {
    			ChangeCustomerChoice();
    			
    		}
    		
    	}
    	
		scnr.close();
    	System.out.println("Goodbye");
    	return;
    }

}


