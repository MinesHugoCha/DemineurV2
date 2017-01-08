import java.util.Scanner;

public class Utilisatrice {

    /**
     * @param args
     */
    public static void main(String[] args) {
	Case.Type type = Case.Type.Empty;
	int value = 0;
	boolean isGameEnded = false;
	
	// partie permettant de créer la grille ("nouveau jeu")
	Matrice mat1 = new Matrice(30,16,99);
	mat1.placeMines();
	mat1.incremCadre();
	mat1.afficheMat(); //affichage version dÃ©veloppeur
	mat1.afficheMatJoueur();  //affichage version joueur 

	Scanner sc = new Scanner(System.in);
	while (!isGameEnded){
	    System.out.println("saisir les coordonnÃ©es x y");
	    int x = sc.nextInt();
	    int y = sc.nextInt();
	    Case myCase  = mat1.getMat(x, y);

	    discoverCase(mat1, myCase, x, y);
	    isGameEnded = checkEndGame(mat1, myCase);
	    
	    mat1.afficheMatJoueur(); //affiche la matrice à jour
	}
	displayEnd(mat1.gagner());
    }

    private static void discoverCase(Matrice mat1, Case myCase, int x, int y) {
	if (myCase.getType() == Case.Type.Empty) {
	    if (myCase.getVal() == 0){
		mat1.decouvrezero_rec(x, y); // on decouvre les cases autour
	    }
	}
        myCase.setEtat(Case.State.Discovered);
    }
    
    private static boolean checkEndGame(Matrice mat1, Case myCase) {
	return (mat1.gagner() == true || myCase.getType() == Case.Type.Mine);
    }

    private static void displayEnd(boolean isVictory) {
	if (isVictory){
	    System.out.println("Vous avez gagné :)");
	} else {
	    System.out.println("Vous avez perdu :(");
	}
    }
}
