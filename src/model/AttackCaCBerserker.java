package model;

public class AttackCaCBerserker extends AttackCaC {

	@Override
	public String attack(Unit att, Unit def) throws DeadUnitException {
		
		if(!canHit(att.getHit()))
			return  def.getName() + " a esquiv� l'attaque !";
		
		int dmg = att.getDmg();
		def.receiveDmg(att.getDmg());
		
		return "L'attaque de " + att.getName() + " a inflig� " + dmg + " � " + def.getName();
	}

}
