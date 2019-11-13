package monster;

public class ElectricMonster extends Monster {

	public ElectricMonster(int hitPoints, Attack[] attacks) {
		super("Electric", hitPoints, attacks);
	}

	@Override
	public boolean dodge() {
		return false;
	}

}
