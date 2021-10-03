//============================================================================
// Name        : Project Two - Elijah Hickey.cpp
// Author      : Elijah Hickey
// CS-410
// 7-2 Project Two
// 8/14/2021
//============================================================================


// NOTE!!!!!!!!!!!!!!!!!
// This file only has security issues commented on, no fixes
// To see file with fixes, see: Project Two - With Fixes 
// !!!!!!!!!!!!!!!!!!!!!


#include <iostream>
#include <string>
using namespace std;

//SECURITY RISKS:
//Global non-constant variables are used. 
//This is a security risk since any function has access and can alter them, in a more complicated system it because difficult to control this


//Global variables
string name1 = "Bob Jones";
string name2 = "Sarah Davis";
string name3 = "Amy Friendly";
string name4 = "Johnny Smith";
string name5 = "Carol Spears";

int num1 = 1;
int num2 = 2;
int num3 = 1;
int num4 = 1;
int num5 = 2;


int CheckUserPermissionAccess() {
    //SECURITY RISKS:
    //This function did not have ANY input validation. Incorrect entries could cause overflow, lead to undefined behavior, or lead to an infinite loop.


    //While the username isnt ever used, it is still collected, so I stored it in a string
    //The orignal code will cause an error if there is a space present (it will take the entry after the space and apply it to password, unsure exactly how that is done)

    //A string for username will simply infinetly loop if a space is present (unless like above the entry after the space is 123)
    string username;

    int password;
    cout << "Enter your username: " << endl;
    cin >> username;
    cout << "Enter your password: " << endl;
    cin >> password;

    //passes password back to main
    return password;


}


//This simply takes the clients data and displays it
void DisplayInfo() {
    //not how I hoped to operate this function, but seems to mimic the orignal program.
    cout << "  Client's Name   Service Select(1 = Brokerage, 2 = Retirement)" << endl;
    cout << "1. " << name1 << " selected option " << num1 << endl;
    cout << "2. " << name2 << " selected option " << num2 << endl;
    cout << "3. " << name3 << " selected option " << num3 << endl;
    cout << "4. " << name4 << " selected option " << num4 << endl;
    cout << "5. " << name5 << " selected option " << num5 << endl;

}

void ChangeCustomerChoice() {
    //SECURITY RISK:
    //No input validation at all. 
    //Inputs could be of the wrong type, which can lead to undefined behavior.
    //Inputs could be out of range, leading to integer overflow



    //I know the original code uses ints because if non-numeric entries are given it will result in an infinite loop
    int changechoice, newservice;



    cout << "Enter the number of the client that you wish to change" << endl;
    cin >> changechoice;
    cout << "Enter the client's new service choice (1 = Brokerage, 2 = Retirement)" << endl;
    cin >> newservice;

    //Like the original code, this will NOT validate the clients number or the clients choice.
    //If the user enters a non-numeric entry it will infinitely loop, and can accept numbers out of intended range


    //while doing it this way pains me, this matches the original design of the program.
    if (changechoice == 1) {
        num1 = newservice;
    }
    if (changechoice == 2) {
        num2 = newservice;
    }
    if (changechoice == 3) {
        num3 = newservice;
    }
    if (changechoice == 4) {
        num4 = newservice;
    }
    if (changechoice == 5) {
        num5 = newservice;
    }

}


int main()
{
    //SECURITY RISK:
    //No input validation at all. 
    //Inputs could be of the wrong type, which can lead to undefined behavior.
    //Inputs could be out of range, leading to integer overflow
    //No data sanitization when data is being passed to CheckUserPermissionAccess()

    //I know that the original program did not use char or string for the users menu choice
    //This is apparent when entering a non-numaric entry, causing the program to infinitly loop
    int answer, choice;


    //added additional greeting with my name to meet req
    cout << " ---------------------------------------------\n|CS-410, Project One, Created by Elijah Hickey|\n ---------------------------------------------\n" << endl;

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
        //as seen throughout this program, the users entry is not validated. A non-numeric entry causes an infinite loop
        cout << "You chose " << choice << endl;

        if (choice == 1) {
            DisplayInfo();
        }
        else if (choice == 2) {
            ChangeCustomerChoice();
        }

    }

    return 0;

}

