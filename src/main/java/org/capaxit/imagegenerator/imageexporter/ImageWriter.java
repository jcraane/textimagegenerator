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

import org.capaxit.imagegenerator.TextImage;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;

/**
 * User: Jamie Craane
 */
public interface ImageWriter {
    /**
     * Writes the given image to the given outputStream.
     * Does not close the outputStream.
     *
     * @param image The image to write to the outputstream.
     * @param outputStream The outputStream to write the image to.
     */
    void writeImageToOutputStream(TextImage image, OutputStream outputStream) throws IOException;

    /**
     * Writes the given image to the given file.
     * @param image The image to write to the file.
     * @param file The outputStream to write the image to.
     */
    void writeImageToFile(TextImage image, File file) throws IOException;
}
