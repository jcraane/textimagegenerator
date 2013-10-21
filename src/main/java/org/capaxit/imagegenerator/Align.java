package org.capaxit.imagegenerator;

import java.util.List;
import java.awt.*;

/**
 * Author: Jamie Craane
 * Date: 22-dec-2008
 * Time: 10:44:59
 */
public interface Align {
    /**
     * Aligns the given text based on the given fortmetrics and linewidth.
     * @param text The text which should be aligned.
     * @param fm fontmetrics used to determine the dimensions of the text.
     * @param linewidth The total space available for the text.
     * @return List of Items                                                  
     */
    List<DrawableText> align(String text, FontMetrics fm, int linewidth);
}
