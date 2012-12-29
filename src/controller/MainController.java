package controller;

import model.*;

public class MainController {

	private Player a;
	private Player b;

	/**
	 * @param args
	 */
	public MainController() {
		a = new Player();
		b = new Player();
	}

	public void init() {

		this.a.addUnit("Eclaireur");

		// b est g�r� par le r�seau on ne le cr�� jamais il est r�cup�r� !
		this.b.addUnit("Eclaireur");

		System.out.println(attack("Eclaireur", "Eclaireur"));
		System.out.println(attack("Eclaireur", "Eclaireur"));
		System.out.println(attack("Eclaireur", "Eclaireur"));
		System.out.println(attack("Eclaireur", "Eclaireur"));

	}

	public static void main(String[] args) {

		MainController controller = new MainController();

		controller.init();

	}

	private String attack(String att, String def) {
		try {
			return a.attackWith(a.getUnit(att), b.getUnit(def));
		} catch (DeadUnitException e) {
			b.delUnit(e.getName());
			return e.getName() + " est mort !";
		} catch (NullPointerException e) {
			return "L'unit� " + def + " n'�xiste pas !";
		}
	}

}
