import java.util.Arrays;

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

	public String toString() {
		String state = "State" + "\n Type: " + type + "\n Hit Points: " + hitPoints
				+ "\n Attacks: " + Arrays.toString(attacks) + "\n Attack Points: " + Arrays.toString(attackPoints) + "\n";
		return state;
	}

	public void removeHitPoints(int pointsToRemove) {
		int hitPoints = this.hitPoints - pointsToRemove;
		System.out.println("removing" + pointsToRemove + " from " + this.type );
		if (hitPoints < 0) {
			this.hitPoints = 0;
		}

	}

	/**
	 * Attack Validator.
	 * 
	 * @param attack
	 * @param otherMonster
	 * @return
	 */
	public boolean attack(String attack, Monster otherMonster) {
		boolean attackExists = true;
		int attackIndex = 0;

		for (int i = 0; i < this.attacks.length; i++) {
			System.out.println(i+"   "+attack+"     "+this.attacks[i]+"      " + attackExists);
			if (this.attacks[i].equals(attack)) {
				attackIndex = i;
				break;
			} else {
				attackExists = false;
			}

		}
		if (otherMonster.type == this.type) {
			System.out.println("type");
			return false;
		} else if ((otherMonster.hitPoints == 0) || (this.hitPoints == 0)) {
			System.out.println("dead");
			return false;
		} else if (!attackExists) {
			System.out.println("attack");
			return attackExists;
		
		} else {
			System.out.println(attackIndex + "  inex / attack      " + attackPoints[attackIndex]);
			otherMonster.removeHitPoints(attackPoints[attackIndex]);
			return true;
		}

	}


	public static void main(String[] args) {
		Monster moltres = new Monster("Fire", 114,
				new String[] { "Fire Spin", "Overheat" },
				new int[] { 14, 160 });
				Monster articuno = new Monster("Ice", 112,
				new String[] { "Frost Breath", "Ice Beam" },
				new int[] { 10, 90 });
				Monster zapdos = new Monster("Electric", 119,
				new String[] { "Charge Beam", "Thunder" },
				new int[] { 8, 100 });
		/*
		 * System.out.println(moltres); System.out.println(articuno);
		 * System.out.println(zapdos);
		 */		System.out.println(moltres);
				System.out.println(moltres.attack("Fire Spin", articuno)); // true
				System.out.println(moltres.getHitPoints()); // 114
				System.out.println(articuno.getHitPoints()); // 98
				System.out.println(moltres);
				System.out.println(moltres.attack("Overheat", zapdos)); // true
				System.out.println(zapdos.getHitPoints()); // 0
				
				System.out.println(moltres.attack("Bad Attack", articuno)); // false
				System.out.println(articuno.getHitPoints()); // 98
				System.out.println(articuno.attack("Ice Beam", articuno)); // false
				System.out.println(articuno.getHitPoints()); // 98
				System.out.println(zapdos.attack("Thunder", moltres)); // false
	}
}