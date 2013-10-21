package org.capaxit.imagegenerator;

import java.awt.Graphics2D;

import org.capaxit.imagegenerator.impl.TextImageImpl;

/**
 * Callback for the textimage.
 */
public interface TextImageCallback {
	/**
	 * Exposes the graphics object which clients can use to perform more advanced functionality than
	 * that the {@link TextImageImpl} implements.
	 * @param graphics
	 */
	void doWithGraphics(Graphics2D graphics);
}
