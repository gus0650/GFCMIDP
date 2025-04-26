package gfc.microedition.lcdui;

import gfc.widgets.*;


/**
 * @see javax.microedition.lcdui.Item
 * @author Gustaf Stechmann
 */
public abstract class Item {
	
	private Label	i_label;
	private Widget	i_widget;
	private Panel	i_panel;
	private Screen	i_screen;
	private Command	i_cmd;
	
	
	Item(String label) {
		i_label = new Label( 0, 0, getScreen(), label, DisplayProperties.getFont(), DisplayProperties.getPassiveColor() );

		i_panel = new Panel( 0, 0, getScreen() );
		i_panel.append(i_label);
	}


	void setWidget(Widget w) {
		if (i_widget != null) i_panel.remove(i_widget);

		i_widget = w;
		
		i_panel.insert(i_widget, 0);
	}

	
	Widget getWidget() {
		return i_widget;
	}
	

	void setScreen(Screen s) {
		i_screen = s;
		if (i_widget != null) i_widget.setDisplayable(s);
		if (i_label  != null)  i_label.setDisplayable(s);
	}

	
	Screen getScreen() {
		return i_screen;
	}
	
	
	public String getLabel() {
		return i_label.getString();
	}
	
	
	public void setLabel( String label ) {
		i_label.setString( label );
	}
	
	
	public void addCommand( Command cmd ) {
		i_cmd = cmd;
	}
	
	
	public Command getCommand( ) {
		return i_cmd;
	}
	
	
	public void removeCommand( Command cmd ) {
		if (cmd == i_cmd) cmd = null;
		else System.err.println("ERROR in Item.removeCommand(): Command is not on stack");
	}
}