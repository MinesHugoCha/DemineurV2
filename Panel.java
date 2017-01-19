
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JPanel;

public class Panel extends JPanel{

	private CaseComponent[][] grille;
	public Panel(CaseComponent[][] grille) {
		super();
		this.grille = grille;
		this.setLayout(null);
		//repaint();

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

/*
class Zoom6000 implements ActionListener{
public void actionPerformed(ActionEvent e){
    outputGUIView.graphPanel.setPreferredSize(new  Dimension(screenWidth, 6000));
    outputGUIView.graphScrollPanel.updateUI();

}
}*/
