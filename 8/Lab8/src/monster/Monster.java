package monster;
import java.util.Arrays;
import java.util.Objects;

/**
 * Sample solution to JP2 lab 3 2019. Represents a monster in a battling game.
 * 
 * @author mefoster
 *
 */
public abstract class Monster implements Comparable<Monster> {

	/** The type */
	protected String type;
	/** Current hit points */
	protected int hitPoints;
	/** List of attacks */
	protected Attack[] attacks;
	/** Name */
	protected String name;
	
	/**
	 * Creates a new Monster with the given properties.
	 * 
	 * @param type         The type to use
	 * @param hitPoints    The initial hit points
	 * @param attacks      The list of attacks
	 */
	public Monster(String name, String type, int hitPoints, Attack[] attacks) {
		this.name = name;
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
	 * Returns the list of attacks available to this Monster.
	 * 
	 * @return The list of attacks
	 */
	public Attack[] getAttacks() {
		return attacks;
	}
	
	/**
	 * Returns the monster's name
	 * 
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * Helper method to find the points for the given attack.
	 * 
	 * @param attack The attack to look for
	 * @return The corresponding points, or -1 if the attack is not found
	 */
	private int getAttackPoints(String attackName) throws MonsterException {
		for (Attack attack : attacks) {
			if (attack.getName().equals(attackName)) {
				return attack.getPoints();
			}
		}
		throw new MonsterException("Invalid attack name: " + attackName);
	}

	/**
	 * Attacks the given other monster. An attack fails if otherMonster is
	 * equal to this monster, if either this Monster or otherMonster is
	 * knocked out, or if the given attack name is not valid. If the attack
	 * succeeds, the corresponding hit points are removed from otherMonster;
	 * if it fails, no changes are made to either Monster.
	 * 
	 * @param attack The attack to use
	 * @param otherMonster The Monster to attack
	 */
	public void attack(String attack, Monster otherMonster) throws MonsterException {
		// A monster cannot attack itself
		if (otherMonster == this) {
			throw new MonsterException("A monster cannot attack itself");
		}

		// A monster cannot attack or be attacked if it is knocked out
		if (this.hitPoints <= 0) {
			throw new MonsterException("Attacking monster is knocked out");
		}
		if (otherMonster.hitPoints <= 0) {
			throw new MonsterException("Attacked monster is knocked out");
		}

		// Check if the other monster has dodged
		if (!otherMonster.dodge()) {
			// Find the attack -- if it exists, use it and return true, otherwise
			// do nothing and return false
			otherMonster.removeHitPoints(getAttackPoints(attack));
		} else {
			this.removeHitPoints(10);
		}
	}
	
	/**
	 * An abstract method used when a monster might dodge in battle.
	 * @return Whether the monster dodges
	 */
	protected abstract boolean dodge();

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
	
	@Override
	/**
	 * Returns a nice String representation of this Monster.
	 */
	public String toString() {
		return name + ": " + type + " (" + hitPoints + "): " + Arrays.toString(attacks);
	}

	public int compareTo(Monster otherMonster) {
		int result = Integer.compare(otherMonster.hitPoints, this.hitPoints);
		if (result == 0) {
			return this.type.compareTo(otherMonster.type);
		} else {
			return result;
		}
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + Arrays.hashCode(attacks);
		result = prime * result + Objects.hash(hitPoints, type);
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Monster other = (Monster) obj;
		return Arrays.equals(attacks, other.attacks) && hitPoints == other.hitPoints
				&& Objects.equals(type, other.type);
	}
	
	
}
