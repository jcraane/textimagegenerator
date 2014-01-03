/*
 * Copyright 2014 Jamie Craane - Capax IT
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

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
