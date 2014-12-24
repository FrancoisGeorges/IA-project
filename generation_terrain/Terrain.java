package generation_terrain;

import java.util.ArrayList;

public class Terrain {
	public int length, width;//les dimensions du terrain
	public ArrayList<Case> cases;//les cases du terrain
	
	public double get_variation_potentiel(){//On renvoie la norme de la différence des potentiels des 2 dernières générations de cases
		double diff=0;
		int size=this.cases.get(0).getEstim_value().size();
		if(size>=2){//si il y a au moins deux générations
			for(int i=0; i<this.cases.size(); i++){
				diff+=Math.pow(this.cases.get(i).getEstim_value().get(size-1) - this.cases.get(i).getEstim_value().get(size-2), 2);
			}
			return Math.sqrt(diff);
		}else if(size==1){
			for(int i=0; i<this.cases.size(); i++){
				diff+=this.cases.get(i).getEstim_value().get(0);
			}
			return diff;
		}else{
			return -1;
		}
	}
	
	public void complete_cases(){//On vérifie que les dimensions du terrain et le nombre de cases sont cohérents, puis on en rajoute/enlève
		if(cases.size()<width*length){//si on manque de cases
			while(cases.size()<width*length){//tant qu'on manque de cases
				cases.add(new Case());
			}
		}else if(cases.size()>width*length){//si au contraire on en a trop
			while(cases.size()>width*length){//tant qu'on a trop de cases
				cases.remove(cases.size()-1);
			}
		}
	}
	
	public void check_directions(){//pour chaque case, on vérifie que les directions sont disponibles ou non (présence d'un bord, ...)
		//la première ligne ne peut pas descendre
		for(int i=0; i<length; i++){
			cases.get(i).setDown(false);
		}
		//la dernière ligne ne peut pas monter
		for(int i=length*(width-1); i<width*length; i++){
			cases.get(i).setUp(false);
		}
		//la première colonne ne peut pas aller à gauche
		for(int i=0; i<width; i++){
			cases.get(length*i).setLeft(false);
		}
		//la dernière colonne ne peut pas aller à droite
		for(int i=0; i<width; i++){
			cases.get(length*i + length - 1).setRight(false);
		}
	}
	
	/*
	 *  BUILDERS
	 */
	
	public Terrain(){
		this.width=0;
		this.length=0;
		this.cases=new ArrayList<Case>();
	}
	
	public Terrain(int length, int width, ArrayList<Case> cases){
		this.length=length;
		this.width=width;
		this.cases=cases;
	}
	
	/*
	 * GETTERS
	 *    &
	 * SETTERS   
	 */
	
	public int getWidth() {
		return width;
	}
	
	public void setWidth(int width) {
		this.width = width;
	}
	
	public int getLength() {
		return length;
	}
	
	public void setLength(int length) {
		this.length = length;
	}
	
	public ArrayList<Case> getCases() {
		return cases;
	}
	
	public void setCases(ArrayList<Case> cases) {
		this.cases = cases;
	}
	
	/*
	 * TOSTRING   
	 */

	@Override
	public String toString() {
		return "Terrain [width=" + width + ", length=" + length + ", cases="
				+ cases + "]";
	}
	
	
}
