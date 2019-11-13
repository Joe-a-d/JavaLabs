package monster;
public class FireMonster extends Monster {

	private boolean lastDodge = true;
	
	public FireMonster(int hitPoints, Attack[] attacks) {
		super("Fire", hitPoints, attacks);
	}

	@Override
	public boolean dodge() {
		lastDodge = !lastDodge;
		return !lastDodge;
	}

}
