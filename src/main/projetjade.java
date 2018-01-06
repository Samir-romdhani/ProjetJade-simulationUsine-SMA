package main;

import jade.core.Profile;
import jade.core.ProfileImpl;
import jade.core.Runtime;
import jade.wrapper.AgentContainer;
import jade.wrapper.AgentController;
import jade.wrapper.StaleProxyException;

public class projetjade {

	public static void main(String[] args) throws StaleProxyException, InterruptedException {
		final Runtime runTime = Runtime.instance();


	      runTime.setCloseVM(true);
	      Profile mainProfile = new ProfileImpl(true);
	      AgentContainer mainContainer = runTime.createMainContainer(mainProfile);
	      AgentController rma = mainContainer.createNewAgent("rma", "jade.tools.rma.rma", null);
	      rma.start();
	      Thread.sleep(900);
	      



	      Profile anotherProfile;
	      AgentContainer anotherContainer;
	      AgentController agent;
	      AgentController agent2;    
	   
	      anotherProfile = new ProfileImpl(false);
	      anotherContainer = runTime.createAgentContainer(anotherProfile);
	      System.out.println("Starting up a Production...");
	      agent = anotherContainer.createNewAgent("Atelier", "Agents.Atelier", null);
	      agent.start();
	      Thread.sleep(900); 
	      
	      anotherProfile = new ProfileImpl(false);
	      anotherContainer = runTime.createAgentContainer(anotherProfile);
	      System.out.println("Starting up a Demandes...");
	      agent = anotherContainer.createNewAgent("Clients", "Agents.Client", null);
	      agent.start();
	      Thread.sleep(900);
	     /*
	      
	      anotherProfile = new ProfileImpl(false);
	      anotherContainer = runTime.createAgentContainer(anotherProfile);
	      System.out.println("Sending...");
	      agent = anotherContainer.createNewAgent("Client1", "Agents.Client1", null);
	      agent.start();
	      Thread.sleep(900); 
	      anotherProfile = new ProfileImpl(false);
	      anotherContainer = runTime.createAgentContainer(anotherProfile);
	      System.out.println("receive...");
	      agent = anotherContainer.createNewAgent("Atelier1", "Agents.Atelier1", null);
	      agent.start();
	      Thread.sleep(900); 
	        
	    */  
	      String[] args0 = {"-gui","atelier1:Agents.Atelier1"};
	      String[] args1 = {"-container","client1:Agents.Client1"};
	      //jade.Boot.main(args0);
	      //jade.Boot.main(args1);
	      
	      String[] args2 = {"-gui","Atelier:Agents.Atelier"};
	      String[] args3 = {"-container","Client:Agents.Client"};
	      String[] args4 = {"-container","Atelier:Agents.Atelier"};
	      //jade.Boot.main(args2);
	      //jade.Boot.main(args3);

	}

}
