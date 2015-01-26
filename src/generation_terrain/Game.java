package generation_terrain;

/**
 * @author François
 *
 */
public class Game {
	public double gamma, epsilon;//paramètres d'apprentissage
	public int x_start, y_start, x_end, y_end;//coordonnées des cases de départ, d'arrivée
	public Terrain terrain;//le terrain sur lequel on se situe
	
	//petite fonction utile qui appelle toutes les méthodes qu'on doit faire sur les terrains
	public void prepare_terrain(){
		this.terrain.complete_cases();
		this.terrain.check_directions();
		this.check_arrival();
		this.check_departure();
	}
	
	//si les coordonnées de départ/arrivée débordent, on les raccorde.
	public void check_departure(){
		if(this.x_start<0){
			this.setX_start(0);
		}else if(this.x_start>this.terrain.getLength()){
			this.x_start=this.terrain.getLength();
		}
		if(this.y_start<0){
			this.setY_start(0);
		}else if(this.y_start>this.terrain.getWidth()){
			this.y_start=this.terrain.getWidth();
		}
	}
	
	public void check_arrival(){
		if(this.x_end<0){
			this.setX_end(0);
		}else if(this.x_end>this.terrain.getLength()){
			this.x_end=this.terrain.getLength();
		}
		if(this.y_end<0){
			this.setY_end(0);
		}else if(this.y_end>this.terrain.getWidth()){
			this.y_end=this.terrain.getWidth();
		}
	}
	
	
	/*
	 *  BUILDERS
	 */
	
	public Game(){
		this.gamma=0.9;
		this.epsilon=0.01;
		this.x_start=0;
		this.y_start=0;
		this.x_end=0;
		this.y_end=0;
		this.terrain=new Terrain();
	}
	
	public Game(double gamma, double epsilon, int x_start, int y_start, int x_end, int y_end, Terrain terrain){
		this.gamma=gamma;
		this.epsilon=epsilon;
		this.x_start=x_start;
		this.y_start=y_start;
		this.x_end=x_end;
		this.y_end=y_end;
		this.terrain=terrain;
	}
	
	/*
	 * GETTERS
	 *    &
	 * SETTERS   
	 */
	
	public double getGamma() {
		return gamma;
	}

	public void setGamma(double gamma) {
		this.gamma = gamma;
	}

	public double getEpsilon() {
		return epsilon;
	}

	public void setEpsilon(double epsilon) {
		this.epsilon = epsilon;
	}

	public int getX_start() {
		return x_start;
	}

	public void setX_start(int x_start) {
		this.x_start = x_start;
	}

	public int getY_start() {
		return y_start;
	}

	public void setY_start(int y_start) {
		this.y_start = y_start;
	}

	public int getX_end() {
		return x_end;
	}

	public void setX_end(int x_end) {
		this.x_end = x_end;
	}

	public int getY_end() {
		return y_end;
	}

	public void setY_end(int y_end) {
		this.y_end = y_end;
	}

	public Terrain getTerrain() {
		return terrain;
	}

	public void setTerrain(Terrain terrain) {
		this.terrain = terrain;
	}
	
	/*
	 * TOSTRING   
	 */
	
	@Override
	public String toString() {
		return "Game [gamma=" + gamma + ", epsilon=" + epsilon + ", x_start="
				+ x_start + ", y_start=" + y_start + ", x_end=" + x_end
				+ ", y_end=" + y_end + ", terrain=" + terrain + "]";
	}
	
	/*
	 * ALGORITHM   
	 */
		
	public void valueIteration(){
		/*
		 * 1 : on initialise les valeurs de tous les états (cases) avec une valeur arbitraire
		 * ici : 0 ou la valeur de la case (marécage ou non, ...)
		 */
		for(int i=0; i<this.terrain.getCases().size(); i++){
			//this.terrain.getCases().get(i).getEstim_value().add((double) this.terrain.getCases().get(i).getCase_value());
			this.terrain.getCases().get(i).getEstim_value().add((double) 0);
		}
		
		/*
		 * 2 : Pour chaque état, on calcule sa valeur à l'étape suivante :
		 * Vt+1(i)=min{c(i, aik) + gamma*somme(pij(aik)*Vi(j))}
		 */
		for(int i=0; i<this.terrain.getCases().size(); i++){
			//On calcule ce que rapporte chaque action (haut, bas, gauche, droite)
			//en haut
			double Vhaut=0;
			double cost_up=0;
			if(this.terrain.getCases().get(i).isUp()){//si i n'est pas sur la dernière ligne
				double proba_haut=0.8;
				if(this.terrain.getCases().get(i).isRight()){//si il a une case en haut à droite
					Vhaut += 0.1 * this.terrain.getCases().get(i+1+this.terrain.getLength()).getEstim_value().get(this.terrain.getCases().get(i+1+this.terrain.getLength()).getEstim_value().size()-1);
					cost_up += 0.1*this.terrain.getCases().get(i+1+this.terrain.getLength()).getCase_value();
				}else{
					proba_haut+=0.1;
				}
				if(this.terrain.getCases().get(i).isLeft()){//si il a une case en haut à gauche
					Vhaut += 0.1 * this.terrain.getCases().get(i-1+this.terrain.getLength()).getEstim_value().get(this.terrain.getCases().get(i-1+this.terrain.getLength()).getEstim_value().size()-1);
					cost_up += 0.1*this.terrain.getCases().get(i-1+this.terrain.getLength()).getCase_value();
				}else{
					proba_haut+=0.1;
				}
				Vhaut += proba_haut * this.terrain.getCases().get(i+this.terrain.getLength()).getEstim_value().get(this.terrain.getCases().get(i+this.terrain.getLength()).getEstim_value().size()-1);
				cost_up += proba_haut * this.terrain.getCases().get(i+this.terrain.getLength()).getCase_value();
				Vhaut=this.getGamma()*Vhaut;
			}
			//en bas
			double Vbas=0;
			double cost_down=0;
			if(this.terrain.getCases().get(i).isDown()){//si i n'est pas sur la première ligne
				double proba_bas=0.8;
				if(this.terrain.getCases().get(i).isRight()){//si il a une case en bas à droite
					Vbas += 0.1 * this.terrain.getCases().get(i+1-this.terrain.getLength()).getEstim_value().get(this.terrain.getCases().get(i+1-this.terrain.getLength()).getEstim_value().size()-1);
					cost_down += 0.1 * this.terrain.getCases().get(i+1-this.terrain.getLength()).getCase_value();
				}else{
					proba_bas+=0.1;
				}
				if(this.terrain.getCases().get(i).isLeft()){//si il a une case en bas à gauche
					Vbas += 0.1 * this.terrain.getCases().get(i-1-this.terrain.getLength()).getEstim_value().get(this.terrain.getCases().get(i-1-this.terrain.getLength()).getEstim_value().size()-1);
					cost_down += 0.1 * this.terrain.getCases().get(i-1-this.terrain.getLength()).getCase_value();
				}else{
					proba_bas+=0.1;
				}
				Vbas += proba_bas * this.terrain.getCases().get(i-this.terrain.getLength()).getEstim_value().get(this.terrain.getCases().get(i-this.terrain.getLength()).getEstim_value().size()-1);
				cost_down += proba_bas * this.terrain.getCases().get(i-this.terrain.getLength()).getCase_value();
				Vbas=this.getGamma()*Vbas;
			}
			//à gauche
			double Vgauche=0;
			double cost_left=0;
			if(this.terrain.getCases().get(i).isLeft()){//si i n'est pas sur la première colonne
				double proba_gauche=0.8;
				if(this.terrain.getCases().get(i).isDown()){//si il a une case en bas à gauche
					Vgauche += 0.1 * this.terrain.getCases().get(i-1-this.terrain.getLength()).getEstim_value().get(this.terrain.getCases().get(i-1-this.terrain.getLength()).getEstim_value().size()-1);
					cost_left += 0.1 * this.terrain.getCases().get(i-1-this.terrain.getLength()).getCase_value(); 
				}else{
					proba_gauche+=0.1;
				}
				if(this.terrain.getCases().get(i).isUp()){//si il a une case en haut à gauche
					Vgauche += 0.1 * this.terrain.getCases().get(i-1+this.terrain.getLength()).getEstim_value().get(this.terrain.getCases().get(i-1+this.terrain.getLength()).getEstim_value().size()-1);
					cost_left += 0.1 * this.terrain.getCases().get(i-1+this.terrain.getLength()).getCase_value();
				}else{
					proba_gauche+=0.1;
				}
				Vgauche += proba_gauche * this.terrain.getCases().get(i-1).getEstim_value().get(this.terrain.getCases().get(i-1).getEstim_value().size()-1);
				cost_left += proba_gauche * this.terrain.getCases().get(i-1).getCase_value();
				Vgauche=this.getGamma()*Vgauche;
			}
			//à droite
			double Vdroite=0;
			double cost_right=0;
			if(this.terrain.getCases().get(i).isRight()){//si i n'est pas sur la dernière colonne
				double proba_droite=0.8;
				if(this.terrain.getCases().get(i).isDown()){//si il a une case en bas à droite
					Vdroite += 0.1 * this.terrain.getCases().get(i+1-this.terrain.getLength()).getEstim_value().get(this.terrain.getCases().get(i+1-this.terrain.getLength()).getEstim_value().size()-1);
					cost_right += 0.1 * this.terrain.getCases().get(i+1-this.terrain.getLength()).getCase_value();
				}else{
					proba_droite+=0.1;
				}
				if(this.terrain.getCases().get(i).isUp()){//si il a une case en haut à droite
					Vdroite += 0.1 * this.terrain.getCases().get(i+1+this.terrain.getLength()).getEstim_value().get(this.terrain.getCases().get(i+1+this.terrain.getLength()).getEstim_value().size()-1);
					cost_right += 0.1 * this.terrain.getCases().get(i+1+this.terrain.getLength()).getCase_value();
				}else{
					proba_droite+=0.1;
				}
				Vdroite += proba_droite * this.terrain.getCases().get(i+1).getEstim_value().get(this.terrain.getCases().get(i+1).getEstim_value().size()-1);
				cost_right += proba_droite * this.terrain.getCases().get(i+1).getCase_value();
				Vdroite=this.getGamma()*Vdroite;
			}
			
			//on fait le max de chaque action.
			double Vtemp=Math.max(Math.max(cost_up + Vhaut, cost_down + Vbas), Math.max(cost_left + Vgauche, cost_right + Vdroite));
			this.terrain.getCases().get(i).getEstim_value().add(Vtemp);
		}
		
		/*
		 * 3 : tant que la variation des potentiels est trop forte, on itère
		 * condition d'arrêt : ||Vt+1 - Vt||<epsilon*(1-gamma)/(2*gamma)
		 */
		double seuil=this.epsilon*(1-this.epsilon)/(2*this.gamma);
		double variation=this.terrain.get_variation_potentiel();//la norme de la différence entre les états de chaque génération
		while(variation>seuil){
			for(int i=0; i<this.terrain.getCases().size(); i++){
				//On calcule ce que rapporte chaque action (haut, bas, gauche, droite)
				//en haut
				double Vhaut=0;
				double cost_up=0;
				if(this.terrain.getCases().get(i).isUp()){//si i n'est pas sur la dernière ligne
					double proba_haut=0.8;
					if(this.terrain.getCases().get(i).isRight()){//si il a une case en haut à droite
						Vhaut += 0.1 * this.terrain.getCases().get(i+1+this.terrain.getLength()).getEstim_value().get(this.terrain.getCases().get(i+1+this.terrain.getLength()).getEstim_value().size()-1);
						cost_up += 0.1 * this.terrain.getCases().get(i+1+this.terrain.getLength()).getCase_value();
					}else{
						proba_haut+=0.1;
					}
					if(this.terrain.getCases().get(i).isLeft()){//si il a une case en haut à gauche
						Vhaut += 0.1 * this.terrain.getCases().get(i-1+this.terrain.getLength()).getEstim_value().get(this.terrain.getCases().get(i-1+this.terrain.getLength()).getEstim_value().size()-1);
						cost_up += 0.1 * this.terrain.getCases().get(i-1+this.terrain.getLength()).getCase_value();
					}else{
						proba_haut+=0.1;
					}
					Vhaut += proba_haut * this.terrain.getCases().get(i+this.terrain.getLength()).getEstim_value().get(this.terrain.getCases().get(i+this.terrain.getLength()).getEstim_value().size()-1);
					cost_up += 0.1 * this.terrain.getCases().get(i+this.terrain.getLength()).getCase_value();
					Vhaut=this.getGamma()*Vhaut;
				}
				//en bas
				double Vbas=0;
				double cost_down=0;
				if(this.terrain.getCases().get(i).isDown()){//si i n'est pas sur la première ligne
					double proba_bas=0.8;
					if(this.terrain.getCases().get(i).isRight()){//si il a une case en bas à droite
						Vbas += 0.1 * this.terrain.getCases().get(i+1-this.terrain.getLength()).getEstim_value().get(this.terrain.getCases().get(i+1-this.terrain.getLength()).getEstim_value().size()-1);
						cost_down += 0.1 * this.terrain.getCases().get(i+1-this.terrain.getLength()).getCase_value();
					}else{
						proba_bas+=0.1;
					}
					if(this.terrain.getCases().get(i).isLeft()){//si il a une case en bas à gauche
						Vbas += 0.1 * this.terrain.getCases().get(i-1-this.terrain.getLength()).getEstim_value().get(this.terrain.getCases().get(i-1-this.terrain.getLength()).getEstim_value().size()-1);
						cost_down += 0.1 * this.terrain.getCases().get(i-1-this.terrain.getLength()).getCase_value();
					}else{
						proba_bas+=0.1;
					}
					Vbas += proba_bas * this.terrain.getCases().get(i-this.terrain.getLength()).getEstim_value().get(this.terrain.getCases().get(i-this.terrain.getLength()).getEstim_value().size()-1);
					cost_down += 0.1 * this.terrain.getCases().get(i-this.terrain.getLength()).getCase_value();
					Vbas=this.getGamma()*Vbas;
				}
				//à gauche
				double Vgauche=0;
				double cost_left=0;
				if(this.terrain.getCases().get(i).isLeft()){//si i n'est pas sur la première colonne
					double proba_gauche=0.8;
					if(this.terrain.getCases().get(i).isDown()){//si il a une case en bas à gauche
						Vgauche += 0.1 * this.terrain.getCases().get(i-1-this.terrain.getLength()).getEstim_value().get(this.terrain.getCases().get(i-1-this.terrain.getLength()).getEstim_value().size()-1);
						cost_left += 0.1 * this.terrain.getCases().get(i-1-this.terrain.getLength()).getCase_value();
					}else{
						proba_gauche+=0.1;
					}
					if(this.terrain.getCases().get(i).isUp()){//si il a une case en haut à gauche
						Vgauche += 0.1 * this.terrain.getCases().get(i-1+this.terrain.getLength()).getEstim_value().get(this.terrain.getCases().get(i-1+this.terrain.getLength()).getEstim_value().size()-1);
						cost_left += 0.1 * this.terrain.getCases().get(i-1+this.terrain.getLength()).getCase_value();
					}else{
						proba_gauche+=0.1;
					}
					Vgauche += proba_gauche * this.terrain.getCases().get(i-1).getEstim_value().get(this.terrain.getCases().get(i-1).getEstim_value().size()-1);
					cost_left += 0.1 * this.terrain.getCases().get(i-1).getCase_value();
					Vgauche=this.getGamma()*Vgauche;
				}
				//à droite
				double Vdroite=0;
				double cost_right=0;
				if(this.terrain.getCases().get(i).isRight()){//si i n'est pas sur la dernière colonne
					double proba_droite=0.8;
					if(this.terrain.getCases().get(i).isDown()){//si il a une case en bas à droite
						Vdroite += 0.1 * this.terrain.getCases().get(i+1-this.terrain.getLength()).getEstim_value().get(this.terrain.getCases().get(i+1-this.terrain.getLength()).getEstim_value().size()-1);
						cost_right += 0.1 * this.terrain.getCases().get(i+1-this.terrain.getLength()).getCase_value();
					}else{
						proba_droite+=0.1;
					}
					if(this.terrain.getCases().get(i).isUp()){//si il a une case en haut à droite
						Vdroite += 0.1 * this.terrain.getCases().get(i+1+this.terrain.getLength()).getEstim_value().get(this.terrain.getCases().get(i+1+this.terrain.getLength()).getEstim_value().size()-1);
						cost_right += 0.1 * this.terrain.getCases().get(i+1+this.terrain.getLength()).getCase_value();
					}else{
						proba_droite+=0.1;
					}
					Vdroite += proba_droite * this.terrain.getCases().get(i+1).getEstim_value().get(this.terrain.getCases().get(i+1).getEstim_value().size()-1);
					cost_right += 0.1 * this.terrain.getCases().get(i+1).getCase_value();
					Vdroite=this.getGamma()*Vdroite;
				}
				
				//on fait le max de chaque action.
				double Vtemp=Math.max(Math.max(cost_up + Vhaut, cost_down + Vbas), Math.max(cost_left + Vgauche, cost_right + Vdroite));
				this.terrain.getCases().get(i).getEstim_value().add(Vtemp + this.terrain.getCases().get(i).getCase_value());
			}
			variation=this.terrain.get_variation_potentiel();
		}
		
	}
	
}
