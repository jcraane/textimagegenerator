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

package org.capaxit.imagegenerator.html.tags;

import org.capaxit.imagegenerator.Style;
import org.capaxit.imagegenerator.TextImage;

/**
 * Created by jcraane on 04-01-14.
 */
public class Underline implements Tag {
    public int start(final TextImage textImage, final int level) {
        textImage.withFontStyle(Style.UNDERLINED);
        return level + 1;
    }

    public boolean end(final TextImage textImage, final String textToWrite, boolean charactersAlreadyWritten) {
        if (!charactersAlreadyWritten) {
            textImage.write(textToWrite);
            charactersAlreadyWritten = true;
        }
        textImage.withFontStyle(Style.PLAIN);
        return charactersAlreadyWritten;
    }
}
