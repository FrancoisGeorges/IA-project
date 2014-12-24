package generation_terrain;

import java.util.ArrayList;

public class Case {
	public int case_value;//la valeur de la case (ex: -1 si marécage, 0 sinon)
	public boolean up, down, left, right;//true ssi la direction est possible depuis cette case
	public ArrayList<Double> estim_value;//V0(case), V1(case), V2(case), ...
	
	/*
	 *  BUILDERS
	 */
	
	public Case(){
		this.case_value=0;
		this.up=true;
		this.down=true;
		this.left=true;
		this.right=true;
		this.estim_value= new ArrayList<Double>();
	}
	
	public Case(int case_value, boolean up, boolean down, boolean left, boolean right, ArrayList<Double> estim_value){
		this.case_value=case_value;
		this.up=up;
		this.down=down;
		this.left=left;
		this.right=right;
		this.estim_value=estim_value;
	}
	
	/*
	 * GETTERS
	 *    &
	 * SETTERS   
	 */
	
	public int getCase_value() {
		return case_value;
	}
	
	public void setCase_value(int case_value) {
		this.case_value = case_value;
	}
	
	public boolean isUp() {
		return up;
	}
	
	public void setUp(boolean up) {
		this.up = up;
	}
	
	public boolean isDown() {
		return down;
	}
	
	public void setDown(boolean down) {
		this.down = down;
	}
	
	public boolean isLeft() {
		return left;
	}
	
	public void setLeft(boolean left) {
		this.left = left;
	}
	
	public boolean isRight() {
		return right;
	}
	
	public void setRight(boolean right) {
		this.right = right;
	}
	
	public ArrayList<Double> getEstim_value() {
		return estim_value;
	}
	
	public void setEstim_value(ArrayList<Double> estim_value) {
		this.estim_value = estim_value;
	}
	
	/*
	 * TOSTRING   
	 */

	@Override
	public String toString() {
		return "Case [case_value=" + case_value + ", up=" + up + ", down="
				+ down + ", left=" + left + ", right=" + right
				+ ", estim_value=" + estim_value + "]";
	}
	

}
