

public class Case {
    private State etat;
    private int val; // le chiffre contenu dans la case
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
    
    public Case() {
	etat = State.Hidden;
	val  = 0;
	type = Type.Empty;
    }
	
    public int getVal() {
	return val;
    }
	
    public void setVal(int x) {
	val = x;
    }
	
    public State getEtat() {
	return etat;
    }
	
    public void setEtat(State etat) {
	this.etat = etat;
    }

    public Type getType() {
	return type;
    }
	
    public void setEtat(Type type) {
	this.type = type;
    }
}
