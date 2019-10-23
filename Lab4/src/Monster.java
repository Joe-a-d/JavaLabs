import java.util.Arrays;
import java.util.ArrayList;

public abstract class Monster {

	/** The type */
	public String type;
	/** Current hit points */
	protected int hitPoints;
	/** List of attack Objects*/
	protected ArrayList<Attack> attacks;

	/**
	 * Creates a new Monster with the given properties.
	 * 
	 * @param type         The type to use
	 * @param hitPoints    The initial hit points
	 * @param attacks      The list of attacks
	 * @param attackPoints The list of points for each attack
	 */
	public Monster(String type, int hitPoints, ArrayList<Attack> attacks) {
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
	public ArrayList<Attack> getAttacks() {
		return attacks;
	}


	/**
	 * Helper method to find the points for the given attack.
	 * 
	 * @param attack The attack to look for
	 * @return The corresponding points, or -1 if the attack is not found
	 */
	private int getAttackPoints(String attack) {
		// Look through the list and find the position of the attack
		int pos = -1;
		for (int i = 0; i < attacks.length; i++) {
			if (attacks[i].equals(attack)) {
				pos = i;
				break;
			}
		}
		
		// If we found it, return the points; otherwise, return -1
		if (pos >= 0) {
			return attackPoints[pos];
		} else {
			return -1;
		}
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
		if (otherMonster == this) {
			throw new MonsterException("A monster cannot attack itself");
		}

		// A monster cannot attack or be attacked if it is knocked out
		if (this.hitPoints <= 0 || otherMonster.getHitPoints() <= 0) {
			throw new MonsterException("You can't fetch the coffee 'cause you're dead");
		}

		// Find the attack -- if it exists, use it and return true, otherwise
		// do nothing and return false
		int points = getAttackPoints(attack);
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

	/**
	 * A main method to test the Monster class.
	 * 
	 * @param args
	 */
	/*public static void main(String[] args) {
		Monster moltres = new Monster("Fire", 114, new String[] { "Fire Spin", "Overheat" }, new int[] { 14, 160 });
		Monster articuno = new Monster("Ice", 112, new String[] { "Frost Breath", "Ice Beam" }, new int[] { 10, 90 });
		Monster zapdos = new Monster("Electric", 119, new String[] { "Charge Beam", "Thunder" }, new int[] { 8, 100 });

		System.out.println(moltres);
		System.out.println(articuno);
		System.out.println(zapdos);

		System.out.println(moltres.attack("Fire Spin", articuno)); // true
		System.out.println(moltres.getHitPoints()); // 114
		System.out.println(articuno.getHitPoints()); // 98

		System.out.println(moltres.attack("Overheat", zapdos)); // true
		System.out.println(zapdos.getHitPoints()); // 0

		System.out.println(moltres.attack("Bad Attack", articuno)); // false
		System.out.println(articuno.getHitPoints()); // 98

		System.out.println(articuno.attack("Ice Beam", articuno)); // false
		System.out.println(articuno.getHitPoints()); // 98

		System.out.println(zapdos.attack("Thunder", moltres)); // false
	}*/

}
