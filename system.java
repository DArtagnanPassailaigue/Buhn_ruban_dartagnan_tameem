import java.util.Scanner;
import java.io.File;
import java.io.FileWriter;
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

    public static void generateCustomerDataFile(){
        // temporary copy of the code from python
        ArrayList String tempData;
        Scanner input = new Scanner(System.in);
        System.out.println("Enter the name of the folder you wish to save to: ");
        String fileChoice = input.next();
        File temp = new File("temp.csv");
        Scanner tempReader = new Scanner(temp);
        while (tempReader.hasNextLine()) {
            tempData = tempReader.nextLine();
        }
        File userIDFile = new File("userID.txt");
        Scanner userIDReader = new Scanner(userIDFile);
        String userID = userIDReader.nextLine();
        String userFileContents = userID + tempData;
        Integer.parseInt(userID);
        
        fileName = str(folder) + "\\userID.txt"
        with open(fileName, "w") as currentEdit_w:
            userID += 1
            currentEdit_w.write(str(userID))
        
    }

    public static void main(String[] args){
        selection();
        Scanner menuChoice = new Scanner(System.in);
        String userInput;
        String enterCustomer = "1";
        String generateCustomer = "2";
        String reportSales = "3";
        String checkFraud = "4";
        String exitCondition = "9";
        do {
            selection();
            userInput = menuChoice.nextLine();
        } while(userInput != exitCondition);
        System.out.println("Program Terminated");
        menuChoice.close();
    }
}
