package tests;

import static org.junit.Assert.*;
import generation_terrain.Case;
import generation_terrain.Game;
import generation_terrain.Terrain;

import java.util.ArrayList;

import org.junit.Test;

public class tests_game {
	@Test
	public void test_small_linear_terrain(){//dans le cas où notre terrain est une bande de terre de dimension (1, 3)
		ArrayList<Case> cases = new ArrayList<Case>();
		for(int i=0; i<3; i++){
			cases.add(new Case());
		}
		cases.get(2).setCase_value(5);
		Terrain terrain = new Terrain(1, 3, cases);
		//on va de (0,0) à (4,2)
		Game game = new Game(0.9, 0.01, 0, 0, 0, 2, terrain);
		//On vérifie que ces points sont bien sur le terrain
		game.valueIteration();//on lance l'algorithme bourrin
		assertTrue(game.getTerrain().getCases().get(0).getEstim_value().size()<40);//il ne faut pas beaucoup d'itérations ici
	}
	
	/*@Test
	public void test_first_generation(){//on teste les valeurs de V0
		ArrayList<Case> cases = new ArrayList<Case>();
		//cases doit être peuplée maintenant
		for(int i=0; i<15; i++){
			cases.add(new Case());
		}
		//on en déclare certaines comme marécage (-1), et on donne une récompense à l'arrivée (5)
		cases.get(14).setCase_value(5);
		cases.get(4).setCase_value(-1);
		cases.get(7).setCase_value(-1);
		cases.get(8).setCase_value(-1);
		cases.get(9).setCase_value(-1);
		
		Terrain terrain = new Terrain(5, 3, cases);
		terrain.complete_cases();
		terrain.check_directions();
		//on va de (0,0) à (4,2)
		Game game = new Game(0.9, 0.01, 0, 0, 4, 2, terrain);
		//On vérifie que ces points sont bien sur le terrain
		game.check_departure();
		game.check_arrival();
		
		game.valueIteration();//on lance l'algorithme bourrin
		ArrayList<Double> resultat_attendu = new ArrayList<Double>();
		//il faudrait vérifier ces valeurs à la main au moins une fois... Qui s'y colle?
		resultat_attendu.add(0.0);
		resultat_attendu.add(0.0);
		resultat_attendu.add(0.0);
		resultat_attendu.add(0.0);
		resultat_attendu.add(-1.0);
		resultat_attendu.add(0.0);
		resultat_attendu.add(0.0);
		resultat_attendu.add(-1.0);
		resultat_attendu.add(-0.55);
		resultat_attendu.add(3.05);
		resultat_attendu.add(0.0);
		resultat_attendu.add(0.0);
		resultat_attendu.add(0.0);
		resultat_attendu.add(0.0);
		resultat_attendu.add(7.421);
		ArrayList<Double> resultat = new ArrayList<Double>();
		for(int i=0; i<game.getTerrain().getCases().size(); i++){
			resultat.add(game.getTerrain().getCases().get(i).estim_value.get(1));
		}
		boolean difference_is_low=true;
		for(int i=0; i<game.getTerrain().getCases().size(); i++){
			if(Math.abs( resultat.get(i)-resultat_attendu.get(i))>0.001){
				difference_is_low=false;
			}
		}
		assertTrue(difference_is_low);
	}*/
	
	@Test
	public void test_depart_dimensions(){//quand on a pas assez de cases par-rapport à la dimension de l'espace
		ArrayList<Case> cases = new ArrayList<Case>();
		//cases doit être peuplée maintenant
		for(int i=0; i<15; i++){
			cases.add(new Case());
		}
		Terrain terrain = new Terrain(5, 3, cases);
		terrain.complete_cases();
		terrain.check_directions();
		//on va de (-3,8) à (4,2)
		Game game = new Game(0.9, 0.01, -3, 8, 4, 2, terrain);
		game.check_departure();//on projette le départ sur le terrain
		
		assertTrue(game.getX_start()>=0 && game.getX_start()<=terrain.getLength() && game.getY_start()>=0 && game.getY_start()<=terrain.getWidth());
	}

	@Test
	public void test_arrivee_dimensions(){//quand on a pas assez de cases par-rapport à la dimension de l'espace
		ArrayList<Case> cases = new ArrayList<Case>();
		//cases doit être peuplée maintenant
		for(int i=0; i<15; i++){
			cases.add(new Case());
		}
		Terrain terrain = new Terrain(5, 3, cases);
		terrain.complete_cases();
		terrain.check_directions();
		//on va de (0,0) à (9,-1)
		Game game = new Game(0.9, 0.01, 0, 0, 9, -1, terrain);
		game.check_arrival();//on projette l'arrivée sur le terrain
		
		assertTrue(game.getX_end()>=0 && game.getX_end()<=terrain.getLength() && game.getY_end()>=0 && game.getY_end()<=terrain.getWidth());
	}

}
