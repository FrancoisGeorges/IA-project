package tests;

import static org.junit.Assert.*;
import generation_terrain.Case;
import generation_terrain.Game;
import generation_terrain.Terrain;

import java.util.ArrayList;

import org.junit.Test;

/**
 * @author Fran�ois
 *
 */
public class tests_game {

	@Test
	public void test_small_linear_terrain(){//dans le cas o� notre terrain est une bande de terre de dimension (1, 3)
		ArrayList<Case> cases = new ArrayList<Case>();
		for(int i=0; i<3; i++){
			cases.add(new Case());
		}
		cases.get(2).setCase_value(5);
		Terrain terrain = new Terrain(1, 3, cases);
		//on va de (0,0) � (4,2)
		Game game = new Game(0.9, 0.01, 0, 0, 0, 2, terrain);
		//On v�rifie que ces points sont bien sur le terrain
		game.valueIteration();//on lance l'algorithme
		System.out.println(game.getTerrain().getCases().get(0).getEstim_value().size());
		assertTrue(game.getTerrain().getCases().get(0).getEstim_value().size()<100);//il ne faut pas beaucoup d'it�rations ici
	}
	
	@Test
	public void test_depart_dimensions(){//quand on a pas assez de cases par-rapport � la dimension de l'espace
		ArrayList<Case> cases = new ArrayList<Case>();
		//cases doit �tre peupl�e maintenant
		for(int i=0; i<15; i++){
			cases.add(new Case());
		}
		Terrain terrain = new Terrain(5, 3, cases);
		terrain.complete_cases();
		terrain.check_directions();
		//on va de (-3,8) � (4,2)
		Game game = new Game(0.9, 0.01, -3, 8, 4, 2, terrain);
		game.check_departure();//on projette le d�part sur le terrain
		
		assertTrue(game.getX_start()>=0 && game.getX_start()<=terrain.getLength() && game.getY_start()>=0 && game.getY_start()<=terrain.getWidth());
	}

	@Test
	public void test_arrivee_dimensions(){//quand on a pas assez de cases par-rapport � la dimension de l'espace
		ArrayList<Case> cases = new ArrayList<Case>();
		//cases doit �tre peupl�e maintenant
		for(int i=0; i<15; i++){
			cases.add(new Case());
		}
		Terrain terrain = new Terrain(5, 3, cases);
		terrain.complete_cases();
		terrain.check_directions();
		//on va de (0,0) � (9,-1)
		Game game = new Game(0.9, 0.01, 0, 0, 9, -1, terrain);
		game.check_arrival();//on projette l'arriv�e sur le terrain
		
		assertTrue(game.getX_end()>=0 && game.getX_end()<=terrain.getLength() && game.getY_end()>=0 && game.getY_end()<=terrain.getWidth());
	}

}
