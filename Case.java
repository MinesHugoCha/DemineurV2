/**
 * Case de le grille dans laquelle on place les mines, valeurs et drapeaux.
 * Une case est contenue dans une grille.
 */
public class Case {
	private State etat;
	private Type type;
	private int val, x, y; // le chiffre contenu dans la case
	

	/**
	 *State est une énumération de trois valeurs pour indiquer si la case est découverte, cachée ou avec un drapeau. 
	 */
	public enum State {
		Discovered,
		Hidden,
		Flag;
	}

	/**
	 * Type est une énumération de deux valeur pour indiquer la présence de mine ou non
	 */
	public enum Type {
		Empty,
		Mine;
	}

	public Case(int x, int y) {
		etat = State.Hidden;
		val  = 0;
		type = Type.Empty;
		this.x = x;
		this.y = y;
	}

	public int getVal() {
		return val;
	}

	public void setVal(int x) {
		val = x;
	}

	public State getState() {
		return etat;
	}

	public void setState(State etat) {
		this.etat = etat;
	}

	public Type getType() {
		return type;
	}

	public void setType(Type type) {
		this.type = type;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}
}
