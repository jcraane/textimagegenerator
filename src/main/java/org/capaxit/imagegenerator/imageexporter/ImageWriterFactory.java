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

package org.capaxit.imagegenerator.imageexporter;

import org.capaxit.imagegenerator.imageexporter.exporters.JpegImageWriter;
import org.capaxit.imagegenerator.imageexporter.exporters.PngImageWriter;

/**
 * Factory which creates an ImageWriter based on the ImageType.
 *
 * User: Jamie Craane
 */
public final class ImageWriterFactory {
    private static final ImageWriter JPEG_IMAGE_WRITER = new JpegImageWriter();
    private static final ImageWriter PNG_IMAGE_WRITER = new PngImageWriter();

    private ImageWriterFactory() {
        // Prevent instantiation
    }

    /**
     * @param imageType The exporttpye to create an ImageWriter for.
     * @return An implementation of ImageWriter which is capable of writing images
     * indicated by the imageType.
     */
    public static ImageWriter getImageWriter(final ImageType imageType) {
        switch (imageType) {
            case PNG:
                return PNG_IMAGE_WRITER;
            case JPEG:
                return JPEG_IMAGE_WRITER;
            default:
                throw new IllegalStateException("No imagewriter defined for imageType[" + imageType + "].");
        }
    }
}
