import java.awt.Button;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

import java.awt.event.*;

public class Frame extends JFrame{
	private CaseComponent[][] grilleAffichage;
	private Panel panel;	
	private JMenuBar menuBar = new JMenuBar();
	private JMenu partie = new JMenu("Partie");
	private JMenu aide = new JMenu("?");
	private JMenuItem newGame = new JMenuItem("new game"),
			stats = new JMenuItem("statistiques"),
			options = new JMenuItem("options"),
			quitter = new JMenuItem("quitter"),
			aideMenu = new JMenuItem("aide"),
			aPropos = new JMenuItem("à propos");


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
		newGame.addActionListener(new ActionListener(){
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
		//déroulement menu "option"
		options.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0) {
				Option opt = new Option(null,"Options",true);
				ZdialogInfo zInfo = opt.showOption(); 
				String diff = zInfo.getDifficulte();
				if (diff=="Intermediaire"){
					Grille grille=new Grille(2);
					for (int i=0; i<grille.getHauteur(); i++){
						for (int j=0; j<grille.getLongueur(); j++){
							grilleAffichage[i][j]=new CaseComponent(grille.getCase(i, j), grille, grilleAffichage);				
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

		this.menuBar.add(partie);
		this.menuBar.add(aide);
		this.setJMenuBar(menuBar);
		this.setVisible(true);

	}
}

