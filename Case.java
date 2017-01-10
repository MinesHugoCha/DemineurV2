

public class Case {
	private State etat;
	private int val, x, y; // le chiffre contenu dans la case
	private Type type;

	public enum State {
		Discovered,
		Hidden,
		Flag;
	}

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
