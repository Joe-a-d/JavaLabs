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
public class FireMonster extends Monster {

	boolean call = true ;
	/**
	 * @param type
	 * @param hitPoints
	 * @param attacks
	 */
	public FireMonster (int hitPoints, ArrayList<Attack> attacks) {
		super("Fire",hitPoints, attacks);
		
	}
	
	protected boolean dodge() {
		if(call){
			call = false;
			return true;
		}else {
			call = true;
			return false;
		}
	}

}
