package trainer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import monster.*;


public class Trainer {

/* Data Structures
 * 	monsterSet contains an array of Monster objects for the current Trainer
 *  monsterMap creates a map (k,v) where k = Monster type and v = Type count 
 */
	private String name;
	private ArrayList<Monster> monsterSet = new ArrayList<Monster>();
	private Map<String, Integer> monsterMap = new HashMap<>();

	public Trainer(String name) {
		this.name = name;

	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Adds the monster passed as argument to the trainer's set if monster is not already in {@link Trainer#monsterSet}
	 * @param monster
	 * @return Boolean
	 */
	public boolean addMonster(Monster monster) {
		if (this.hasMonster(monster)) {
			return false;
		} else {
			this.monsterSet.add(monster);
			return true;
		}
	}

	/**
	 * 
	 * Checks if a given monster is in {@link Trainer#monsterSet}
	 * @param monster
	 * @return Boolean
	 */
	public boolean hasMonster(Monster monster) {
		return this.monsterSet.contains(monster);
	}

	/**
	 * Removes the monster passed as an argument from the trainer's set if monster is not already in {@link Trainer#monsterSet}.
	 * @param monster
	 * @return Boolean
	 */
	public boolean removeMonster(Monster monster) {
		if (!this.hasMonster(monster)) {
			return false;
		} else {
			this.monsterSet.remove(monster);
			return true;
		}

	}

	/**
	 * Creates {@link Trainer#monsterMap}
	 * @return Boolean 
	 */
	public Map<String, Integer> countMonstersByType() {
		int countFire = 0;
		int countElectric = 0;
		int countWater = 0;
		for (Monster monster : this.monsterSet) {
			switch (monster.getType()) {

			case "Fire":
				countFire++;
				break;
			case "Electric":
				countElectric++;
				break;
			case "Water":
				countWater++;
				break;

			}
		}

		this.monsterMap.put("Fire", countFire);
		this.monsterMap.put("Electric", countElectric);
		this.monsterMap.put("Water", countWater);

		return this.monsterMap;
	}

	@Override
	public String toString() {
		return "*****Trainer****" + "\n" + " Name = " + name + "\n" + " Monsters = " + monsterSet + "\n"
				+ " #Monsters = " + monsterMap + "\n" + "****************";
	}

	
}
