package trainer;

import monster.Monster;
import monster.MonsterException;

public class Trade {

	private Trainer trainer1, trainer2;
	private Monster monster1, monster2;

	public Trade(Trainer trainer1, Monster monster1, Trainer trainer2, Monster monster2) throws MonsterException {
		this.trainer1 = trainer1;
		this.trainer2 = trainer2;
		this.monster1 = monster1;
		this.monster2 = monster2;
		
		if (!trainer1.hasMonster(monster1)) {
			throw new MonsterException(monster1 + " is not one of " + trainer1.getName() + "'s monsters");
		}
		if (!trainer2.hasMonster(monster2)) {
			throw new MonsterException(monster2 + " is not one of " + trainer2.getName() + "'s monsters");
		}
	}

	public void doTrade() {
		trainer1.removeMonster(monster1);
		trainer2.removeMonster(monster2);
		trainer1.addMonster(monster2);
		trainer2.addMonster(monster1);
	}
}
