package gfc.microedition.lcdui;

import gfc.microedition.midlet.*;

/**
 * @see javax.microedition.lcdui.List
 * @author Gustaf Stechmann
 * @version alpha
 */
public class List extends Screen implements Choice {

	public static Command SELECT_COMMAND	= new Command(DisplayProperties.TEXT_SELECT, Command.SCREEN, 0);

	private int	l_type;
	protected ChoiceGroup l_choicegroup;
	

	public List( String title, int listType, String[] stringElements, Image[] imageElements ) {
		super(title);
		
		if (listType < EXCLUSIVE | listType > MULTIPLE) System.err.println("List(): illegal argument" + listType);
		l_type = listType;
		
		l_choicegroup = new ChoiceGroup( null, l_type );
		setContent(l_choicegroup);
		
		if (stringElements.length != imageElements.length) System.err.println("List(): number of stringElements does not match number of imageElements");
		
		for (int i = 0; i < stringElements.length; i++) {
			append(stringElements[i], imageElements[i]);
		}

		if (l_type == Choice.IMPLICIT) addCommand(SELECT_COMMAND);

		if (Boolean.parseBoolean(MIDlet.GetAppProperty("debug"))) System.out.println("List()");
	}


	public List( String title, int listType ) {
		super(title);

		if (listType < EXCLUSIVE | listType > MULTIPLE) System.err.println("List(): illegal argument" + listType);
		l_type = listType;

		l_choicegroup = new ChoiceGroup( null, l_type );
		setContent(l_choicegroup);

		if (l_type == Choice.IMPLICIT) addCommand(SELECT_COMMAND);
		
		if (Boolean.parseBoolean(MIDlet.GetAppProperty("debug"))) System.out.println("List()");
	}

	
	//---- methods inherited from Choice

	@Override
	public int append( String stringPart, Image imagePart ) {
		if (Boolean.parseBoolean(MIDlet.GetAppProperty("debug"))) System.out.println("List.append()");
		
		l_choicegroup.append( stringPart, imagePart );
		
		posHome();
		
		return 1;
	}

	@Override
	public void insert( int elementNum, String stringPart, Image imagePart ) {
		if (Boolean.parseBoolean(MIDlet.GetAppProperty("debug"))) System.out.println("List.insert()");
		
		l_choicegroup.insert(elementNum, stringPart, imagePart);
		
		posHome();
	}

	@Override
	public void delete( int elementNum ) {
		if (Boolean.parseBoolean(MIDlet.GetAppProperty("debug"))) System.out.println("List.delete()");
		
		l_choicegroup.delete(elementNum);
		
		posHome();
	}

	@Override
	public int size() {
		return l_choicegroup.size();
	}

	@Override
	public int getSelectedFlags( boolean[] flags ) {
		return l_choicegroup.getSelectedFlags(flags);
	}
	
	@Override
	public int getSelectedIndex() {
		return l_choicegroup.getSelectedIndex();
	}

	@Override
	public Image getImage( int elementNum ) {
		return l_choicegroup.getImage(elementNum);
	}

	@Override
	public String getString( int elementNum ) {
		return l_choicegroup.getString(elementNum);
	}

	@Override
	public boolean isSelected( int elementNum ) {
		return l_choicegroup.isSelected(elementNum);
	}

	@Override
	public void set( int elementNum, String stringPart, Image imagePart ) {
		l_choicegroup.set(elementNum, stringPart, imagePart);
		
		posHome();
	}

	@Override
	public void setSelectedFlags( boolean[] selectArray ) {
		l_choicegroup.setSelectedFlags(selectArray);
	}

	@Override
	public void setSelectedIndex( int elementNum, boolean selected ) {
		l_choicegroup.setSelectedIndex(elementNum, selected);
		
		posHome();	//TODO: flip to active item
	}
	

	// ----- methods inherited from Displayable
	
	boolean keyPressedPreprocess(int keyCode) {
		if (Boolean.parseBoolean(MIDlet.GetAppProperty("debug"))) System.out.println("List.keyPressedPreprocess()");

//TODO
		boolean intercepted = false;//super.keyPressedPreprocess(keyCode);

		if (false);
		else if (keyCode == Canvas.KEY_NUM5 | keyCode == DisplayProperties.KEY_TRIGGER) {
			l_choicegroup.notifyPressed();

			if (l_type == IMPLICIT) {
				CommandListener cl = d_command_listener;
				if (cl != null) cl.commandAction(SELECT_COMMAND, this);
			}
		}
		else if (keyCode == Canvas.KEY_NUM2 | getGameAction(keyCode) == Canvas.UP) {
			l_choicegroup.selectPrevious();
		}
		else if (keyCode == Canvas.KEY_NUM8 | getGameAction(keyCode) == Canvas.DOWN) {
			l_choicegroup.selectNext();
		}
		
		repaint();//TODO: widgets should repaint
		
		return intercepted;
	}
}