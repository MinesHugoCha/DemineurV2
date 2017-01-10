public class Main {
	public static void main (String[] args){
		Grille grille = new Grille(1);
		grille.placeMines();
		grille.incremCadre();
		Frame fram = new Frame(grille);
	}
}
