package gfc.microedition.lcdui;

import java.awt.Frame;
import java.io.*;
import java.util.*;


public abstract class MIDlet extends Frame {

	Display display;
	static MIDlet instance;
	static Properties properties = new Properties();
	
	public MIDlet() {
		super("UNDEF");
	} 


	public static void main( String[] args ) {
		//read properties from file
		
		try {
			//this resource loading mechanism works from inside a JAR (both applet or application context)
			InputStream is = Thread.currentThread().getContextClassLoader().getResourceAsStream("app.properties");
			properties.load( is ) ;
	    }
		catch (Exception e) {
			e.printStackTrace();
		}
		
		//initialize Application
		Object o = null;
		
		try {
			Class c = Class.forName( properties.getProperty("app.class") );
			o = c.newInstance();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		
		MIDlet app = (MIDlet)o;
		
		app.init();
		
		//set Window properties
		app.resize(
				Integer.parseInt(properties.getProperty("app.width")),
				Integer.parseInt(properties.getProperty("app.height")));
		app.setTitle(properties.getProperty("app.title"));
		app.show();
	}
	
	
	//map Applet lifecycle to MIDlet lifecycle. instead of constructor
	final public void init() {
		/*config:debug:OFF*///System.out.println("Application()" );
		
		instance = this;

		display = new Display((Frame)this);
		display.resize( getWidth(), getHeight() );
		add(display);
		
		startup();
	}
	
	
	/** 1000 millis = 1 second */
	public static void sleep(int millis) {
		/*config:debug:OFF*///System.out.println("GameApp.sleep() " + millis);

		try {
			Thread.sleep(millis);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	
	//overriding update method to avoid flicker
	final public void update( java.awt.Graphics g ) {
		paint( g );
	}

	
	static public MIDlet getInstance() {
		return instance;
	}

	Display getDisplay() {
		return display;
	}

	
	/** the application must call this method to shut down */
	final public void shutdown() {
		//nop
	}

	/** this method is called when application starts */
	abstract public void startup();	//TODO??

	
	final public void start() {
		startApp();
	}

	
	final public void destroy() {
		//display.finalize();

		this.hide();
		this.destroy();

		destroyApp(false);
	}

	
	final public void stop() {
		pauseApp();
	}

	//provided for MIDlet compatibility
	final protected void notifyStarted() { }
	final protected void notifyPaused() { }
	final protected void notifyDestroyed() { }

	abstract protected void startApp();
	abstract protected void pauseApp();
	abstract protected void destroyApp(boolean b);


	//--- custom methods

	protected static boolean IsDebug() {
		return (properties.getProperty("app.debug").equals("true"));
	}
}
