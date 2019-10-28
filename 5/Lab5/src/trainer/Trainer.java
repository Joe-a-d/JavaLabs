package trainer;

import java.util.ArrayList;

import monster.Attack;
import monster.FireMonster;
import monster.Monster;

/* tutor questions
 * 		
 */
public class Trainer {
	
	private String name;
	private ArrayList<Monster> monsterSet;

	
	

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
		if (!hasMonster(monster)){
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


	public Object countMonstersByType() {
		// TODO Auto-generated method stub
		return null;
	}

	


}
