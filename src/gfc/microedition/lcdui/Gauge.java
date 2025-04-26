package gfc.microedition.lcdui;

import gfc.widgets.*;


/**
 * @see javax.microedition.lcdui.Gauge
 * @author Alex Knorr
 */
public class Gauge extends Item {

	int g_value, g_max_value;
	
	final boolean g_interactive;
	
	GaugeX g_gauge;
	

	public Gauge( String label, boolean interactive, int maxValue, int initialValue ) throws IllegalArgumentException {
		super(label);

		g_value 		= initialValue;
		g_max_value 	= maxValue;
		g_interactive	= interactive;
		
		if (initialValue > maxValue) throw new IllegalArgumentException();
		if (interactive) System.err.println("WARNING in Gauge(): interactive mode not implemented");
		
		g_gauge 			= new GaugeX( 
								100,	//TODO
								10, 	//TODO
								0,
								0,
								getScreen(),
								0,
								maxValue,
								DisplayProperties.getActiveColor(),
								DisplayProperties.getPassiveColor()
							);
		
		setWidget( g_gauge );
		
		g_gauge.setValue(initialValue);
	}

	
	public int getMaxValue() {
		return g_max_value;
	}


	public int getValue() {
		return g_value;
	}


	public void setMaxValue( int maxValue ) {
		g_max_value = maxValue;
	}


	public void setValue( int newvalue ) throws IllegalArgumentException {
		g_value = newvalue;
	}

	
	public boolean isInteractive() {
		return g_interactive;
	}

	
	protected void increaseSliderValue() {
		if (!g_interactive) System.out.println("this Gauge is not interactive!");
		setValue(g_value + 1);
	}


	protected void reduceSliderValue() {
		if (!g_interactive) System.out.println("this Gauge is not interactive!");
		setValue(g_value - 1);
	}
}