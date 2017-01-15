import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.Ref;

import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JOptionPane;


public class CaseComponent extends JComponent {
	private Case refCase;
	private int width = 20;		//taille côté des cases (g)
	//constructeur
	//private int ind=0;
	boolean clic = false;
	boolean perdu = false;
	
	public CaseComponent (Case refCase, Grille grille, CaseComponent[][] grilleAffichage)
	{
		super();
		this.setSize(width, width);
		this.refCase = refCase;
		
		
		this.addMouseListener(new MouseListener() {
			@Override

			public void mousePressed(MouseEvent e) {}

			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				clic = true;
				if (e.getButton() == MouseEvent.BUTTON1) {
					// Bouton gauche
					refCase.setState(Case.State.Discovered);
					//if (refCase.getType() == Case.Type.Empty && grille.getCase(refCase.getX(), refCase.getY()).getVal() == 0){ 
					if ((grille.getCase(refCase.getX(), refCase.getY())).getVal()==0 && (grille.getCase(refCase.getX(), refCase.getY())).getType()==Case.Type.Empty){ 
						// on decouvre les cases autour
						grille.decouvrezero_rec(refCase.getX(), refCase.getY());	
					}				
					if(refCase.getType() == Case.Type.Mine){
						System.out.println("vous avez perdu!");	
						grille.decouvre_all(refCase.getX(), refCase.getY());

						//permet de faire attendre une seconde avant l'affichage du message "vous avez perdu"
						//long start=System.nanoTime();
						//while((System.nanoTime()-start)<1000000000); 

						//affichage du message vous avez perdu 
						JOptionPane jop = new JOptionPane();
						ImageIcon img = new ImageIcon("images/informatio.png");
						jop.showMessageDialog(null, "Vous avez perdu!", "Fin", JOptionPane.INFORMATION_MESSAGE, img);
						perdu = true;
					}
					if (e.getClickCount() == 2 && !e.isConsumed()) {
					     e.consume();
					     //handle double click event.
					     if (refCase.getState() == Case.State.Discovered && refCase.getVal()>=1){
					    	 grille.decouvre_doubleClic(refCase.getX(), refCase.getY());
					     }
					     
					}
				} 
				if (e.getButton() == MouseEvent.BUTTON3) {
					// Bouton droit
					//permet de mettre un drapeau
					refCase.setState(Case.State.Flag);
				}
				if (grille.gagner()==true && perdu==false){
					System.out.println("vous avez gagné");
					//box message "gagné"
					JOptionPane jop = new JOptionPane();
					ImageIcon img = new ImageIcon("images/informatio.png");
					jop.showMessageDialog(null, "Vous avez gagné!", "Fin", JOptionPane.INFORMATION_MESSAGE, img);
				}
				repaint();
				
			}

			@Override
			public void mouseEntered(MouseEvent e) {}

			@Override
			public void mouseExited(MouseEvent e) {}

			@Override
			public void mouseReleased(MouseEvent e) {}
		});
	}

	protected void paintComponent (Graphics g)
	{
		super.paintComponent(g);

		g.drawImage(new ImageIcon("case.png").getImage(), 0, 0, null);
		if(refCase.getType() == Case.Type.Empty){
			g.setColor(new Color(255, 0, 0));
			//g.drawString(""+refCase.getVal(), refCase.getX(), refCase.getY()); à étudier 
			switch (refCase.getVal()){
			case 0: g.drawImage(new ImageIcon("case.png").getImage(), 0, 0, null);break;
			case 1:  g.drawImage(new ImageIcon("case1.png").getImage(), 0, 0, null);break;
			case 2:  g.drawImage(new ImageIcon("case2.png").getImage(), 0, 0, null);break;
			case 3:  g.drawImage(new ImageIcon("case3.png").getImage(), 0, 0, null);break;
			case 4:  g.drawImage(new ImageIcon("case4.png").getImage(), 0, 0, null);break;
			case 5:  g.drawImage(new ImageIcon("case5.png").getImage(), 0, 0, null);break;
			case 6:  g.drawImage(new ImageIcon("case6.png").getImage(), 0, 0, null);break;
			case 7:  g.drawImage(new ImageIcon("case7.png").getImage(), 0, 0, null);break;
			case 8:  g.drawImage(new ImageIcon("case8.png").getImage(), 0, 0, null);break;
			}
			System.out.println(refCase.getVal());
		}
		if(refCase.getType() == Case.Type.Mine){
			g.drawImage(new ImageIcon("-1.png").getImage(), 0, 0, null);					
		}
		if(refCase.getState() == Case.State.Hidden){
			g.drawImage(new ImageIcon("caseN.png").getImage(), 0, 0, null);
		}
		/*if(refCase.getState()==Case.State.Discovered && refCase.getType()==Case.Type.Mine){
			Graphics2D g2d =(Graphics2D) g;
			g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, .9F));
		}*/
		if (refCase.getState()==Case.State.Flag){
			g.drawImage(new ImageIcon("case.png").getImage(), 0, 0, null);
			g.drawImage(new ImageIcon("flag.png").getImage(), 0, 0, null);
		}

	}

	public int getWidth() {
		return this.width;
	}

	public boolean isClic() {
		return clic;
	}

	public void setClic(boolean clic) {
		this.clic = clic;
	}


}
