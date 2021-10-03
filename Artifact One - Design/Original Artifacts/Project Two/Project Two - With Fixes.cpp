//============================================================================
// Name        : Project Two - With Fixes.cpp
// Author      : Elijah Hickey
// CS-410
// 7-2 Project Two
// 8/14/2021
//============================================================================



#include <iostream>
#include <string>
#include<limits>
using namespace std;

//SECURITY RISKS: FIXED
//Global non-constant variables are used. 
//This is a security risk since any function has access and can alter them, in a more complicated system it because difficult to control this

//SECURITY FIX: Fixed security risk of having global variables



int CheckUserPermissionAccess() {
    //SECURITY RISKS: FIXED
    //This function did not have ANY input validation. Incorrect entries could cause overflow, lead to undefined behavior, or lead to an infinite loop.

    

    //While the username isnt ever used, it is still collected, so I stored it in a string
    //The orignal code will cause an error if there is a space present (it will take the entry after the space and apply it to password, unsure exactly how that is done)

    //A string for username will simply infinetly loop if a space is present (unless like above the entry after the space is 123)
    string username;
    int password;


    cout << "Enter your username: " << endl;

    cin >> username;
    //SECURITY FIX: Only first word is counted
    cin.ignore(numeric_limits<streamsize>::max(), '\n');



    cout << "Enter your password: " << endl;
    cin >> password;
    //SECURITY FIX: ensures entry is a int
    while (!cin) {
        cout << "Password is numeric only, try again." << endl;
        cin.clear();
        cin.ignore();
        cin >> password;
    }


    //passes password back to main
    return password;


}


//This simply takes the clients data and displays it
void DisplayInfo(string clients[][5]) {
    cout << "  Client's Name   Service Select (1 = Brokerage, 2 = Retirement)" << endl;
    for (int i = 1; i < 6; i++) {
        cout << i << ". " << clients[i - 1][0] << " selected option " << clients[i - 1][1] << endl;
    }

}

void ChangeCustomerChoice(string clients[][5]) {
    //SECURITY RISK: FIXED
    //No input validation at all. 
    //Inputs could be of the wrong type, which can lead to undefined behavior.
    //Inputs could be out of range, leading to integer overflow



    //I know the original code uses ints because if non-numeric entries are given it will result in an infinite loop
    int changechoice, newservice;



    cout << "Enter the number of the client that you wish to change" << endl;
    cin >> changechoice;
    //SECURITY FIX: validates that input is both an int and in the proper range. Prevents undefined behvaior and integer overflow

    while (changechoice > 5 || changechoice < 1 || !cin) {
        cout << "Invalid entry. Please select a number 1-5." << endl;
        cin.clear();
        cin.ignore();
        cin >> changechoice;

    }
    cout << "Enter the client's new service choice (1 = Brokerage, 2 = Retirement)" << endl;
    cin >> newservice;


    //SECURITY FIX: validates that input is both an int and in the proper range. Prevents undefined behvaior and integer overflow
    while (newservice > 2 || newservice < 1 || !cin) {
        cout << "Invalid entry. Please enter 1 or 2." << endl;
        cin.clear();
        cin.ignore();
        cin >> newservice;

    }


    //Like the original code, this will NOT validate the clients number or the clients choice.
    //If the user enters a non-numeric entry it will infinitely loop, and can accept numbers out of intended range

    clients[changechoice - 1][1] = to_string(newservice);

}


int main()
{
    //SECURITY RISK: FIXED
    //No input validation at all. 
    //Inputs could be of the wrong type, which can lead to undefined behavior.
    //Inputs could be out of range, leading to integer overflow
    //No data sanitization when data is being passed to other functions. This was not directly fixed, but data is validated in such a way where only only expected data should be passed to proper functions

    //I know that the original program did not use char or string for the users menu choice
    //This is apparent when entering a non-numaric entry, causing the program to infinitly loop
    int answer, choice;

    //Instead of storing clients in global variables. they are stored in an array of a list.
    //This is a more secure option than using global variables
    string clients[][5] = { {"Bob Jones", "1"}, {"Sarah Davis", "2"}, {"Amy Friendly", "1"}, {"Johnny Smith", "1"}, {"Carol Spears", "2"} };

    //added additional greeting with my name to meet req
    cout << " -------------------------------------------------------------------\n|CS-410, Project Two - With Security Fixes, Created by Elijah Hickey|\n -------------------------------------------------------------------\n" << endl;

    //Greating that displays once at the start of the program
    cout << "Hello! Welcome to our investment Company\n";


    //calls the function to validate that the user has the correct password
    answer = CheckUserPermissionAccess();
    while (answer != 123) {
        //error prompt, will repeat this function until the program is closed or the user enters 123 for the password.
        cout << "Invalid Password. Please try again" << endl;
        answer = CheckUserPermissionAccess();
    }

    choice = 0;
    //This will repeat until the user enters 3. entering 1 or 2 results in the respective function being called. any other numeric entry causes the program to prompt the user again for entry
    while (choice != 3) {
        cout << "What would you like to do?\nDISPLAY the client list (enter 1)\nChange a client's choice (enter 2)\nExit the program.. (enter 3) " << endl;

        cin >> choice;

        //SECURITY FIX: Ensures entry is numeric &
        //makes it so that only proper menu entries can be entered
        //prevents integer overflow
        while (choice > 3 || choice < 1 || !cin) {
            cout << "Invalid entry. Please enter 1, 2 or 3." << endl;
            cin.clear();
            cin.ignore();
            cin >> choice;
        }

        cout << "You chose " << choice << endl;

        if (choice == 1) {
            DisplayInfo(clients);
        }
        else if (choice == 2) {
            ChangeCustomerChoice(clients);
        }

    }

    return 0;

}

