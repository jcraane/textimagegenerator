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

import org.capaxit.imagegenerator.TextImage;
import org.ccil.cowan.tagsoup.jaxp.SAXParserImpl;
import org.xml.sax.SAXException;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;

/**
 * Factory class which implements creating text images from basic HTML structure. The following HTML
 * tags are supported at the moment:
 *
 * TODO: Update documentation.
 *
 * Created by jcraane on 03-01-14.
 */
public final class HtmlTextImageFactory {
    private HtmlTextImageFactory() {
    }

    public static TextImage fromHtml(final String html) {
        try {
            TextImageHtmlHandler handler = new TextImageHtmlHandler();
            SAXParserImpl.newInstance(null).parse(new ByteArrayInputStream(html.getBytes("UTF-8")), handler);
            return handler.getTextImage();
        } catch (SAXException e) {
            throw new HtmlParseException("Unable to parse the HTML, see the stacktrace for details.", e);
        } catch (UnsupportedEncodingException e) {
            // Should not happen
            throw new HtmlParseException("The encoding is not supported.", e);
        } catch (IOException e) {
            throw new HtmlParseException("Unable to create a TextImage from the HTML, see the stacktrace for details.", e);
        }
    }
}
