package model.attack;

import model.exception.DeadUnitException;
import model.units.Unit;

/**
 * @author Aurel
 * 
 */
public class AttackDistance extends Attack {

    private int range;

    public AttackDistance() {
	range = 0;
    }

    public AttackDistance(int i) {
	range = i;
    }

    /**
     * @param i
     * 
     */

    @Override
    public String attack(Unit att, Unit def) throws DeadUnitException {
	int hit = att.getHit();
	int dmg = att.getDmg();
	int crit = att.getCrit();

	if (!canHit(hit))
	    return def.getName() + " a esquiv� l'attaque !";

	System.out.println("dmg avant crit: " + dmg);
	dmg += dmg * ((double) crit / 100);
	System.out.println("dmg apr�s crit: " + dmg);
	dmg -= dmg * ((double) def.getDef() / 100);
	System.out.println("dmg apr�s def: " + dmg);

	def.receiveDmg(dmg);

	return "L'attaque � distance de votre " + att.getName() + " a inflig� "
		+ dmg + " � " + def.getName();
    }

    @Override
    public boolean canAttackFromRange(int i) {
	return i <= range;
    }
    
    @Override
    public int getRange() {
	return range;
    }

}