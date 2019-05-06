
package test.ui;

import java.awt.*;
import java.awt.image.*;

class Logo extends java.awt.Canvas {
	private Image fImage;
	private int fWidth;
	private int fHeight;

public Logo() {
	super();
	fWidth= 20;
	fHeight= 20;

	fImage= loadImage("logo.gif");
	MediaTracker tracker = new MediaTracker(this);
  	tracker.addImage(fImage, 0);
   try {
		tracker.waitForAll();
	} catch (Exception e) {
	}

	if (fImage != null) {
		fWidth= fImage.getWidth(this);
		fHeight= fImage.getHeight(this);
	}
	setSize(fWidth, fHeight);
}

public Image loadImage(String name) {
	Toolkit toolkit = Toolkit.getDefaultToolkit();
	try {
		java.net.URL url = getClass().getResource(name);
		return toolkit.createImage((ImageProducer) url.getContent());
	} catch (Exception ex) {
		return null;
	}
}

public void paint ( java.awt.Graphics g) {
	paintBackground(g);
	if (fImage != null)
		g.drawImage(fImage, 0, 0, fWidth, fHeight, this);
	return;
}

public void paintBackground( java.awt.Graphics g) {
	g.setColor(SystemColor.control);
	g.fillRect(0, 0, getBounds().width, getBounds().height);
}
}