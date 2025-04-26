package gfc.microedition.lcdui;

import gfc.microedition.midlet.*;

import java.util.Vector;

//used by GameCanvas

public class Menu extends List implements CommandListener {

	private Displayable m_parent;
	private Vector 		m_commands;
	
	private static Command CLOSE_COMMAND	= new Command(DisplayProperties.TEXT_MENU_CLOSE, Command.CANCEL, 0);
	
	
	Menu( String title, Displayable parent ) {
		super( title, IMPLICIT );
		
		m_parent = parent;
	
		m_commands = new Vector();

		addCommand(SELECT_COMMAND);
		addCommand(CLOSE_COMMAND);
		setCommandListener(this);
	}
	
	
	public void commandAction( Command cmd, Displayable d ) {
		if (MIDlet.GetAppProperty("debug").equals("true")) System.out.println("Menu.commandAction() " + cmd.getLabel());

		display.setCurrent(m_parent);

		if (cmd == CLOSE_COMMAND) return;
		
		if (cmd != SELECT_COMMAND) System.err.println("ILLEGAL STATE in Menu.commandAction(): undefined Command");
			
		CommandListener cl = m_parent.d_command_listener;
		if ( cl == null ) 	return;
		
		int index = getSelectedIndex();
		if (index == -1) 	System.err.println("ILLEGAL STATE in Menu.commandAction(): no List item selected");
		
		cl.commandAction( (Command)m_commands.elementAt(index), m_parent );
	}
	
	
	void add(Command c) {
		if (MIDlet.GetAppProperty("debug").equals("true")) System.out.println("Menu.add() " + c.getLabel());
		
		append(c.getLabel(), null);
		
		m_commands.addElement(c);
	}
}
