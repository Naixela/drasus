package model;

public class DeadUnitException extends Exception {

	public DeadUnitException() {
		System.out.println("L'unit� est morte !");
	}
	
	public DeadUnitException(String str) {
		System.out.println("L'unit� " + str + " est morte !");
	}

}
