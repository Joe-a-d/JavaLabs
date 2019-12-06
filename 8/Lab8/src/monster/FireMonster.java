package monster;
public class FireMonster extends Monster {

	private boolean lastDodge = true;
	
	public FireMonster(String name, int hitPoints, Attack[] attacks) {
		super(name, "Fire", hitPoints, attacks);
	}

	@Override
	public boolean dodge() {
		lastDodge = !lastDodge;
		return !lastDodge;
	}

}
