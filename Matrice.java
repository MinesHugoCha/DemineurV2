
public class Matrice {
    // creation de la matrice 
    private Case[][] mat;
    private int nbMines; // nb de mines à placer
    private int longueur;
    private int hauteur;
	
    public Matrice(){
	/**
	 * Par defaut la matrice est de dimension 8x8
	 * creation et initialisatioyn des cases
	 */

	hauteur=8;
	longueur=8;
	mat=new Case[hauteur][longueur];
	nbMines=10;
	for (int i=0; i<hauteur; i++){
	    for (int j=0; j<longueur; j++){
		mat[i][j]=new Case();
	    }
	}
    }
	
    //constructeur matrice: choix difficulté
    //1 facile, 2 moyen, 3 difficile
    public Matrice(int k){
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
		mat[i][j]=new Case();
	    }
	}
    }
	
    public Matrice (int longueur, int hauteur, int mines){
	this.longueur=longueur;
	this.hauteur=hauteur;
	nbMines=mines;
	mat=new Case[hauteur][longueur];
	for (int i=0; i<hauteur; i++){
	    for (int j=0; j<longueur; j++){
		mat[i][j]=new Case();
	    }
	}
    }
    public void placeMines(){
	int x, y; //position de la mine
	int i=0; //compteur du nb de mines
	while (i<nbMines){
	    x=(int)Math.round(Math.random()*(hauteur-1));
	    y=(int)Math.round(Math.random()*(longueur-1));
	    if (mat[x][y].getEtat() == Case.State.Hidden ){
		mat[x][y].setEtat(Case.Type.Mine);
		i++;
	    }
	    System.out.println();
	}
    }

    // fonction qui incrꮥnte les valeurs autour d'une case [x][y] 
    public void incremtation (int m, int n, int x, int y){ // m et n sont les limites de la matrice et x, y coord de la case au centre
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
	
    //faire une fonction qui parcourt la matrice pour trouver les 9 et utiliser incremtation
    public void incremCadre (){
	for (int i=0; i<hauteur; i++){
	    for (int j = 0; j<longueur; j++){
		if (mat[i][j].getType() == Case.Type.Mine){
		    incremtation(longueur-1, hauteur-1, i, j);
		}
	    }
	}	
    }
	
	
    //permet d'afficher la matrice
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
    // affichage de la matrice Joueur selon les cases decouvertes
    public void afficheMatJoueur(){
	for (int i=0; i<hauteur; i++){
	    for (int j=0; j<longueur; j++){
		if (mat[i][j].getEtat() == Case.State.Hidden) {
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
    
    //getteur pour la matrice
    public Case getMat(int x, int y){
	return mat[x][y];
    }
	
    // fonction recursive pour montrer les cases alentour d'un 0
    public void decouvrezero_rec(int x,int y){
	for (int i=x-1; i<=x+1; i++){ // parcours des cases alentour
	    for (int j=y-1; j<=y+1; j++){ //idem
		// on exclue le centre
	        // on evite les parties hors matrice
		// on etudie suelement les cases non decouvertes
		if ((i==x && j==y) ||
		    (i == -1 || j == -1 || i == hauteur || j == longueur) ||
		    (mat[i][j].getEtat() == Case.State.Discovered)) {
		    continue;
		}
		mat[i][j].setEtat(Case.State.Discovered); // on decouvre la case
		if(mat[i][j].getVal()==0){ // si la valeur est à zero
		    decouvrezero_rec(i,j); // on appelle la fonction de nouveau
		}
	    }	
	}
    }
	
    //fonction verifiant si on a gagné
    //verifie si les cases qui ne sont pas des mines sont découvertes ou non 
    public boolean gagner(){
	boolean retour = true;
	
	for (int i=0; i<hauteur;i++){
	    for (int j=0; j<longueur;j++){
		if (mat[i][j].getEtat() == Case.State.Hidden && mat[i][j].getType() != Case.Type.Empty){
		    retour=false;
		}
	    }
	}
	return retour;
    }
}



