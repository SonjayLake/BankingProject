package banking;
import java.util.Scanner;

public class UserInterface {
    private Scanner scanner;
    private Database db;

    public UserInterface(Scanner scanner, Database db){
        this.scanner =scanner;
        this.db = db;
    }

    public void start(){
        System.out.println("1. Create an account");
        System.out.println("2. Log into account");
        System.out.println("0. Exit");
        int input = scanner.nextInt();
        this.scanner.nextLine(); //consume the blank line created by nextInt()
        switch (input){
            case 1:
                this.createCard();
                this.start();
                break;
            case 2:
                this.logIn();
                break;
            case 0:
                System.out.println("Bye!");
                db.close();
                break;
            default:
                break;
        }





    }

    public void createCard(){
        CreditCard newCard = new CreditCard();
        db.insert(newCard.getNumber(),newCard.getPin());
        System.out.println("Your card has been created");
        System.out.println("Your card number: ");
        System.out.println(newCard.getNumber());
        System.out.println("Your card PIN:");
        System.out.println(newCard.getPin());
    }

    public void logIn() {
        System.out.println("Enter your card number:");
        String cardNum = this.scanner.nextLine();
        System.out.println("Enter your PIN:");
        String pin = this.scanner.nextLine();

        if (!db.getNumber(cardNum,pin).equals("")) {
            System.out.println("You have successfully logged in!");
            getLogOnUI(cardNum,pin);

        } else {
            System.out.println("Wrong card number or PIN!");
            start();
        }
    }

    public void getLogOnUI(String cardNum, String pin){
        System.out.println("1. Balance");
        System.out.println("2. Add income");
        System.out.println("3. Do transfer");
        System.out.println("4. Close account");
        System.out.println("5. Log out");
        System.out.println("0. Exit");

        int option = Integer.valueOf(scanner.nextLine());
        switch (option) {
            case 1:
                System.out.println("Balance: " + db.getBalance(cardNum));
                getLogOnUI(cardNum,pin);
                break;
            case 2:
                System.out.println("Enter income:");
                int income = Integer.valueOf(this.scanner.nextLine());
                db.addIncome(cardNum,income);
                System.out.println("Income was added!");
                getLogOnUI(cardNum,pin);
                break;

            case 3:
                System.out.println("Transfer");
                System.out.println("Enter card number:");
                try {
                    String transferNumber = this.scanner.nextLine();
                    if(!db.cardExists(transferNumber)){
                        if(!CreditCard.passesLuhn(transferNumber)){
                            System.out.println("Probably you made a mistake in the card number. Please try again!");
                        }
                        System.out.println("Such a card does not exist.");

                    } else if(transferNumber.equals(cardNum)){
                        System.out.println("You can't transfer money to the same account!");

                    } else if(db.cardExists(transferNumber)){
                        System.out.println("Enter how much money you want to transfer:");
                        int transferAmount = Integer.valueOf(this.scanner.nextLine());
                        if(transferAmount > Integer.valueOf(db.getBalance(cardNum))){
                            System.out.println(db.getBalance(cardNum));
                            System.out.println("Not enough money!");

                        } else{
                            db.addIncome(transferNumber,transferAmount);
                            db.addIncome(cardNum, -1*transferAmount);
                            System.out.println();
                            System.out.println("Success!");

                        }

                    }

                } catch (Exception e){
                    //System.out.println(e.getMessage());
                    System.out.println("Such a card does not exist.");

                }
                getLogOnUI(cardNum,pin);
                break;

            case 4:
                System.out.println("The account has been closed!");
                db.deleteCard(cardNum,pin);
                start();
                break;
            case 5:
                System.out.println("You have successfully logged out!");
                start();
                break;
            case 0:
                System.out.println("Bye!");
                db.close();
                break;
            default:
                break;
        }
    }
}
