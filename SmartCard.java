package SMARTMETROACCESSCARD;
import java.io.*;
import java.util.*;
public class SmartCard implements Rechargable {
	private static ArrayList<SmartCard> cardList = new ArrayList<>();
private int cardnumber;
private String holderName;
private double balance;
private int trips;

private String issueDate;
private String contactNumber;
private String status;

private static double minFare=10;
private static double perStationFare=5;
private static double maxBalance=3000;
private static double minEntryBalance=220;
private static int cardCount;
//We should make them private to maintain encapsulation.
//One exception: If you declare a parameterized constructor, Java will no longer automatically provide the default constructor. So if your assignment requires both default and parameterized constructors, you must explicitly declare the default constructor.
public SmartCard() {
	cardnumber=0;
	holderName="";
	balance=0.0;
	trips=0;
	issueDate=" ";
	contactNumber=" ";
	status="ACTIVE";
	
}
public SmartCard(int cardNo,String holder,double balance,int trips) {
	this.cardnumber=cardNo;
	this.holderName=holder;
	this.balance=balance;
	this.trips=trips;
	this.issueDate=" ";
	this.contactNumber=" ";
	this.status="ACTIVE";
}
public SmartCard(int cardNumber,String holdername,double balance1,int trips1,String issueDate,String contactNumber,String status) {
this.cardnumber=cardNumber;
this.holderName=holdername;
this.balance=balance1;
this.trips=trips1;
this.issueDate=issueDate;
this.contactNumber=contactNumber;
this.status=status;
}

@Override
public boolean recharge(double amount) throws InvalidRechargeException{
	if(amount <100 || amount >2000 ) {
	
	throw new InvalidRechargeException("Invalid recharge amount");
	}
	if(this.balance+amount>SmartCard.maxBalance) {
	throw new InvalidRechargeException("Maximum Balnce Limit is exceeded");	
	}
	
		this.balance=this.balance+amount;
		return true;
	
}
@Override
public double calculateFare(int stations) {
	if(stations<=0) {
		return 0.0;
	}
	else {
		double fare=10+(stations -1)*5;
		return fare;
	}
	
}
public double calculateFare(int stations, boolean offpeak) {
	double fare=calculateFare(stations);
	if(offpeak) {
		fare=fare*0.80;
	}
	return fare;
}
public void compareBalance(SmartCard other) {
	
		System.out.println("Details of card with more balance");
		if(this.balance >other.balance) {
			System.out.println("Card 1");
			this.display();
	}
		else {
			System.out.println("Card 2");
			other.display();
		}
}
public void display() {
    System.out.println("Card Number: " + this.cardnumber);
    System.out.println("Holder Name: " + this.holderName);
    System.out.println("Balance: Rs." + this.balance);
    System.out.println("Trip Count: " + this.trips);
    System.out.println("Issue Date: " + this.issueDate);
    System.out.println("Contact Number: " + this.contactNumber);
    System.out.println("Status: " + this.status);
}

public void setCardNo(int cardNo) {
    this.cardnumber = cardNo;
}
public int getCardNo() {
    return this.cardnumber;
}
public void setholdername(String  name) {
    this.holderName = name;
}
public String getholdername() {
    return this.holderName;
}
public void setbalance(double balance) {
    this.balance = balance;
}
public double getbalance() {
    return this.balance;
}
public void settrips(int trips) {
    this.trips = trips;
}
public int getTrips() {
    return this.trips;
}
public void setIssueDate(String iod) {
    this.issueDate = iod;
}
public String getIssueDate() {
    return this.issueDate;
}
public void setContactNumber(String contactNumber) {
    this.contactNumber = contactNumber;
}
public String getContactNumber() {
    return this.contactNumber;
}
public void setStatus(String status) {
    this.status = status;
}
public String getStatus() {
    return this.status;
}
public void checkEntryBalance() throws InsufficientBalanceException{
	if(this.balance< minEntryBalance) {
		throw new InsufficientBalanceException("Insufficient balance to enter metro");
	}
}
public static void addRecord(SmartCard card) {
    cardList.add(card);
    cardCount++;
}
public static void displayAll() {

    for(SmartCard card : cardList) {
        card.display();
        System.out.println("-------------------");
    }

}
public static SmartCard searchById(int cardNumber)
        throws CardNotFound {

    for(int i = 0; i < cardList.size(); i++) {

        SmartCard c = cardList.get(i);

        if(c.getCardNo() == cardNumber) {
            return c;
        }
    }

    throw new CardNotFound("Card not found");
}
public static void deleteById(int cardNumber)
        throws CardNotFound {

    for(int i = 0; i < cardList.size(); i++) {

        SmartCard c = cardList.get(i);

        if(c.getCardNo() == cardNumber) {
            cardList.remove(i);
            cardCount--;
            return;
        }
    }

    throw new CardNotFound("Card not found");
}
public static void mostTrips() {

    if(cardList.isEmpty()) {
        System.out.println("No cards available");
        return;
    }

    SmartCard mostTravelled = cardList.get(0);

    for(int i = 1; i < cardList.size(); i++) {

        SmartCard c = cardList.get(i);

        if(c.getTrips() > mostTravelled.getTrips()) {
            mostTravelled = c;
        }
    }

    System.out.println("Card with most trips:");
    mostTravelled.display();
}
private double totalFarePaid;

	public static double totalFare() {

	    double total = 0.0;

	    for(int i = 0; i < cardList.size(); i++) {

	        SmartCard c = cardList.get(i);

	        total = total + c.totalFarePaid;
	    }

	    return total;
	}

public void showHistory() {
    System.out.println("----- Card History -----");
    System.out.println("Card Number: " + this.cardnumber);
    System.out.println("Holder Name: " + this.holderName);
    System.out.println("Total Trips: " + this.trips);
    System.out.println("Current Balance: " +this.balance);
}
public static void saveRecords() {
    try {
        BufferedWriter bw = new BufferedWriter(new FileWriter("metroCards.txt"));

        for (SmartCard c : cardList) {
            bw.write(c.getCardNo() + "," +
                     c.getholdername() + "," +
                     c.getbalance() + "," +
                     c.getTrips() + "," +
                     c.getIssueDate() + "," +
                     c.getContactNumber() + "," +
                     c.getStatus());

            bw.newLine();
        }

        bw.close();

        System.out.println("Records saved successfully");

    } catch (IOException e) {
        System.out.println("Error saving records");
    }
}
public static void loadRecords() {
    try {
        BufferedReader br = new BufferedReader(new FileReader("metroCards.txt"));

        String line;

        while ((line = br.readLine()) != null) {
            String[] data = line.split(",");

            SmartCard c = new SmartCard(
                Integer.parseInt(data[0]),
                data[1],
                Double.parseDouble(data[2]),
                Integer.parseInt(data[3]),
                data[4],
                data[5],
                data[6]
            );

            cardList.add(c);
            cardCount++;
        }

        br.close();

        System.out.println("Records loaded successfully");

    } catch (FileNotFoundException e) {
        System.out.println("No previous records found");

    } catch (IOException e) {
        System.out.println("Error loading records");
    }
}

}