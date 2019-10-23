import java.util.ArrayList;

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
public class ElectricMonster extends Monster {

	/**
	 * @param type
	 * @param hitPoints
	 * @param attacks
	 */
	public ElectricMonster (int hitPoints, ArrayList<Attack> attacks) {
		super("Electric",hitPoints, attacks);
		
	}
	
	protected boolean dodge() {
		return false;
	}

}
