package nc.ui.so.qs.pub.tools;

import java.awt.Dimension;
import java.awt.Toolkit;

public class ScreenTools {
	
	private static float width;
	private static float height;
	  
	public ScreenTools() {}
	
	private static Dimension screen = null;
	
	public static Dimension getScreen() {
	  if (screen == null)
	    screen = Toolkit.getDefaultToolkit().getScreenSize();
	  width = screen.width;
	  height = screen.height;
	  return screen;
	}
	
	public static int calW(int w) {
	  float wRate = width / 1024.0F;
	  float ww = wRate * w;
	  
	  int iw = Math.round(ww);
	  
	  if (iw > width)
	    iw = Math.round(width) - 10;
	  return iw;
	}
	
	public static int calH(int h) {
	  float hRate = height / 768.0F;
	  float hh = h * hRate;
	  
	  int ih = Math.round(hh);
	  
	  if (ih > height)
	    ih = Math.round(height) - 10;
	  return ih;
	}
	
	public static Dimension newDimension(int w, int h) {
	  return new Dimension(calW(w), calH(h));
	}

}
