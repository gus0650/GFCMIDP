package gfc.microedition.midlet;

import gfc.microedition.lcdui.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.*;


public abstract class MIDlet extends Frame {

	Display display;
	static Properties properties = new Properties();
	
	public MIDlet() {
		super("UNDEF");

		System.out.println("MIDlet()");

		String workingDir = System.getProperty("user.dir");
		System.out.println("current working directory: " + workingDir);

		//close window when X button is clicked
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				dispose();
			}
		});

		instance = this;

		display = new Display((Frame)this);
		display.resize( getWidth(), getHeight() );
		add(display);

	} 

	public static void main( String[] args ) {
		//read properties from file
		
		try {
			//this resource loading mechanism works from inside a JAR (both applet or application context)
			InputStream is = Thread.currentThread().getContextClassLoader().getResourceAsStream("midlet.properties");
			properties.load( is ) ;
	    }
		catch (Exception e) {
			e.printStackTrace();
		}
		
		//initialize Application
		Object o = null;
		
		try {
			Class c = Class.forName( properties.getProperty("class") );
			o = c.newInstance();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		
		MIDlet app = (MIDlet)o;

		//set Window properties
		app.resize(
				Integer.parseInt(properties.getProperty("window.width")),
				Integer.parseInt(properties.getProperty("window.height")));
		app.setTitle(properties.getProperty("window.title"));
		app.show();

		app.startApp();
	}
	
	//overriding update method to avoid flicker
	@Override
	final public void update( java.awt.Graphics g ) {
		paint( g );
	}

	//TODO call pauseApp() when awt frame is out of focus
	//TODO call startApp() when awt frame is back in focus
	//TODO call destroyApp() when awt frame closes
	//TODO call resumeRequest() ..?

	//--- custom methods

	public Display getDisplay() {
		return display;
	}


	//---inherited from MIDlet

	final protected void notifyStarted() { }
	final protected void notifyPaused() { }
	final protected void notifyDestroyed() { }

	abstract protected void startApp();
	abstract protected void pauseApp();
	abstract protected void destroyApp(boolean b);

	public static String GetAppProperty(String s) {
		return properties.getProperty(s);
	}
}
