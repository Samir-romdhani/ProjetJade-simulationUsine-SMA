package GuiAgent;

import Objects.*;
import Objects.Produits;
import jade.core.AID;
import jade.core.Agent;
import jade.core.behaviours.CyclicBehaviour;
import jade.core.behaviours.OneShotBehaviour;
import jade.domain.DFService;
import jade.domain.FIPAAgentManagement.DFAgentDescription;
import jade.domain.FIPAAgentManagement.ServiceDescription;
import jade.domain.FIPAException;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.MessageTemplate;
import jade.lang.acl.UnreadableException;
import jade.util.leap.Iterator;

import javax.swing.*;

import GuiAgent.ControllerAgentGui;

import java.awt.*;
import java.awt.event.ContainerEvent;
import java.awt.event.ContainerListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

public class Fournisseurs extends Agent {
	private int numero ;
  
    private myframeGUI gui;
    
    private Fournisseurs a ;
    

    //gui.setVisible(true);
    public myframeGUI getGui() {
		return gui;
	}
    

    
    
    @Override
    protected void setup() {

 	   Object[] args = getArguments();
 	   String arg1 = (String)args[0]; 
 	   System.out.println(arg1);


		addBehaviour(new OneShotBehaviour() {
			@Override
			public void action() {
				
				
				
				/*System.out.println("****Fournisseurs  receive msg1**cc**");
				// receive du demande
				ACLMessage msgA = receive();
				if((msgA != null)) {
					JOptionPane.showMessageDialog(null, "message1 --"+msgA.getContent());
					System.out.println("****Fournisseurs  receive msg1****"+msgA.getContent());
					
					String[] liste = {msgA.getContent(),msgA.getContent()};
					gui.setRowsDemande(liste);
				}
				else block();
				*/
			}
		});
    }

}
