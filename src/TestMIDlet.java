import gfc.microedition.lcdui.*;
import gfc.microedition.midlet.*;
import java.io.*;

//this midlet showcases the use of gfc.microedition

public class TestMIDlet extends MIDlet {

	Display display;
	//Timer timer = new Timer();

	public TestMIDlet() {
		System.out.println("TestMidlet created");

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

		display = Display.getDisplay(this);

		var canvas = new MyCanvas();

		display.setCurrent( canvas );
		
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

	Image img;

	MyCanvas() {
		//load image
		try {                
			img = Image.createImage("res/duke.jpg");
			System.out.println("image loaded");
	    } catch (IOException ex) {
	    	ex.printStackTrace();
	    }
	}

	@Override
	public void paint( Graphics g ) {
		System.out.println("MyCanvas.paint()");

		//clear
		g.setColor( 10 );
		g.fillRect( 0, 0, getWidth(), getHeight() );
		
		//draw image
		g.drawImage(img, 10, 5, 0);
	}
}