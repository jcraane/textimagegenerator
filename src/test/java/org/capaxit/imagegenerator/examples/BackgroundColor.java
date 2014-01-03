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

import org.capaxit.imagegenerator.Margin;
import org.capaxit.imagegenerator.Style;
import org.capaxit.imagegenerator.TextImage;
import org.capaxit.imagegenerator.imagecallbacks.BackgroundColorCallback;
import org.capaxit.imagegenerator.imageexporter.ImageType;
import org.capaxit.imagegenerator.imageexporter.ImageWriter;
import org.capaxit.imagegenerator.imageexporter.ImageWriterFactory;
import org.capaxit.imagegenerator.impl.TextImageImpl;

import java.awt.*;
import java.io.File;

/**
 * User: Jamie Craane
 */
public class BackgroundColor {
    public static void main(String[] args) throws Exception {
        new BackgroundColor().runExample();
    }

    private void runExample() throws Exception {
        // 1. create a new TextImageImpl with a size of 300x300 pixels
        // and a left and top margin of 5 pixels. The default font is SansSerif,
        // PLAIN,12
        final TextImage textImage = new TextImageImpl(300, 300, new Margin(0, 0));

        textImage.performAction(new BackgroundColorCallback(Color.CYAN, Color.BLACK, textImage));

        // 2. These methods add text and a newline
        textImage.writeLine("This is an example");
        textImage.writeLine("of creating a textimage");
        textImage.writeLine("with an alternative backgroundcolor.");

        // 3. Add explicit newlines. All methods can be chained for convenience.
        textImage.newLine().newLine();

        // 4. Add other text
        textImage.withFontStyle(Style.UNDERLINED).write("Hello world!");

        ImageWriter imageWriter = ImageWriterFactory.getImageWriter(ImageType.JPEG);
        imageWriter.writeImageToFile(textImage, new File("background-color.jpg"));
    }
}
