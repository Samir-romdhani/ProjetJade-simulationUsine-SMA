package jade.gui1;

//Import required java classes
import java.util.Vector;

//Import required Jade classes
import jade.core.*;
import jade.core.behaviours.*;
import jade.gui.GuiEvent;


public abstract class GuiAgent extends Agent {
private static final long     serialVersionUID = 3487495895819010L;
	
	/**
	@serial
	*/
	private Vector guiEventQueue;
	/**
	@serial
	*/
	private Boolean guiEventQueueLock;

	////////////////////////
	// GUI HANDLER BEHAVIOUR
	private class GuiHandlerBehaviour extends SimpleBehaviour
	{
	private static final long     serialVersionUID = 3487495895819011L;
		protected GuiHandlerBehaviour()
		{
			super(GuiAgent.this);
		}

		public void action()
		{
			if (!guiEventQueue.isEmpty())
			{
				GuiEvent ev = null;  				
				synchronized(guiEventQueueLock)
				{
					try
					{
						ev  = (GuiEvent) guiEventQueue.elementAt(0);
						guiEventQueue.removeElementAt(0);
					}
					catch (ArrayIndexOutOfBoundsException ex)
					{
						ex.printStackTrace(); // Should never happen
					}
				}			
				onGuiEvent(ev);
			}
			else
				block();
		}

		public boolean done()
		{
			return(false);
		}
	}

 /**
    Default constructor.
 */
 public GuiAgent()
 {
	super();
	guiEventQueue = new Vector();
	guiEventQueueLock = new Boolean(true);

	// Add the GUI handler behaviour
	Behaviour b = new GuiHandlerBehaviour();
	addBehaviour(b);
 }

 /**
    Posts an event from the GUI thread to the agent event queue.
    @param e The GUI event to post.
 */
 public void postGuiEvent(GuiEvent e)
 {
	synchronized(guiEventQueueLock)
	    {
		guiEventQueue.addElement( (Object) e );
		doWake();
	    }
 }

	/////////////////////////////////////////////////////////////////////////
	// METHODS TO POST PREDEFINED EXIT AND CLOSEGUI EVENTS IN GUI EVENT QUEUE
	/*public void postExitEvent(Object g)
	{
		GuiEvent e = new GuiEvent(g, GuiEvent.EXIT);
		postGuiEvent(e);
	}

	public void postCloseGuiEvent(Object g)
	{
		GuiEvent e = new GuiEvent(g, GuiEvent.CLOSEGUI);
		postGuiEvent(e);
	}*/


	/**
	   Abstract method to handle posted GUI events. Subclasses of
	   <code>GuiAgent</code> will implement their own reactions to
	   GUI events starting with this method.
	   @param ev The GUI event to handle.
	*/
	protected abstract void onGuiEvent(GuiEvent ev);
	
}