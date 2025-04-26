package gfc.microedition.lcdui;

import gfc.widgets.*;
import gfc.util.Timer;
import gfc.util.TimerTask;


/**
 * @see javax.microedition.lcdui.Ticker
 * @author Gustaf Stechmann
 * @version alpha
 */
public class Ticker implements Runnable {

	private Scroller 	t_scroller;

    private TimerTask 	timerTask 	= new TimerTask(this);
    private Timer timer 			= new Timer();


	public Ticker( String text ) {
		
		t_scroller = new Scroller(
				DisplayProperties.getScreenWidth(),
				DisplayProperties.getScreenHeight(),
				0,
				DisplayProperties.getFont().getHeight() +1,
				null,
				text,
				DisplayProperties.getFont(),
				DisplayProperties.getPassiveColor(),
				1
			);
		
        this.timerTask.setInterval( Math.abs( DisplayProperties.TICKER_SPEED ) );
	}


	public String getString() {
		return t_scroller.getString();
	}


	public void setString( String text ) {
		t_scroller.setString(text);
	}

	
    /**
     * Start scrolling the ticker
     */
    void start() {
    	timer.schedule(timerTask);
    }

    
    /**
     * Stop scrolling the ticker. Ticker text rests in his current
     * scroll-position untill start() is called again.
     */
    void stop() {
        timerTask.cancel();
    }
	
    
	public void run() {
		t_scroller.scroll();
	}

	
	int getHeight() {
		return t_scroller.getHeight();
	}
}