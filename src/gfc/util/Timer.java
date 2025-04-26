package gfc.util;

/**
 * equivalent to java.util.Timer
 * 
 * @see java.util.Timer
 * @author Alexander Knorr 
 */
final public class Timer extends Thread implements Runnable {

	/** start of a linked list of TimerEvents */
	private TimerTask list = null;
	
	/** exit flag */
	private boolean runs = false;
	
	/** do-not wait flag */
	private boolean wait = true;
	
	/** one and only instance of this timer */
	private Timer instance;

	
	/**
	 *	Initialize the timer
	 */
	public Timer () {
		 instance = this;
	}
	
	
	/**
	 *	Notify the internal thread to exit & stop all activity.
	 *
	 *	The internal thread will exit, when all outstanding Tasks are called.
	 */
	final public void cancel()
	{
		/*config:debug:ON*///System.out.println("Timer.cancel()");
		
		runs = false;
		
		synchronized( instance ) { wait = false;
			instance.notify();
		}
	}
	
	
	/**
	 *  Schedule an task to the timer for repeated execution with an initial delay
	 *  
	 * @param task - task to schedule
	 * @param interval - time interval in millis between task executions
	 * @param delay - initial delay in millis
	 */
	final public void schedule( TimerTask task, int delay, int interval ) {
		
		task.interval = interval;
		task.delay = delay;
		schedule( task );		
	}


	/**
	 *	Schedule an task to the timer's queue.<p>
	 *	
	 *	If the given task is not always in the queue, or was not yet removed
	 *	by the internal thread, it is linked into the queue.<p>
	 *	Given task is then released from pause, if it was paused
	 *	or ist restarted, if it was canceled.<p>
	 *
	 *	@param task - Task to schedule
	 */
	final public void schedule( TimerTask task ) {
		
		/*config:debug:ON*///System.out.println("Timer.schedule (" + task + ")");
		
		synchronized( instance ) {

			task.trigger = System.currentTimeMillis();

			/* check if event is not in queue */
			if( ( task.flags & TimerTask.CANCELED ) != 0 ) {
				
				/* link event into our list */
				task.next = list;			
				list = task;
				
				task.flags &= ~(TimerTask.CANCELED | TimerTask.PAUSED);
				task.flags |= TimerTask.RUN;
						
			} else {
				
				task.flags &= ~TimerTask.PAUSED;
				task.flags |= TimerTask.RUN;
			}
		}
		
		/* wakeup timer thread */
		if( runs ) {
			synchronized( instance ) { 
				wait = false;
				instance.notify();
			}
		} else { runs = true;
			
			instance.start();
		}
	}
	
	
	/**
	 *	Serves the timer. All event calling, timing is done here.
	 *
	 *	This method overrides the Thread.run () method in order to
	 *	queue and process events, that are pushed to the timer via
	 *	pushEvent ().
	 *	This method is only used internally, and schould not be touched
	 *	from outside the timer.
	 */
	final public void run()
	{
		/*config:debug:ON*///System.out.println("Timer.run ()");
		
		while( runs ) {

			int sleep = Integer.MAX_VALUE;
			
			/* step trough event list */
			for( TimerTask prev = null, curr = list; curr != null; ) {
				
				/* serve event */
				if( !curr.serve() ) {	
					
					synchronized( instance ) {
					
						/* Check, if event is in queue */
						if( ( curr.flags & TimerTask.CANCELED ) == 0 ) {

							TimerTask next = curr.next;
						
							/* link out this event from our list */
							curr.next = null;
							if (prev != null) {
								prev.next = next;
							} else {				
								list = next;
							}
				
							/* notify the event, that it is canceled */
							curr.flags |= TimerTask.CANCELED;			
	
							curr = next;
						}
					}
										
					continue;
				}
				
				/* update our time to the closest next event-trigger */
				int time = (int)((curr.trigger + curr.delay) - System.currentTimeMillis());
				if( sleep > time ) {		
					sleep = time;
				}
				
				/* step to next linked event */
				prev = curr;
				curr = curr.next;
			}
			
			/* get some sleep ? */
			if( sleep <= 0 ) {
				continue;
			}

			/* sleep */
			synchronized( instance ){
				if( wait ) {
					try {
						instance.wait( ( sleep < Integer.MAX_VALUE ) ? sleep : 0 );
					} catch( InterruptedException e ) {}
				}
				
				wait = true;
			}
		}
	}
}
