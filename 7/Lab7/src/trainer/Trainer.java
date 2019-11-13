package trainer;

import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

import monster.Attack;
import monster.ElectricMonster;
import monster.FireMonster;
import monster.Monster;
import monster.WaterMonster;

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
	
	public void saveToFile(String filename) throws IOException {
		// Open the file
		PrintWriter writer = new PrintWriter(Files.newBufferedWriter(Paths.get(filename)));

		writer.println(this.name);

		// Write the monsters one line at a time
		for (Monster monster : monsters) {
			writer.print(monster.getType());
			writer.print("^");
			writer.print(monster.getHitPoints());
			for (Attack attack : monster.getAttacks()) {
				writer.print("^");
				writer.print(attack.getName());
				writer.print("^");
				writer.print(attack.getPoints());
			}
			writer.println();
		}

		// We are done!
		writer.close();
	}
	
	public static Trainer loadFromFile(String filename) throws IOException {
		// Load the whole file into a List<String> in memory
		Path p = Paths.get(filename);
		List<String> lines = Files.readAllLines(p);

		// First line is name
		String name = lines.remove(0);
		Trainer trainer = new Trainer(name);

		// Process the rest of the lines into Monster objects
		for (String line : lines) {
			// Split the line
			String[] fields = line.split("\\^");
			Attack[] attacks = new Attack[(fields.length - 2)/2];
			for (int i = 2; i < fields.length; i+=2) {
				attacks[(i-2)/2] = new Attack(fields[i], Integer.parseInt(fields[i+1]));
			}
			// Use the fields to create a new Monster and add it
			Monster monster = createMonster(fields[0], Integer.parseInt(fields[1]),
					attacks);
			if (monster != null) {
				trainer.addMonster(monster);
			}
		}

		// Return the newly created Trainer
		return trainer;
	}

	public static Monster createMonster(String type, int hitPoints, Attack[] attacks) {
		switch (type) {
		case "Fire":
			return new FireMonster(hitPoints, attacks);
			
		case "Water":
			return new WaterMonster(hitPoints, attacks);
			
		case "Electric":
			return new ElectricMonster(hitPoints, attacks);
		}
		System.err.println("Invalid type: " + type);
		return null;
	}

}
