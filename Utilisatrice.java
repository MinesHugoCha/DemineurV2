import java.util.Scanner;

public class Utilisatrice {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Case.Type type = Case.Type.Empty;
		int value = 0;		
		// partie permettant de creer la grille ("nouveau jeu")
		Grille mat1 = new Grille(10,10,20);
		Frame fram = new Frame(mat1);
		mat1.placeMines();
		mat1.incremCadre();
		mat1.afficheMat(); //affichage version developpeur
		mat1.afficheMatJoueur();  //affichage version joueur 

		Scanner sc = new Scanner(System.in);
		//tant que l'on ne tombe pas sur une mine on continue de jouer
		while (type == Case.Type.Empty && mat1.gagner() == false){ 
			System.out.println("saisir les coordonnees x y");
			int x = sc.nextInt();
			int y = sc.nextInt();
			type  = mat1.getCase(x, y).getType();
			// on enregistre la valeur de la case ds la variable value
			value = mat1.getCase(x, y).getVal();
			// si pas de bombe
			if (type == Case.Type.Empty){ 
				// on decouvre la case pour devoiler le chiffre
				mat1.getCase(x, y).setState(Case.State.Discovered); 
				// si on trouve un zero
				if (value == 0){ 
					// on decouvre les cases autour
					mat1.decouvrezero_rec(x, y); 
				}
			}else{
				System.out.println("vous avez perdu!");
				mat1.getCase(x, y).setState(Case.State.Discovered);
			}
			//affiche la Grille a  jour
			mat1.afficheMatJoueur(); 
		}
		if (mat1.gagner()==true){
			System.out.println("vous avez gagnÃ©");
		}		
	}
}
