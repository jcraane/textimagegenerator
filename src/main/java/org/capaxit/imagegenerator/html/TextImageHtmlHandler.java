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

package org.capaxit.imagegenerator.html;

import org.capaxit.imagegenerator.Style;
import org.capaxit.imagegenerator.TextImage;
import org.capaxit.imagegenerator.impl.TextImageImpl;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

/**
 * Handler which transforms HTML elements to TextImage methods calls.
 *
 * Created by jcraane on 03-01-14.
 */
public class TextImageHtmlHandler extends DefaultHandler {
    private static final String U = "u";
    private static final String P = "p";

    private int tagNestLevel = 0;
    private String previousTag;
    private boolean charactersAlreadyWritten;

    private final TextImage textImage;
    private StringBuilder sb = new StringBuilder(100);

    public TextImageHtmlHandler() {
        // TODO: Read the width/height from the HTML and issue a warning if not available.
        this.textImage = new TextImageImpl(300, 300);
    }

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        sb.append(ch, start, length);
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        sb.setLength(0);
        if (U.equalsIgnoreCase(qName)) {
            previousTag = qName;
            tagNestLevel++;
            textImage.withFontStyle(Style.UNDERLINED);
        }

        if (P.equalsIgnoreCase(qName)) {
            previousTag = qName;
            tagNestLevel++;
        }
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        tagNestLevel--;

        if (U.equalsIgnoreCase(qName)) {
            if (!charactersAlreadyWritten) {
                textImage.write(sb.toString());
                charactersAlreadyWritten = true;
            }
            textImage.withFontStyle(Style.PLAIN);
        }

        if (P.equalsIgnoreCase(qName)) {
            if (!charactersAlreadyWritten) {
                textImage.write(sb.toString());
                charactersAlreadyWritten = true;
            }

            textImage.newLine();
        }

        if (tagNestLevel <= 0) {
            charactersAlreadyWritten = false;
            tagNestLevel = 0;
        }
    }

    public TextImage getTextImage() {
        return textImage;
    }
}
