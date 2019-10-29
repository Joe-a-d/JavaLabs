package trainer;

import monster.* ;

public class Trade {
	
	private Trainer trainer1;
	private Trainer trainer2;
	private Monster monster1;
	private Monster monster2;
	
	/**
	 * 
	 * Checks if monster is in each trainer's {@link Trainer#monsterSet}; if it is not 
	 *	an exception is thrown, otherwise a new Trainer object is created
	 * @param trainer1
	 * @param monster1
	 * @param trainer2
	 * @param monster2
	 * @throws MonsterException
	 */
	public Trade(Trainer trainer1 , Monster monster1 , Trainer trainer2, Monster monster2)
		throws MonsterException{
		
		if(!trainer1.hasMonster(monster1) || !trainer2.hasMonster(monster2)) {
			throw new MonsterException("At least one of the trainers does not have that monster in hers set");
		}else {
			this.trainer1 = trainer1;
			this.monster1 = monster1;
			this.trainer2 = trainer2;
			this.monster2 = monster2;
		}
		
	}
	
	/**
	 * Performs trade by calling the add and remove methods from the {@link Trainer} Class
	 */
	
	public void doTrade() {
		trainer1.addMonster(monster2);
		trainer1.removeMonster(monster1);
		trainer2.addMonster(monster1);
		trainer2.removeMonster(monster2);
		
	}

}

	