import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.BufferedReader;
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
    public static String enterCustomerInfo(Scanner input){
        String firstname = enterFirstName(input);
        String lastname = enterLastName(input);
        String city = enterCity(input);
        String postalcode = enterPostalCode(input);
        String creditcard = enterCreditCard(input);
        return firstname + "," + lastname + "," + city + "," + postalcode + "," + creditcard;
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
            postalcode = input.next().toUpperCase();    // Makes the input uppercase
            if (postalcode.length() < 3 || !postalCodeCheck(postalcode, "postal_codes.csv")) {
                System.out.println("Invalid postal code");
            }
        } while (postalcode.length() < 3 || !postalCodeCheck(postalcode, "postal_codes.csv"));
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
    /**
     * this method checks the users inputed data, as long as it greater or equal then/to 3, against the imported csv file.
     * returns confirmation message if it is valid.
     * @author Ruban
     * @return boolean
     */
    public static boolean postalCodeCheck(String postalcode, String postalFile){
        postalcode = postalcode.substring(0, 3);    // taks the first 3 characters
        String postalRegex = "[A-Z][0-9][A-Z]";     // looks for letter, number, letter pattern
        if(postalcode.matches(postalRegex)){     // checks if the postal code follows the leter, number, letter pattern
	        try {		
		    FileReader fileRead = new FileReader(postalFile);
		    BufferedReader reader = new BufferedReader(fileRead);
            String line;
            for (;;)  {   // infinite loop
                line = reader.readLine();   // reads lines
                if (line.contains(postalcode)) { // checks line
                    return true;
                }
                
		    }
        }   catch (Exception f) {
			    ;
            }
        }
        return false;
        
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
    /**
     * this method looks at a given sales data file and stores the first digit of the data in an array.
     * It then return that array.
     * 
     * @author Ruban
     * @return Array
     */
    public static ArrayList<String> reportSales(String salesFile){
        
        ArrayList<String> salesList= new ArrayList<String>();     //establishing the array
        try {		
		    FileReader fileRead = new FileReader(salesFile);
		    BufferedReader reader = new BufferedReader(fileRead);
            String line;
            String numberRegex = "\\d+";    // regex (Regular Expressions) establishes patterns. This one speciffically looks for anything with more than one digit.
            while((line = reader.readLine()) != null) {     // reads lines until there are no lines to read.
                String salesAmountStr = line.split(",")[1];     // this checks/reads only the second row (1), established by the comma.
                if (salesAmountStr.matches(numberRegex)) {     // ignores anything thats not a number(Titles, comments, labels, etc.)
                    //salesList.add(line.split(",")[1]);
                    salesList.add((line.split(",")[1].substring(0,1)));     // adds only the first digit of the int, and adds it to the array.
                }
		    }
            reader.close();
            System.out.println("Sales: "+ salesList);
            
        }   catch (FileNotFoundException g) {
                System.out.println(g.getMessage());
        }   catch (Exception h) {
			    System.out.println(h.getMessage());
            }
        return salesList;
    }

    /*public static void benfordLawCheck(){
        int[] benlist = new int[9];
        int count1 = 0;
        int count2 = 0;
        int count3 = 0;
        int count4 = 0;
        int count5 = 0;
        int count6 = 0;
        int count7 = 0;
        int count8 = 0;
        int count9 = 0;
        // declaring the local list variable and the int variables for each number that could appear
        for(int i = 0; i < salesList; i++){
            benford = (i[0]);
            benlist.append(benford);
            //appends each first number in each value in the sales list to the "benlist"
        }

        for i in benlist:
            count1 = benlist.count('1');
            count2 = benlist.count('2');
            count3 = benlist.count('3');
            count4 = benlist.count('4');
            count5 = benlist.count('5');
            count6 = benlist.count('6');
            count7 = benlist.count('7');
            count8 = benlist.count('8');
            count9 = benlist.count('9');
            // counts the amount of times each number appears in benlist
        print("Numbers Recorded:", count1, count2, count3, count4, count5, count6, count7, count8, count9);
        total = count1+count2+count3+count4+count5+count6+count7+count8+count9;
        detectFraud(count1, total)
        valueList = [count1, count2, count3, count4, count5, count6, count7, count8, count9];
    }
    */

    /**
    * uses the assigned number value and total to determine if fraud is present in the sales data based on set percentages
    * @author D'Artagnan
    */
    public static void detectFraud(int num, int total){
        if(numericRep(num, total) == "29%" || numericRep(num, total) == "30%" || numericRep(num, total) == "31%" || numericRep(num, total) == "32%"){
            System.out.println("Fraud not detected :3");
        } else{
            System.out.println("!!FRAUD DETECTED!!FRAUD DETECTED!!FRAUD DETECTED!!FRAUD DETECTED!!FRAUD DETECTED!!FRAUD DETECTED!!FRAUD DETECTED!!");
        }
        // if the calculation is within the allocated percent range, fraud is not detected
    }

    /**
    * uses cross multiply and divide to find the percentage of the number plugged in to the function
    * @author D'Artagnan
    * @return the percentage value of the designated number over the total, with a percentage symbol at the end
    */
    public static String numericRep(int num, int total){
        int calculation = num * 100 / total;  // cross multiply and divide to find the percentage
        String strCalc = Integer.toString(calculation);
        return strCalc + "%"; // removes the decimal from the percentage and adds the % sign
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
                writeToTempFile(enterCustomerInfo(input),input); // Pass the Scanner instance as a parameter
            } else if (userInput.equals(generateCustomer)) {
                generateCustomerInfo(input); // Pass the Scanner instance as a parameter
            } else if (userInput.equals(exitCondition)) {
                ;
                // does nothing so that an exit condition being met will not print "invalid input"
            } else if (userInput.equals(reportSales)) {
                reportSales("sales.csv");
            } else {
                System.out.println("Invalid input");
            }
        } while (!userInput.equals(exitCondition));
        // do-while loop for multiple runs of the program
        System.out.println("Program Terminated");
    }
}