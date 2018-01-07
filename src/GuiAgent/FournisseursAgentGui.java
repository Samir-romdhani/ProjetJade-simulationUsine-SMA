package GuiAgent;


import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.*;

import jade.gui1.*;


public class FournisseursAgentGui extends JFrame {
// -----------------------------------------

   private JTextField location;
   private JTextField info;
   private FournisseursAgent myAgent;

   public FournisseursAgentGui(FournisseursAgent a) {
// --------------------------------------

	  myAgent = a;
      setTitle(myAgent.getLocalName());

	  // Add button and text field
      Container c = getContentPane();
      JPanel base = new JPanel();
      base.setBorder(new EmptyBorder(20,20,20,20));
      getContentPane().add(base);
	  base.setLayout(new BorderLayout(0,20));
	  JPanel pane = new JPanel();
	  pane.setLayout(new BorderLayout(10,0));
	  pane.add(new JLabel("CLIENT  SEND: "), BorderLayout.WEST);
	  
	  
	  pane.add(location = new JTextField(15), BorderLayout.EAST);
	  location.setEditable(false);
	  location.setBackground(Color.white);
	  base.add(pane, BorderLayout.NORTH);
	  
	  
	  
	  base.add(info = new JTextField(30), BorderLayout.SOUTH);
	  //base.add(info = new JTextField(), BorderLayout.SOUTH);
	  

      
	  info.setEditable(false);
	  //info.setHorizontalAlignment(JTextField.CENTER);

	  setSize(200,100);
	  pack();
	  setResizable(false);
	  Rectangle r = getGraphicsConfiguration().getBounds();
	  setLocation(r.x + r.width-getWidth(), r.y);
   }

   public void setLocation(String loc){

      this.location.setText(loc);
   }
   

   public void setInfo(String info){

      this.info.setText(info);
   }

}