package trainer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import org.junit.Assert;

import monster.Attack;
import monster.FireMonster;
import monster.Monster;
import monster.WaterMonster;


/*
 * TUTOR:
 * 	-> Are we assuming that each monster traits are unique? 
 * 				If not, how are names linked to Objects, mapping possible, but test doesn't pass string,
 * 					just the variable name containing the Monster instance
 */

public class Trainer {
	
	private String name;
	private ArrayList<Monster> monsterSet = new ArrayList<Monster> ();
	private Map<String,Integer> monsterMap = new HashMap<>();

	
	

	public Trainer(String name) {
		this.name = name;
		
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}
	
	public boolean addMonster(Monster monster) {
		if (this.hasMonster(monster)){
			return false;
		}else {
			this.monsterSet.add(monster);
			return true;
		}
	}


	public boolean hasMonster(Monster monster) {
		if(this.monsterSet.contains(monster)) {
			return true;
		}else {
			return false;
		}
	}


	public boolean removeMonster(Monster monster) {
		if (!hasMonster(monster)) {
			return true;
		}else {
			this.monsterSet.remove(monster);
			return false;
		}
		
	}

	public Map<String,Integer> countMonstersByType() {
		int countFire=0; int countElectric = 0; int countWater = 0;
		for(Monster monster : this.monsterSet) {
			switch(monster.getType()) {
			
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
		return "*****Trainer****" + "\n" + " Name = " + name + "\n" + " Monsters = "  + monsterSet  + "\n" + " #Monsters = " + monsterMap + "\n" + "****************" ;
	}


	public static void main(String[] args) {
		FireMonster charizard = new FireMonster (130, new Attack[] {
				new Attack("Fire Spin", 14), new Attack("Blast Burn", 110),
				new Attack("Overheat", 160)});
		WaterMonster asda = new WaterMonster (120, new Attack[] {
				new Attack("Fire Spin", 14), new Attack("Blast Burn", 110),
				new Attack("Overheat", 160)});

		Trainer ethan = new Trainer("ethan");
		ArrayList<Monster> ms = ethan.monsterSet;

		System.out.println(ethan.addMonster(charizard));
		System.out.println(ethan.addMonster(asda));
		//System.out.println(ms);
		//System.out.println(ethan.removeMonster(charizard));
		//System.out.println(ms);
		
		//System.out.println("monster".equalsIgnoreCase(Monster.class.getPackageName()));
		System.out.println(ethan.monsterSet);
		System.out.println(ethan.countMonstersByType());
		
		Map<String, Integer> expected = new HashMap<>();
		expected.put("Water", 1);
		expected.put("Fire", 1);
		expected.put("Electric",0);

		System.out.println(expected);
		System.out.println(expected.equals(ethan.countMonstersByType()));
		
		System.out.println(ethan.toString());
		
		
		
	
	
	}
	
	
	
}
