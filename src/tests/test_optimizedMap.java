package tests;

import static org.junit.Assert.*;
import generation_terrain.*;

import java.util.ArrayList;

import org.junit.Test;

public class test_optimizedMap {

	@Test
	public void test_2x2() {
		ArrayList<Case> cases = new ArrayList<Case>();
		//cases doit être peuplée maintenant
		for(int i=0; i<4; i++){
			cases.add(new Case());
		}
		//on en déclare certaines comme marécage (-1), et on donne une récompense à l'arrivée (5)
		cases.get(3).setCase_value(5);
		cases.get(1).setCase_value(-1);
		cases.get(3).setDown(false);
		cases.get(3).setUp(false);
		cases.get(3).setLeft(false);
		cases.get(3).setRight(false);
		
		Terrain terrain = new Terrain(2, 2, cases);
		terrain.complete_cases();
		terrain.check_directions();
		//une fois arrivé, il ne bouge plus
		
		//on va de (0,0) à (2,1)
		Game game = new Game(0.9, 0.01, 0, 0, 1, 1, terrain);

		game.valueIteration();
		
		OptimizedMap map = new OptimizedMap(game, new ArrayList<String>());
		map.generate_directions();//l'attribut directions est maintenant rempli (normalement^^)
		ArrayList<String> expected_directions = new ArrayList<String>();
		expected_directions.add("up");
		expected_directions.add("up");
		expected_directions.add("right");
		expected_directions.add("arrival");
		System.out.println(map.getDirections().toString());
		for(int i=0; i<map.getGame().getTerrain().getCases().size(); i++){
			ArrayList<Double> temp = map.getGame().getTerrain().getCases().get(i).getEstim_value();
			System.out.println(temp.get(temp.size()-1));
		}
		assertTrue(expected_directions.equals(map.getDirections()));
	}

	@Test
	public void test_3x2() {
		ArrayList<Case> cases = new ArrayList<Case>();
		//cases doit être peuplée maintenant
		for(int i=0; i<6; i++){
			cases.add(new Case());
		}
		//on en déclare certaines comme marécage (-1), et on donne une récompense à l'arrivée (5)
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
		
		//on va de (0,0) à (2,1)
		Game game = new Game(0.9, 0.01, 0, 0, 1, 1, terrain);
		System.out.println(game.getTerrain().getCases().get(5).toString());
		game.valueIteration();
		
		OptimizedMap map = new OptimizedMap(game, new ArrayList<String>());
		map.generate_directions();//l'attribut directions est maintenant rempli (normalement^^)
		ArrayList<String> expected_directions = new ArrayList<String>();
		expected_directions.add("up");
		expected_directions.add("up");
		expected_directions.add("up");
		expected_directions.add("right");
		expected_directions.add("right");
		expected_directions.add("arrival");
		System.out.println(map.getDirections().toString());
		for(int i=0; i<map.getGame().getTerrain().getCases().size(); i++){
			ArrayList<Double> temp = map.getGame().getTerrain().getCases().get(i).getEstim_value();
			System.out.println(temp.get(temp.size()-1));
		}
		assertTrue(expected_directions.equals(map.getDirections()));
	}
	
	
}
