package org.capaxit.imagegenerator.textalign;

import org.capaxit.imagegenerator.TextWrapper;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/**
 * This class uses a greedy based text wrapping algorithm which uses all
 * available space on the line to fit in the words.
 */
public final class GreedyTextWrapper implements TextWrapper {
    private static final String SPACE = " ";
    private static final char CSPACE = ' ';

    /**
     * @see org.capaxit.imagegenerator.TextWrapper#doWrap(String, int, FontMetrics)
     */
    public List<String> doWrap(final String text, final int lineWidth, final FontMetrics fm) {
        final List<String> lines = new ArrayList<String>();

        final StringTokenizer tokenizer = new StringTokenizer(text, SPACE);
        int spaceLeft = lineWidth;
        final StringBuilder builder = new StringBuilder();
        boolean removed = false;
        String word = "";
        boolean nospaceleft;
        while (tokenizer.hasMoreTokens()) {
            if (removed) {
                // continue with last read word
                removed = false;
            } else {
                word = tokenizer.nextToken() + SPACE;
            }

            char[] chars = new char[word.length()];
            word.getChars(0, word.length(), chars, 0);

            for (int i = 0; i < chars.length; i++) {
                if (fm.charWidth(chars[i]) > spaceLeft) {
                    if (chars[i] != CSPACE) {
                        builder.delete(builder.length() - i, builder.length());
                        removed = true;
                    }

                    if (builder.charAt(builder.length() - 1) == CSPACE) {
                        builder.delete(builder.length() - 1, builder.length());
                    }

                    lines.add(builder.toString());
                    spaceLeft = lineWidth;
                    builder.setLength(0);
                    nospaceleft = true;
                } else {
                    spaceLeft -= fm.charWidth(chars[i]);
                    nospaceleft = false;
                }

                if (!removed && !nospaceleft) {
                    builder.append(chars[i]);
                } else {
                    break;
                }
            }
        }

        // In case the last word did not fit in
        if (removed) {
            builder.append(word.trim());
        }

        if (builder.length() > 1 && builder.charAt(builder.length() - 1) == CSPACE) {
            builder.delete(builder.length() - 1, builder.length());
        }

        lines.add(builder.toString());

        return lines;
    }

}
