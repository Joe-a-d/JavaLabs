import java.util.Arrays;
import java.util.ArrayList;

public abstract class Monster {

	/** The type */
	public String type;
	/** Current hit points */
	protected int hitPoints;
	/** List of attack Objects*/
	protected Attack[] attacks;

	/**
	 * Creates a new Monster with the given properties.
	 * 
	 * @param type         The type to use
	 * @param hitPoints    The initial hit points
	 * @param attacks      The list of attacks
	 * @param attackPoints The list of points for each attack
	 */
	

	public Monster(String type, int hitPoints, Attack[] attacks) {
		this.type = type;
		this.hitPoints = hitPoints;
		this.attacks = attacks;
	}

	/**
	 * Returns the current hit points of this Monster.
	 * 
	 * @return The current hit points
	 */
	public int getHitPoints() {
		return hitPoints;
	}

	/**
	 * Returns the type of this Monster.
	 * 
	 * @return The monster type
	 */
	public String getType() {
		return this.type;
	}

	/**
	 * Returns the list of attack objects available to this Monster.
	 * 
	 * @return The list of attacks
	 */
	public Attack[] getAttacks() {
		return attacks;
	}


	
	/**
	 * Attacks the given other monster, and returns a Boolean value indicating
	 * whether the attack was successful. An attack fails if otherMonster is
	 * equal to this monster, if either this Monster or otherMonster is
	 * knocked out, or if the given attack name is not valid. If the attack
	 * succeeds, the corresponding hit points are removed from otherMonster;
	 * if it fails, no changes are made to either Monster.
	 * 
	 * @param attack The attack to use
	 * @param otherMonster The Monster to attack
	 * @return True if the attack was successful, and false if not
	 */
	public void attack (String attack, Monster otherMonster) throws MonsterException {
		// A monster cannot attack itself
		if (otherMonster == this.type) {
			throw new MonsterException("A monster cannot attack itself");
		}

		// A monster cannot attack or be attacked if it is knocked out
		if (this.hitPoints <= 0 || otherMonster.getHitPoints() <= 0) {
			throw new MonsterException("You can't fetch the coffee 'cause you're dead");
		}

		// Find the attack -- if it exists, use it and return true, otherwise
		// do nothing and return false
		int points = getAttacks();
		if (points < 0) {
			throw new MonsterException("Attack not found");
		} else {
			boolean dodge = otherMonster.dodge();
			if(dodge){
				removeHitPoints(10);
			}else{
				otherMonster.removeHitPoints(points);
			}
		}
	}

	/**
	 * Removes the given hit points from the current monster. If the hit 
	 * points would go below zero, it is set to zero.
	 * 
	 * @param points The number of points to remove
	 */
	public void removeHitPoints(int points) {
		this.hitPoints -= points;
		if (hitPoints <= 0) {
			// Monster is knocked out
			hitPoints = 0;
		}
	}
	
	protected abstract boolean dodge() ;

	@Override
	/**
	 * Returns a nice String representation of this Monster.
	 */
	public String toString() {
		return "Monster [type=" + type + ", hitPoints=" + hitPoints + ", attacks=" + Arrays.toString(attacks) + " "
				+ Arrays.toString(attackPoints);
	}

	

}
