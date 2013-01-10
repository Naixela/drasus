package model.attack;

import model.exception.DeadUnitException;
import model.units.Unit;

public class AttackDistanceArcher extends AttackDistance {

    public AttackDistanceArcher() {
	super(0);
    }

    public AttackDistanceArcher(int i) {
	super(i);
    }
    
    @Override
    public String attack(Unit att, Unit def) throws DeadUnitException {
	int hit = att.getHit();
	int dmg = att.getDmg();
	int crit = att.getCrit();

	if (!canHit(hit))
	    return def.getName() + " a esquiv� l'attaque !";

	dmg += dmg * ((double) crit / 100);
	dmg -= dmg * ((double) def.getDef() / 100);

	def.receiveDmg(dmg);

	return "L'attaque � distance de votre " + att.getName() + " a inflig� "
		+ dmg + " � " + def.getName();
    }

}
