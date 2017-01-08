import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

public class Frame extends JFrame{
	public static int Cases[][] = new int[100][100];
	public static int x = 16;
	public static int y = 16;
	static Panel panel = new Panel();	
	private JMenuBar menuBar = new JMenuBar();
	private JMenu partie = new JMenu("Partie");
	private JMenu aide = new JMenu("?");
	private JMenuItem newGame = new JMenuItem("new game"),
	  stats = new JMenuItem("statistiques"),
	  options = new JMenuItem("options"),
	  quitter = new JMenuItem("quitter"),
	  aideMenu = new JMenuItem("aide"),
	  aPropos = new JMenuItem("à propos");
	
	public Frame(){
		this.setTitle("title");
		this.setVisible(true);
		this.setSize(800, 400);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
		this.setContentPane(panel);
		
		//implementation menu
		this.partie.add(newGame);
	    this.partie.addSeparator();
	    this.partie.add(stats);
	    this.partie.add(options);
	    //déroulement menu "option" ne fonctionne plus --working on it
	    options.addActionListener(new ActionListener(){
	    	public void actionPerformed(ActionEvent arg0) {
	          Option opt = new Option(null,"Options",true);
	          JOptionPane jop = new JOptionPane();
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
