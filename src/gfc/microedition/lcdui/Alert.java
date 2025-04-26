package gfc.microedition.lcdui;



/**
 * @see javax.microedition.lcdui.Alert
 * @author Gustaf Stechmann
 */
public class Alert extends Screen implements CommandListener {

	public final static int 		FOREVER 			= -1;
	private final static int 		DEFTIMEOUT 			= FOREVER;
	private int 					a_timeout 			= DEFTIMEOUT;
	private AlertType 				a_type;
	private Image					a_image;
	private StringItem				a_stringitem;
	
	public static final Command 	DISMISS_COMMAND 	= new Command(DisplayProperties.TEXT_DISMISS, 	Command.CANCEL, 0 );
	private static final Command	MORE_COMMAND		= new Command(DisplayProperties.TEXT_MORE, 		Command.OK, 0 );
	

	public Alert(String title) {
		super(title);
		
		a_stringitem = new StringItem(null, "");
		setContent(a_stringitem);
		
		addCommand(DISMISS_COMMAND);
		setCommandListener(this);
		
		setType( AlertType.INFO );
	}

	
	public Alert(String title, String string, Image image, AlertType type) {
		super(title);
		
		a_stringitem = new StringItem( null, string );
		setContent(a_stringitem);
		
		addCommand(DISMISS_COMMAND);
		if ( isMultiPage() ) addCommand(MORE_COMMAND);
		setCommandListener(this);

		setImage(image);
		setType(type);
	}

	
	public int getDefaultTimeout() {
		return a_timeout;
	}

	
	public Image getImage() {
		return a_image;
	}

	public void setString(String s) {
		a_stringitem.setText(s);
		posHome();
	}
	
	public String getString() {
		return a_stringitem.getText();
	}

	
	public int getTimeout() {
		return a_timeout;
	}

	
	public AlertType getType() {
		return a_type;
	}

	
	public void setImage(Image img) {
		a_image = img;
		posHome();
	}

	
	public void setTimeout(int time) {
		//TODO implement
		
		this.a_timeout = time;
	}

	
	public void setType(AlertType type) {
		this.a_type = type;
	}

	
	public void commandAction(Command c, Displayable d) {
		if (c == DISMISS_COMMAND) {
			display.setPrevious();
		}
		else if (c == MORE_COMMAND) {
			posDown();
		}
	}
}