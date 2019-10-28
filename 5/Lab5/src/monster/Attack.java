package monster;


public class Attack {
	private String name;
	private int points;
	
	public Attack(String name, int points) {
		this.name = name;
		this.points = points;
	}

	public String getName() {
		return name;
	}

	public int getPoints() {
		return points;
	}

	@Override
	public String toString() {
		return "Attack [name=" + name + ", points=" + points + "]";
	}
	
}
