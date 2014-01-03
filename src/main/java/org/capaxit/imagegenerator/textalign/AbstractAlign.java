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
