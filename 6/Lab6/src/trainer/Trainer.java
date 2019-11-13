package trainer;

import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import monster.Monster;

public class Trainer {

	private String name;
	private Set<Monster> monsters;
	
	public Trainer(String name) {
		this.name = name;
		this.monsters = new HashSet<>();
	}
	
	public Map<String, Integer> countMonstersByType() {
		Map<String, Integer> monsterMap = new HashMap<>();
		for (Monster m : monsters) {
			if (monsterMap.get(m.getType()) == null) {
				monsterMap.put(m.getType(), 1);
			} else {
				monsterMap.put(m.getType(), monsterMap.get(m.getType()) + 1);
			}
		}
		return monsterMap;
	}
	
	public boolean hasMonster(Monster monster) {
		return monsters.contains(monster);
	}

	public String getName() {
		return name;
	}

	public boolean removeMonster(Monster monster) {
		return monsters.remove(monster);
	}
	
	public boolean addMonster(Monster monster) {
		return monsters.add(monster);
	}
	
	public String toString() {
		return name + ": " + monsters;
	}
	
	public Collection<Monster> getMonsters() {
		return monsters;
	}
	
}
