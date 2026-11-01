package SMARTMETROACCESSCARD;
import java.util.*;//Array List?
public class MetroPass {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		SmartCard.loadRecords();
	int n,n2,n3;       
		do {
System.out.println("------------=======METRO PASS MENU======--------------------");
System.out.println("1.ADMIN LOGIN");
System.out.println("2.USER LOGIN");
System.out.println("3.EXIT");
		
System.out.println("Enter your choice in integer as given above");
 n = sc.nextInt();
	
switch(n) {
case 1: 
	do {
	System.out.println("Admin Login Succesfull");
	System.out.println("====== ADMIN MENU =======");
	System.out.println("Enter 1 to Display All Cards");
	System.out.println("Enter 2 to Search Card");
	System.out.println("Enter 3 to Delete Card");
	System.out.println("Enter 4 to Show Most Trips");
	System.out.println("Enter 5 to Show Total Fare");
	System.out.println("Enter 6 to Exit");
	System.out.println("Enter your Choice");
	 n2=sc.nextInt();
	switch(n2) {
	case 1:

		SmartCard.displayAll();
		break;
	
    case 2:
        System.out.print("Enter card number: ");
        int cardNo = sc.nextInt();

        try {
            SmartCard c = SmartCard.searchById(cardNo);
            c.display();
        } catch (CardNotFound e) {
            System.out.println(e.getMessage());
        }
        break;
	
case 3:
    System.out.print("Enter card number to delete: ");
    int deleteNo = sc.nextInt();

    try {
        SmartCard.deleteById(deleteNo);
        System.out.println("Card deleted successfully");
    } catch (CardNotFound e) {
        System.out.println(e.getMessage());
    }
    break;
case 4:
    SmartCard.mostTrips();
    break;

case 5:
    SmartCard.totalFare();
    break;

case 6:
    System.out.println("Admin logged out");
    break;

default:
    System.out.println("Invalid choice");
    break;
}
	}while(n2 !=6)	 ;
break;
case 2:
	do {
	System.out.println("===== PASSENGER MENU =====");
	System.out.println("1. Register New Card");
	System.out.println("2. Recharge Card");
	System.out.println("3. Use Metro Trip");
	System.out.println("4. View Card Details");
	System.out.println("5. View History");
	System.out.println("6. Logout");

	System.out.print("Enter your choice: ");
	 n3 = sc.nextInt();

	switch (n3) {

	    case 1:
	    	 System.out.println("===== REGISTER NEW CARD =====");
	    	    System.out.println("1. Regular Card");
	    	    System.out.println("2. Student Card");
	    	    System.out.println("3. Senior Citizen Card");

	    	    System.out.print("Choose Card Type: ");
	    	    int cardType = sc.nextInt();

	    	    System.out.print("Enter Card Number: ");
	    	    int cardNumber = sc.nextInt();

	    	    sc.nextLine(); // consume newline

	    	    System.out.print("Enter Holder Name: ");
	    	    String holderName = sc.nextLine();

	    	    System.out.print("Enter Initial Balance: ");
	    	    double balance = sc.nextDouble();

	    	    System.out.print("Enter Number of Trips: ");
	    	    int trips = sc.nextInt();

	    	    sc.nextLine();

	    	    System.out.print("Enter Issue Date: ");
	    	    String issueDate = sc.nextLine();

	    	    System.out.print("Enter Contact Number: ");
	    	    String contactNumber = sc.nextLine();

	    	    String status = "ACTIVE";

	    	    switch (cardType) {

	    	        case 1:
	    	            SmartCard c = new SmartCard(
	    	                cardNumber, holderName, balance, trips,
	    	                issueDate, contactNumber, status
	    	            );

	    	            SmartCard.addRecord(c);
	    	            System.out.println("Regular Card Registered");
	    	            break;

	    	        case 2:
	    	            System.out.print("Enter Institution ID: ");
	    	            String institutionId = sc.nextLine();

	    	            StudentCard s = new StudentCard(
	    	                cardNumber, holderName, balance, trips,
	    	                issueDate, contactNumber, status, institutionId
	    	            );

	    	            SmartCard.addRecord(s);
	    	            System.out.println("Student Card Registered");
	    	            break;

	    	        case 3:
	    	            System.out.print("Enter Age: ");
	    	            int age = sc.nextInt();

	    	            sc.nextLine();

	    	            System.out.print("Enter ID Proof Number: ");
	    	            String idProofNo = sc.nextLine();

	    	            try {
	    	                SeniorCitizenCard senior = new SeniorCitizenCard(
	    	                    cardNumber, holderName, balance, trips,
	    	                    issueDate, contactNumber, status,
	    	                    age, idProofNo
	    	                );

	    	                SmartCard.addRecord(senior);
	    	                System.out.println("Senior Citizen Card Registered");

	    	            } catch (InelligibleCardException e) {
	    	                System.out.println(e.getMessage());
	    	            }
	    	            break;

	    	        default:
	    	            System.out.println("Invalid Card Type");
	    	    }

	    	    break;
	       

	    case 2:
	        System.out.println("====Recharge Card====");
	        System.out.print("Enter Card Number: ");
	        int rechargeCardNo = sc.nextInt();

	        try {
	            SmartCard c = SmartCard.searchById(rechargeCardNo);

	            System.out.print("Enter Recharge Amount: ");
	            double amount = sc.nextDouble();

	            boolean result = c.recharge(amount);

	            if (result) {
	                System.out.println("Recharge Successful!");
	                System.out.println("Updated Balance: " + c.getbalance());
	            }

	        } catch (CardNotFound e) {
	            System.out.println(e.getMessage());

	        } catch (InvalidRechargeException e) {
	            System.out.println(e.getMessage());
	        }

	        break;
	
	        

	    case 3:
	        System.out.println("====Use Metro Trip====");
	        System.out.print("Enter Card Number: ");
	        int tripCardNo = sc.nextInt();

	        try {
	            SmartCard c = SmartCard.searchById(tripCardNo);

	            c.checkEntryBalance();

	            System.out.print("Enter Number of Stations: ");
	            int stations = sc.nextInt();

	            double fare = c.calculateFare(stations);

	            if (fare > c.getbalance()) {
	                throw new InsufficientBalanceException("Insufficient balance to complete trip");
	            }

	            c.setbalance(c.getbalance() - fare);
	            c.settrips(c.getTrips() + 1);

	            System.out.println("Trip Successful!");
	            System.out.println("Fare Deducted: " + fare);
	            System.out.println("Updated Balance: " + c.getbalance());
	            System.out.println("Total Trips: " + c.getTrips());

	        } catch (CardNotFound e) {
	            System.out.println(e.getMessage());

	        } catch (InsufficientBalanceException e) {
	            System.out.println(e.getMessage());
	        }

	        break;

	    case 4:
	        System.out.println("====View Card Details=====");
	        try {
				System.out.println("=====Enter Your CardNumber=====");
				int CardNumber=sc.nextInt();
				SmartCard c=SmartCard.searchById(CardNumber);
				System.out.println("Details of entered card");
				c.display();
		        }
		        catch(CardNotFound e) {
		        	System.out.println(e.getMessage());
		        }
	        break;

	    case 5:
	        System.out.println("=====View History=====");
	        System.out.print("Enter Card Number: ");
	        int historyCardNo = sc.nextInt();

	        try {
	            SmartCard c = SmartCard.searchById(historyCardNo);
	            c.showHistory();

	        } catch (CardNotFound e) {
	            System.out.println(e.getMessage());
	        }
	        break;

	    case 6:
	        System.out.println("=====Passenger logged out=====");
	        System.out.println("Logged out successfully");
	        break;

	    default:
	        System.out.println("=====Invalid choice=====");
}
	}while(n3 !=6);
break;
case 3:
	System.out.println("----======Exit Menu======---");
	SmartCard.saveRecords();
	break;

default:
	System.out.println("=====Invalid choice=====");
}
		} while(n !=3);




	}

}
