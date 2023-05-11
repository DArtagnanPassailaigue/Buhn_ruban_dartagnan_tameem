import java.util.Scanner;
import java.io.File;
import java.io.FileWriter;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.FileNotFoundException;

public class system {

    public static void selection(){
        System.out.println("Customer and Sales System\n 1. Enter Customer Information\n 2. Generate Customer data file\n 3. Report on total Sales \n 4. Check for fraud in sales data \n 9. Quit\n Enter menu option (1-9): ");
    }

    public static void enterCustomerInfo(){
        // temporary code; try to split this into smaller functions
        Scanner input = new Scanner(System.in);
        System.out.println("Enter your first name: ");
        String firstname = input.next();
        System.out.println("Enter your surname: ");
        String lastname = input.next();
        System.out.println("Enter the name of your city: ");
        String city = input.next();
        System.out.println("Enter your postal code: ");
        String postalcode = input.next();
        if(postalcode.length() >= 3){
            ;
        }
        else {
            System.out.println("Invalid postal code");
            enterCustomerInfo();
        }
        System.out.println("Enter your credit card number: ");
        String creditcard = input.next();
        if(creditcard.length() >= 9){
            ;
        }
        else {
            System.out.println("Invalid credit card number.");
            enterCustomerInfo();
        }
        input.close();
        String customerInfo = firstname + "," + lastname + "," + city + "," + postalcode + "," + creditcard;
        try {
            FileWriter myWriter = new FileWriter("temp.csv");
            myWriter.write(customerInfo);
            myWriter.close();
            System.out.println("Customer data ready for geneartion.");
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    public static void generateCustomerInfo(){
        Scanner input = new Scanner(System.in);
        System.out.println("Enter the name of the folder you wish to save to: ");
        String fileName = input.next();
        input.close();
        try {
            FileWriter write = new FileWriter(fileName, true);
            BufferedWriter append = new BufferedWriter(write);
            String userInfo = readUserID() + readTemp();
            append.write(userInfo);
            append.newLine();
            append.close();
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
        updateUserID();
    }

    public static String readUserID(){
        try{
            File userIDFile = new File("userID.txt");
            Scanner userIDReader = new Scanner(userIDFile);
            String userID = userIDReader.next();
            userIDReader.close();
            return userID;
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
            return "";
        }
    }

    public static String readTemp(){
        try {
            File temp = new File("temp.csv");
            Scanner tempReader = new Scanner(temp);
            String tempData = tempReader.next();
            tempReader.close();
            return tempData;
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
            return "";
        }
    }

    public static void updateUserID(){
        String userID = readUserID();
        Integer.parseInt(userID);
        userID += 1;
        try{
            FileWriter userIDUpdateWriter = new FileWriter("userID.txt");
            userIDUpdateWriter.write(userID);
            userIDUpdateWriter.close();
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    public static void main(String[] args){
        Scanner menuChoice = new Scanner(System.in);
        String userInput = "";
        String enterCustomer = "1";
        String generateCustomer = "2";
        String reportSales = "3";
        String checkFraud = "4";
        String exitCondition = "9";
        do {
            selection();
            userInput = menuChoice.next();
            if(userInput == enterCustomer){
                enterCustomerInfo();
            } else if(userInput == generateCustomer){
                generateCustomerInfo();
            } else{
                System.out.println("Invalid input");
            }
        } while(userInput != exitCondition);
        System.out.println("Program Terminated");
        menuChoice.close();
    }
}
