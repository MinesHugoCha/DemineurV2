import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.MouseInfo;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class Panel extends JPanel{
	int mouseX = 0;
	int mouseY = 0;
	
	public void paintComponent(Graphics g){
		//mouseX= MouseInfo.getPointerInfo().getLocation().x.getLocationOnScreen().x;
		g.setColor(Color.white);
		System.out.println(getSize().width+"\t"+getSize().height);
		g.fillRect(0,  0,  getSize().width, getSize().height);
		for (int x = 0; x < Frame.x; x++){
			for ( int y = 0; y < Frame.y; y++){
				g.drawImage(new ImageIcon("case.png").getImage(),  x*20, y*20, null);
				if(Frame.Cases[x][y] > 0 ){
					g.setColor(new Color(255, 0, 0));
					//g.drawString();
				}
				else if(Frame.Cases[x][y] == -1){
					g.drawImage(new ImageIcon("-1.png").getImage(),  x*20, y*20, null);					
				}
				if(Frame.Hidden[x][y]){
					Graphics2D g2d =(Graphics2D) g;
					//g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, .8F));
					g.drawImage(new ImageIcon("caseN.png").getImage(),  x*20, y*20, null);
				}
			}
		}
	}
}
