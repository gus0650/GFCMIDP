package gfc.microedition.lcdui;

import gfc.microedition.midlet.*;
import gfc.widgets.*;
import java.util.Vector;


/**
 * @see javax.microedition.lcdui.ChoiceGroup
 * @author Gustaf Stechmann
 */
public class ChoiceGroup extends Item implements Choice {

	private int		cg_type;
	private Panel 	cg_panel;
	private Vector 	cg_choice_items = new Vector();
	

	public ChoiceGroup( String label, int choiceType ) {
		super(label);
		
		cg_type = choiceType;
		
        cg_panel = new Panel( 0, 0, getScreen() );
        cg_panel.setLayout( Layout.createTableLayout(1, 32) );

		setWidget(cg_panel);

		if (Boolean.parseBoolean(MIDlet.GetAppProperty("debug"))) System.out.println("ChoiceGroup()");
	}

	
	void notifyPressed() {
		if (Boolean.parseBoolean(MIDlet.GetAppProperty("debug"))) System.out.println("ChoiceGroup.notifyPressed()");
		
		ChoiceItem current = ((ChoiceItem)cg_choice_items.elementAt( getSelectedIndex() ));

		switch (cg_type) {
			case EXCLUSIVE:
				//deactivate others
				deselectAll();
				
				//activate current
				current.setChecked(true);

				break;

			case IMPLICIT:
				//nop -- but COMMAND_SELECT is triggered in commandAction()
				break;
		
			case MULTIPLE:
				//check / uncheck current
				current.setChecked(!current.isChecked());
				break;
		}
	}
	
	
	void selectPrevious() {
		if (Boolean.parseBoolean(MIDlet.GetAppProperty("debug"))) System.out.println("ChoiceGroup.selectPrevious()");

		cg_panel.selectPrevious();
	}
	
	
	void selectNext() {
		if (Boolean.parseBoolean(MIDlet.GetAppProperty("debug"))) System.out.println("ChoiceGroup.selectNext()");

		cg_panel.selectNext();
	}
	
	
	public int append( String stringPart, Image imagePart ) {
		if (Boolean.parseBoolean(MIDlet.GetAppProperty("debug"))) System.out.println("ChoiceGroup.append()");

		ChoiceItem ci = new ChoiceItem( 
				null, 
				getScreen(), 
				stringPart, 
				cg_type 
			);
		
		cg_panel.append( ci.getWidget() );

		cg_panel.selectFirst();
		
        cg_choice_items.addElement( ci );
		
        if (cg_type == Choice.EXCLUSIVE) {
            deselectAll();
            ChoiceItem cifirst = (ChoiceItem)cg_choice_items.firstElement();
            cifirst.setChecked(true);
        }
		
		return 1;
	}
	
	
	public void delete( int elementNum ) {
		if (Boolean.parseBoolean(MIDlet.GetAppProperty("debug"))) System.out.println("ChoiceGroup.delete()");

		cg_panel.remove( cg_panel.getWidgetAt(elementNum) );
		cg_choice_items.removeElementAt(elementNum);	//TODO untested
	}


	public Image getImage( int elementNum ) {
		//TODO
		System.err.println("ChoiceGroup.getImage() - method not implemented");

		return null;
	}


	public int getSelectedFlags( boolean[] selectedArray_return ) {
		//TODO
		System.err.println("ChoiceGroup.getSelectedFlags() - method not implemented");

		return -1;
	}


	public int getSelectedIndex() {
		if (Boolean.parseBoolean(MIDlet.GetAppProperty("debug"))) System.out.println("ChoiceGroup.getSelectedIndex()");

		return cg_panel.getSelectedIndex();
	}


	public String getString( int elementNum ) {
		//TODO
		System.err.println("ChoiceGroup.getString() - method not implemented");

		return null;
	}


	public void insert( int elementNum, String stringElement, Image imageElement ) {
		if (Boolean.parseBoolean(MIDlet.GetAppProperty("debug"))) System.out.println("ChoiceGroup.insert()");

		//TODO
		System.err.println("ChoiceGroup.insert() - method not implemented");
	}


	public boolean isSelected( int elementNum ) {
		//TODO
		System.err.println("ChoiceGroup.isSelected() - method not implemented");

		return false;
	}


	public void set( int elementNum, String stringElement, Image imageElement ) {
		if (Boolean.parseBoolean(MIDlet.GetAppProperty("debug"))) System.out.println("ChoiceGroup.set()");

		//TODO
		System.err.println("ChoiceGroup.set() - method not implemented");
	}


	public void setSelectedFlags( boolean[] selectedArray ) {
		if (Boolean.parseBoolean(MIDlet.GetAppProperty("debug"))) System.out.println("ChoiceGroup.setSelectedFlags()");

		//TODO
		System.err.println("ChoiceGroup.setSelectedFlags() - method not implemented");
	}


	public void setSelectedIndex( int elementNum, boolean selected ) {
		if (Boolean.parseBoolean(MIDlet.GetAppProperty("debug"))) System.out.println("ChoiceGroup.setSelectedIndex()");

		if (cg_type == Choice.EXCLUSIVE) deselectAll(); 
		
		ChoiceItem ci = ((ChoiceItem)cg_choice_items.elementAt( elementNum ));
		ci.setChecked(true);
	}


	public int size() {
		return cg_panel.numWidgets();
	}
	
	
	Item getItem(int index) {
		return (Item)cg_choice_items.elementAt(index);
	}
	
	
	private void deselectAll() {
		for (int i = 0; i < cg_choice_items.size(); i++) {
			ChoiceItem ci = (ChoiceItem)cg_choice_items.elementAt(i);
			ci.setChecked(false);
		}
	}
}