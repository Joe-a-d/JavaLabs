import java.util.Arrays;

/**
* Class which simulates a simple text based game, with methods to create "Monster" objects, and methods to update their state
*
* Created by Joao Almeida-Domingues # 2334590D , JP2 - LB3 , University of Glasgow 2019
*/
public class Monster {

	private String type;
	private int hitPoints;
	private String[] attacks;
	private int[] attackPoints;

	/**
	 * Creates a new Monster object with the following parameters:
	 *
	 * @param type
	 * @param hitPoints
	 * @param attacks
	 * @param attackPoints
	 */
	public Monster(String type, int hitPoints, String[] attacks, int[] attackPoints) {

		this.type = type;
		this.hitPoints = hitPoints;
		this.attacks = attacks;
		this.attackPoints = attackPoints;

	}

	public String getType() {
		return type;
	}

	public int getHitPoints() {
		return hitPoints;
	}

	public String[] getAttacks() {
		return attacks;
	}

	public int[] getAttackPoints() {
		return attackPoints;
	}

	/**
	 * Outputs the current Monster Object's fields to screen
	 *
	 */
	public String toString() {
		String state = "State" + "\n Type: " + type + "\n Hit Points: " + hitPoints + "\n Attacks: "
				+ Arrays.toString(attacks) + "\n Attack Points: " + Arrays.toString(attackPoints) + "\n";
		return state;
	}

	/**
	 * Updates monster hitPoints field after an attack, and validates for negative values
	 * @param pointsToRemove
	 */
	public void removeHitPoints(int pointsToRemove) {
		this.hitPoints = this.hitPoints - pointsToRemove;
		if (hitPoints < 0) {
			this.hitPoints = 0;
		}

	}

	/**
	 * Validator.
	 *
	 * Validates user's actions
	 * <p>
	 * 1. Attack_Validator : Iterates through the array of attacks, matching each value against the user input string.
	 * <p>
	 * 2. Self_Attack : Checks if otherMonster == thisMonster
	 * <p>
	 * 3. is_Alive : Checks if either monster is dead
	 * <p>
	 * Calls method to perform attack(i.e. remove points) if (1)=(2)=(3)=True
	 *
	 * @param attack
	 * @param otherMonster
	 */
	public boolean attack(String attack, Monster otherMonster) {
		boolean attackExists = true;
		int attackIndex = 0;

		for (int i = 0; i < this.attacks.length; i++) {

			if (this.attacks[i].equals(attack)) {
				attackIndex = i;
				attackExists = true;
				break;
			} else {
				attackExists = false;
			}

		}
		if (otherMonster.type == this.type) {

			return false;
		} else if ((otherMonster.hitPoints == 0) || (this.hitPoints == 0)) {
			return false;
		} else if (!attackExists) {

			return attackExists;

		} else {
			otherMonster.removeHitPoints(attackPoints[attackIndex]);
			return true;
		}

	}

}
