package tests;

import static org.junit.Assert.*;
import generation_terrain.*;

import java.util.ArrayList;

import org.junit.Test;

public class test_optimizedMap {

	@Test
	public void test_2x2(){
		ArrayList<Case> cases = new ArrayList<Case>();
		// boxes must be populated now
		for(int i=0; i<4; i++){
			cases.add(new Case());
		}
		// we declare some boxes like "marecage"/swamp (-1), and we give a reward to the arrival (5)
		cases.get(3).setCase_value(5);
		cases.get(1).setCase_value(-1);
		cases.get(3).setDown(false);
		cases.get(3).setUp(false);
		cases.get(3).setLeft(false);
		cases.get(3).setRight(false);

		Terrain terrain = new Terrain(2, 2, cases);
		terrain.complete_cases();
		terrain.check_directions();
<<<<<<< HEAD
		//une fois arrivé, il ne bouge plus

		//on va de (0,0) à (1,1)
=======
		// when arrived, it can't move anymore
		
		// we go from (0,0) to (2,1)
>>>>>>> origin/master
		Game game = new Game(0.9, 0.01, 0, 0, 1, 1, terrain);

		game.valueIteration();

		OptimizedMap map = new OptimizedMap(game, new ArrayList<String>());
		map.generate_directions();// the directions attribute is now defined (if all worked well ^^)
		ArrayList<String> expected_directions = new ArrayList<String>();
		expected_directions.add("up");
		expected_directions.add("up");
		expected_directions.add("right");
		expected_directions.add("arrival");
		//System.out.println(map.getDirections().toString());
		for(int i=0; i<map.getGame().getTerrain().getCases().size(); i++){
			ArrayList<Double> temp = map.getGame().getTerrain().getCases().get(i).getEstim_value();
			//System.out.println(temp.get(temp.size()-1));
		}
		assertTrue(expected_directions.equals(map.getDirections()));
	}
	
	

	@Test
	public void test_3x2() {
		ArrayList<Case> cases = new ArrayList<Case>();
		// boxes must be populated now
		for(int i=0; i<6; i++){
			cases.add(new Case());
		}
		// we declare some boxes like marecage (-1), and we give a reward to the arrival (5)
		cases.get(5).setCase_value(5);
		cases.get(1).setCase_value(-1);
		cases.get(2).setCase_value(-1);
		cases.get(5).setDown(false);
		cases.get(5).setUp(false);
		cases.get(5).setLeft(false);
		cases.get(5).setRight(false);

		Terrain terrain = new Terrain(3, 2, cases);
		terrain.complete_cases();
		terrain.check_directions();
<<<<<<< HEAD

		//on va de (0,0) à (2,1)
=======
		
		// we go from (0,0) to (2,1)
>>>>>>> origin/master
		Game game = new Game(0.9, 0.01, 0, 0, 1, 1, terrain);
		//System.out.println(game.getTerrain().getCases().get(5).toString());
		game.valueIteration();

		OptimizedMap map = new OptimizedMap(game, new ArrayList<String>());
		map.generate_directions();// the directions attribute is now defined (if all worked well ^^)
		ArrayList<String> expected_directions = new ArrayList<String>();
		expected_directions.add("up");
		expected_directions.add("up");
		expected_directions.add("up");
		expected_directions.add("right");
		expected_directions.add("right");
		expected_directions.add("arrival");
		//System.out.println(map.getDirections().toString());
		for(int i=0; i<map.getGame().getTerrain().getCases().size(); i++){
			ArrayList<Double> temp = map.getGame().getTerrain().getCases().get(i).getEstim_value();
			//System.out.println(temp.get(temp.size()-1));
		}
		assertTrue(expected_directions.equals(map.getDirections()));
	}
<<<<<<< HEAD

	/*@Test
	public void test_10x5() {
=======
	
	
	//column map
	
	
	@Test
	public void test_2x3() {
>>>>>>> origin/master
		ArrayList<Case> cases = new ArrayList<Case>();
		// boxes must be populated now
		for(int i=0; i<6; i++){
			cases.add(new Case());
		}
<<<<<<< HEAD
		//on en déclare certaines au milieu comme marécage
		for(int i=2; i<8; i++){
			cases.get(i).setCase_value(-10);
		}
		cases.get(49).setCase_value(5);

		Terrain terrain = new Terrain(10, 5, cases);
		terrain.complete_cases();
		terrain.check_directions();

		//on va de (0,0) à (9,4)
		Game game = new Game(0.9, 0.01, 0, 0, 9, 4, terrain);
		game.valueIteration();

=======
		//we declare some boxes at the center like marecage
		cases.get(2).setCase_value(-5);
		cases.get(5).setCase_value(5);
		Terrain terrain = new Terrain(2, 3, cases);
		terrain.complete_cases();
		terrain.check_directions();
		
		// we go from (0,9) to (0,0)
		Game game = new Game(0.9, 0.01, 0, 0, 2, 0, terrain);
		game.valueIteration();
>>>>>>> origin/master
		OptimizedMap map = new OptimizedMap(game, new ArrayList<String>());
		map.generate_directions();// the directions attribute is defined now (if all worked well ^^)
		
		// for this one, there are two equivalent ways
		ArrayList<String> expected_directions = new ArrayList<String>();
		expected_directions.add("right");
		expected_directions.add("up");
		expected_directions.add("up");
		expected_directions.add("up");
		expected_directions.add("right");
<<<<<<< HEAD
		expected_directions.add("right");
		expected_directions.add("arrival");
		System.out.println(map.getDirections().toString());
		int index = map.getGame().getTerrain().getCases().get(0).getEstim_value().size()-1;
		/*for(int i=0; i<map.getGame().getTerrain().getCases().size(); i++){
			System.out.println(map.getGame().getTerrain().getCases().get(i).getEstim_value().get(index));
		}

		System.out.println(map.getDirections().toString());
		for(int i=0; i<map.getGame().getTerrain().getCases().size(); i++){
			ArrayList<Double> temp = map.getGame().getTerrain().getCases().get(i).getEstim_value();
			System.out.println(temp.get(temp.size()-1));
		}
		assertTrue(true);
	}*/

	//carte colonne
=======
		expected_directions.add("left");
		
		ArrayList<String> other_expected_directions = new ArrayList<String>();
		other_expected_directions.add("right");
		other_expected_directions.add("up");
		other_expected_directions.add("right");
		other_expected_directions.add("up");
		other_expected_directions.add("right");
		other_expected_directions.add("left");
		
		assertTrue(expected_directions.equals(map.getDirections()) || other_expected_directions.equals(map.getDirections()));
	}
	
>>>>>>> origin/master
	@Test
	public void test_5x1() {
		ArrayList<Case> cases = new ArrayList<Case>();
		
		for(int i=0; i<5; i++){
			cases.add(new Case());
		}
	
		cases.get(4).setCase_value(-1);
		Terrain terrain = new Terrain(5, 1, cases);
		terrain.complete_cases();
		terrain.check_directions();
<<<<<<< HEAD

		//on va de (0,9) à (0,0)
		Game game = new Game(0.9, 0.01, 0, 0, 0, 9, terrain);
		game.valueIteration();
		OptimizedMap map = new OptimizedMap(game, new ArrayList<String>());
		map.generate_directions();//l'attribut directions est maintenant rempli (normalement^^)

=======
		
		Game game = new Game(0.9, 0.01, 0, 0, 4, 0, terrain);
		game.valueIteration();
		OptimizedMap map = new OptimizedMap(game, new ArrayList<String>());
		map.generate_directions();
		
>>>>>>> origin/master
		ArrayList<String> expected_directions = new ArrayList<String>();
		for(int i=0; i<4; i++){
			expected_directions.add("right");
		}
		expected_directions.add("left");
		assertTrue(expected_directions.equals(map.getDirections()));
	}
<<<<<<< HEAD

	@Test
	public void test_2x3() {
=======
	
	
	@Test 
	public void test_6x6() {
>>>>>>> origin/master
		ArrayList<Case> cases = new ArrayList<Case>();
		// boxes must be populated now
		for(int i=0; i<36; i++){
			cases.add(new Case());
		}
		// we declare some boxes like marecage (-1), and we give a reward to the arrival (5)
		cases.get(5).setCase_value(5);
		cases.get(0).setCase_value(-1);
		cases.get(1).setCase_value(-1);
		cases.get(2).setCase_value(-1);
		cases.get(6).setCase_value(-1);
		cases.get(7).setCase_value(-1);
		cases.get(10).setCase_value(-1);
		cases.get(12).setCase_value(-1);
		cases.get(15).setCase_value(-1);
		cases.get(16).setCase_value(-1);
		cases.get(20).setCase_value(-1);
		cases.get(21).setCase_value(-1);
		cases.get(22).setCase_value(-1);
		cases.get(25).setCase_value(-1);
		cases.get(26).setCase_value(-1);
		cases.get(27).setCase_value(-1);
		cases.get(28).setCase_value(-1);
		cases.get(5).setDown(false);
		cases.get(5).setUp(false);
		cases.get(5).setLeft(false);
		cases.get(5).setRight(false);
		
		Terrain terrain = new Terrain(6, 6, cases);
		terrain.complete_cases();
		terrain.check_directions();
<<<<<<< HEAD

		//on va de (0,9) à (0,0)
		Game game = new Game(0.9, 0.01, 0, 0, 2, 0, terrain);
		game.valueIteration();
		OptimizedMap map = new OptimizedMap(game, new ArrayList<String>());
		map.generate_directions();//l'attribut directions est maintenant rempli (normalement^^)

		//pour celui-là, il y a deux chemins équivalents
		ArrayList<String> expected_directions = new ArrayList<String>();
		expected_directions.add("right");
		expected_directions.add("up");
		expected_directions.add("up");
		expected_directions.add("up");
		expected_directions.add("right");
		expected_directions.add("left");

		ArrayList<String> other_expected_directions = new ArrayList<String>();
		other_expected_directions.add("right");
		other_expected_directions.add("up");
		other_expected_directions.add("right");
		other_expected_directions.add("up");
		other_expected_directions.add("right");
		other_expected_directions.add("left");

		assertTrue(expected_directions.equals(map.getDirections()) || other_expected_directions.equals(map.getDirections()));
=======
		
		// we go from (0,0) to (2,1)
		Game game = new Game(0.9, 0.01, 0, 5, 5, 0, terrain);
		//System.out.println(game.getTerrain().getCases().get(5).toString());
		game.valueIteration();
		
		OptimizedMap map = new OptimizedMap(game, new ArrayList<String>());
		map.generate_directions();// the directions attribute is defined now (if all worked well ^^)
		
		boolean corr = true;
		corr = corr && !map.getDirections().get(2).equals("left");
	    corr = corr && map.getDirections().get(3).equals("right");
	    corr = corr && map.getDirections().get(4).equals("right");
	    corr = corr && map.getDirections().get(5).equals("arrival");
	    corr = corr && map.getDirections().get(8).equals("right");
	    corr = corr && map.getDirections().get(9).equals("down");
	    corr = corr && ( map.getDirections().get(10).equals("down"));
	    corr = corr && ( map.getDirections().get(14).equals("down"));
	    corr = corr && ( map.getDirections().get(12).equals("right"));
	    corr = corr && ( map.getDirections().get(19).equals("down"));
	    corr = corr && ( map.getDirections().get(0).equals("right"));
	    corr = corr && ( map.getDirections().get(30).equals("down"));
	    
		assertTrue(corr);
>>>>>>> origin/master
	}

	
	
}
