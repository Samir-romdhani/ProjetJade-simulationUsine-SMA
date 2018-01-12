package GuiAgent;


import java.util.*;

import Agents.Atelier;
import Objects.Produits;

import java.io.*;

import jade.core.*;
import jade.core.behaviours.*;
import jade.lang.acl.*;
import jade.content.*;
import jade.content.lang.*;
import jade.content.lang.sl.*;
import jade.content.onto.basic.*;
import jade.domain.*;
import jade.domain.mobility.*;
import jade.gui.GuiAgent;
import jade.gui.GuiEvent;
import jade.gui1.*;
import jade.domain.JADEAgentManagement.*;

public class ClientAgent extends GuiAgent {
// ----------------------------------------

  // private AID controller;
   //private Location destination;
   transient protected ClientAgentGui myGui;
   
  // private jade.wrapper.AgentContainer home;
   
   
	Atelier At = new Atelier();
	private List<Produits> List_P = new ArrayList<>();
	public List<String> tableauProduits = new ArrayList<>();
	Produits p ;

   @SuppressWarnings("serial")
protected void setup() {

   	  // Retrieve arguments passed during this agent creation
   	 // Object[] args = getArguments();
	  //controller = (AID) args[0];
	 // destination = here();
	   System.out.println("Agent:" + this.getLocalName()+" "+"TickerBehaviour(this, 1500)");
	 init();

	  //behaviour of this agent
	 /*
		addBehaviour(new OneShotBehaviour() {				
			@Override
			public void action() {
				//recoit information
				List_P = At.getProduitList();
		        for(java.util.Iterator<Produits> it=List_P.iterator(); it.hasNext();) {
		        	p=it.next();
		        	tableauProduits.add(p.getNom());
		        }
		        //generer des demandes
		        Random r = new Random();
		        int [] tableauChiffres = {1,2,3,4,5,6,7,8,9};
			      //  for(int j=0;j<2;j++) { 	   
			        	int Chiffre = r.nextInt(tableauChiffres.length);
			        	String[] liste = {tableauProduits.get(Chiffre),Integer.toString(Chiffre+1)};
			        	// envoi du demande
						ACLMessage msg1 = new ACLMessage(ACLMessage.INFORM);
						ACLMessage msg2 = new ACLMessage(ACLMessage.INFORM);
						msg1.addReceiver(new AID("Atelier", AID.ISLOCALNAME));
						msg2.addReceiver(new AID("Atelier", AID.ISLOCALNAME));
						msg1.setContent(liste[0]);
						msg2.setContent(liste[1]);
						msg1.setLanguage("Prolog");msg2.setLanguage("Prolog");
						send(msg1);
						send(msg2);
						myGui.setInfo("Produit :"+msg1.getContent()+" Quntitée : "+msg2.getContent());
						myGui.setLocation("Table->"+msg1.getContent()+"et qt->"+msg2.getContent());
				        //agents.add(nameAtelier);
				        //myGui.updateList(agents);
						//System.out.println("*********CLIENT  SEND**************"
						//+"msg1"+msg1.getContent()+" msg2"+msg2.getContent());
			 //      }	
			}
		});
		*/
		addBehaviour(new TickerBehaviour(this, 1500) {
			
			@Override
			protected void onTick() {
				// TODO Auto-generated method stub
				//recoit information
				List_P = At.getProduitList();
		        for(java.util.Iterator<Produits> it=List_P.iterator(); it.hasNext();) {
		        	p=it.next();
		        	tableauProduits.add(p.getNom());
		        }
		        //generer des demandes
		        Random r = new Random();
		        int [] tableauChiffres = {1,2,3,4,5,6,7,8,9};
			      //  for(int j=0;j<2;j++) { 	   
			        	int Chiffre1 = r.nextInt(tableauChiffres.length);
			        	int Chiffre2 = r.nextInt(tableauChiffres.length);
			        	String[] liste = {tableauProduits.get(Chiffre1),Integer.toString(Chiffre2+1)};
			        	// envoi du demande
						ACLMessage msg1 = new ACLMessage(ACLMessage.INFORM);
						ACLMessage msg2 = new ACLMessage(ACLMessage.INFORM);
						msg1.addReceiver(new AID("Atelier", AID.ISLOCALNAME));
						msg2.addReceiver(new AID("Atelier", AID.ISLOCALNAME));
						msg1.setContent(liste[0]);
						msg2.setContent(liste[1]);
						msg1.setLanguage("Prolog");msg2.setLanguage("Prolog");
						try {
							send(msg1);
							send(msg2);
						} catch (Exception e) {
							// TODO: handle exception
						}

						myGui.setInfo("Produit :"+msg1.getContent()+" Quntitée : "+msg2.getContent());
						myGui.setmessage("Table->"+msg1.getContent()+" et qt->"+msg2.getContent());
				        //agents.add(nameAtelier);
				        //myGui.updateList(agents);
						//System.out.println("*********CLIENT  SEND**************"
						//+"msg1"+msg1.getContent()+" msg2"+msg2.getContent());
			 //      }	
				
			}
		});
   }

   void init() {
	  // Create and display the gui
	  myGui = new ClientAgentGui(this);
	  myGui.setVisible(false);
	  
   }
   protected void onGuiEvent(GuiEvent e) {
   }
}