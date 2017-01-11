import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

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
		this.setTitle("title");
		this.setVisible(true);
		this.setSize(hauteur*20 + 16, longueur*20 + 59);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
		this.setContentPane(panel);

		//implementation menu
		newGame.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0) {				
				
				repaint();
			}
		});
		this.partie.add(newGame);
		this.partie.addSeparator();
		this.partie.add(stats);
		this.partie.add(options);
		//déroulement menu "option" ne fonctionne plus --working on it
		options.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0) {
				Option opt = new Option(null,"Options",true);
				ZdialogInfo zInfo = opt.showOption(); 
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
