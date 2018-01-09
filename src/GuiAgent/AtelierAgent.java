package GuiAgent;

import Objects.*;
import Objects.Produits;
import jade.core.AID;
import jade.core.Agent;
import jade.core.behaviours.CyclicBehaviour;
import jade.core.behaviours.OneShotBehaviour;
import jade.core.behaviours.ReceiverBehaviour;
import jade.domain.DFService;
import jade.domain.FIPAAgentManagement.DFAgentDescription;
import jade.domain.FIPAAgentManagement.ServiceDescription;
import jade.domain.introspection.AddedBehaviour;
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
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Vector;

public class AtelierAgent extends Agent {
	private int numero ;
    public ArrayList<Produits> produitList  ; 
    private List<String> produits = new ArrayList<>();
    
    
    private Map locations = new HashMap();
    private Vector agents = new Vector();
    transient protected ControllerAgentGui myGui;
    private jade.wrapper.AgentContainer home;
    
    
    private int agentCnt = 1;
    private int FouniseursagentCnt = 1;
    

    Produits p = new Produits();
    
    public AtelierAgent(int numero  , ArrayList<Produits> lp) {
    	this.numero = numero;
    	//this.produitList = new ArrayList<>();
    	this.produitList = lp;	
    }

    public void setProduitList(ArrayList<Produits> produitList) {
		this.produitList = produitList;
	}
    public List<Produits> getProduitList() {
		return produitList;
	}
    
    ArrayList<String> TypeBois = new ArrayList();
    
    public ArrayList<String> getTypeBois() {
		return TypeBois;
	}
    
    Produits p1 = new Produits("Table", 20, 4, 5) ;
    Produits p2 = new Produits("Chaise", 120, 4, 2) ;
    Produits p3 = new Produits("Buffet", 20, 4, 6) ;

   
    /////////////////////////
    Produits p4 = new Produits("Lit", 10,2, 7) ;
    Produits p5 = new Produits("Chevet", 20, 2, 1) ;
    Produits p6 = new Produits("Armoire",10, 2, 8) ;


   ////////////////////////////////////////
    Produits p7 = new Produits("Banquette", 30, 3, 6) ;
    Produits p8 = new Produits("Fauteuil", 60, 3, 3) ;
    Produits p9 = new Produits("Etagère", 30, 3, 8) ;

    public AtelierAgent() {
    	TypeBois.add("Chêne");
    	TypeBois.add("Merisier");
    	TypeBois.add("Noyer");
    	produitList = new ArrayList<>();
    	gui = new myframeGUI();
    	produitList.add(p1);
    	produitList.add(p2);
    	produitList.add(p3);
    	produitList.add(p4);
    	produitList.add(p5);
    	produitList.add(p6);
    	produitList.add(p7);
    	produitList.add(p8);
    	produitList.add(p9);
    	
		 for(java.util.Iterator<Produits> it=produitList.iterator(); it.hasNext();) {
	        	p=it.next();
	        	String[] liste1 = {p.getNom(),Integer.toString(p.getstock()),Integer.toString(p.getfornisseur())};
	        	gui.setRows(liste1);
	        }
    }
    
    
    private myframeGUI gui;
    
    public myframeGUI getGui() {
		return gui;
	}
    
    
    
    @Override
    protected void setup() {
    	//System.out.println("Aaa");
    	//System.out.println(p.getListProduit().get(0));
        ControllerAgent a ;
        gui.setVisible(true);
        //myGui = new ControllerAgentGui(this, locations.keySet());

        /*
        for(java.util.Iterator<Produits> it=produitList.iterator(); it.hasNext();) {
        	p=it.next();
        	String[] liste = {p.getNom(),Integer.toString(p.getstock()),Integer.toString(p.getfornisseur())};
        	//System.out.println(it.next().getNom());
        	gui.setRows(liste);
        }
        */
        
        
       /* String[] list1 = {p1.getNom(),Integer.toString(p1.getstock()),Integer.toString(p1.getfornisseur())};
         gui.setRows(list1);
         String[] list2 = {p2.getNom(),Integer.toString(p2.getstock()),Integer.toString(p2.getfornisseur())};
         gui.setRows(list2);
         */
	
  	  // Program the main behaviour of this agent
  		addBehaviour(new OneShotBehaviour() {				
  			@Override
  			public void action() {
				for (int i = 0; i < produitList.size(); i++) {
					
					Produits p = produitList.get(i);
					
		        	if(p.getstock()<=0) {
						
		    			addBehaviour(new OneShotBehaviour() {
		    				@Override
		    				public void action() {
		    					System.out.println("****AtelierAgent  send msg1 to controller Agent****");
		    	        		ACLMessage msgA = new ACLMessage(ACLMessage.INFORM);
		    	        		ACLMessage msgP = new ACLMessage(ACLMessage.INFORM);
		    	        		msgA.addReceiver(new AID("ControllerAgent", AID.ISLOCALNAME));
		    	        		msgP.addReceiver(new AID("ControllerAgent", AID.ISLOCALNAME));
		    	        		msgA.setContent("Une rupture de stock");
		    	        		msgP.setContent(p.getNom());
		    	        		msgA.setLanguage("Prolog");msgP.setLanguage("Prolog");
		    					send(msgA);
		    					send(msgP);
		    					System.out.println("****AtelierAgent  send msg1****"+msgA.getContent()
		    					                      +" de "+msgP.getContent());					
		    				}
		    			});
		    		}
					
					
				}
				}
  		});
        
		
		addBehaviour(new CyclicBehaviour() {
			@Override
			public void action() {
				
				
				//
				
				
				// receive du demande  clientsa.compareTo(sb)
				ACLMessage msg1 = receive();
				ACLMessage msg2 = receive();
				if((msg1 != null) && (msg2 != null) && ((msg1.getSender().getLocalName().compareTo("Client"))==1)) {
										
					JOptionPane.showMessageDialog(null, "message1 --"+msg1.getContent()
					                                   +"message2 --"+msg2.getContent());
					String[] liste = {msg1.getContent(),msg2.getContent()};
					gui.setRowsDemande(liste);
					//System.out.println("****Atelier1  receive msg1****"+msg1.getContent());
					//System.out.println("****Atelier1  receive msg2****"+msg2.getContent());
					System.out.println("****Atelier1  receive msg ----from-----"+msg1.getSender().getLocalName());
					
					init(msg1.getContent(),msg2.getContent());
				}
				else block();
				
					// receive msg from fournisseurs
					ACLMessage msgF1 = receive();
					ACLMessage msgF2 = receive();
					ACLMessage msgF3 = receive();
					if((msgF1 != null) && (msgF2 != null) && (msgF3 != null) 
							&& (msgF1.getSender().getLocalName()).equals("Fournisseurs1"+FouniseursagentCnt++)) {
											
						JOptionPane.showMessageDialog(null, "message1 --"+msgF3.getContent()
						                                   +"message2 --"+msgF1.getContent()
						                                   +"message3 --"+msgF2.getContent());

						/*System.out.println("****Atelier1  receive msg Fournisseyrs ****"+msgF3.getContent());
						System.out.println("****Atelier1  receive msg Fournisseyrs ****"+msgF1.getContent());
						System.out.println("****Atelier1  receive msg Fournisseyrs****"+msgF2.getContent());
						*/
						System.out.println("****Atelier1  receive ----from-----"+msgF1.getSender().getLocalName());
						String[] liste = {msgF1.getContent(),msgF2.getContent()};
						gui.setRowsDemande(liste);
						/*
						for (int i = 0; i < produitList.size(); i++) {
							
							Produits p = produitList.get(i);
							
				        	if(p.getNom().equals(msgF3.getContent())) {
				        		p.setStock(Integer.parseInt(msgF2.getContent()));
					        	String[] liste1 = {p.getNom(),Integer.toString(p.getstock()),Integer.toString(p.getfornisseur())};
					        	gui.setRows(liste1);
				        		
				        	}
						}
						*/

					
					//doWait(500);
					init(msg1.getContent(),msg2.getContent());
				}
				else block();

				
			}
		});
		
		

		
		/*addBehaviour(new CyclicBehaviour() {
			
			@Override
			public void action() {
				// TODO Auto-generated method stub
				ACLMessage msg1 = receive();
				ACLMessage msg2 = receive();
		        for(java.util.Iterator<Produits> it=produitList.iterator(); it.hasNext();) {
		        	p=it.next();
		        	if(p.getNom()==msg1.getContent()) {
		        		if(Integer.parseInt(msg2.getContent())<=p.getstock()) {
		        			p.setStock(p.getstock()-(Integer.parseInt(msg2.getContent())));
		        			System.out.println("****p.getNom()  ****"+p.getNom());
		        			System.out.println("****msg1.getContent() ****"+msg1.getContent());
		        			
			        		//String[] liste1 = {p.getNom(),Integer.toString(p.getstock()),Integer.toString(p.getfornisseur())};
				        	//gui.setRows(liste1);
		        		}
		        	}
		        	else {
		        		String[] liste1 = {p.getNom(),Integer.toString(p.getstock()),Integer.toString(p.getfornisseur())};
			        	gui.setRows(liste1);
		        	}			        	
		        	
		        }
				
			}
		});*/
        
        
    }
    
    void init(String pr,String qt) { 
    	
    	/*
		for (int i = 0; i < produitList.size(); i++) {
			
			Produits p = produitList.get(i);
			
        	if(p.getstock()<=0) {
				
    			addBehaviour(new OneShotBehaviour() {
    				@Override
    				public void action() {
    					System.out.println("****AtelierAgent  send msg1 to controller Agent****");
    	        		ACLMessage msgA = new ACLMessage(ACLMessage.INFORM);
    	        		ACLMessage msgP = new ACLMessage(ACLMessage.INFORM);
    	        		msgA.addReceiver(new AID("ControllerAgent", AID.ISLOCALNAME));
    	        		msgP.addReceiver(new AID("ControllerAgent", AID.ISLOCALNAME));
    	        		msgA.setContent("Une rupture de stock");
    	        		msgP.setContent(p.getNom());
    	        		msgA.setLanguage("Prolog");msgP.setLanguage("Prolog");
    					send(msgA);
    					send(msgP);
    					System.out.println("****AtelierAgent  send msg1****"+msgA.getContent()
    					                      +"de "+msgP.getContent());					
    				}
    			});
    		}
			
			
		}
		*/
    	
    	if (gui.getTableModel().getRowCount() > 0) {
    	    for (int i = gui.getTableModel().getRowCount() - 1; i > -1; i--) {
    	    	gui.getTableModel().removeRow(i);
    	    }
    	}
    	
        for(int i = 0; i < produitList.size(); i++) {
        	Produits p = produitList.get(i);
        	//p.setStock(500);
        	if(p.getNom().equals(pr)) {
        		p.setStock(p.getstock()-(Integer.parseInt(qt)));
        		 //System.out.println("p.setStock(p.getstock()-(Integer.parseInt(qt))) == **"+p.getstock());
        		if((p.getstock())>0) {
        			  System.out.println("**produits livré");
		        		String[] liste1 = {p.getNom(),Integer.toString(p.getstock()),Integer.toString(p.getfornisseur())};
			        	gui.setRows(liste1);
			        	 

	        			
        		        }
	        	      else {
	        	    	//  System.out.println("**produits p.getstock())<=0");
				        	
		        			addBehaviour(new OneShotBehaviour() {
		        				@Override
		        				public void action() {
		        					System.out.println("****AtelierAgent  send msg1 dans onebehaviour****");
		        	        		ACLMessage msgA = new ACLMessage(ACLMessage.INFORM);
		        	        		ACLMessage msgP = new ACLMessage(ACLMessage.INFORM);
		        	        		msgA.addReceiver(new AID("ControllerAgent", AID.ISLOCALNAME));
		        	        		msgP.addReceiver(new AID("ControllerAgent", AID.ISLOCALNAME));
		        	        		msgA.setContent("Une rupture de stock");
		        	        		msgP.setContent(p.getNom());
		        	        		msgA.setLanguage("Prolog");msgP.setLanguage("Prolog");
		        					send(msgA);
		        					send(msgP);
		        					System.out.println("****AtelierAgent  send msg1****"+msgA.getContent()
		        					                      +"de "+msgP.getContent());
		        				}
		        			}); 
		        			
	          		    String[] liste1 = {p.getNom(),Integer.toString(p.getstock()),Integer.toString(p.getfornisseur())};
	  	        	    gui.setRows(liste1);
	          	             }
        		}
        	   else {
        		 //  System.out.println("**produits != produit demandé");  
        	   String[] liste1 = {p.getNom(),Integer.toString(p.getstock()),Integer.toString(p.getfornisseur())};
	           gui.setRows(liste1);
        	  }		
        	
        }//for

}

}
