package Agents;


import Objects.*;
import Objects.Produits;
import jade.core.AID;
import jade.core.Agent;
import jade.core.behaviours.CyclicBehaviour;
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

public class Atelier extends Agent {
	private int numero ;
    private List<Produits> produitList = new ArrayList<>();
    private List<String> produits = new ArrayList<>();
    

    Produits p ;//=  new Produits();
    
    public Atelier(int numero  , List<Produits> lp) {
    	this.numero = numero;
    	//this.produitList = new ArrayList<>();
    	this.produitList = lp;	
    }

    public void setProduitList(List<Produits> produitList) {
		this.produitList = produitList;
	}
    public List<Produits> getProduitList() {
		return produitList;
	}
    
    Produits p1 = new Produits("Table", 20, 4, 5) ;
    Produits p2 = new Produits("Chaise", 120, 4, 2) ;
    Produits p3 = new Produits("Buffet", 20, 4, 6) ;

   
    /////////////////////////
    Produits p4 = new Produits("Lit", 10, 2, 7) ;
    Produits p5 = new Produits("Chevet", 20, 2, 1) ;
    Produits p6 = new Produits("Armoire", 10, 2, 8) ;


   ////////////////////////////////////////
    Produits p7 = new Produits("Banquette", 20, 3, 6) ;
    Produits p8 = new Produits("Fauteuil", 120, 3, 3) ;
    Produits p9 = new Produits("Etagère", 20, 3, 8) ;

    public Atelier() {
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
    }
    
    
    private myframeGUI gui;
    
    public myframeGUI getGui() {
		return gui;
	}
    @Override
    protected void setup() {
    	//System.out.println("Aaa");
    	//System.out.println(p.getListProduit().get(0));
        
        gui.setVisible(true);
        for(java.util.Iterator<Produits> it=produitList.iterator(); it.hasNext();) {
        	
        	p=it.next();
        	
        	String[] liste = {p.getNom(),Integer.toString(p.getstock()),Integer.toString(p.getfornisseur())};
        	//System.out.println(it.next().getNom());
        	gui.setRows(liste);
        }
        
       /* String[] list1 = {p1.getNom(),Integer.toString(p1.getstock()),Integer.toString(p1.getfornisseur())};
         gui.setRows(list1);
         String[] list2 = {p2.getNom(),Integer.toString(p2.getstock()),Integer.toString(p2.getfornisseur())};
         gui.setRows(list2);
         */
        
		addBehaviour(new CyclicBehaviour() {
			@Override
			public void action() {
				// receive du demande
				//p.setStock(p.getstock()+1);
				ACLMessage msg1 = receive();
				ACLMessage msg2 = receive();
				if((msg1 != null) && (msg2 != null)) {
					JOptionPane.showMessageDialog(null, "message1 --"+msg1.getContent()
					                                   +"message2 --"+msg2.getContent());
					String[] liste = {msg1.getContent(),msg2.getContent()};
					gui.setRowsDemande(liste);
					System.out.println("****Atelier1  receive msg1****"+msg1.getContent());
					System.out.println("****Atelier1  receive msg2****"+msg2.getContent());
					

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


}
