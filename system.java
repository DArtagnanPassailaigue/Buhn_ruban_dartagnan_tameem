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
    
    public static void selection(){
        System.out.println("Customer and Sales System\n 1. Enter Customer Information\n 2. Generate Customer data file\n 3. Report on total Sales \n 4. Check for fraud in sales data \n 9. Quit\n Enter menu option (1-9): ");
    }

    public static void enterCustomerInfo(Scanner input){
        String firstname, lastname, city, postalcode, creditcard;

    do {
        System.out.println("Enter your first name: ");
        firstname = input.next();
        System.out.println("Enter your surname: ");
        lastname = input.next();
        System.out.println("Enter the name of your city: ");
        city = input.next();
        System.out.println("Enter your postal code: ");
        postalcode = input.next().toUpperCase();

        if (postalcode.length() < 3) {
            System.out.println("Invalid postal code");
        }
        } while (postalcode.length() < 3);
        
      	System.out.println(postalcodeCheck(postalcode, "postal_codes.csv"));
        // System.out.println(code);
        do {
            System.out.println("Enter your credit card number: ");
            creditcard = input.next();

            if (creditcard.length() < 9) {
                System.out.println("Invalid credit card number.");
            }
        } while (creditcard.length() < 9);
            String customerInfo = firstname + "," + lastname + "," + city + "," + postalcode + "," + creditcard;
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

    public static String postalcodeCheck(String postalcode, String postalFile){
        System.out.println("");
        String test = null;
        // ArrayList<String> lines = new ArrayList<String>();
        
        if(postalcode.length() >= 3){
            postalcode = postalcode.substring(0, 3);
	        try {		
		    FileReader fileRead = new FileReader(postalFile);
		    BufferedReader reader = new BufferedReader(fileRead);
            String line;
            while(true) {
                line = reader.readLine();
                if (line.contains(postalcode)) {
                    test = "Valid Postal code";
                    break;
                }
                // lines.add(line);
                // System.out.println(lines);
                // test = "works";
                // System.out.println(test);
		    }
            reader.close();

        }   catch (Exception f) {
			    System.out.println(f.getMessage());
            }
        }
            return test;
            
    }
            
    public static void generateCustomerInfo(Scanner scanner){
        String fileName;
        do {
            System.out.println("Enter the name of the folder you wish to save to: ");
            fileName = scanner.next();
        } while (fileName == null || fileName.trim().isEmpty());

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

    public static ArrayList<String> reportSales(String salesFile){
        ArrayList<String> salesList= new ArrayList<String>();
        try {		
		    FileReader fileRead = new FileReader(salesFile);
		    BufferedReader reader = new BufferedReader(fileRead);
            String line;
            String numberRegex = "\\d+";
            while((line = reader.readLine()) != null) {
                String salesAmountStr = line.split(",")[1];
                if (salesAmountStr.matches(numberRegex)) {
                    salesList.add(line.split(",")[1]);
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
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in); // Create a single instance of Scanner
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
                enterCustomerInfo(input); // Pass the Scanner instance as a parameter
            } else if (userInput.equals(generateCustomer)) {
                generateCustomerInfo(input); // Pass the Scanner instance as a parameter
            } else if (userInput.equals(reportSales)) {

                reportSales("sales.csv");
            } else {
                System.out.println("Invalid input");
            }
        } while (!userInput.equals(exitCondition));
        System.out.println("Program Terminated");
    }
}