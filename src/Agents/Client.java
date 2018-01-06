package Agents;

import Objects.*;
import jade.core.AID;
import jade.core.Agent;
import jade.core.behaviours.CyclicBehaviour;
import jade.core.behaviours.OneShotBehaviour;
import jade.core.behaviours.TickerBehaviour;
import jade.domain.DFService;
import jade.domain.FIPAAgentManagement.DFAgentDescription;
import jade.domain.FIPAAgentManagement.ServiceDescription;
import jade.domain.FIPAException;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.MessageTemplate;
import jade.lang.acl.UnreadableException;
import jade.util.leap.Iterator;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ContainerEvent;
import java.awt.event.ContainerListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;;

public class Client extends Agent {
	private int numero_Client ;
	Atelier At = new Atelier();
	private List<Produits> List_P = new ArrayList<>();
	public List<String> tableauLettres = new ArrayList<>();
	Produits p ;
	   private myframeGUI gui;
	   @Override
	    protected void setup() {
	    	//System.out.println("Aaa");
	    	//System.out.println(p.getListProduit().get(0));
		   // new
	        /*gui = new myframeGUI();
	        gui.setVisible(true);

	        List_P = At.getProduitList();

	        for(java.util.Iterator<Produits> it=List_P.iterator(); it.hasNext();) {
	        	
	        	p=it.next();
	        	tableauLettres.add(p.getNom());
	        	
	        	//String[] liste = {p.getNom(),Integer.toString(p.getstock()),Integer.toString(p.getfornisseur())};
	        	//System.out.println(it.next().getNom());
	        	//gui.setRowsDemande(liste);
	        }
	        //String [] tableauLettres = {",","eee"};
	        Random r = new Random();
	        String motPasse = null;
	        int [] tableauChiffres = {0,1,2,3,4,5,6,7,8};
	 
		        for(int j=0;j<2;j++) { 	   
		        	int mpChiffre = r.nextInt(tableauChiffres.length);
		        	String[] liste = {tableauLettres.get(mpChiffre),Integer.toString(mpChiffre)};
		        	//System.out.println(it.next().getNom());
		        	gui.setRowsDemande(liste);
		        }	
		        */  
		  
			addBehaviour(new OneShotBehaviour() {				
				@Override
				public void action() {
					//recoit information
					List_P = At.getProduitList();
			        for(java.util.Iterator<Produits> it=List_P.iterator(); it.hasNext();) {
			        	p=it.next();
			        	tableauLettres.add(p.getNom());
			        }
			        //generer des demandes
			        Random r = new Random();
			        int [] tableauChiffres = {0,1,2,3,4,5,6,7,8};
				        //for(int j=0;j<2;j++) { 	   
				        	int mpChiffre = r.nextInt(tableauChiffres.length);
				        	String[] liste = {tableauLettres.get(mpChiffre),Integer.toString(mpChiffre)};
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
							System.out.println("*********CLIENT  SEND**************"
							+"msg1"+msg1.getContent()+" msg2"+msg2.getContent());
				       // }	
				}
			});
			
		   
		   
		   /*
		   List_P = At.getProduitList();
	        for(java.util.Iterator<Produits> it=List_P.iterator(); it.hasNext();) {
	        	p=it.next();
	        	tableauLettres.add(p.getNom());
	        }
	        
	       
			addBehaviour(new TickerBehaviour(this,10000) {
				@Override
				protected void onTick() {
					// TODO Auto-generated method stub
			        //generer des demandes
			        Random r = new Random();
			        int [] tableauChiffres = {0,1,2,3,4,5,6,7,8};
				        //for(int j=0;j<2;j++) { 	   
				        	int mpChiffre = r.nextInt(tableauChiffres.length);
				        	String[] liste = {tableauLettres.get(mpChiffre),Integer.toString(mpChiffre)};
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
							System.out.println("*********CLIENT  SEND**************"
							+"msg1"+msg1.getContent()+" msg2"+msg2.getContent());
				        //}	
					
				}
			});
			*/
	    }
	   
}
					
	
