import java.util.ArrayList;

import javax.swing.JPanel;

public class Grille extends JPanel {
	// creation de la Grille 
	private Case[][] mat;
	private int nbMines; // nb de mines Ã  placer    
	private int hauteur;
	private int longueur;

	public Grille(){
		/**
		 * Par defaut la Grille est de dimension 8x8
		 * creation et initialisatioyn des cases
		 */

		hauteur=8;
		longueur=8;
		mat=new Case[hauteur][longueur];
		nbMines=10;
		for (int i=0; i<hauteur; i++){
			for (int j=0; j<longueur; j++){
				mat[i][j]=new Case(i, j);
			}
		}
	}

	//constructeur Grille: choix difficultÃ©
	//1 facile, 2 moyen, 3 difficile
	public Grille(int k){
		if (k==1){
			hauteur=8;
			longueur=8;
			nbMines=10;
		}else if (k==2){
			hauteur=16;
			longueur=16;
			nbMines=40;
		}else if (k==3){
			hauteur=16;
			longueur=30;
			nbMines=99;
		}
		mat=new Case[hauteur][longueur];
		for (int i=0; i<hauteur; i++){
			for (int j=0; j<longueur; j++){
				mat[i][j]=new Case(i, j);
			}
		}
	}

	public Grille (int longueur, int hauteur, int mines){
		this.longueur=longueur;
		this.hauteur=hauteur;
		nbMines=mines;
		mat=new Case[hauteur][longueur];
		for (int i=0; i<hauteur; i++){
			for (int j=0; j<longueur; j++){
				mat[i][j]=new Case(i, j);
			}
		}
	}
	public void placeMines(){
		ArrayList<Case> leftCases = new ArrayList<Case>(); 
		
		for (int x = 0; x < hauteur; x++) {
			for (int y = 0; y < longueur; y++) {
				leftCases.add(mat[x][y]);
			}
		}
		
		for (int j = 0; j < nbMines; j++) {
			int caseId =(int) Math.round(Math.random()*(leftCases.size() - 1));  
			if (leftCases.size() <= 0) {
				return;
			}
			leftCases.get(caseId).setType(Case.Type.Mine);
			leftCases.remove(caseId);
		}
	}

	// fonction qui incrémente les valeurs autour d'une case [x][y] 
	public void incremtation (int m, int n, int x, int y){ // m et n sont les limites de la Grille et x, y coord de la case au centre
		for (int i = x-1; i <= x+1; i++){
			for (int j = y-1; j <= y+1; j++){
				if (i != x || j != y){
					if (i != -1 && j != -1 && i != n+1 && j != m+1){
						if (mat[i][j].getType() == Case.Type.Empty){
							mat[i][j].setVal(mat[i][j].getVal() + 1);
						}
					}
				}
			}
		}
	}

	//faire une fonction qui parcourt la Grille pour trouver les 9 et utiliser incremtation
	public void incremCadre (){
		for (int i=0; i<hauteur; i++){
			for (int j = 0; j<longueur; j++){
				if (mat[i][j].getType() == Case.Type.Mine){
					incremtation(longueur-1, hauteur-1, i, j);
				}
			}
		}	
	}


	//permet d'afficher la Grille
	public void afficheMat(){
		for (int i=0; i<hauteur; i++){
			for (int j=0; j<longueur; j++){
				if (mat[i][j].getType() == Case.Type.Mine) {
					System.out.print("X ");
				}
				else{
					System.out.print(mat[i][j].getVal()+" ");
				}
			}
			System.out.println();
		}
	}
	// affichage de la Grille Joueur selon les cases decouvertes
	public void afficheMatJoueur(){
		for (int i=0; i<hauteur; i++){
			for (int j=0; j<longueur; j++){
				if (mat[i][j].getState() == Case.State.Hidden) {
					System.out.print("- ");
				}else{				
					if (mat[i][j].getType() == Case.Type.Mine) {
						System.out.print("X ");
					}
					else{
						System.out.print(mat[i][j].getVal()+" ");
					}
				}
			}
			System.out.println();
		}
	}

	public int getHauteur(){
		return hauteur;    	
	}

	public int getLongueur(){
		return longueur;    	
	}
	//getteur pour la Grille
	public Case getCase(int x, int y){
		return mat[x][y];
	}

	// fonction recursive pour montrer les cases alentour d'un 0
	public void decouvrezero_rec(int x,int y){
		for (int i=x-1; i<=x+1; i++){ // parcours des cases alentour
			for (int j=y-1; j<=y+1; j++){ //idem
				// on exclue le centre
				// on evite les parties hors Grille
				// on etudie suelement les cases non decouvertes
				if ((i==x && j==y) ||
						(i == -1 || j == -1 || i == hauteur || j == longueur) ||
						(mat[i][j].getState() == Case.State.Discovered)) {
					continue;
				}
				mat[i][j].setState(Case.State.Discovered); // on decouvre la case
				if(mat[i][j].getVal()==0){ // si la valeur est Ã  zero
					decouvrezero_rec(i,j); // on appelle la fonction de nouveau
				}
			}	
		}
	}

	//fonction verifiant si on a gagne
	//verifie si les cases qui ne sont pas des mines sont dÃ©couvertes ou non 
	public boolean gagner(){
		boolean retour = true;

		for (int i=0; i<hauteur;i++){
			for (int j=0; j<longueur;j++){
				if (mat[i][j].getState() == Case.State.Hidden && mat[i][j].getType() != Case.Type.Empty){
					retour=false;
				}
			}
		}
		return retour;
	}
}
