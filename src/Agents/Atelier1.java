package Agents;

import javax.swing.JOptionPane;

import jade.core.AID;
import jade.core.Agent;
import jade.core.behaviours.CyclicBehaviour;
import jade.lang.acl.ACLMessage;

public class Atelier1 extends Agent {
	
	@Override
	protected void setup() {
		addBehaviour(new CyclicBehaviour(this) {
			
			@Override
			public void action() {
				// receive du demande
				ACLMessage msg = receive();
				//JOptionPane.showMessageDialog(null, "message --"+msg.getContent());
				//System.out.println("My name is "+ getLocalName());
				//System.out.println("msg.getLanguage "+ msg.getLanguage());
				//System.out.println("*********Atelier1  receive**************"+msg.getContent());
				//System.out.println("*********Atelier1  receive**************"+msg.getContent());
				if(msg != null) {
					JOptionPane.showMessageDialog(null, "message --"+msg.getContent());
					System.out.println("*********Atelier1  receive**************"+msg.getContent());

					//System.out.println("*********Atelier1  receive**************"+msg.getContent());
				}
				else block();			
			}
		});
	}

}
