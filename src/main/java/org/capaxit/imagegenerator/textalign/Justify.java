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
 * Justifies the given text bsed on the linewidth.
 * Author: Jamie Craane
 * Date: 22-dec-2008
 * Time: 12:39:33
 */
public final class Justify extends AbstractAlign implements Align {
    public List<DrawableText> align(final String text, final FontMetrics fm, final int linewidth) {
        final List<DrawableText> result = new ArrayList<DrawableText>();

        final String[] words = super.getWords(text);

        int spaceLeft = linewidth - fm.stringWidth(text);
        // Do not count the spaces because the glue will become the new spaces
        for (int i = 0; i < words.length - 1; i++) {
            spaceLeft += fm.charWidth(' ');
        }

        int glue = 0;

        // TODO Modify algorithm which takes into account rounding issues
        if (words.length > 0) {
            glue = Math.round((float) spaceLeft / (words.length - 1));
        }

        int xPosition = 0;
        for (String word : words) {
            result.add(new DrawableText(word, xPosition));
            xPosition += fm.stringWidth(word) + glue;
        }

        return result;
    }
}
