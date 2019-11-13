

/**
 * 
 */

/**
 * JP2 - LB4 , University of Glasgow 2019
 * <p>
 * @author Joao Almeida-Domingues 2334590D
 * 
 *
 */
public class WaterMonster extends Monster {

	/**
	 * @param type
	 * @param hitPoints
	 * @param attacks
	 */
	public WaterMonster (int hitPoints, Attack[] attacks) {
		super("Water",hitPoints, attacks);
		
	}
	
	protected boolean dodge() {
		if(this.hitPoints >=100) {
			return true;
		}else {
			return false;
		}
	}

}
