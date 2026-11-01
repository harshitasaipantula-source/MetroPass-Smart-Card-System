package SMARTMETROACCESSCARD;

public interface Rechargable {
	boolean recharge(double amount) throws InvalidRechargeException;
	double calculateFare(int stations); // why only this methodes.

}
