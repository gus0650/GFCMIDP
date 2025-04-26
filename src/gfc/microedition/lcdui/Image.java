package gfc.microedition.lcdui;

//import java.awt.Toolkit;
import gfc.microedition.midlet.*;
import java.awt.image.BufferedImage;
import java.io.*;
import javax.imageio.ImageIO;


public class Image {

//	private java.awt.Image awtimage;
	BufferedImage awtimage;
	
	
	/*
	private Image (java.awt.Image i) {
		awtimage = i;
	}
	*/
	
	private Image (BufferedImage i) {
		awtimage = i;
	}
	
	public static Image createImage(String filename) throws IOException, IllegalArgumentException {
		if (MIDlet.GetAppProperty("debug").equals("true")) System.out.println("Image.createImage "+filename);
		
		if ((filename == "") | (filename == null)) new IllegalArgumentException("no filename");

		/*
		//this code works only in applet context
		java.awt.Image i = applet.getImage(applet.getCodeBase(), filename);
		*/

		/*
		//this code works only in application context BUT NOT from inside a .jar!
		Toolkit tk = Toolkit.getDefaultToolkit();
		java.awt.Image i = tk.createImage( filename );
		tk.prepareImage(i, -1, -1, null);
		*/

		/*
		//this code works in both applet and application context, but has PNG parsing problems!
		InputStream is = Thread.currentThread().getContextClassLoader().getResourceAsStream(filename);
		byte[] b = new byte[is.available()];
		if ( b.length == 0 ) throw new IOException("ERROR in Image.create(): it seems that the file was not properly opened - " + filename );
		is.read(b);
		is.close();
		Toolkit tk = Toolkit.getDefaultToolkit();
		java.awt.Image i = tk.createImage( b );
		*/

		/*
		//third version
		Toolkit tk = Toolkit.getDefaultToolkit();
		java.awt.Image i = tk.getImage( tk.getClass().getResource( filename ) );
		tk.prepareImage(i, -1, -1, null);
		*/
		
		/*
		//optional: make sure image is fully read before continuing
		MediaTracker mediaTracker = new MediaTracker( );
		mediaTracker.addImage(i, 0);
		try	{
			mediaTracker.waitForID(0);
		} catch (Exception e) {
			e.printStackTrace();
		}
		*/
		
		/*
		 */
		//fourth version -- works
		BufferedImage i = null;
		 try {                
	          i = ImageIO.read(new File( filename ));
	       } catch (IOException ex) {
	            ex.printStackTrace();
	       }

		if (MIDlet.GetAppProperty("debug").equals("true")) System.out.println("Image.load(): w=" + i.getWidth() + " h=" + i.getHeight());
		
		if ( i.getWidth(null) < 0 ) throw new IOException("ERROR in Image.create(): it seems that the file was not properly loaded - " + filename );
		
		return new Image(i);
	}

	public int getWidth() {
		return awtimage.getWidth(null);
	}
	
	public int getHeight() {
		return awtimage.getHeight(null);
	}

	public Graphics getGraphics() {
		Graphics igfx = new Graphics();
		igfx.set( awtimage.getGraphics() );
		
		return igfx;
	}
	
	java.awt.Image getAWTImage() {
		return awtimage;
	}
	
	static public Image createImage(byte[] data, int offset, int length) {
		return null;
		//TODO return new Image( Toolkit.getDefaultToolkit().createImage( data, offset, length ) );
	}
	
	static public Image createImage(int w, int h) {
		return null;
		//TODO 
	}

	public void getRGB(int[] rgbData, int offset, int scanlength, int x, int y, int width, int height) {
		awtimage.getRGB( x, y, width, height, rgbData, offset, scanlength );
	}
	
	public void setRGB( int x, int y, int w, int h, int[] rgbArray, int offset, int scansize ) {
		awtimage.setRGB( x, y, w, h, rgbArray, offset, scansize );
	}
}
