package Users;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class UserHandler {

    // takes inputed email string from user and checks it against user.txt, returns user object to main if found
    public static User loginUser(String inputEmail) {

        System.out.println(System.getProperty("user.dir"));
        
        try {  
            
            //opens user file to read
            BufferedReader reader = new BufferedReader(
                new FileReader(
                    "resources/users.txt"
                )
            );

            String line;         
            while ((line = reader.readLine()) != null) {
                
                //iterates through users.txt and checks to see if email field of string matches user input
                String[] parts = line.split(";");
                if (parts[2].equals(inputEmail.toLowerCase())){

                    reader.close();
                    
                    String userType = parts[0];
                    String name = parts[1];
                    String fileEmail = parts[2];
                    String telNo = parts[3];
                    String extra = parts[4];

                    // initialising user subclass based on stored userType
                    if (userType.equals("Student")) {
                        User user = new Student(name, fileEmail, telNo, extra);
                        return user;
        
                    } else if (userType.equals("Homeowner")) {
                        User user = new Homeowner(name, fileEmail, telNo, extra);
                        return user;
                    }
                }
            }
        } catch (IOException e) {
            //basically only here so vscode doesnt yell at me, but please run the program from the src folder
            System.out.println("Error reading file, please ensure cwd is /src/");
        }
        return null;    
    }

    // method called by main for user authentication, self contained input handling
    public static User login(Scanner scan) {
        Boolean validUser = false;
        User user = null;

        //creates a while loop while the user attempts to input a valid email address (one contained in users.txt)
        while (validUser == false) {

            //User input
            System.out.println("enter email");
            String email = scan.nextLine();

            //input handling
            user = loginUser(email);
            if (user != null) {
                validUser = true;
                System.out.println("Welcome, " + user.getName());
            } else {
                System.out.println("Please try again");
            }
        }
        return user;
    }

    // method to print all user information for display purposes (view profile setting)
    public static void displayUserInfo(User user) {
        System.out.println();
        System.out.println(String.format("User Information (%s)", user.getUserType()));
        System.out.println(String.format("Name: %s", user.getName()));
        System.out.println(String.format("Email: %s", user.getEmail()));
        System.out.println(String.format("Telephone Number: %s", user.getTelNo()));
        
        //checks user type for unique attribute getter call
        if (user instanceof Student student) {
            System.out.println(String.format("University: %s", student.getUniversity()));
        } else if (user instanceof Homeowner homeowner) {
            System.out.println(String.format("Address: %s", homeowner.getAddress()));
        }

        System.out.println();
    }
}