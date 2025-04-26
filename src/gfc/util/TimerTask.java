package gfc.util;

/**
 * equivalent to java.util.TimerTask
 * 
 * @see java.util.TimerTask
 * @author Alexander Knorr
 */

public class TimerTask implements Runnable {
	
	/** internal flags */
	final static int PAUSED		= (1 << 0);
	final static int RUN		= (1 << 1);
	final static int CANCELED	= (1 << 2);
	
	
	/** absolute system time of next event trigger */
	long trigger;
	
	/** trigger interval in millis */
	int interval = 0;
	
	/** trigger time - offset */
	int delay = 0;
	
	/** status - flags */
	int flags = CANCELED;
	
	/** possible TimerCall - Interface */
	Runnable callback = this;
	
	/** next linked event */
	TimerTask next = null;	
	
	
	/**
	 * Construct a new TimerTask
	 */
	public TimerTask() {}
	
	
	/**
	 * Construct a new TimerTask with an attached Runnable
	 * 
	 * @param callback - Runnable (callback) to call
	 */
	public TimerTask( Runnable callback ) {
		
		this.callback = callback;		
	}
	
	
	/**
	 * Serve this event by "Law"
	 * 
	 * @return "false" if event should be removed from the timer queue
	 * @see EventTimer.run ()
	 */
	final boolean serve() {
		
		/* check, if active */
		if( ( this.flags & RUN ) == 0 ) {
			return (false);
		}
		
		long current_time = System.currentTimeMillis();
		
		/* check, if event has to trigger */
		if( current_time >= ( this.trigger + this.delay ) ) {
				
			/* clear a possible trigger offset */
			this.delay = 0;
			
			/* update next trigger time */
			this.trigger = current_time + this.interval;

			/* call event callback */
			this.callback.run();
			
			/* re-check, if active */
			if( ( this.flags & RUN ) == 0 ) {
				return (false);
			}
		}
		
		return (true);
	}

	
	/**
	 * Set a new time - interval
	 * 
	 * @param interval Time intervall between triggers in millis.
	 */
	public final void setInterval( int interval ) {
		/*config:debug:ON*///System.out.println("TimerTask(" + this + ").setInterval(" +interval + ")");
		
		if( interval == this.interval ) {
			return; /* dont adjust anything */
		}

		/* adjust next trigger time with the time-delta between old and new interval */
		this.trigger += ( interval - this.interval );
		
		/* store new interval */
		this.interval = interval;
	}
	
	
	/**
	 * Returns the current time interval
	 * 
	 * @return int Time interval in millis
	 */
	public final int getInterval() {

		return this.interval;
	}
	

	/**
	 * Pause this task.<p>
	 * 
	 * Task will be removed from the Timer - queue, when
	 * the timer thread visits its queue the next time.
	 */
	public final void pause() {
		/*config:debug:ON*///System.out.println("TimerTask(" + this + ").pause()");

		if ((this.flags & RUN) != 0) {

			/* calculate time-delay to next trigger */
			this.delay = (int)((this.trigger + this.delay) - System.currentTimeMillis());
			
			this.flags &= ~RUN;
			this.flags |= PAUSED;
		}
	}


	/**
	 * Check, if event is paused
	 * 
	 * @return "true" if paused, "false" otherwise
	 */
	public final boolean isPaused() {
		
		return ((this.flags & PAUSED) != 0);
	}
		
	
	/**
	 * Cancel this event.
	 * 
	 * Event will be removed from the Timer - queue, when
	 * the timer thread visits its queue the next time.
	 */
	public final void cancel() {
		/*config:debug:ON*///System.out.println("TimerTask(" + this + ").cancel()");
		
		this.flags &= ~RUN;
	}
	
	
	/**
	 * callback method, subclasses may override
	 */
	public void run(){
		/*config:debug:ON*///System.out.println("TimerTask(" + this + ").run()");
	}
}
