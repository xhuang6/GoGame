package gogame;


import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/**
 *
 * @author Xiying Huang
 */

 public class finish extends JDialog implements ActionListener{
    private static final long serialVersionUID = 1L;
    JFrame f = new JFrame("Finish");
    boolean countFlag=true;
    public finish(JFrame jframe){
                Button Yes, Cancel;   
                f.setSize(250,100);
                f.setLocation(350,250);
                f.setLayout(new FlowLayout());
                f.add(new JLabel("Do you really want to finish the game?"));
                f.add(Yes = new Button("Yes"));
                f.add(Cancel = new Button("Cancel"));
                Yes.addActionListener(this);
                Cancel.addActionListener(this);
                f.setResizable(false);
                f.setVisible(true);
            }

    public void actionPerformed(ActionEvent e) {
                if (e.getActionCommand().equals("Yes")) {
                    f.dispose();
                    countFlag = true;
                }
                else if (e.getActionCommand().equals("Cancel")){
                    f.dispose();
                    countFlag = false;
                }
   }
        public boolean getButtonPrassed(){
        return countFlag;
    }

   }




