package battle;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import monster.Attack;
import monster.ElectricMonster;
import monster.FireMonster;
import monster.Monster;
import monster.MonsterException;
import monster.WaterMonster;

public class Utils {
	
	public static List<Monster> generateMonsters(int num) {
		List<Monster> monsters = new ArrayList<>(num);
		for (int i = 0; i < num; i++) {
			monsters.add(generateMonster("Monster" + (i+1)));
		}
		return monsters;
	}

	private static Monster generateMonster(String name) {
		switch (RAND.nextInt(3)) {
		case 0:
			return new FireMonster(name, Utils.RAND.nextInt(200), Utils.generateAttacks());
			
		case 1:
			return new WaterMonster(name, Utils.RAND.nextInt(200), Utils.generateAttacks());
			
		case 2:
			return new ElectricMonster(name, Utils.RAND.nextInt(200), Utils.generateAttacks());
		}
		return null;
	}

	private static Attack[] generateAttacks() {
		// Between one and three attacks
		int numAttacks = RAND.nextInt(3) + 1;
		Attack[] attacks = new Attack[numAttacks];
		for (int i = 0; i < numAttacks; i++) {
			attacks[i] = new Attack("Attack" + (i+1), RAND.nextInt(150));
		}
		return attacks;
	}

	public static void doAttack(Monster attacker, Monster defender) {
		System.out.println(attacker.getName() + " attacks " + defender.getName());
		
		// Choose an attack to use
		Attack[] attacks = attacker.getAttacks();
		String attack = attacks[Utils.RAND.nextInt(attacks.length)].getName();
		try {
			System.out.println("Selected attack: " + attack);
			attacker.attack(attack, defender);
		} catch (MonsterException e) {
			e.printStackTrace();
		}
	}

	public static Random RAND = new Random();

}
