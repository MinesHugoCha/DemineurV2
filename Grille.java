import java.util.ArrayList;

import javax.swing.JPanel;

public class Grille extends JPanel {
	// creation de la Grille 
	private Case[][] grille;
	private int nbMines; // nb de mines Ã  placer    
	private int hauteur;
	private int longueur;

	public Grille(){
		/**
		 * Par defaut la Grille est de dimension 8x8
		 * creation et initialisatioyn des cases
		 */

		hauteur=8;
		longueur=8;
		grille=new Case[hauteur][longueur];
		nbMines=10;
		for (int i=0; i<hauteur; i++){
			for (int j=0; j<longueur; j++){
				grille[i][j]=new Case(i, j);
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
		grille=new Case[hauteur][longueur];
		for (int i=0; i<hauteur; i++){
			for (int j=0; j<longueur; j++){
				grille[i][j]=new Case(i, j);
			}
		}
	}
	
	//grille custom
	public Grille (int longueur, int hauteur, int mines){
		this.longueur=longueur;
		this.hauteur=hauteur;
		nbMines=mines;
		grille=new Case[hauteur][longueur];
		for (int i=0; i<hauteur; i++){
			for (int j=0; j<longueur; j++){
				grille[i][j]=new Case(i, j);
			}
		}
	}
	
	public void placeMines(){
		ArrayList<Case> leftCases = new ArrayList<Case>(); 
		
		for (int x = 0; x < hauteur; x++) {
			for (int y = 0; y < longueur; y++) {
				leftCases.add(grille[x][y]);
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
						if (grille[i][j].getType() == Case.Type.Empty){
							grille[i][j].setVal(grille[i][j].getVal() + 1);
						}
					}
				}
			}
		}
	}

	//faire une fonction qui parcourt la Grille pour trouver les mines et utiliser incremtation
	public void incremCadre (){
		for (int i=0; i<hauteur; i++){
			for (int j = 0; j<longueur; j++){
				if (grille[i][j].getType() == Case.Type.Mine){
					incremtation(longueur-1, hauteur-1, i, j);
				}
			}
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
		return grille[x][y];
	}

	// fonction recursive pour montrer les cases alentour d'un 0
	public void decouvrezero_rec(int x,int y){
		for (int i=x-1; i<=x+1; i++){ // parcours des cases alentour
			for (int j=y-1; j<=y+1; j++){ //idem
				// on exclue le centre
				// on evite les parties hors Grille
				// on etudie suelement les cases non decouvertes
				if ((i==x && j==y) || (i == -1 || j == -1 || i == hauteur || j == longueur || grille[i][j].getState() == Case.State.Discovered )) {
					continue;
				}else{
					grille[i][j].setState(Case.State.Discovered); // on decouvre la case
				}
				if(grille[i][j].getVal()==0 ){ // si la valeur est Ã  zero
					decouvrezero_rec(i,j); // on appelle la fonction de nouveau
				}
			}	
		}
	}
	
	public void decouvre_all(int x,int y){
		if (grille[x][y].getState()==Case.State.Discovered && grille[x][y].getType()==Case.Type.Mine){
			for (int i=0; i<hauteur; i++){
				for (int j=0; j<longueur; j++){
					grille[i][j].setState(Case.State.Discovered);
				}
			}
		}
	}
	
	public void decouvre_doubleClic(int x,int y){
		int flags =0;
		if (grille[x][y].getState()==Case.State.Discovered && grille[x][y].getVal()!=0){
			for (int i=x-1; i<x+2; i++){
				for (int j=y-1; j<y+2; j++){
					if (!((i==x && j==y) || (i == -1 || j == -1 || i == hauteur || j == longueur ))) {
						if(grille[i][j].getState()==Case.State.Flag){
							flags++;
						}
					}
				}
			}
			if (flags == grille[x][y].getVal()){
				for (int i=x-1; i<x+2; i++){
					for (int j=y-1; j<y+2; j++){
						if (!((i==x && j==y) || (i == -1 || j == -1 || i == hauteur || j == longueur || grille[i][j].getState()==Case.State.Flag))) {
							grille[i][j].setState(Case.State.Discovered);
							if (grille[i][j].getVal()==0){
								decouvrezero_rec(i, j);
							}
							/*if (grille[i][j].getType()==Case.Type.Mine){
								
								
							}*/
						}
					}
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
				if (grille[i][j].getState() == Case.State.Hidden && grille[i][j].getType() == Case.Type.Empty){
					retour=false;
				}
			}
		}
		return retour;
	}
}
