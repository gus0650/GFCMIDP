package gfc.graphics;

import gfc.microedition.lcdui.Graphics;
import gfc.microedition.midlet.MIDlet;

import java.util.*;


public class AnimationGroup implements Animation {
		
	private Vector anims = new Vector();
	private boolean more;
	
	
	public void addAnimation(Animation a) {
		anims.addElement(a);
	}
	
	
	public int numAnims() {
		return anims.size();
	}


	public Animation getAnim(int index) {
		return (Animation)anims.elementAt(index);
	}
	
	
	public void toFirstFrame() {
		if (MIDlet.GetAppProperty("debug").equals("true")) System.out.println("AnimationGroup.toFirstFrame()");

		more = false;

		for (int i = 0; i < anims.size(); ++i) {
			Animation p = (Animation)anims.elementAt(i);
			
			p.toFirstFrame();
			
			if (p.hasMoreFrames()) {
				more = true;
			}
		}
	}
	
	
	public void toNextFrame() {
		if (MIDlet.GetAppProperty("debug").equals("true")) System.out.println("AnimationGroup.toNextFrame()");

		more = false;

		for (int i = 0; i < anims.size(); ++i) {
			Animation p = (Animation)anims.elementAt(i);

			p.toNextFrame();

			if (p.hasMoreFrames()) {
				more = true;
			} 
		}
	}
	
	
	public boolean hasMoreFrames() {
		return more;
	}

	
	public final void paint(Graphics g) {
		for (int i = 0; i < anims.size(); ++i) {
			Animation p = (Animation)anims.elementAt(i);
			
			p.paint(g);
		}
	}
}
