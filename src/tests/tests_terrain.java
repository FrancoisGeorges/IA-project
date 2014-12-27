package tests;

import static org.junit.Assert.*;
import generation_terrain.Case;
import generation_terrain.Game;
import generation_terrain.Terrain;

import java.util.ArrayList;

import org.junit.Test;

/**
 * @author François
 *
 */
public class tests_terrain {
	
	@Test
	public void test_get_variation_potentiel(){//teste la norme de le différence entre les deux dernières valeurs des V
		ArrayList<Case> cases = new ArrayList<Case>();
		//cases doit être peuplée maintenant
		for(int i=0; i<6; i++){
			cases.add(new Case());
		}		
		Terrain terrain = new Terrain(3, 2, cases);
		for(int i=0; i<6; i++){
			cases.get(i).getEstim_value().add(0.0);
			cases.get(i).getEstim_value().add((double) i);
			cases.get(i).getEstim_value().add((double) 6);
		}
		assertTrue(terrain.get_variation_potentiel()==Math.sqrt(91));
	}
	
	@Test
	public void test_dimensions_too_few(){//quand on n'a pas assez de cases par-rapport à la dimension de l'espace
		ArrayList<Case> cases = new ArrayList<Case>();
		//cases doit être peuplée maintenant
		for(int i=0; i<13; i++){
			cases.add(new Case());
		}		
		Terrain terrain = new Terrain(5, 3, cases);
		terrain.complete_cases();//il doit respecter les dimensions de l'espace
		assertTrue(terrain.getCases().size()==15);
	}
	
	@Test
	public void test_dimensions_too_much(){//quand on a trop de cases par-rapport à la dimension de l'espace
		ArrayList<Case> cases = new ArrayList<Case>();
		//cases doit être peuplée maintenant
		for(int i=0; i<18; i++){
			cases.add(new Case());
		}
		Terrain terrain = new Terrain(5, 3, cases);
		terrain.complete_cases();//il doit respecter les dimensions de l'espace
		assertTrue(terrain.getCases().size()==15);
	}
	
	@Test
	public void test_directions_up(){//peut-il aller en haut en étant sur la dernière ligne?
		ArrayList<Case> cases = new ArrayList<Case>();
		//cases doit être peuplée maintenant
		for(int i=0; i<15; i++){
			cases.add(new Case());
		}
		Terrain terrain = new Terrain(5, 3, cases);
		terrain.check_directions();//il doit ne doit pas tomber par-dessus bord
		assertTrue(!terrain.getCases().get(10).isUp() && !terrain.getCases().get(11).isUp() && !terrain.getCases().get(12).isUp() && !terrain.getCases().get(13).isUp() && !terrain.getCases().get(14).isUp());
	}
	
	@Test
	public void test_directions_down(){//peut-il aller en bas en étant sur la première ligne?
		ArrayList<Case> cases = new ArrayList<Case>();
		//cases doit être peuplée maintenant
		for(int i=0; i<15; i++){
			cases.add(new Case());
		}
		Terrain terrain = new Terrain(5, 3, cases);
		terrain.check_directions();//il doit ne doit pas tomber par-dessus bord
		assertTrue(!terrain.getCases().get(0).isDown() && !terrain.getCases().get(1).isDown() && !terrain.getCases().get(2).isDown() && !terrain.getCases().get(3).isDown() && !terrain.getCases().get(4).isDown());
	}
	
	@Test
	public void test_directions_right(){//peut-il aller à droite en étant sur la dernière colonne?
		ArrayList<Case> cases = new ArrayList<Case>();
		//cases doit être peuplée maintenant
		for(int i=0; i<15; i++){
			cases.add(new Case());
		}
		Terrain terrain = new Terrain(5, 3, cases);
		terrain.check_directions();//il doit ne doit pas tomber par-dessus bord
		assertTrue(!terrain.getCases().get(4).isRight() && !terrain.getCases().get(9).isRight() && !terrain.getCases().get(14).isRight());
	}
	
	@Test
	public void test_directions_left(){//peut-il aller à gauche en étant sur la première colonne?
		ArrayList<Case> cases = new ArrayList<Case>();
		//cases doit être peuplée maintenant
		for(int i=0; i<15; i++){
			cases.add(new Case());
		}
		Terrain terrain = new Terrain(5, 3, cases);
		terrain.check_directions();//il doit ne doit pas tomber par-dessus bord
		assertTrue(!terrain.getCases().get(0).isLeft() && !terrain.getCases().get(5).isLeft() && !terrain.getCases().get(10).isLeft());
	}
	
	@Test
	public void test_directions_from_middle(){//juste pur vérifier qu'une case du milieu peut aller dans les 4 directions. On ne sait jamais^^
		ArrayList<Case> cases = new ArrayList<Case>();
		//cases doit être peuplée maintenant
		for(int i=0; i<15; i++){
			cases.add(new Case());
		}
		Terrain terrain = new Terrain(5, 3, cases);
		terrain.check_directions();//il doit ne doit pas tomber par-dessus bord
		Case middle=terrain.getCases().get(6);
		assertTrue(middle.isDown() && middle.isLeft() && middle.isRight() && middle.isUp());
	}
	

}
