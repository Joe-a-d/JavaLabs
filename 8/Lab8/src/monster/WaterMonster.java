package monster;

public class WaterMonster extends Monster {

	public WaterMonster(String name, int hitPoints, Attack[] attacks) {
		super(name, "Water", hitPoints, attacks);
	}


	@Override
	public boolean dodge() {
		// only if hit points is at least 100
		return hitPoints >= 100;
	}
}
