package controller;

import model.units.Unit;

import java.util.Hashtable;

public class TurnController {

    public static int numberTurn = 0;

    private Hashtable<Unit, Boolean> hasAttack;
    private Hashtable<Unit, Boolean> hasMove;
    private Hashtable<Unit, Boolean> hasMoveAfterAttack;
    private Hashtable<Unit, Boolean> isCrippled;
    private Hashtable<Unit, Boolean> isPoisoned;

    public TurnController(Hashtable<String, Unit> unitsA, Hashtable<String, Unit> unitsB) {

	numberTurn++;

	hasAttack = new Hashtable<Unit, Boolean>();
	hasMove = new Hashtable<Unit, Boolean>();
	hasMoveAfterAttack = new Hashtable<Unit, Boolean>();
	isCrippled = new Hashtable<Unit, Boolean>();
	isPoisoned = new Hashtable<Unit, Boolean>();

	for (Unit u : unitsA.values()) {
	    hasAttack.put(u, false);
	    hasMove.put(u, false);
	    hasMoveAfterAttack.put(u, false);
	    int i = u.getTurnsCripple();
	    if(i > 1) {
		System.out.println(u.getName() + " reste " + i + "cripple.");
		isCrippled.put(u, true);
		u.setTurnsCripple(i - 1);
	    } else {
		isCrippled.put(u, false);
		u.setTurnsCripple(0);
		System.out.println(u.getName() + " reste " + i + "cripple.");
	    }
		
	    i = u.getTurnsPoisoned();
	    if (i > 1) {
		System.out.println(u.getName() + " reste " + i + "poison.");
		isPoisoned.put(u, true);
		u.setTurnsPoisoned(i - 1);
	    } else {
		isPoisoned.put(u,false);
		u.setTurnsPoisoned(0);
		System.out.println(u.getName() + " reste " + i + "poison.");
	    }
		
	}
	
	for (Unit u : unitsB.values()) {
	    int i = u.getTurnsCripple();
	    if(i >= 1) {
		isCrippled.put(u, true);
	    } else {
		isCrippled.put(u, false);
	    }
	    i = u.getTurnsPoisoned();
	    if (i >= 1) {
		System.out.println(u.getName() + " reste " + i + "poison.");
		isPoisoned.put(u, true);
	    } else {
		isPoisoned.put(u, false);
		System.out.println(u.getName() + " reste " + i + "poison.");
	    }
	}
    }

    public boolean isCrippled(Unit u) {
	return isCrippled.get(u);
    }

    public boolean isPoisoned(Unit u) {
	return isPoisoned.get(u);
    }
    
    public void setPoisoned(Unit u) {
	isPoisoned.put(u, true);
    }
    
    public void setCrippled(Unit u) {
	isCrippled.put(u, true);
    }
    

    public void setHasAttack(Unit u) {
	hasAttack.put(u, true);
    }

    public void setHasMove(Unit u) {
	hasMove.put(u, true);
    }

    public void setHasMoveAfterAttack(Unit u) {
	hasMoveAfterAttack.put(u, true);
    }

    public boolean hasAttack(Unit u) {
	return hasAttack.get(u);
    }

    public boolean hasMove(Unit u) {
	return hasMove.get(u);
    }

    public boolean hasMoveAfterAttack(Unit u) {
	return hasMoveAfterAttack.get(u);
    }

}
