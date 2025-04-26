package gfc.microedition.lcdui;

/**
 * @see javax.microedition.lcdui.Command
 * @author Gustaf Stechmann
 */
public class Command {

	public final static int
		SCREEN = 1,
		BACK   = 2,
		CANCEL = 3,
		OK     = 4,
		HELP   = 5,
		STOP   = 6,
		EXIT   = 7,
		ITEM   = 8;

  	private int 		type;
  	private int 		priority;
  	private String 		label;

	
	public Command( String label, int commandType, int priority ) {
		this.label 		= label;
		this.type 		= commandType;
		this.priority 	= priority;
	}


	public void setLabel( String label ) {
		this.label = label;
	}


	public int getCommandType() {
		return type;
	}


	public String getLabel() {
		return label;
	}


	public int getPriority() {
		return priority;
	}
}