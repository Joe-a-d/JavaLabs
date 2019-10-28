

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

import org.junit.Assert;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import monster.Attack;
import monster.ElectricMonster;
import monster.FireMonster;
import monster.Monster;
import monster.MonsterException;
import monster.WaterMonster;
import trainer.Trade;
import trainer.Trainer;

public class TestLab5 {

	// Test fixture -- named by my son :)
	private Monster charizard, gyarados, magikarp, luvdisc, alolanGolem, charmander;
	private Trainer ethan, brendan, dawn, lyra;

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
		
		ethan = new Trainer("Ethan");
		brendan = new Trainer("Brendan");
		lyra = new Trainer("Lyra");
		dawn = new Trainer("Dawn");
		
		ethan.addMonster(charizard);
		ethan.addMonster(gyarados);
		ethan.addMonster(alolanGolem);
		brendan.addMonster(magikarp);
		dawn.addMonster(luvdisc);
	}

	@AfterEach
	void tearDown() throws Exception {
		charizard = null;
		gyarados = null;
		magikarp = null;
		luvdisc = null;
		alolanGolem = null;
		ethan = null;
		brendan = null;
		dawn = null;
		lyra = null;
	}
	
	/** Packaging tests */
	@Test
	public void testMonsterIsInMonsterPackage() {
		Assert.assertEquals("Monster should be in 'monster' package", "monster", Monster.class.getPackageName());
	}

	@Test
	public void testTrainerIsInTrainerPackage() {
		Assert.assertEquals("Trainer should be in 'trainer' package", "trainer", Trainer.class.getPackageName());
	}
	
	@Test
	public void testTradeIsInTrainerPackage() {
		Assert.assertEquals("Trade should be in 'trainer' package", "trainer", Trade.class.getPackageName());
	}
	
	/** Make sure toString() is overridden in Trainer */
	@Test
	public void testTrainerToStringIsOverridden() {
		try {
			Method method = Trainer.class.getMethod("toString", new Class[0]);
			Class cls = method.getDeclaringClass();
			Assert.assertTrue("Trainer does not override toString()", cls == Trainer.class);
		} catch (NoSuchMethodException e) {
		} catch (SecurityException e) {
		}
	}
	
	@Test
	public void testTrainerToStringIsPlausible() {
		String str = dawn.toString();
		Assert.assertTrue("Trainer.toString() should contain trainer name", str.contains("Dawn"));
		Assert.assertTrue("Trainer.toString() should contain monster details", str.contains("Splash"));
	}
	
	/** hasMonster() tests */
	@Test
	public void testHasMonsterReturnsTrueCorrectly() {
		Assert.assertTrue("hasMonster should return true for owned monster", brendan.hasMonster(magikarp));
	}
	
	@Test
	public void testHasMonsterReturnsFalseCorrectly() {
		Assert.assertFalse("hasMonster should return false for unowned monster", brendan.hasMonster(charmander));
	}
	
	/** addMonster() and removeMonster() tests */
	@Test
	public void testAddNewMonsterWorksAndReturnsTrue() {
		boolean result = lyra.addMonster(charmander);
		Assert.assertTrue("Successful addMonster should return true", result);
		Assert.assertTrue("Successful addMonster should add monster to list", lyra.hasMonster(charmander));
	}
	
	@Test
	public void testAddExistingMonsterReturnsFalse() {
		boolean result = dawn.addMonster(luvdisc);
		Assert.assertFalse("Unsuccessful addMonster should return false", result);
		Assert.assertTrue("Unsuccessful addMonster should not remove monster", dawn.hasMonster(luvdisc));
	}
	
	@Test
	public void testRemoveExistingMonsterWorksAndReturnsTrue() {
		boolean result = dawn.removeMonster(luvdisc);
		Assert.assertTrue("Successful removeMonster should return true", result);
		Assert.assertFalse("Successful addMonster should remove monster from trainer",
				dawn.hasMonster(luvdisc));
	}
	
	@Test
	public void testRemoveNonExistingMonsterReturnsFalse() {
		boolean result = dawn.removeMonster(charmander);
		Assert.assertFalse("Unsuccessful removeMonster should return false", result);
		Assert.assertFalse("Unsuccessful remove should not change monsters", dawn.hasMonster(charmander));
	}
	
	/** countMonstersByType() tests */
	@Test
	public void testCountMonstersByType() {
		// Create a new trainer with lots of monsters
		Trainer trainer = new Trainer("Test");
		trainer.addMonster(alolanGolem);
		trainer.addMonster(charizard);
		trainer.addMonster(charmander);
		trainer.addMonster(gyarados);
		trainer.addMonster(luvdisc);
		trainer.addMonster(magikarp);
		
		Map<String, Integer> expected = new HashMap<>();
		expected.put("Water", 3);
		expected.put("Fire", 2);
		expected.put("Electric", 1);
		
		Assert.assertEquals("countMonstersByType should return correct results", expected, trainer.countMonstersByType());
	}
	
	/** Trading tests */
	@Test
	public void testTradeConstructorSucceeds() {
		try {
			new Trade(ethan, charizard, dawn, luvdisc);
		} catch (MonsterException ex) {
			Assert.fail("Trade constructor should not throw exception on valid trade");
		}
	}

	@Test
	public void testTradeConstructorThrowsException() {
		Assertions.assertThrows(MonsterException.class, 
				() -> { new Trade(ethan, luvdisc, dawn, charizard); });

	}
	
	@Test
	public void testDoTradeSucceeds() {
		try {
			Trade t = new Trade(brendan, magikarp, ethan, gyarados);
			t.doTrade();
			Assert.assertFalse("trainer1 should not have monster1 after trade", brendan.hasMonster(magikarp));
			Assert.assertFalse("trainer2 should not have monster2 after trade", ethan.hasMonster(gyarados));
			Assert.assertTrue("trainer1 should have monster2 after trade", brendan.hasMonster(gyarados));
			Assert.assertTrue("trainer2 should have monster1 after trade", ethan.hasMonster(magikarp));
		} catch (MonsterException ex) {
			Assert.fail("Trade constructor should not throw exception on valid trade");
		}
	}
	

}
