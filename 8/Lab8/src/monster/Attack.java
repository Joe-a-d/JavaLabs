package monster;

import java.util.Objects;

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
		return name + " (" + points + ")";
	}

	@Override
	public int hashCode() {
		return Objects.hash(name, points);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Attack other = (Attack) obj;
		return Objects.equals(name, other.name) && points == other.points;
	}
	
}
