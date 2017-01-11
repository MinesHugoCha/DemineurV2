import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JPanel;

public class Panel extends JPanel{

	private CaseComponent[][] grille;
	public Panel(CaseComponent[][] grille) {
		super();
		this.grille = grille;
		this.setLayout(null);
	}

	public void paintComponent(Graphics g){		
		g.setColor(Color.white);		
		g.fillRect(0,  0,  getSize().width, getSize().height);
		for (int x = 0; x < grille.length; x++){
			for ( int y = 0; y < grille[x].length; y++){
				CaseComponent caseComponent = grille[x][y];
				int width = caseComponent.getWidth();
				caseComponent.setLocation(y * width, x * width);
				this.add(caseComponent);
			}
		}
	}
}
