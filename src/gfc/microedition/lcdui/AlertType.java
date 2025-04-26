package gfc.microedition.lcdui;


/**
 * @see javax.microedition.lcdui.AlertType
 * @author Gustaf Stechmann
 */
public class AlertType {

	public final static AlertType	
		ALARM			= new AlertType(1), 
		CONFIRMATION 	= new AlertType(2), 
		ERROR 			= new AlertType(3), 
		INFO 			= new AlertType(4), 
		WARNING 		= new AlertType(5);

	private int	type;


	protected AlertType( int type ) {
		this.type = type;
	}


	public boolean playSound( Display display ) {
		System.out.println("AlertType.playSound() -- WARNING: method not implemented");
		
		return false;
	}
}