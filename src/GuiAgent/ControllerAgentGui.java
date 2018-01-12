package GuiAgent;


import java.util.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
import javax.swing.table.*;

import Objects.Produits;

import javax.swing.border.*;

import jade.core.*;
import jade.gui.GuiEvent;
import jade.gui1.*;
import jade.util.leap.List;


public class ControllerAgentGui extends JFrame implements ActionListener {
// -----------------------------------------------------------------------

   private JList list;
   private DefaultListModel listModel;
   //private JComboBox locations;
   private JButton newCommandes, quit;
   private ControllerAgent myAgent;

   public ControllerAgentGui(ControllerAgent a, Set s) {
	   
      super("Commercial");
      this.myAgent = a;
      JPanel base = new JPanel();
      base.setBorder(new EmptyBorder(15,15,15,15));
      base.setLayout(new BorderLayout(10,0));
	  getContentPane().add(base);


	  JPanel pane = new JPanel();
	  base.add(pane, BorderLayout.WEST);
      pane.setLayout(new BorderLayout(0,10));

      
      listModel = new DefaultListModel();
      list = new JList(listModel);
      
      list.setBorder(new EmptyBorder(0,0,0,0));
      list.setVisibleRowCount(12);
      list.setFixedCellHeight(16);
      list.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
      pane.add(new JScrollPane(list), BorderLayout.NORTH);
      
      
      pane = new JPanel();
      pane.setBorder(new EmptyBorder(0,0,110,0));
	  base.add(pane, BorderLayout.EAST);
      pane.setLayout(new GridLayout(2,1,0,5));
      /*
      pane.add(newAgent = new JButton("New agent"));
      newAgent.setToolTipText("Create a new agent");
      newAgent.addActionListener(this);
      */
      JLabel client = new JLabel();
      client.setFont(new Font("Tahoma", Font.PLAIN, 15));
      //livraison.setBounds(250, 100, 250, 490);
      //contentPane.add(livraison);
      pane.add(client = new JLabel("Clients :"));
      pane.add(newCommandes = new JButton("new Commandes"));
      newCommandes.setToolTipText("Create a new Commandes");
      newCommandes.addActionListener(this);
      
      pane.add(quit = new JButton("Quit"));
      quit.setToolTipText("Terminate this program");
      quit.addActionListener(this);
      

      addWindowListener(new WindowAdapter() {
	     public void windowClosing(WindowEvent e) {
		    shutDown();
		 }
	  });

      setSize(300, 210);
      setResizable(false);
      pack();
   }

   public void actionPerformed(ActionEvent ae) {
     if (ae.getSource() == newCommandes) {

          GuiEvent ge = new GuiEvent(this, myAgent.NEW_Commande);
          myAgent.postGuiEvent(ge);
 	  }
      else if (ae.getSource() == quit) {
         shutDown();
	  }
   }

   void shutDown() {
      GuiEvent ge = new GuiEvent(this, myAgent.QUIT);
      myAgent.postGuiEvent(ge);
   }

   public void updateList(Vector v) {

      listModel.clear();
      for (int i = 0; i < v.size(); i++){
         listModel.addElement(v.get(i));
	  }
   }
   
   
   public void updateList2(String[] l) {
      listModel.clear();
      for (int i = 0; i < l.length; i++){
         listModel.addElement(l);
	  }
   }

}