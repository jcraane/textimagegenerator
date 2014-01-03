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

package org.capaxit.imagegenerator.imageexporter.exporters;

import org.capaxit.imagegenerator.TextImage;
import org.capaxit.imagegenerator.imageexporter.ImageWriter;
import org.capaxit.imagegenerator.impl.TextImageImpl;
import org.capaxit.imagegenerator.util.Validate;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

/**
 * Implementation of the ImageWriter which handles png exports.
 * User: Jamie Craane
 */
public final class PngImageWriter implements ImageWriter {
    private static final String PNG = "png";

    /**
     * {@inheritDoc}
     * @param image The image to write to the outputstream.
     * @param outputStream The outputStream to write the image to.
     */
    public void writeImageToOutputStream(final TextImage image, final OutputStream outputStream) throws IOException {
        Validate.notNull(image, "The image may not be null.");
        Validate.notNull(outputStream, "The outputStream may not be null.");

        ImageIO.write(((TextImageImpl)image).getBufferedImage(), PNG, outputStream);
    }

    /**
     * {@inheritDoc}
     * @param image The image to write to the file.
     * @param file The outputStream to write the image to.
     */
    public void writeImageToFile(final TextImage image, final File file) throws IOException {
        Validate.notNull(image, "The image may not be null.");
        Validate.notNull(file, "The file may not be null.");

        OutputStream os = new FileOutputStream(file);
        try {
            ImageIO.write(((TextImageImpl)image).getBufferedImage(), PNG, os);
        } finally {
            os.close();
        }
    }
}
