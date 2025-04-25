import gfc.microedition.lcdui.*;
import java.io.*;

//this midlet showcases the use of gfc.microedition

public class TestMIDlet extends MIDlet {

	Display display;
	//Timer timer = new Timer();

	public TestMIDlet() {
		System.out.println("TestMidlet created");

		String workingDir = System.getProperty("user.dir");
		System.out.println("current working directory: " + workingDir);
	}
	
	@Override
	public void startup() {
		System.out.println("TestMidlet started");

		display = Display.getDisplay(this);

		try {                
			Image i = Image.createImage("res/duke.jpg");
	    } catch (IOException ex) {
	    	ex.printStackTrace();
	    }

		var canvas = new MyCanvas();

		display.setCurrent( canvas );

		//timer.schedule(this, 100.0f);
	}
	
	@Override
	protected void pauseApp() {
		System.out.println("TestMidlet paused");

		//timer.stop();

		notifyPaused();
	}

	@Override
	protected void startApp() {
		System.out.println("TestMidlet started");

		//timer.run();
	}

	@Override
	protected void destroyApp(boolean b) {
		System.out.println("TestMidlet destroyed");

		//timer.cancel();
		
		notifyDestroyed();
	}

}

class MyCanvas extends Canvas {
	public void paint( Graphics g ) {
		//clear
		g.setColor( 10 );
		g.fillRect( 0, 0, getWidth(), getHeight() );
		

	}
}