package model.units;

import model.attack.AttackCaC;

/**
 * @author Aurel
 * 
 */
public class FantassinPegasus extends Unit {

	public FantassinPegasus() {
		super(new AttackCaC(), 220, 50, 85, 5, 25, 15, 0, Weapon.SWORD,
				"Fantassin");

	}

	@Override
	public void activatePower() {
		pow = true;
	}

	@Override
	public void addRegeneration() {
		this.hp += 10;
		if (isPowActivate())
			this.hp += 5;
		if (this.hp > this.maxHp)
			this.hp = this.maxHp;
	}

}
