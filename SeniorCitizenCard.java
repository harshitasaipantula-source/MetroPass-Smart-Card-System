package SMARTMETROACCESSCARD;

public class SeniorCitizenCard extends SmartCard {
private int age;
private String idProofNo;

private static double seniorCitizenConcession=40.00;
public SeniorCitizenCard() {
	super();
	this.age=0;
	this.idProofNo=" ";
}
public SeniorCitizenCard(int cardNumber,String holdername,double balance1,int trips1,String issueDate,String contactNumber,String status,int age,String idproof) throws InelligibleCardException {
super( cardNumber,holdername, balance1, trips1, issueDate, contactNumber, status);
if(age <= 60) {
    throw new InelligibleCardException("Senior citizen must be above 60 years");
}

this.idProofNo=this.idProofNo;
this.age=age;

}
public void setAge(int age) {

	this.age=age;
}
public int getAge() {
	return age;
}
public void setIdProofNo(String id) {
	this.idProofNo=id;
}
public String getId() {
	return this.idProofNo;
}
@Override
public double calculateFare(int stations) {
	
	double fare=super.calculateFare(stations);
	fare=fare-(fare*SeniorCitizenCard.seniorCitizenConcession)/100;
	
return fare;}
@Override
public void display() {
	super.display();
	System.out.println("Age "+this.age);
	System.out.println("Id Proof Number "+this.idProofNo);
}

}
