import java.util.Scanner;
import java.io.File;
import java.io.FileWriter;
import java.io.BufferedWriter;
import java.io.IOException;
public class system {

    private static Scanner input;
    // global scanner used for multiple runs of the program
    
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
                // gets the file path to temp.csv on any device
                FileWriter myWriter = new FileWriter(path);
                myWriter.write(customerInfo);
                // writes the temporary data to the temp file
                myWriter.close();
                System.out.println("Customer data ready for geneartion.");
            } catch (IOException e) {
                System.out.println("An error occurred.");
                e.printStackTrace();
                // catches in case of an IOException error
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
            // asks the user for a filename input and loops if nothing is input
        } while (fileName == null || fileName.trim().isEmpty());

        try {
            FileWriter write = new FileWriter(fileName, true);
            BufferedWriter append = new BufferedWriter(write);
            // uses a combination of filewriter and buffered writer to append
            String userInfo = readFile("userID.txt") + "," + readFile("temp.csv");
            // combines the results of readUserId() and readTemp() 
            append.write(userInfo);
            // appends the result to the desired file
            append.newLine();
            append.close();
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
            // catches in case of an IOException error
        }
        updateUserID();
        // runs updateUserId() at the end
    }

    public static String readFile(String filename){
        /**
         * @author D'Artagnan
         * @return the value of userID from the file as a string
         * reads from the userID file and returns it as a string
         */
        try{
            File file = new File(filename);
            Scanner fileReader = new Scanner(file);
            // reads the userID
            String fileInfo = fileReader.next();
            fileReader.close();
            return fileInfo;
            // returns it as a string
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
            return "";
            // catches in case of an IOException error
        }
    }

    public static void updateUserID(){
        /**
         * @author D'Artagnan
         * adds 1 to the userID value and writes it to userID.txt
         */
        String userID = readFile("userID.txt");
        int userIDValue = Integer.parseInt(userID); // Convert to integer
        userIDValue++; // Increment the value
        userID = Integer.toString(userIDValue); // Convert back to string
    
        try {
            FileWriter userIDUpdateWriter = new FileWriter("userID.txt");
            userIDUpdateWriter.write(userID);
            userIDUpdateWriter.close();
            // writes the updated userID to the userID file
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
            // catches in case of an IOException error
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
        // calls all of the values of the menu option inputs
        do {
            selection();
            // prints the main menu
            userInput = input.next();
            if (userInput.equals(enterCustomer)) {
                writeToTempFile(enterCustomerInfo(),input); // Pass the Scanner instance as a parameter
            } else if (userInput.equals(generateCustomer)) {
                generateCustomerInfo(input); // Pass the Scanner instance as a parameter
            } else if (userInput.equals(exitCondition)) {
                ;
                // does nothing so that an exit condition being met will not print "invalid input"
            } else {
                System.out.println("Invalid input");
            }
        } while (!userInput.equals(exitCondition));
        // do-while loop for multiple runs of the program
        System.out.println("Program Terminated");
    }
}