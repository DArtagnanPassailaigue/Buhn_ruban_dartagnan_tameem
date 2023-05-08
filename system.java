import java.util.Scanner;

public class system {

    public static void selection(){
        System.out.println("Customer and Sales System\n 1. Enter Customer Information\n 2. Generate Customer data file\n 3. Report on total Sales \n 4. Check for fraud in sales data \n 9. Quit\n Enter menu option (1-9): ");
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
    }
}
