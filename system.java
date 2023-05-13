import java.util.Scanner;
import java.io.File;
import java.io.FileWriter;
import java.io.BufferedWriter;
import java.io.IOException;
public class system {

    private static Scanner input;
    
    public static void selection(){
        /**
         * @author D'Artagnan
         * prints the system's main menu
         */
        System.out.println("Customer and Sales System\n 1. Enter Customer Information\n 2. Generate Customer data file\n 3. Report on total Sales \n 4. Check for fraud in sales data \n 9. Quit\n Enter menu option (1-9): ");
    }

    public static void writeToTempFile(String customerInfo, Scanner input){
        /**
         * @author D'Artagnan
         * @param takes the scanner input when the function is accessed from the menu
         * takes the user's information and stores them in a temp file
         */
            try {
                File file = new File("temp.csv");
                String path = file.getAbsolutePath();
                FileWriter myWriter = new FileWriter(path);
                myWriter.write(customerInfo);
                myWriter.close();
                System.out.println("Customer data ready for geneartion.");
            } catch (IOException e) {
                System.out.println("An error occurred.");
                e.printStackTrace();
            }
    }
    
    public static String enterFirstName(Scanner input) {
        System.out.println("Enter your first name: ");
        return input.next();
    }
    
    public static String enterLastName(Scanner input) {
        System.out.println("Enter your surname: ");
        return input.next();
    }
    
    public static String enterCity(Scanner input) {
        System.out.println("Enter the name of your city: ");
        return input.next();
    }
    
    public static String enterPostalCode(Scanner input) {
        String postalcode;
        do {
            System.out.println("Enter your postal code: ");
            postalcode = input.next();
            if (postalcode.length() < 3) {
                System.out.println("Invalid postal code");
            }
        } while (postalcode.length() < 3);
        return postalcode;
    }
    
    public static String enterCreditCard(Scanner input) {
        String creditcard;
        do {
            System.out.println("Enter your credit card number: ");
            creditcard = input.next();
            if (creditcard.length() < 9 || !luhnAlgo(creditcard)) {
                System.out.println("Invalid credit card number.");
            }
        } while (creditcard.length() < 9 || !luhnAlgo(creditcard));
        return creditcard;
    }

    public static String enterCustomerInfo() {
        String firstname = enterFirstName(input);
        String lastname = enterLastName(input);
        String city = enterCity(input);
        String postalcode = enterPostalCode(input);
        String creditcard = enterCreditCard(input);
        return firstname + "," + lastname + "," + city + "," + postalcode + "," + creditcard;
    }

    public static void generateCustomerInfo(Scanner scanner){
        /**
         * @author D'Artagnan
         * @param takes the scanner input when the function is accessed from the menu
         * asks for a file name to save the user's data to and pulls it from the temp file to save it to the specified file
         */
        String fileName;
        do {
            System.out.println("Enter the name of the folder you wish to save to: ");
            fileName = scanner.next();
        } while (fileName == null || fileName.trim().isEmpty());

        try {
            FileWriter write = new FileWriter(fileName, true);
            BufferedWriter append = new BufferedWriter(write);
            String userInfo = readUserID() + "," + readTemp();
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
        /**
         * @author D'Artagnan
         * @return the value of userID from the file as a string
         * reads from the userID file and returns it as a string
         */
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
        /**
         * @author D'Artagnan
         * @return the contents of the temp file
         * reads the temp file and returns the contents as a string
         */
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
        /**
         * @author D'Artagnan
         * adds 1 to the userID value and writes it to userID.txt
         */
        String userID = readUserID();
        int userIDValue = Integer.parseInt(userID); // Convert to integer
        userIDValue++; // Increment the value
        userID = Integer.toString(userIDValue); // Convert back to string
    
        try {
            FileWriter userIDUpdateWriter = new FileWriter("userID.txt");
            userIDUpdateWriter.write(userID);
            userIDUpdateWriter.close();
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    public static boolean luhnAlgo(String cardNo) {
        int nDigits = cardNo.length();

        int nSum = 0;
        boolean isSecond = false;
        for (int i = nDigits - 1; i >= 0; i--) {
            int d = cardNo.charAt(i) - '0';

            if (isSecond == true)
                d = d * 2;

            nSum += d / 10;
            nSum += d % 10;

            isSecond = !isSecond;
        }
        return (nSum % 10 == 0);
    }

    public static void main(String[] args) {
        /**
         * Prints the menu and takes the user's inputs to run the various commands of the program
         */
        input = new Scanner(System.in); // Create a single instance of Scanner
        String userInput = null;
        String enterCustomer = "1";
        String generateCustomer = "2";
        String reportSales = "3";
        String checkFraud = "4";
        String exitCondition = "9";
        
        do {
            selection();
            userInput = input.next();
            if (userInput.equals(enterCustomer)) {
                writeToTempFile(enterCustomerInfo(),input); // Pass the Scanner instance as a parameter
            } else if (userInput.equals(generateCustomer)) {
                generateCustomerInfo(input); // Pass the Scanner instance as a parameter
            } else if (userInput.equals(exitCondition)) {
                ;
            } else {
                System.out.println("Invalid input");
            }
        } while (!userInput.equals(exitCondition));
        System.out.println("Program Terminated");
    }
}