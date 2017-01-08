import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;

public class Option extends JDialog {
  private ZDialogInfo Info = new ZDialogInfo();
  private boolean sendData;
  private JRadioButton debutant, intermed, avancé, custom;

  public Option(JFrame parent, String title, boolean modal){
    super(parent, title, modal);
    this.setSize(550, 270);
    this.setLocationRelativeTo(null);
    this.setResizable(false);
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    this.initComponent();
  }

  public ZDialogInfo showOption(){
    this.sendData = false;
    this.setVisible(true);      
    return this.Info;      
  }

  private void initComponent(){
    //Difficulté
    JPanel panDiff = new JPanel();
    panDiff.setBackground(Color.white);
    panDiff.setBorder(BorderFactory.createTitledBorder("Difficulté"));
    panDiff.setPreferredSize(new Dimension(440, 60));
    debutant = new JRadioButton("Debutant");
    debutant.setSelected(true);
    intermed = new JRadioButton("Intermédiaire");
    avancé = new JRadioButton("Avancé");
    //tranche4 = new JRadioButton("Custom");
    ButtonGroup bg = new ButtonGroup();
    bg.add(debutant);
    bg.add(intermed);
    bg.add(avancé);
    panDiff.add(debutant);
    panDiff.add(intermed);
    panDiff.add(avancé);


    JPanel content = new JPanel();
    content.setBackground(Color.white);
    content.add(panDiff);

    JPanel control = new JPanel();
    JButton okBouton = new JButton("OK");
    
    okBouton.addActionListener(new ActionListener(){
      public void actionPerformed(ActionEvent arg0) {        
       // Info = new ZDialogInfo(getAge());
        setVisible(false);
      }

      public String getAge(){
        return (debutant.isSelected()) ? debutant.getText() : 
               (intermed.isSelected()) ? intermed.getText() : 
               (avancé.isSelected()) ? avancé.getText() :               
            	   debutant.getText();  
      }    
    });

    JButton cancelBouton = new JButton("Annuler");
    cancelBouton.addActionListener(new ActionListener(){
      public void actionPerformed(ActionEvent arg0) {
        setVisible(false);
      }      
    });

    control.add(okBouton);
    control.add(cancelBouton);

    this.getContentPane().add(content, BorderLayout.CENTER);
    this.getContentPane().add(control, BorderLayout.SOUTH);
  }  
}
