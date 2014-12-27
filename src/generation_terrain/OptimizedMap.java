package generation_terrain;

import java.util.ArrayList;

/**
 * @author François
 *
 */
public class OptimizedMap {
	public Game game;
	public ArrayList<String> directions;
	
	public void generate_directions(){//renvoie les directions optimales de chaque case
		this.setDirections(new ArrayList<String>());//juste au cas où on appelle cette méthode plusieurs fois^^
		for(int i=0; i<this.game.getTerrain().getCases().size(); i++){//pour chaque case
			Case c = this.game.getTerrain().getCases().get(i);
			String best_direction="";
			double value_best_direction=-10000000;
			if(c.isRight()){//si il y a une case à droite
				double right_value=this.game.getTerrain().getCases().get(i+1).getEstim_value().get(c.getEstim_value().size()-1);
				if(right_value > value_best_direction){//si c'est plus intéressant à droite
					best_direction="right";
					value_best_direction=right_value;
				}
			}
			if(c.isLeft()){//si il y a une case à gauche
				double left_value=this.game.getTerrain().getCases().get(i-1).getEstim_value().get(c.getEstim_value().size()-1);
				if(left_value > value_best_direction){//si c'est plus intéressant à gauche
					best_direction="left";
					value_best_direction=left_value;
				}			
			}
			if(c.isUp()){//si il y a une case en haut
				double up_value=this.game.getTerrain().getCases().get(i + this.getGame().getTerrain().getLength()).getEstim_value().get(c.getEstim_value().size()-1);
				if(up_value > value_best_direction){//si c'est plus intéressant en haut
					best_direction="up";
					value_best_direction=up_value;
				}
			}
			if(c.isDown()){//si il y a une case en bas
				double down_value=this.game.getTerrain().getCases().get(i - this.getGame().getTerrain().getLength()).getEstim_value().get(c.getEstim_value().size()-1);
				if(down_value > value_best_direction){//si c'est plus intéressant en bas
					best_direction="down";
					value_best_direction=down_value;
				}
			}
			//si c'est la fin
			if(best_direction.equals("")){
				best_direction="arrival";
			}
			//on attribue sa meilleure direction à la case
			this.directions.add(best_direction);
		}
	}
	
	/*
	 *  BUILDERS
	 */
	
	public OptimizedMap(){
		this.game=new Game();
		this.directions=new ArrayList<String>();
	}
	
	public OptimizedMap(Game game, ArrayList<String> directions){
		this.game=game;
		this.directions=directions;
	}
	
	/*
	 * GETTERS
	 *    &
	 * SETTERS   
	 */
	
	public Game getGame() {
		return game;
	}


	public void setGame(Game game) {
		this.game = game;
	}


	public ArrayList<String> getDirections() {
		return directions;
	}


	public void setDirections(ArrayList<String> directions) {
		this.directions = directions;
	}
	
	/*
	 * TOSTRING   
	 */
	
	@Override
	public String toString() {
		return "OptimizedMap [game=" + game + ", directions=" + directions
				+ "]";
	}

	
}
