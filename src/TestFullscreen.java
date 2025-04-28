import java.awt.*;
import java.awt.image.*;
import java.io.*;
import javax.imageio.*;
import javax.swing.*;

//this program tries to go into fullscreen and NOT do anti aliasing/interpolation which makes the image look blurry
//https://docs.oracle.com/javase/8/docs/api/java/awt/RenderingHints.html#VALUE_ANTIALIAS_OFF

public class TestFullscreen extends JPanel {

	BufferedImage i = null;

	public TestFullscreen() {
		try {                
			i = ImageIO.read(new File( "res/320x200.png" ));
		} catch (IOException ex) {
			ex.printStackTrace();
		}

		GraphicsDevice g = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();

		if (g.isFullScreenSupported()) {
			System.out.println("going fullscreen");
			setResizable(false);
			setUndecorated(true);
			g.setFullScreenWindow(this);
		}
		else {
			System.out.println("fullscreen not supported on this device");
		}

		if (g.isDisplayChangeSupported()) {
			System.out.println("changing display");

			for (DisplayMode d : g.getDisplayModes()) {
				System.out.println(d);
			}

			DisplayMode dm = new DisplayMode(
				320, 
				200, 
				32,
				DisplayMode.REFRESH_RATE_UNKNOWN );
			g.setDisplayMode(dm);
		}
		else {
			System.err.println("display change not supported on this device");
		}
	}

	public void paintComponent( Graphics2D g ) {
		System.out.println("paintComponent()");

	    // Disable anti-aliasing
		Graphics2D g2d = (Graphics2D)g;
	    g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_OFF);

		//clear
//		g.setColor( 10 );
		g.fillRect( 0, 0, getWidth(), getHeight() );
		
		//draw image
		g.drawImage(i, null, null);
	}
}
