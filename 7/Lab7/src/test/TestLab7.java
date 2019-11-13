package test;

import org.junit.Assert;
import org.junit.Test;

import monster.Attack;
import monster.FireMonster;
import monster.Monster;
import monster.WaterMonster;
import trainer.Trainer;

public class TestLab7 {

	// Attacks
	
	@Test
	public void testTwoIdenticalAttacksEqual() {
		Attack a1 = new Attack("Name", 100);
		Attack a2 = new Attack("Name", 100);
		Assert.assertEquals("Two identical attacks should be equal", a1, a2);
	}
	
	@Test
	public void testTwoDifferentAttacksNotEqual() {
		Attack a1 = new Attack("Name", 100);
		Attack a2 = new Attack("Name2", 100);
		Attack a3 = new Attack("Name", 105);
		Assert.assertNotEquals("Two different attacks should not be equal", a1, a2);
		Assert.assertNotEquals("Two different attacks should not be equal", a2, a3);
		Assert.assertNotEquals("Two different attacks should not be equal", a1, a3);
	}
	
	@Test
	public void testTwoEqualAttacksHaveSameHashCode() {
		Attack a1 = new Attack("Name", 100);
		Attack a2 = new Attack("Name", 100);
		Attack a3 = new Attack("Name", 105);
		Attack a4 = new Attack("Name", 105);
		Assert.assertEquals("hashCode of equal attacks should be equal", a1.hashCode(), a2.hashCode());
		Assert.assertEquals("hashCode of equal attacks should be equal", a3.hashCode(), a4.hashCode());
	}
	
	// Monster
	
	@Test
	public void testTwoIdenticalMonstersEqual() {
		Attack a1 = new Attack("Name", 100);
		Attack a2 = new Attack("Name2", 100);
		Monster m1a = new FireMonster(100, new Attack[] { a1, a2 });
		Monster m1b = new FireMonster(100, new Attack[] { a1, a2 });
		Monster m2a = new FireMonster(100, new Attack[] { a1 });
		Monster m2b = new FireMonster(100, new Attack[] { a1 });
		Monster m3a = new WaterMonster(100, new Attack[] { a1, a2 });
		Monster m3b = new WaterMonster(100, new Attack[] { a1, a2 });
		Monster m4a = new WaterMonster(110, new Attack[] { a1, a2 });
		Monster m4b = new WaterMonster(110, new Attack[] { a1, a2 });
		Assert.assertEquals("Two identical monsters should be equal", m1a, m1b);
		Assert.assertEquals("Two identical monsters should be equal", m2a, m2b);
		Assert.assertEquals("Two identical monsters should be equal", m3a, m3b);
		Assert.assertEquals("Two identical monsters should be equal", m4a, m4b);
	}
	
	@Test
	public void testTwoDifferentMonstersNotEqual() {
		Attack a1 = new Attack("Name", 100);
		Attack a2 = new Attack("Name2", 100);
		Monster m1 = new FireMonster(100, new Attack[] { a1, a2 });
		Monster m2 = new FireMonster(100, new Attack[] { a1 });
		Monster m3 = new WaterMonster(100, new Attack[] { a1, a2 });
		Monster m4 = new WaterMonster(110, new Attack[] { a1, a2 });
		Assert.assertNotEquals("Two non-identical monsters should not be equal", m1, m2);
		Assert.assertNotEquals("Two non-identical monsters should not be equal", m1, m3);
		Assert.assertNotEquals("Two non-identical monsters should not be equal", m1, m4);
		Assert.assertNotEquals("Two non-identical monsters should not be equal", m2, m3);
		Assert.assertNotEquals("Two non-identical monsters should not be equal", m2, m4);
		Assert.assertNotEquals("Two non-identical monsters should not be equal", m3, m4);
	}
	
	@Test
	public void testTwoEqualMonstersHaveSameHashCode() {
		Attack a1 = new Attack("Name", 100);
		Attack a2 = new Attack("Name2", 100);
		Monster m1a = new FireMonster(100, new Attack[] { a1, a2 });
		Monster m1b = new FireMonster(100, new Attack[] { a1, a2 });
		Monster m2a = new FireMonster(100, new Attack[] { a1 });
		Monster m2b = new FireMonster(100, new Attack[] { a1 });
		Monster m3a = new WaterMonster(100, new Attack[] { a1, a2 });
		Monster m3b = new WaterMonster(100, new Attack[] { a1, a2 });
		Monster m4a = new WaterMonster(110, new Attack[] { a1, a2 });
		Monster m4b = new WaterMonster(110, new Attack[] { a1, a2 });
		Assert.assertEquals("hashCode of equal monsters should be equal", m1a.hashCode(), m1b.hashCode());
		Assert.assertEquals("hashCode of equal monsters should be equal", m2a.hashCode(), m2b.hashCode());
		Assert.assertEquals("hashCode of equal monsters should be equal", m3a.hashCode(), m3b.hashCode());
		Assert.assertEquals("hashCode of equal monsters should be equal", m4a.hashCode(), m4b.hashCode());
	}
	
	// Trainer
	@Test
	public void testTwoIdenticalTrainersEqual() {
		Attack a1 = new Attack("Name", 50);
		Attack a2 = new Attack("Name2", 100);
		Monster m1a = new FireMonster(100, new Attack[] { a1, a2 });
		Monster m1b = new FireMonster(100, new Attack[] { a1, a2 });
		Monster m2a = new FireMonster(100, new Attack[] { a1 });
		Monster m2b = new FireMonster(100, new Attack[] { a1 });
		Trainer t1 = new Trainer("Trainer");
		Trainer t2 = new Trainer("Trainer");
		t1.addMonster(m1a);
		t1.addMonster(m2a);
		t2.addMonster(m2b);
		t2.addMonster(m1b);
		Assert.assertEquals("Two identical trainers should be equal", t1, t2);
	}
	
	@Test
	public void testTwoDifferentTrainersNotEqual() {
		Attack a1 = new Attack("Name", 100);
		Attack a2 = new Attack("Name2", 100);
		Monster m1 = new FireMonster(100, new Attack[] { a1, a2 });
		Monster m2 = new FireMonster(100, new Attack[] { a1 });
		Monster m3 = new WaterMonster(100, new Attack[] { a1, a2 });
		Monster m4 = new WaterMonster(110, new Attack[] { a1, a2 });
		Monster m5 = new WaterMonster(110, new Attack[] { a1, a2 });
		Monster m6 = new WaterMonster(100, new Attack[] { a1, a2 });
		Trainer t1 = new Trainer("Name");
		Trainer t2 = new Trainer("Name");
		Trainer t3 = new Trainer("Name2");
		t1.addMonster(m1);
		t1.addMonster(m2);
		t2.addMonster(m3);
		t2.addMonster(m4);
		t3.addMonster(m6);
		t3.addMonster(m3);
		Assert.assertNotEquals("Two non-identical trainers should not be equal", t1, t2);
		Assert.assertNotEquals("Two non-identical trainers should not be equal", t2, t3);
	}
	
	@Test
	public void testTwoEqualTrainersHaveSameHashCode() {
		Attack a1 = new Attack("Name", 50);
		Attack a2 = new Attack("Name2", 100);
		Monster m1a = new FireMonster(100, new Attack[] { a1, a2 });
		Monster m1b = new FireMonster(100, new Attack[] { a1, a2 });
		Monster m2a = new FireMonster(100, new Attack[] { a1 });
		Monster m2b = new FireMonster(100, new Attack[] { a1 });
		Trainer t1 = new Trainer("Trainer");
		Trainer t2 = new Trainer("Trainer");
		t1.addMonster(m1a);
		t1.addMonster(m2a);
		t2.addMonster(m2b);
		t2.addMonster(m1b);
		Assert.assertEquals("Two identical trainers should be have same hashcode", t1.hashCode(), t2.hashCode());
	}
	
	
}
