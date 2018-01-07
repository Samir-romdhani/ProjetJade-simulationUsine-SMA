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
import jade.domain.JADEAgentManagement.*;
import jade.gui.GuiEvent;
import jade.gui1.*;

public class FournisseursAgent extends GuiAgent {
// ----------------------------------------

   private String type_Bois ;
   private int nbPlanhes ;
   
   
	AtelierAgent At = new AtelierAgent();
	private List<Produits> List_P = new ArrayList<>();
	public List<String> tableauProduits = new ArrayList<>();
	Produits p ;
	
    ArrayList<String> TypeBois = new ArrayList();
    
    public void setTypeBois(String typeBois) {
		this.type_Bois = typeBois;
	}
    
    public String getType_Bois() {
		return type_Bois;
	}
    public void setNbPlanhes(int i) {
		this.nbPlanhes = i;
	}
    public int getNbPlanhes() {
		return nbPlanhes;
	}
    
    public ArrayList<String> getTypeBois() {
		return TypeBois;
	}
    
    public FournisseursAgent() {
    	TypeBois.add("Chêne");
    	TypeBois.add("Merisier");
    	TypeBois.add("Noyer");
	}

   protected void setup() {
	   Object[] args = getArguments();
	   String arg1 = (String)args[0]; 
	   System.out.println(arg1);

	  
	   
	  //init();

	  // Program the main behaviour of this agent
		addBehaviour(new OneShotBehaviour() {				
			@Override
			public void action() {	
				   List_P = At.getProduitList();
				for (int i = 0; i < List_P.size(); i++) {
					Produits p = List_P.get(i);
					tableauProduits.add(p.getNom());					
					
		        	if((p.getNom().equals(arg1))&&i<3) {
		        		setTypeBois(TypeBois.get(0));
		        		setNbPlanhes(p.getPlanche().getNb());
		        	}
		        	else
			        	if((p.getNom().equals(arg1))&&i<6) {
			        		setTypeBois(TypeBois.get(1));
			        		setNbPlanhes(p.getPlanche().getNb());
			        	}
			        	else
				        	if((p.getNom().equals(arg1))) {
				        		setTypeBois(TypeBois.get(2));
				        		setNbPlanhes(p.getPlanche().getNb());
				        	}
		        	}
		        	

		        	
					ACLMessage msgF1 = new ACLMessage(ACLMessage.INFORM);
					ACLMessage msgF2 = new ACLMessage(ACLMessage.INFORM);
					ACLMessage msgF3 = new ACLMessage(ACLMessage.INFORM);
					msgF1.addReceiver(new AID("Atelier", AID.ISLOCALNAME));
					msgF2.addReceiver(new AID("Atelier", AID.ISLOCALNAME));
					msgF3.addReceiver(new AID("Atelier", AID.ISLOCALNAME));
					msgF1.setContent(type_Bois);
					msgF2.setContent(String.valueOf(nbPlanhes));
					msgF3.setContent(arg1);
					msgF1.setLanguage("Prolog");msgF2.setLanguage("Prolog");msgF3.setLanguage("Prolog");
					send(msgF1);
					send(msgF2);
					send(msgF3);
					System.out.println("Fournisseur Send : Produit :"+msgF3.getContent());
					System.out.println("Fournisseur Send : nb Planches :"+msgF2.getContent());
					System.out.println("Fournisseur Send : type de Bois->"+msgF1.getContent());
					
				}
			});
   }
   void init() {
// -------------

	  // Register language and ontology
	  getContentManager().registerLanguage(new SLCodec());
	  getContentManager().registerOntology(MobilityOntology.getInstance());

	  // Create and display the gui
	 // myGui = new FournisseursAgentGui(this);
	 // myGui.setVisible(true);
	 //myGui.setLocation(destination.getName());
	  
   }
   protected void onGuiEvent(GuiEvent e) {
   }
} // class MobileAgent