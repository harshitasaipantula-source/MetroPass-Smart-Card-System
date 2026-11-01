package SMARTMETROACCESSCARD;

public class StudentCard extends SmartCard {
private String instituitionId;
private static double studentConcessionPercent=25.00;
public StudentCard() {
	super();
	this.instituitionId=" ";
}
public StudentCard(int cardNumber,String holdername,double balance1,int trips1,String issueDate,String contactNumber,String status,String instId) {
super( cardNumber,holdername, balance1, trips1, issueDate, contactNumber, status);
this.instituitionId=instId;
}
@Override
public double calculateFare(int stations) {
	double fare=super.calculateFare(stations);
	fare=fare-(fare*StudentCard.studentConcessionPercent)/100;
	
return fare;
}
@Override
public void display() {
	super.display();
	System.out.println("Instituition ID "+this.instituitionId);
}
public void setInstituitionId(String id) {

	this.instituitionId=id;
}
public String getInstituitionId() {
	return this.instituitionId;
}


}
