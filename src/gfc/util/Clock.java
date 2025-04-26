package gfc.util;

public class Clock {
	
	private int secs;
	
	public Clock(int h, int m, int s) {
		changeBy( h, m, s );
	}
	
	public void setTo( int h, int m, int s ) {
		secs = 0;
		changeBy( h, m, s );
	}
	
	public void changeBy(int hours, int minutes, int seconds) {
		secs += seconds;
		secs += minutes * 60;
		secs += hours * 3600;
	}
	
	public String getTime() {
		return getTime(secs);
	}
	
	public static String getTime(int seconds) {
		StringBuffer sb = new StringBuffer();
		
		int h 	= seconds / 3600;
		if (h < 10) sb.append("0");
		sb.append(h);

		sb.append(":");
		
		int m 	= (seconds - h * 3600) / 60;
		if (m < 10) sb.append("0");
		sb.append(m);

		sb.append(":");

		int s 	= seconds - h * 3600 - m * 60;
		if (s < 10) sb.append("0");
		sb.append(s);

		return sb.toString();
	}

}
