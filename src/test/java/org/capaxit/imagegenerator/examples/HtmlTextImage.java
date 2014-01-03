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

package org.capaxit.imagegenerator.examples;

import org.capaxit.imagegenerator.TextImage;
import org.capaxit.imagegenerator.html.HtmlTextImageFactory;
import org.capaxit.imagegenerator.imageexporter.ImageType;
import org.capaxit.imagegenerator.imageexporter.ImageWriter;
import org.capaxit.imagegenerator.imageexporter.ImageWriterFactory;

import java.io.File;

/**
 * Created by jcraane on 03-01-14.
 */
public class HtmlTextImage {
    public static void main(String[] args) throws Exception {
        new HtmlTextImage().runExample();
    }

    private void runExample() throws Exception {
        String html = "<p><u>Dit is een test</u></p>" +
                "<p>Nog een regel text</p>" +
                "<u>Underlined text</u>" +
                "<u>More underlined</u>";
        TextImage textImage = HtmlTextImageFactory.fromHtml(html);
        ImageWriter imageWriter = ImageWriterFactory.getImageWriter(ImageType.PNG);
        imageWriter.writeImageToFile(textImage, new File("html.png"));
    }
}
