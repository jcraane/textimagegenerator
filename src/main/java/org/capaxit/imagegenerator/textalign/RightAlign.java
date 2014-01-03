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

import org.capaxit.imagegenerator.Align;
import org.capaxit.imagegenerator.DrawableText;

import java.util.List;
import java.util.ArrayList;
import java.awt.*;

/**
 * * Left aligns the given text bsed on the linewidth.
 * Author: Jamie Craane
 * Date: 22-dec-2008
 * Time: 12:33:48
 */
public final class RightAlign extends AbstractAlign implements Align {
    public List<DrawableText> align(final String text, final FontMetrics fm, final int linewidth) {
        final List<DrawableText> result = new ArrayList<DrawableText>();

        final String[] words = super.getWords(text);
        int xPosition = (linewidth - fm.stringWidth(text));
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
