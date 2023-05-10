import java.util.Scanner;

public class LuhnAlgorithm {

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

    public static void selection(){
        System.out.println("Customer and Sales System\n 1. Enter Customer Information\n 2. Generate Customer data file\n 3. Report on total Sales \n 4. Check for fraud in sales data \n 9. Quit\n Enter menu option (1-9): ");
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
        Scanner input = new Scanner(System.in);
        String firstname = enterFirstName(input);
        String lastname = enterLastName(input);
        String city = enterCity(input);
        String postalcode = enterPostalCode(input);
        String creditcard = enterCreditCard(input);
        input.close();
        return firstname + "," + lastname + "," + city + "," + postalcode + "," + creditcard;
    }
       
    public static void main(String[] args){
        Scanner input = new Scanner(System.in);
        String userInput;
        String enterCustomerOption = "1";
        String exitCondition = "9";
        do {
            selection();
            userInput = input.nextLine();
            if (userInput.equals(enterCustomerOption)) {
                String customerInfo = enterCustomerInfo();
                if (customerInfo != null) {
                    System.out.println(customerInfo);
                }
            } else if (!userInput.equals(exitCondition)) {
                System.out.println("Please type in a valid option (A number from 1-9)");
            }
        } while (!userInput.equals(exitCondition));
        System.out.println("Program Terminated");
        input.close();
    }
}
