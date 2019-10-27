
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

import org.junit.Assert;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TestMonster {

	// Test fixture -- named by my son :)
	private Monster charizard, gyarados, magikarp, luvdisc, alolanGolem, charmander;

	@BeforeEach
	void setUp() throws Exception {
		charizard = new FireMonster (130, new Attack[] {
				new Attack("Fire Spin", 14), new Attack("Blast Burn", 110),
				new Attack("Overheat", 160)});
		gyarados = new WaterMonster (169, new Attack[] {
				new Attack("Waterfall", 16), new Attack("Outrage", 110),
				new Attack("Hydro Pump", 130)});
		magikarp = new WaterMonster (58, new Attack[] {
				new Attack("Splash", 0), new Attack("Struggle", 35)
		});
		luvdisc = new WaterMonster (100, new Attack[] {
				new Attack("Splash", 0), new Attack("Draining Kiss", 60)
		});
		alolanGolem = new ElectricMonster(154, new Attack[] {
				new Attack("Rock Throw", 12), new Attack("Wild Charge", 90)
		});
		charmander = new FireMonster(0, new Attack[] {
				new Attack("Scratch", 6), new Attack("Flame Charge", 70)
		});
	}

	@AfterEach
	void tearDown() throws Exception {
		charizard = null;
		gyarados = null;
		magikarp = null;
		luvdisc = null;
		alolanGolem = null;
	}

	@Test
	public void dodgeExistsAndHasCorrectSignature() {
		try {
			Method m = Monster.class.getDeclaredMethod("dodge", new Class[0]);
			Assert.assertTrue("Monster.dodge is not protected", Modifier.isProtected(m.getModifiers()));
			Assert.assertTrue("Monster.dodge is not abstract", Modifier.isAbstract(m.getModifiers()));
			Assert.assertEquals("Monster.dodge method does not have correct return type",
					boolean.class, m.getReturnType());
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
			Assert.fail("Monster.dodge method not found");
		}
	}

	@Test
	public void fireMonsterDodgeWorksProperly() {
		Assert.assertTrue("First call to FireMonster.dodge() should return true",
				charizard.dodge());
		Assert.assertFalse("Second call to FireMonster.dodge() should return false",
				charizard.dodge());
		Assert.assertTrue("Third call to FireMonster.dodge() should return true",
				charizard.dodge());
	}
	
	@Test
	public void waterMonsterDodgeWorksProperly() {
		Assert.assertTrue("WaterMonster.dodge() should return true if HP is over 100",
				gyarados.dodge());
		Assert.assertFalse("WaterMonster.dodge() should return false if HP is less than 100",
				magikarp.dodge());
		Assert.assertTrue("WaterMonster.dodge() should return true if HP is exactly 100",
				luvdisc.dodge());
	}
	
	@Test
	public void electricMonsterDodgeWorksProperly() {
		Assert.assertFalse("ElectricMonster.dodge() should always return false",
				alolanGolem.dodge());
		Assert.assertFalse("ElectricMonster.dodge() should always return false",
				alolanGolem.dodge());
		Assert.assertFalse("ElectricMonster.dodge() should always return false",
				alolanGolem.dodge());
	}
	
	@Test
	public void attackThrowsExceptionIfAttackSelf() {
		Assertions.assertThrows(MonsterException.class, 
				() -> { charizard.attack("Fire Spin", charizard); });
	}
	
	public void attackDoesNotThrowExceptionIfAttackTwin() {
		FireMonster fm2 = new FireMonster(charizard.getHitPoints(), charizard.getAttacks());
		try {
			fm2.attack("Fire Spin", charizard);
		} catch (MonsterException ex) {
			Assert.fail("attack() should not throw an exception when a monster attacks an identical monster");
		}
	}
	
	@Test
	public void attackThrowsExceptionIfAttackerIsKO() {
		Assertions.assertThrows(MonsterException.class, 
				() -> { charmander.attack("Scratch", charizard); });
	}
	
	@Test
	public void attackThrowsExceptionIfAttackedIsKO() {
		Assertions.assertThrows(MonsterException.class, 
				() -> { charizard.attack("Blast Burn", charmander); });
	}
	
	@Test
	public void attackThrowsExceptionIfAttackNameWrong() {
		Assertions.assertThrows(MonsterException.class, 
				() -> { charizard.attack("BlastBurn", charmander); });
	}

	@Test
	public void fireMonsterTypeCorrect() {
		Assert.assertEquals("Fire monster type incorrect", "Fire", charizard.getType());
	}
	
	@Test
	public void waterMonsterTypeCorrect() {
		Assert.assertEquals("Water monster type incorrect", "Water", gyarados.getType());
	}
	
	@Test
	public void electricMonsterTypeCorrect() {
		Assert.assertEquals("Electric monster type incorrect", "Electric", alolanGolem.getType());
	}
	
	@Test
	public void attackUsesDodge() {
		try {
			// This will not dodge
			gyarados.attack("Waterfall", magikarp);
			Assert.assertEquals("Attacking monster HP should not be lower after no dodge", 169, gyarados.getHitPoints());
			Assert.assertEquals("Attacked monster HP should be lower after attack", 42, magikarp.getHitPoints());
			
			// This will dodge
			alolanGolem.attack("Rock Throw", charizard);
			Assert.assertEquals("Attacking monster HP should be lower after dodge", 144, alolanGolem.getHitPoints());
			Assert.assertEquals("Attacked monster HP should not be lower after dodge", 130, charizard.getHitPoints());
		} catch (MonsterException ex) {
			Assert.fail ("Monster.attack() should not throw exception in normal use");
		}
	}
}
