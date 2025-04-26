package gfc.widgets;

import gfc.microedition.lcdui.*;
import gfc.microedition.midlet.MIDlet;
import java.util.Vector;


//TODO: select(int)
//TODO: select -> disabled item �berspringen, dabei nicht infinit loopen!

/**
 * A Panel holds and manages a group of Widgets, handles user interaction with these Widgets, 
 * and can be painted onto a Canvas.<p>
 * 
 * In order to use Widgets in your application, you must add them to a Panel first.
 * This allows you to render them on screen.
 * 
 * @author Gustaf Stechmann
 */
public class Panel extends Widget {

	protected Vector 	p_widgets 			= new Vector( );
	protected Layout 	p_layout;
	protected int 		p_selected_index;
	protected boolean 	p_dynamic;
	
	
	/**
	 * Constructs a Panel with fixed dimensions.
	 */
	public Panel(int width, int height, int x, int y, Displayable d) {
		super(width, height, x, y, d);

		p_dynamic 		= false;

		if (MIDlet.GetAppProperty("debug").equals("true")) System.out.println("Panel() - fixed size");
	}
	
	
	/**
	 * Constructs a Panel with dynamic dimensions.
	 * 
	 * Panel dimensions will be resized whenever a Widget is added, inserted or removed.
	 */
	public Panel(int x, int y, Displayable d) {
		super(0, 0, x, y, d);
		
		p_dynamic 		= true;
		
		if (MIDlet.GetAppProperty("debug").equals("true")) System.out.println("Panel() - dynamic size");
	}


	/**
	 * Appends a Widget to this Panel.
	 */
	public void append( Widget w ) {
		if (MIDlet.GetAppProperty("debug").equals("true")) System.out.println("Panel.append()");

		p_widgets.addElement(w);
		w.setDisplayable( this.getDisplayable() );
		w.setPanel( this );
		
		doLayout();
	}


	/**
	 * Inserts a new Widget in this Panel at the specified index.
	 */
	public void insert( Widget w, int index ) {
		if (MIDlet.GetAppProperty("debug").equals("true")) System.out.println("Panel.insert()");

		p_widgets.insertElementAt(w, index);
		w.setPanel( this ) ;
		
		doLayout();
	}


	/**
	 * Removes the specified Widget from this Panel.
	 */
	public void remove( Widget w ) {
		if (MIDlet.GetAppProperty("debug").equals("true")) System.out.println("Panel.remove()");

		p_widgets.removeElement(w);
		w.setPanel( null );
		
		doLayout();
	}


	/**
	 * Checks if the specified Widget is managed by this Panel.
	 */
	public boolean contains( Widget w ) {
		if (MIDlet.GetAppProperty("debug").equals("true")) System.out.println("Panel.contains()");

		return p_widgets.contains(w);		
	}

	
	/**
	 * Gets the Widget with the specified index.
	 */
	public Widget getWidgetAt( int index ) {
		if (MIDlet.GetAppProperty("debug").equals("true")) System.out.println("Panel.getWidgetAt()");

		return (Widget)p_widgets.elementAt( index );
	}
	
	
	/**
	 * Gets the number of Widgets on this Panel.
	 */
	public int numWidgets() {
		if (MIDlet.GetAppProperty("debug").equals("true")) System.out.println("Panel.numWidgets()");

		return p_widgets.size();
	}

	@Override
	final public void draw( Graphics g ) {
		if (MIDlet.GetAppProperty("debug").equals("true")) System.out.println("Panel.draw()");

		//paint widgets
	    for (int i = p_widgets.size() - 1; i >= 0 ; i--) {
	    	Widget w = ((Widget)p_widgets.elementAt(i));

			if ( !w.isVisible() ) continue;

			w.paint(g);
		}
	}
	
	@Override
	public void paint( Graphics g ) {
		super.paint(g);
		
		if (MIDlet.GetAppProperty("debug").equals("true")) System.out.println("Panel.paint()");
	}
	
	
	/**
	 * Check if any of the Widgets on the Panel has been clicked on.<p>
	 * 
	 * If there is a Command assigned to the Widget in question, it is passed
	 * to the CommandListener c. Otherwise, a dummy Command is generated.
	 */
	public void click(int x, int y, CommandListener cl, Displayable d) {
		if (MIDlet.GetAppProperty("debug").equals("true")) System.out.println("Panel.click()");

		if (p_widgets.isEmpty()) return;

		for ( int i = 0; i < p_widgets.size(); i++ ) {
			
			Widget w = (Widget)p_widgets.elementAt(i);
			
			if ( w.containsPoint(x - getAbsX(), y - getAbsY()) ) {
				w.triggerCommand(cl, d);
			}
		}
	}
	

	/**
	 * Trigger the currently selected Widget.
	 * 
	 * If there is a Command assigned to the Widget in question, it is passed
	 * to the CommandListener c. Otherwise, a dummy Command is generated.
	 */
	public void triggerSelected(CommandListener cl, Displayable d) {
		if (MIDlet.GetAppProperty("debug").equals("true")) System.out.println("Panel.triggerSelected()");

		if (p_widgets.isEmpty()) return;

		Widget w = getSelected();
		
		if ( w.getCommand() != null ) {
			w.triggerCommand(cl, d);
		}		
	}

	
	public void setLayout(Layout l) {
		if (MIDlet.GetAppProperty("debug").equals("true")) System.out.println("Panel.setLayout()");

		p_layout = l;
		
		doLayout();
		
		repaint();
	}
	
	
	void doLayout() {
		if (MIDlet.GetAppProperty("debug").equals("true")) System.out.println("Panel.doLayout()");

	    if (p_layout != null) p_layout.layoutWidgets(p_widgets);
		
		if (p_dynamic) adjustDimensions();
	}
	
	
	/**
	 * After appending, layouting ... operations the width, height
	 * might be invalid, so calling this would be nessesary.
	 */
	private void adjustDimensions() {
		if (MIDlet.GetAppProperty("debug").equals("true")) System.out.println("Panel.adjustDimensions()");

	    int width 	= 0;
	    int height 	= 0;
	    
	    for (int i = p_widgets.size() - 1; i >= 0 ; i--) {
	     
	        Widget w = (Widget)p_widgets.elementAt(i);
	        
	        int extend_x = w.getX() + w.getWidth();
	        
	        if (extend_x > width) {
	            width = extend_x;
	        }
	        
	        int extend_y = w.getY() + w.getHeight();
	        
	        if (extend_y > height) {
	            height = extend_y;
	        }
	    }
	    
	    setContentWidth(width);
	    setContentHeight(height);
	}
	
	
	public Widget getSelected() {
		return (Widget)p_widgets.elementAt(p_selected_index);
	}

	
	public int getSelectedIndex() {
		/*config:debug*///System.out.println("Panel.getSelectedIndex()");

		return p_selected_index;
	}

	
	public void selectNext() {
		if (MIDlet.GetAppProperty("debug").equals("true")) System.out.println("Panel.selectNext()");

		if (p_widgets.isEmpty()) return;
		
		getSelected().setSelected(false);
		
		p_selected_index++;
		if ( p_selected_index > p_widgets.size()-1 ) p_selected_index = 0;
		
		getSelected().setSelected(true);
	}


	public void selectPrevious() {
		if (MIDlet.GetAppProperty("debug").equals("true")) System.out.println("Panel.selectPrevious()");

		if (p_widgets.isEmpty()) return;

		getSelected().setSelected(false);

		p_selected_index--;
		if ( p_selected_index < 0 ) p_selected_index = p_widgets.size()-1;
		
		getSelected().setSelected(true);
	}


	public void selectFirst() {
		if (MIDlet.GetAppProperty("debug").equals("true")) System.out.println("Panel.selectFirst()");

		if (p_widgets.isEmpty()) return;

		getSelected().setSelected(false);

		p_selected_index = 0;
		
		getSelected().setSelected(true);
	}
}
