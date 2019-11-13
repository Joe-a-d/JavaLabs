package test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.Assert;
import org.junit.Rule;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.rules.TemporaryFolder;

import monster.Attack;
import monster.ElectricMonster;
import monster.FireMonster;
import monster.Monster;
import monster.WaterMonster;
import trainer.Trainer;

public class TestLab6 {

	@Rule
	public TemporaryFolder tempFolder = new TemporaryFolder();

	private List<Monster> sortedMonsters;

	private Monster charizard, gyarados, magikarp, luvdisc, alolanGolem, charmander, charmander2, omastar;
	private Trainer ethan, brendan, dawn, lyra;

	@BeforeEach
	public void setUp() {
		charizard = new FireMonster(130, new Attack[] { new Attack("Fire Spin", 14), new Attack("Blast Burn", 110),
				new Attack("Overheat", 160) });
		gyarados = new WaterMonster(169, new Attack[] { new Attack("Waterfall", 16), new Attack("Outrage", 110),
				new Attack("Hydro Pump", 130) });
		magikarp = new WaterMonster(58, new Attack[] { new Attack("Splash", 0), new Attack("Struggle", 35) });
		luvdisc = new WaterMonster(100, new Attack[] { new Attack("Splash", 0), new Attack("Draining Kiss", 60) });
		alolanGolem = new ElectricMonster(154,
				new Attack[] { new Attack("Rock Throw", 12), new Attack("Wild Charge", 90) });
		charmander = new FireMonster(0, new Attack[] { new Attack("Scratch", 6), new Attack("Flame Charge", 70) });
		charmander2 = new FireMonster(0, new Attack[] { new Attack("Scratch", 6), new Attack("Flame Charge", 70) });
		omastar = new WaterMonster(130, new Attack[] { new Attack("Water Gun", 5), new Attack("Rock Blast", 50) });

		ethan = new Trainer("Ethan");
		brendan = new Trainer("Brendan");
		lyra = new Trainer("Lyra");
		dawn = new Trainer("Dawn");

		ethan.addMonster(charizard);
		ethan.addMonster(gyarados);
		ethan.addMonster(alolanGolem);
		brendan.addMonster(magikarp);
		dawn.addMonster(luvdisc);

		sortedMonsters = new ArrayList<>();
		sortedMonsters.addAll(Arrays.asList(gyarados, alolanGolem, charizard));
	}

	@AfterEach
	public void tearDown() {
		charizard = null;
		gyarados = null;
		magikarp = null;
		luvdisc = null;
		alolanGolem = null;
		charmander = null;
		charmander2 = null;
		omastar = null;
		ethan = null;
		brendan = null;
		dawn = null;
		lyra = null;
	}

	// Get a file name for loading/saving wishlists, and makes sure the file doesn't
	// exist before returning
	private Path getOutputFile() throws IOException {
		tempFolder.create();
		Path path = tempFolder.newFile().toPath();
		Files.deleteIfExists(path);
		return path;
	}

	private boolean attacksMatch(Attack[] attack1, Attack[] attack2) {
		for (Attack a1 : attack1) {
			boolean found = false;
			for (Attack a2 : attack2) {
				if (a1.getName().equals(a2.getName())) {
					if (a1.getPoints() != a2.getPoints()) {
						return false;
					}
					found = true;
				}
			}
			if (!found) {
				return false;
			}
		}
		return true;
	}

	@Test
	public void testSaveAndLoadShouldWorkTogether() throws Exception {
		Path path = getOutputFile();
		brendan.saveToFile(path.toString());
		Trainer trainer2 = Trainer.loadFromFile(path.toString());
		Assert.assertEquals("Names are not identical after reload", brendan.getName(), trainer2.getName());
		List<Monster> monsters = new ArrayList<>(brendan.getMonsters());
		// Compare each monster separately -- can't use Set.equals
		for (Monster m2 : trainer2.getMonsters()) {
			boolean found = false;
			for (Monster m1 : brendan.getMonsters()) {
				if (m1.getType().equals(m2.getType()) && m1.getHitPoints() == m2.getHitPoints()) {
					if (!attacksMatch(m1.getAttacks(), m2.getAttacks())) {
						Assert.fail("Monster attack are not identical after reload: " + Arrays.toString(m1.getAttacks())
								+ " " + Arrays.toString(m2.getAttacks()));
					} else {
						found = true;
						monsters.remove(m1);
					}
				}
			}
			if (!found) {
				Assert.fail("Monsters are not identical after reload: " + brendan.getMonsters() + " vs "
						+ trainer2.getMonsters());
			}
		}
		if (!monsters.isEmpty()) {
			Assert.fail("Monsters are not identical after reload: " + brendan.getMonsters() + " vs "
					+ trainer2.getMonsters());
		}
	}

	@Test
	public void testSaveShouldProduceNonEmptyFile() throws Exception {
		Path path = getOutputFile();
		brendan.saveToFile(path.toString());
		Assert.assertTrue("Trainer output file does not exist after save()", Files.exists(path));
		Assert.assertTrue("Trainer output file is empty after save()", Files.size(path) > 0);
	}

	@Test
	public void testMonsterSort() {
		// All of them
		List<Monster> sortedMonsters = Arrays.asList(gyarados, charizard, omastar, luvdisc, magikarp, charmander);
		List<Monster> testMonsters = new ArrayList<>(sortedMonsters);
		Collections.shuffle(testMonsters);
		Collections.sort(testMonsters);
		Assert.assertEquals("Comparator does not correctly sort Monsters: ", sortedMonsters, testMonsters);
	}

	@Test
	public void testCompareIdenticalMonsters() {
		Assert.assertEquals("Comparing two identical monsters should return zero", 0,
				charmander.compareTo(charmander2));
	}

	@Test
	public void testCompareIdenticalExceptForType() {
		int comp = charizard.compareTo(omastar);
		Assert.assertTrue("Comparison incorrect for identical monsters with different types", comp < 0);
	}

	@Test
	public void testCompareDifferentHP() {
		int comp = magikarp.compareTo(gyarados);
		Assert.assertTrue("Comparison incorrect for monsters with different HP", comp > 0);
	}
}
