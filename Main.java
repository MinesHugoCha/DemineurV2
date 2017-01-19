public class Main {
	public static void main (String[] args){
		Grille grille = new Grille(1);
//		Grille grille = new Grille(2);
//		Grille grillediff = new Grille(3);
		
		grille.placeMines();
		grille.incremCadre();
		
		Frame fram = new Frame(grille);
	
	}
}
