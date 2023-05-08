import java.util.Scanner;

public class system {

    public static void selection(){
        System.out.println("Customer and Sales System\n 1. Enter Customer Information\n 2. Generate Customer data file\n 3. Report on total Sales \n 4. Check for fraud in sales data \n 9. Quit\n Enter menu option (1-9): ");
    }

    public static void enterCustomerInfo(){
        // temporary copy of the code from python
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
        String customerInfo = firstname + "," + lastname + "," + city + "," + postalcode + "," + creditcard;
        folder = os.getcwd()
        fileName = str(folder) + "\\temp.csv"
        with open(fileName, "w") as currentEdit:
            currentEdit.writelines(customerInfo)
    }

    public static void generateCustomerDataFile(){
        // temporary copy of the code from python
        folderChoice = input("Enter the name of the folder you wish to save to: ")
        folder = os.getcwd()
        fileName = str(folder) + "\\temp.csv"
        with open(fileName, "r") as currentEdit_r:
            tempContents = currentEdit_r.read()
        fileName = str(folder) + "\\userID.txt"
        with open(fileName, "r") as currentEdit_r:
            userID = currentEdit_r.read()
        userID = int(userID)
        fileName = str(folder) + "\\" + folderChoice
        with open(fileName, "a") as currentEdit_a:
            currentEdit_a.write(str(userID) + "," + tempContents + "\n")
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
    }
}
