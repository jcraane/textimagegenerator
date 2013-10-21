package org.capaxit.imagegenerator.textalign;

import org.capaxit.imagegenerator.Align;
import org.capaxit.imagegenerator.DrawableText;

import java.util.List;
import java.util.ArrayList;
import java.awt.*;

/**
 * Left aligns the given text bsed on the linewidth.
 * Author: Jamie Craane
 * Date: 22-dec-2008
 * Time: 10:49:03
 */
public final class LeftAlign extends AbstractAlign implements Align {
    public List<DrawableText> align(final String text, final FontMetrics fm, final int linewidth) {
        final List<DrawableText> result = new ArrayList<DrawableText>();

        final String[] words = super.getWords(text);
        int xPosition = 0;
        for (String word : words) {
            if (word.length() == 0) {
                // This is the delimiter
                result.add(new DrawableText(DELIMITER, xPosition));
                xPosition += fm.stringWidth(DELIMITER);
            }
            word += DELIMITER;
            result.add(new DrawableText(word, xPosition));
            xPosition += fm.stringWidth(word);
        }
        
        return result;
    }
}
