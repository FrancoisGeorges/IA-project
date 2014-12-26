package generation_terrain;

import static org.junit.Assert.assertTrue;

import java.util.ArrayList;

public class Main {

	public static void main(String[] args){
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
		System.out.println(cases.get(13).toString());
		game.valueIteration();
		
		//on affiche les valeurs de V1 parce que sinon ça craint un peu 
		for(int i=0; i<game.getTerrain().getCases().size(); i++){
			System.out.println("case " + i + " : " + game.getTerrain().getCases().get(i).estim_value.get(1) + " -> " + game.getTerrain().getCases().get(i).estim_value.get(2) + " -> " + game.getTerrain().getCases().get(i).estim_value.get(3) + " -> " + game.getTerrain().getCases().get(i).estim_value.get(4) + " -> " + game.getTerrain().getCases().get(i).estim_value.get(5) + " -> " + game.getTerrain().getCases().get(i).estim_value.get(6));
		}
		
		//on affiche les valeurs de V1 parce que sinon ça craint un peu 
		for(int i=0; i<game.getTerrain().getCases().size(); i++){
			System.out.println("case " + i + " : " + game.getTerrain().getCases().get(i).estim_value.get(game.getTerrain().getCases().get(i).estim_value.size()-1));
		}
	
		
		//on affiche le nombre d'itérations, juste pour vérifier la convergence
		System.out.println("nombre total d'itérations : " + game.getTerrain().getCases().get(0).estim_value.size());
		
	}
}
