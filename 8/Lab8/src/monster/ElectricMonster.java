package monster;

public class ElectricMonster extends Monster {

	public ElectricMonster(String name, int hitPoints, Attack[] attacks) {
		super(name, "Electric", hitPoints, attacks);
	}

	@Override
	public boolean dodge() {
		return false;
	}

}
