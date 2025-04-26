package gfc.microedition.lcdui;

import gfc.microedition.midlet.MIDlet;
import gfc.widgets.*;


/**
 * @author Gustaf Stechmann
 */
class ChoiceItem extends Item implements WidgetSelectListener {
	
	private	Panel	ci_panel;
    private Label 	ci_text;
    private Icon 	ci_icon;
	private Image 	ci_active, 
					ci_inactive;
    private String 	ci_string;
    private int		ci_type;
    
	private boolean	checked;
	
    static String	
		CHECK	= "*",
		ON		= ">",
		OFF		= " ";

    
    /**
     * @param listType - either Choice.IMPLICIT, Choice.EXCLUSIVE or Choice.MULTIPLE
     */
	ChoiceItem(String label, Displayable d, String text, int listType) {
		super(label);

		ci_inactive = Display.getDefaultMenuitemInactive();
		ci_active 	= Display.getDefaultMenuitemActive();

		if (ci_inactive.getWidth()  != ci_active.getWidth()) 	System.err.println("WARNING in ChoiceItem(): images are not of same width");
		if (ci_inactive.getHeight() != ci_active.getHeight()) 	System.err.println("WARNING in ChoiceItem(): images are not of same height");

		ci_string 	= text;
		
		ci_text = new Label( ci_active.getWidth() / 2, ci_inactive.getHeight() / 2, d, text, Display.getGFCFont(), null );
        ci_text.setAlignment(Graphics.HCENTER | Graphics.VCENTER);

		if (listType < Choice.EXCLUSIVE | listType > Choice.MULTIPLE) System.err.println("ChoiceItem(): illegal argument" + listType);
		ci_type = listType;
        if (ci_type == Choice.EXCLUSIVE | ci_type == Choice.MULTIPLE) setChecked(false);
		
        ci_icon = new Icon( 0, 0, d, ci_inactive);
        
        ci_panel = new Panel(ci_active.getWidth(), ci_active.getHeight(), 0, 0, d);
        ci_panel.setLayout(Layout.createStackLayout());
        ci_panel.append(ci_text);
        ci_panel.append(ci_icon);
        ci_panel.setWidgetSelectListener(this);
        
        setWidget(ci_panel);

		if (MIDlet.GetAppProperty("debug").equals("true")) System.out.println("ChoiceItem()");
	}
	
	
	boolean isChecked() {
		return checked;
	}


	void setChecked(boolean flag) {
		if (MIDlet.GetAppProperty("debug").equals("true")) System.out.println("ChoiceItem.setChecked() " + flag);
		
		if (flag) {
			if (ci_type == Choice.EXCLUSIVE)	ci_text.setString(ON 		+" "+ 	ci_string);
			if (ci_type == Choice.MULTIPLE)		ci_text.setString(CHECK 	+" "+ 	ci_string);
		}
		else {
			if (ci_type == Choice.EXCLUSIVE)	ci_text.setString(OFF 		+" "+ 	ci_string);
			if (ci_type == Choice.MULTIPLE)  	ci_text.setString(" "		+" "+	ci_string);
		}
	}
	
	
	public void widgetStateChanged(Widget w) {
		if (MIDlet.GetAppProperty("debug").equals("true")) System.out.println("ChoiceItem.widgetStateChanged()");

		if (w.isSelected()) ci_icon.setImage(ci_active);
		else ci_icon.setImage(ci_inactive);
	}
}
