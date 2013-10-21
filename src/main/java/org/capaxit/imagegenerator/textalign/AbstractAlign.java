package org.capaxit.imagegenerator.textalign;

import org.capaxit.imagegenerator.Align;
import org.capaxit.imagegenerator.DrawableText;

import java.util.List;
import java.awt.*;

/**
 * Author: Jamie Craane
 * Date: 12-jan-2009
 * Time: 21:22:46
 */
public abstract class AbstractAlign implements Align {
    static final String DELIMITER = " ";

    /**
     * Split the given text based on the DELIMITER and returns the result as a String[]
     * @param text
     * @return
     */
    String[] getWords(String text) {
        return text.split(DELIMITER);
    }

    /**
     * @see Align#align(String, java.awt.FontMetrics, int) 
     */
    abstract public List<DrawableText> align(String text, FontMetrics fm, int linewidth);
}
