package GuiAgent;

import java.util.*;

import javax.swing.JOptionPane;

import Objects.Produits;
import Objects.myframeGUI;

import java.io.*;

import jade.lang.acl.*;
import jade.content.*;
import jade.content.onto.basic.*;
import jade.content.lang.*;
import jade.content.lang.sl.*;
import jade.core.*;
import jade.core.behaviours.*;
import jade.domain.*;
import jade.domain.mobility.*;
import jade.gui.GuiAgent;
import jade.gui.GuiEvent;
import jade.gui1.*;
import jade.domain.JADEAgentManagement.*;


public class ControllerAgent extends GuiAgent {
// --------------------------------------------

   private jade.wrapper.AgentContainer home;
   private jade.wrapper.AgentContainer[] container = null;
   private Map locations = new HashMap();
   private Vector agents = new Vector();
   private ArrayList<Produits> agents1 = new ArrayList<Produits>() ;
   static int agentCnt = 1;
   static int FouniseursagentCnt = 1;
   private int agentAtelierCnt = 1;
   private int command;
   transient protected ControllerAgentGui myGui;
   
   
   public Map getLocations() {
	return locations;
}

   public static final int QUIT = 0;
   public static final int NEW_AGENT = 1;
   public static final int MOVE_AGENT = 2;
   public static final int CLONE_AGENT = 3;
   public static final int KILL_AGENT = 4;
   public static final int NEW_Commande = 5;
   
   private ArrayList<Produits> produitList = new ArrayList<>();
   Produits p ;
   
   public void setProduitList(List<Produits> produitList) {
		this.produitList = (ArrayList<Produits>) produitList;
	}
    public List<Produits> getProduitList() {
		return produitList;
	}
    
    ArrayList<String> TypeBois ;//= new ArrayList();
    
    public ArrayList<String> getTypeBois() {
		return TypeBois;
	}
    
    
    Produits p1 = new Produits("Table", 8, 4, 5) ;
    Produits p2 = new Produits("Chaise", 7, 4, 2) ;
    Produits p3 = new Produits("Buffet", 10, 4, 6) ;

   
    /////////////////////////
    Produits p4 = new Produits("Lit", 11, 2, 7) ;
    Produits p5 = new Produits("Chevet", 5, 2, 1) ;
    Produits p6 = new Produits("Armoire", 8, 2, 8) ;


   ////////////////////////////////////////
    Produits p7 = new Produits("Banquette", 12, 3, 6) ;
    Produits p8 = new Produits("Fauteuil", 0, 3, 3) ;
    Produits p9 = new Produits("Etagère", 6, 3, 8) ;

   // Get a JADE Runtime instance
   jade.core.Runtime runtime = jade.core.Runtime.instance();

   
   public ControllerAgent() {
	   TypeBois = new ArrayList();
	   	//gui = new myframeGUI();
	   	TypeBois.add("Chêne");
    	TypeBois.add("Merisier");
    	TypeBois.add("Noyer");
    	produitList.add(p1);
    	produitList.add(p2);
    	produitList.add(p3);
    	produitList.add(p4);
    	produitList.add(p5);
    	produitList.add(p6);
    	produitList.add(p7);
    	produitList.add(p8);
    	produitList.add(p9);
    	
}
   

   public ArrayList<Produits> getAgents1() {
	return agents1;
}
   protected void setup() {
	  // Register language and ontology
	   
	  getContentManager().registerLanguage(new SLCodec());
	  getContentManager().registerOntology(MobilityOntology.getInstance());
	 

      try {
         // Create the container objects
         home = runtime.createAgentContainer(new ProfileImpl());
         
         container = new jade.wrapper.AgentContainer[1];
         for (int i = 0; i <2; i++){
            container[0] = runtime.createAgentContainer(new ProfileImpl());
	     }
         
	     //doWait(2000);
	     

	     // Get available locations with AMS
	     //sendRequest(new Action(getAMS(), new QueryPlatformLocationsAction()));

	     //Receive response from AMS
	     /*
         MessageTemplate mt = MessageTemplate.and(
			                  MessageTemplate.MatchSender(getAMS()),
			                  MessageTemplate.MatchPerformative(ACLMessage.INFORM));
         ACLMessage resp = blockingReceive(mt);
         ContentElement ce = getContentManager().extractContent(resp);
         Result result = (Result) ce;
         jade.util.leap.Iterator it = result.getItems().iterator();
         while (it.hasNext()) {
            Location loc = (Location)it.next();
            locations.put(loc.getName(), loc);
		 }
         
         */
	  }
	  catch (Exception e) { e.printStackTrace(); }



	  // Create and show the gui
      myGui = new ControllerAgentGui(this, locations.keySet());
      myGui.setVisible(true);
      //init();
      agents.add("Les Agents appelés sont :");
      myGui.updateList(agents);
      
      jade.wrapper.AgentController a = null;
      try {
	  String nameAtelier = "Atelier";
      a = home.createNewAgent(nameAtelier, AtelierAgent.class.getName(), null);
      a.start();
      agents.add(nameAtelier);
      myGui.updateList(agents);
	} catch (Exception e) {
		 System.out.println("Problem creating new agent");
	}
      
      //receive msg from Atelier about product to do appel for founisseur Agent
		addBehaviour(new CyclicBehaviour() {
			@Override
			public void action() {
				//System.out.println("****ConrollersAgent  receive msg1**cc**");
				// receive du demande
				ACLMessage msgA = receive();
				ACLMessage msgP = receive();
				if((msgA != null)&&(msgP != null)) {
					JOptionPane.showMessageDialog(null, "message -->"+msgA.getContent()+msgP.getContent());
					//gui.setRowsDemande(liste);
					jade.wrapper.AgentController a = null;
				      try {
				    	  Object[] args = new Object[1];
				    	  args[0] = msgP.getContent();
				          String nameF = "Fournisseurs"+FouniseursagentCnt++;
				          a = home.createNewAgent(nameF, FournisseursAgent.class.getName(), args);
					        a.start();
					        agents.add(nameF+" : "+msgP.getContent());
					        myGui.updateList(agents);
					        System.out.println("creating new agent");
					} catch (Exception e) {
						 System.out.println("Problem creating new Fournisseurs");
					}
				}
				else block();						
			}						
		});
		
      


     }


   protected void onGuiEvent(GuiEvent ev) {
// ----------------------------------------

	  command = ev.getType();
	  

	  if (command == QUIT) {
	     try {
		    home.kill();
		    for (int i = 0; i < container.length; i++) container[i].kill();
	     }
	     catch (Exception e) { e.printStackTrace(); }
	     myGui.setVisible(false);
	     myGui.dispose();
		 doDelete();
		 System.exit(0);
      }
	  
	  if (command == NEW_Commande) {

		     jade.wrapper.AgentController a = null;
	         try {
	            Object[] args = new Object[2];
	            args[0] = getAID();
	            String name = "Client"+agentCnt++;
	            a = home.createNewAgent(name, ClientAgent.class.getName(), args);
		        a.start();
		        agents.add(name);
		        myGui.updateList(agents);
		        
		        String name1 = "Commande livrée";
		        agents.add(name1);
		        myGui.updateList(agents);
		        
		        
				addBehaviour(new CyclicBehaviour() {
					@Override
					public void action() {
						//System.out.println("****ConrollersAgent  receive msg1**cc*2*");
						// receive du demande
						ACLMessage msgA = receive();
						ACLMessage msgP = receive();
						if((msgA != null)&&(msgP != null)) {
							JOptionPane.showMessageDialog(null, "message1 --"+msgA.getContent()+msgP.getContent());
							//gui.setRowsDemande(liste);
							jade.wrapper.AgentController a = null;
						      try {
						    	  Object[] args = new Object[1];
						    	  args[0] = msgP.getContent();
						          String nameF = "Fournisseurs"+FouniseursagentCnt++;
						          a = home.createNewAgent(nameF, FournisseursAgent.class.getName(), args);
							        a.start();
							        agents.add(nameF+" "+msgP.getContent());
							        myGui.updateList(agents);
							} catch (Exception e) {
								 System.out.println("Problem creating new agent");
							}
						}
						else block();						
					}						
				});
		        		        
		     }
	         catch (Exception ex) {
			    System.out.println("Problem creating new agent");
		     }
	         return;
		  }
	  
	  /*
	  
      String agentName = (String)ev.getParameter(0);
      AID aid = new AID(agentName, AID.ISLOCALNAME);
      System.out.println("aid = agentName : "+agentName);

	  if (command == MOVE_AGENT) {

         String destName = (String)ev.getParameter(1);
         Location dest = (Location)locations.get(destName);
         MobileAgentDescription mad = new MobileAgentDescription();
         mad.setName(aid);
         mad.setDestination(dest);
         MoveAction ma = new MoveAction();
         ma.setMobileAgentDescription(mad);
         sendRequest(new Action(aid, ma));
	  }
      else if (command == CLONE_AGENT) {

         String destName = (String)ev.getParameter(1);
         Location dest = (Location)locations.get(destName);
         MobileAgentDescription mad = new MobileAgentDescription();
         mad.setName(aid);
         mad.setDestination(dest);
         String newName = "Clone-"+agentName;
         CloneAction ca = new CloneAction();
         ca.setNewName(newName);
         ca.setMobileAgentDescription(mad);
         sendRequest(new Action(aid, ca));
         agents.add(newName);
         myGui.updateList(agents);
	  }
      else if (command == KILL_AGENT) {

         KillAgent ka = new KillAgent();
         ka.setAgent(aid);
         sendRequest(new Action(aid, ka));
	     agents.remove(agentName);
		 //myGui.updateList(agents);
		 myGui.updateList(agents);
		 
	  }
	  */
   }
   
   
   
   void init() { 
	  
      for(java.util.Iterator<Produits> it=produitList.iterator(); it.hasNext();) {
      	p=it.next();
      	String[] liste = {p.getNom(),Integer.toString(p.getstock()),Integer.toString(p.getfornisseur())};
      	//System.out.println(it.next().getNom());
      	//gui.setRows(liste);
	        
      agents.add(p.getNom()+" -- "+p.getstock()+" -- "+p.getfornisseur());
      //myGui.updateList1(produitList,"Table","5");	 
      myGui.updateList(agents);
      }
	  
   }
   


   void sendRequest(Action action) {
// ---------------------------------

      ACLMessage request = new ACLMessage(ACLMessage.REQUEST);
      request.setLanguage(new SLCodec().getName());
      request.setOntology(MobilityOntology.getInstance().getName());
      try {
	     getContentManager().fillContent(request, action);
	     request.addReceiver(action.getActor());
	     send(request);
	  }
	  catch (Exception ex) { ex.printStackTrace(); }
   }

}//class Controller