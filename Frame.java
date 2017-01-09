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
	public static int Cases[][] = new int[100][100];
	public static boolean Hidden[][] = new boolean[100][100];
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
		this.setSize(1300, 800);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
		this.setContentPane(panel);
		generate(64, 36, 20, 12);
		
		newGame.addActionListener(new ActionListener(){
	    	public void actionPerformed(ActionEvent arg0) {	          
	    		Cases = null;
	    		Cases = new int[100][100];
	    		setSize(416, 459);
	    		generate(20, 20, 20, 30);	    		
	    		repaint();
	    	}
	    });
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
public static void generate(int X, int Y, int size, int pourcent){
		x= X;
		y = Y;
		for(int x1 = 0; x1 < X; x1++){
			for(int y1 = 0; y1 < Y; y1++){
				Hidden[x1][y1] = true;
				if(Math.random()*100 < pourcent){
					Cases[x1][y1] = -1;
				}
			}
		}
	}
}
/*
public void init(){
	addMouseListener(this);
}

public void mouseReleased(MouseEvent e){
		xmsg = e.getX();
		ymsg = e.getY();
		repaint();
}
}


*/
