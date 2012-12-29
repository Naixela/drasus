package model;

public class AttackCaCBerserker extends AttackCaC {

	@Override
	public String attack(Unit att, Unit def) throws DeadUnitException {
		int hit = att.getHit();
		int dmg = att.getDmg();
		int crit = att.getCrit();

		if (trinityAdvantage(def.getWep(), att.getWep()))
			hit = hit * 1 / 3;
		if (trinityAdvantage(att.getWep(), def.getWep()))
			crit += 5;

		if (!canHit(hit))
			return def.getName() + " a esquiv� l'attaque !";

		System.out.println("dmg avant crit: " + dmg);
		dmg += dmg * ((double) crit / 100);
		System.out.println("dmg apr�s crit: " + dmg);
		dmg -= dmg * ((double) def.getDef() / 100);
		System.out.println("dmg apr�s def: " + dmg);

		def.receiveDmg(dmg);
		if (!((int) (Math.random() * (101)) < 15))
			return "L'attaque de votre " + att.getName() + " a inflig� " + dmg
					+ " � " + def.getName();
		def.receiveDmg(dmg);
		return "La double attaque de votre " + att.getName() + " a inflig� "
				+ dmg * 2 + " � " + def.getName();
	}

}
