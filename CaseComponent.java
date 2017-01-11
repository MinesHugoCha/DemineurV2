import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.ImageIcon;
import javax.swing.JComponent;

public class CaseComponent extends JComponent {
	private Case refCase;
	private int width = 20;		
	public CaseComponent (Case refCase, Grille grille, CaseComponent[][] grilleAffichage)
	{
		super();
		this.setSize(width, width);
		this.refCase = refCase;

		this.addMouseListener(new MouseListener() {
			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub				
			}

			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
			}

			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
			}

			@Override
			public void mouseReleased(MouseEvent e) {
				refCase.setState(Case.State.Discovered);
				if (refCase.getType() == Case.Type.Empty && grille.getCase(refCase.getX(), refCase.getY()).getVal() == 0){ 
						// on decouvre les cases autour
						grille.decouvrezero_rec(refCase.getX(), refCase.getY());						
					}				
				else if(refCase.getType() == Case.Type.Mine){
					System.out.println("vous avez perdu!");	
				}
				repaint();				
			}
		});
	}

	protected void paintComponent (Graphics g)
	{
		super.paintComponent(g);

		g.drawImage(new ImageIcon("case.png").getImage(), 0, 0, null);
		if(refCase.getType() == Case.Type.Empty){
			g.setColor(new Color(255, 0, 0));
			g.drawString(""+refCase.getVal(), refCase.getX()+7, refCase.getY()+10);
		}
		else if(refCase.getType() == Case.Type.Mine){
			g.drawImage(new ImageIcon("-1.png").getImage(), 0, 0, null);					
		}
		if(refCase.getState() == Case.State.Hidden){
						Graphics2D g2d =(Graphics2D) g;
						g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, .9F));
			g.drawImage(new ImageIcon("caseN.png").getImage(), 0, 0, null);
		}
	}

	public int getWidth() {
		return this.width;
	}
}
