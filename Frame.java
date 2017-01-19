import java.awt.Button;
import java.awt.FlowLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.event.*;
import javax.swing.KeyStroke;
import java.awt.event.KeyEvent;
import java.awt.event.*;

/**
 * Frame intègre toutes les informations liées à la fenêtre d'affichage.
 *Que ce soit les menus, l'affichage des cases ou les fenêtres de dialogue.
 */
public class Frame extends JFrame {
	private CaseComponent[][] grilleAffichage;
	private Panel panel;	
	private JMenuBar menuBar = new JMenuBar();
	private JMenu partie = new JMenu("Partie");
	private JMenu aide = new JMenu("?");
	private JMenu F5 = new JMenu("F5");
	private JMenuItem newGame = new JMenuItem("new game"),
			stats = new JMenuItem("statistiques"),
			options = new JMenuItem("options"),
			quitter = new JMenuItem("quitter"),
			aideMenu = new JMenuItem("aide"),
			aPropos = new JMenuItem("à propos"),
			actualiser = new JMenuItem("actualiser");
	
	/**
	 * @param grille on donne en parametre la grille du main pour 
	 * ensuite creer une version graphique de celle-ci.
	 */	
	public Frame(Grille grille){
		
		int hauteur = grille.getHauteur();
		int longueur = grille.getLongueur();		
		grilleAffichage = new CaseComponent[hauteur][longueur];

		for (int i=0; i<hauteur; i++){
			for (int j=0; j<longueur; j++){
				grilleAffichage[i][j]=new CaseComponent(grille.getCase(i, j), grille, grilleAffichage);				
			}		
		}
		
		
			
		
		this.panel = new Panel(grilleAffichage);	
		this.setTitle("demineur");
		this.setVisible(true);
		this.setSize(hauteur*20 + 16, longueur*20 + 69);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
		this.setContentPane(panel);
		this.setVisible(true);
		//this.setResizable(false); // permet d'empecher le redimensionnement de la fenetre

		
		//implementation menu
		
		newGame.addActionListener(/**
		 * au clic on réinitialise la grille pour un nouveau jeu		 *
		 */
			new ActionListener(){
			public void actionPerformed(ActionEvent arg0) {		
				for (int i=0; i<hauteur; i++){
					for (int j = 0; j<longueur; j++){
						(grille.getCase(i, j)).setVal(0);
						(grille.getCase(i, j)).setState(Case.State.Hidden);
						(grille.getCase(i, j)).setType(Case.Type.Empty);
					}
				}
				grille.placeMines();
				grille.incremCadre();
				repaint();
			}
		});

			
			
		
		this.partie.add(newGame);
		this.partie.addSeparator();
		this.partie.add(stats);
		this.partie.add(options);
		
		options.addActionListener(/**
		*affichage au clic du menu d'option
		*/
			new ActionListener(){
			public void actionPerformed(ActionEvent arg0) {
				Option opt = new Option(null,"Options",true);
				ZdialogInfo zInfo = opt.showOption(); 
				String diff = zInfo.getDifficulte();
				if (diff=="Intermediaire"){
					Grille grille=new Grille(2);
					for (int i=0; i<grille.getHauteur(); i++){
						for (int j=0; j<grille.getLongueur(); j++){
							grilleAffichage[i][j]=new CaseComponent(grille.getCase(i, j), grille);				
						}		
					}
				}
			}
		});
		this.partie.addSeparator();
		quitter.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0) {
				System.exit(0);
			}
		});
		this.partie.add(quitter);
		this.aide.add(aideMenu);
		this.aide.addSeparator();
		this.aide.add(aPropos);
		this.F5.add(actualiser);

		this.menuBar.add(partie);
		this.menuBar.add(aide);
		actualiser.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0) {
				repaint();
			}
		});
		
		
		actualiser.setAccelerator(KeyStroke.getKeyStroke("SPACE"));
		
		this.menuBar.add(F5);
	//	actualiser.setMnemonic(KeyEvent.VK_O);
		this.setJMenuBar(menuBar);
		this.setVisible(true);	
	}
}

